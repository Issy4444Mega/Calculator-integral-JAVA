package Window;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import Window.Main_window;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private ExecutorService executor;
    private volatile boolean running = true;

    private String lastResponse;
    private final Object lock = new Object();

    public Client(String serverAddress, int serverPort) throws IOException {
        socket = new Socket(serverAddress, serverPort);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        executor = Executors.newSingleThreadExecutor();
        System.out.println("Подключено к серверу: " + serverAddress + ":" + serverPort);
    }

    public void start() {
        executor.execute(this::receiveMessages);
    }

    public String Help(double mid, double high, double step) throws IOException {
        out.println("HELP:" + mid + ":" + high + ":" + step);

        synchronized (lock) {
            try {
                // Ожидаем ответ не более 10 секунд
                lock.wait(10000);

                if (lastResponse != null) {
                    String response = lastResponse;
                    lastResponse = null; // Сбрасываем после получения
                    return response;
                }
                return "ERROR:Таймаут ожидания ответа";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "ERROR:Прервано ожидание ответа";
            }
        }
    }

    private void receiveMessages() {
        try {
            while (running) {
                String message = in.readLine();
                if (message != null) {
                    System.out.println("Сообщение с сервера: " + message);

                    if (message.startsWith("HELP:")) {

                        String[] parts = message.split(":");
                        double mid = Double.parseDouble(parts[1]);
                        double high = Double.parseDouble(parts[2]);
                        double step = Double.parseDouble(parts[3]);

                        double result = Main_window.computeIntegral(mid, high, step);
                        out.println("RESULT:" + mid + ":" + high + ":" + step + ":" + result);
                    }
                    else if (message.startsWith("RESULT:")) {
                        synchronized (lock) {
                            lastResponse = message;
                            lock.notifyAll();
                        }
                    }
                }
            }
        } catch (IOException e) {
            if (running) e.printStackTrace();
        } finally {
            close();
        }
    }

    public void close() {
        running = false;
        try {
            synchronized (lock) {
                lock.notifyAll();
            }
            executor.shutdownNow();
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}