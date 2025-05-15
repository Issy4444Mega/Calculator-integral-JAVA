import java.util.concurrent.ConcurrentHashMap;
import java.io.*;
import java.net.*;

public class Main {
    private static final int PORT = 8082;

    private static final ConcurrentHashMap<String, ClientHandler> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Новое подключение: " + clientSocket);

                ClientHandler clientHandler = new ClientHandler(clientSocket);

                clients.put(clientSocket.getRemoteSocketAddress().toString(), clientHandler);

                clientHandler.start();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при запуске сервера: " + e.getMessage());
        }
    }

    public static ClientHandler getAnotherClient(String excludeAddress) {
        return clients.values().stream()
                .filter(client -> !client.getClientAddress().equals(excludeAddress))
                .findFirst()
                .orElse(null);
    }

    private static class ClientHandler extends Thread {
        private final Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private String clientAddress;
        private String waitingResponseFor;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            this.clientAddress = socket.getRemoteSocketAddress().toString();
        }

        public String getClientAddress() {
            return clientAddress;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Получено от клиента " + clientAddress + ": " + inputLine);

                    if (inputLine.startsWith("RESULT:")) {
                        if (waitingResponseFor != null) {
                            ClientHandler waitingClient = clients.get(waitingResponseFor);
                            if (waitingClient != null) {
                                waitingClient.out.println(inputLine);
                            }
                            waitingResponseFor = null;
                        }
                    } else {
                        processRequest(inputLine);
                    }
                }
            } catch (IOException e) {
                System.err.println("Ошибка в клиентском потоке: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                    Main.clients.remove(clientAddress);
                } catch (IOException e) {
                    System.err.println("Ошибка при закрытии сокета: " + e.getMessage());
                }
            }
        }

        private void processRequest(String request) {
            String[] parts = request.split(":");
            if (parts.length < 2) {
                out.println("ERROR:Неправильный формат");
                return;
            }

            String command = parts[0];
            String mid = parts[1];
            String high = parts[2];
            String step = parts[3];

            if (command.equalsIgnoreCase("HELP")) {
                HelpRequest(mid, high, step);
            }
        }

        private void HelpRequest(String mid, String high, String step) {
            //другой клиент
            ClientHandler anotherClient = Main.getAnotherClient(clientAddress);
            if (anotherClient != null) {
                //кто ожидает ответа
                anotherClient.waitingResponseFor = this.clientAddress;

                anotherClient.out.println("HELP:" + mid + ":" + high + ":" + step);
            } else {
                out.println("ERROR:Нет других подключенных клиентов");
            }
        }
    }
}