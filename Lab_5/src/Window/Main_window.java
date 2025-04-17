package Window;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.SwingUtilities;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;

public class Main_window extends javax.swing.JFrame {

    LinkedList <Rect_integral> listR = new LinkedList <Rect_integral> ();
    
    public Main_window() {
        initComponents();
        
    }


    private class ParallelIntegral {
        private double low;
        private double high;
        private double step;
        private int selectedRow;
        private DefaultTableModel model;

        public ParallelIntegral(double low, double high, double step,
                                          int selectedRow, DefaultTableModel model) {
            this.low = low;
            this.high = high;
            this.step = step;
            this.selectedRow = selectedRow;
            this.model = model;
        }

        public void calculate() {
            // Разделяем интервал на две части
            double mid = low + (high - low) / 2.0;

            ExecutorService executor = Executors.newFixedThreadPool(2);

            // Задачи для каждой половины интервала
            Future<Double> futureFirstHalf = executor.submit(() ->
                    computeIntegral(low, mid, step/2));

            Future<Double> futureSecondHalf = executor.submit(() ->
                    computeIntegral(mid, high, step/2));

            try {
                double firstResult = futureFirstHalf.get();
                double secondResult = futureSecondHalf.get();
                double totalResult = firstResult + secondResult;

                SwingUtilities.invokeLater(() -> {
                    model.setValueAt(totalResult, selectedRow, 3);
                    listR.set(selectedRow, new Rect_integral(high, low, step, totalResult));
                });

            } catch (InterruptedException | ExecutionException e) {
                SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(null,
                                "Ошибка вычисления: " + e.getMessage(),
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE));
            } finally {
                executor.shutdown();
            }
        }
    }


    public class InvalidException extends Exception {
        public InvalidException(String message) {
            super(message);
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TextBox_HighGran = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Main_Table = new javax.swing.JTable();
        Button_rezult = new javax.swing.JButton();
        Button_add = new javax.swing.JButton();
        Button_delete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TextBox_LowGran = new javax.swing.JTextField();
        TextBox_hod = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Clear_Button = new javax.swing.JButton();
        Button_Fill = new javax.swing.JButton();
        Button_SaveBin = new javax.swing.JButton();
        Button_LoadBin = new javax.swing.JButton();
        Button_Save = new javax.swing.JButton();
        Button_Load = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TextBox_HighGran.setAlignmentX(1.0F);
        TextBox_HighGran.setMinimumSize(new java.awt.Dimension(50, 70));
        TextBox_HighGran.setPreferredSize(new java.awt.Dimension(100, 30));
        TextBox_HighGran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextBox_HighGranActionPerformed(evt);
            }
        });

        Main_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Нижняя граница", "Верхняя граница", "Шаг", "Результат"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Main_Table);

        Button_rezult.setLabel("Вычислить");
        Button_rezult.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button_rezultMouseClicked(evt);
            }
        });
        Button_rezult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_rezultActionPerformed(evt);
            }
        });

        Button_add.setText("Добавить");
        Button_add.setActionCommand("");
        Button_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button_addMouseClicked(evt);
            }
        });

        Button_delete.setText("Удалить");
        Button_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button_deleteMouseClicked(evt);
            }
        });
        Button_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_deleteActionPerformed(evt);
            }
        });

        jLabel1.setText("Верхняя граница");

        TextBox_LowGran.setAlignmentX(1.0F);
        TextBox_LowGran.setMinimumSize(new java.awt.Dimension(50, 70));
        TextBox_LowGran.setPreferredSize(new java.awt.Dimension(100, 30));
        TextBox_LowGran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextBox_LowGranActionPerformed(evt);
            }
        });

        TextBox_hod.setAlignmentX(1.0F);
        TextBox_hod.setMinimumSize(new java.awt.Dimension(50, 70));
        TextBox_hod.setPreferredSize(new java.awt.Dimension(100, 30));
        TextBox_hod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextBox_hodActionPerformed(evt);
            }
        });

        jLabel2.setText("Нижняя граница");

        jLabel3.setText("Шаг");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("sin(x)");

        Clear_Button.setText("Очистить");
        Clear_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Clear_ButtonActionPerformed(evt);
            }
        });

        Button_Fill.setText("Заполнить");
        Button_Fill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_FillActionPerformed(evt);
            }
        });

        Button_SaveBin.setText("Сохранить(двоичный)");
        Button_SaveBin.setActionCommand("");
        Button_SaveBin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button_SaveBinMouseClicked(evt);
            }
        });
        Button_SaveBin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_SaveBinActionPerformed(evt);
            }
        });

        Button_LoadBin.setText("Загрузить(двоичный)");
        Button_LoadBin.setActionCommand("");
        Button_LoadBin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_LoadBinActionPerformed(evt);
            }
        });

        Button_Save.setLabel("Сохранить");
        Button_Save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button_SaveMouseClicked(evt);
            }
        });
        Button_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_SaveActionPerformed(evt);
            }
        });

        Button_Load.setText("Загрузить");
        Button_Load.setActionCommand("");
        Button_Load.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button_LoadMouseClicked(evt);
            }
        });
        Button_Load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_LoadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Button_add)
                            .addComponent(TextBox_hod, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(Button_rezult)
                        .addGap(30, 30, 30)
                        .addComponent(Button_delete)
                        .addGap(18, 18, 18)
                        .addComponent(Button_Fill)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(Clear_Button))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TextBox_LowGran, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TextBox_HighGran, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Button_SaveBin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Button_LoadBin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Button_Save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Button_Load, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(57, 57, 57))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_SaveBin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextBox_HighGran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(Button_LoadBin))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextBox_LowGran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextBox_hod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(Button_Save)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_Load)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_add)
                    .addComponent(Button_delete)
                    .addComponent(Button_rezult)
                    .addComponent(Button_Fill)
                    .addComponent(Clear_Button))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        jLabel1.getAccessibleContext().setAccessibleName("label_HighGrani");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button_rezultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_rezultActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_rezultActionPerformed

    private void Button_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_deleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_deleteActionPerformed

    private void TextBox_HighGranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextBox_HighGranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextBox_HighGranActionPerformed

    private void TextBox_LowGranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextBox_LowGranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextBox_LowGranActionPerformed

    private void TextBox_hodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextBox_hodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextBox_hodActionPerformed

    private void Button_rezultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_rezultMouseClicked
        int selectedRow = Main_Table.getSelectedRow();
        if (selectedRow != -1) 
        {
            DefaultTableModel model = (DefaultTableModel) Main_Table.getModel();

            String lowValue = model.getValueAt(selectedRow, 0).toString();
            String highValue = model.getValueAt(selectedRow, 1).toString();
            String stepValue = model.getValueAt(selectedRow, 2).toString();

            
            double low = Double.parseDouble(lowValue);
            double high = Double.parseDouble(highValue);
            double step = Double.parseDouble(stepValue);


            // Создаем поток
            new Thread(() -> {
                new ParallelIntegral(low, high, step, selectedRow, model).calculate();
            }).start();
        } 
        else 
        {
            JOptionPane.showMessageDialog(this, "Выберите строку для вычисления!", "Ошибка", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_Button_rezultMouseClicked

    private void Button_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_deleteMouseClicked
        int selectedRow = Main_Table.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) Main_Table.getModel();
            model.removeRow(selectedRow);
            listR.remove(selectedRow);
        } 
        else 
        {
            JOptionPane.showMessageDialog(this, "Выберите строку для удаления!", "Ошибка", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_Button_deleteMouseClicked

    private void Button_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_addMouseClicked
        try {
        double low = Double.parseDouble(TextBox_LowGran.getText());
        double high = Double.parseDouble(TextBox_HighGran.getText());
        double step = Double.parseDouble(TextBox_hod.getText());

        if (low >= high || step <= 0) {
            JOptionPane.showMessageDialog(this, "Некорректные границы или шаг!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (low < 0.000001 || high > 1000000 || step < 0.000001 || step > 1000000){
            throw new InvalidException("Значения должны быть в диапазоне от 0.000001 до 1,000,000!");
        }

        Rect_integral rec = new Rect_integral(high, low, step ,0);
        
        DefaultTableModel model = (DefaultTableModel) Main_Table.getModel();
        model.addRow(new Object[]{low, high, step, ""});
        listR.add(rec);

    } catch (InvalidException e) {
        JOptionPane.showMessageDialog(this, 
            e.getMessage(), 
            "Ошибка ввода", 
            JOptionPane.WARNING_MESSAGE);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, 
            "Введите числовые значения!", 
            "Ошибка", 
            JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_Button_addMouseClicked
// clear table
    private void Clear_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Clear_ButtonActionPerformed
        DefaultTableModel model = (DefaultTableModel) Main_Table.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_Clear_ButtonActionPerformed

    private void Button_FillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_FillActionPerformed
        DefaultTableModel model = (DefaultTableModel) Main_Table.getModel();
        for (Rect_integral iter:listR)
        {
            model.addRow(new Object[] {iter.getHigh_value(), iter.getLow_value(), iter.getStep(), iter.getResult()});
        }
    }//GEN-LAST:event_Button_FillActionPerformed

    private void Button_SaveBinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_SaveBinMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_SaveBinMouseClicked

    private void Button_SaveBinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_SaveBinActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            saveToBinaryFile(fileChooser.getSelectedFile());
        }
    }//GEN-LAST:event_Button_SaveBinActionPerformed

    private void saveToBinaryFile(File file) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(file)))) {
            out.writeObject(listR);
            JOptionPane.showMessageDialog(this, "Данные успешно сохранены в двоичный файл", 
                "Успех", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ошибка при сохранении файла: " + e.getMessage(), 
                "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Метод для загрузки из двоичного файла
    private void loadFromBinaryFile(File file) {
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(file)))) {
            @SuppressWarnings("unchecked")
            LinkedList<Rect_integral> loadedList = (LinkedList<Rect_integral>) in.readObject();

            DefaultTableModel model = (DefaultTableModel) Main_Table.getModel();
            model.setRowCount(0);
            listR.clear();

            for (Rect_integral rec : loadedList) {
                model.addRow(new Object[]{
                    rec.getLow_value(), 
                    rec.getHigh_value(), 
                    rec.getStep(), 
                    rec.getResult()
                });
                listR.add(rec);
            }
            JOptionPane.showMessageDialog(this, "Данные успешно загружены из двоичного файла", 
                "Успех", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Ошибка при загрузке файла: " + e.getMessage(), 
                "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void Button_LoadBinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_LoadBinActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            loadFile(fileChooser.getSelectedFile());
        }
    }//GEN-LAST:event_Button_LoadBinActionPerformed

    private void Button_SaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_SaveMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_SaveMouseClicked

    private void Button_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_SaveActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            saveToTextFile(fileChooser.getSelectedFile());
        }
    }//GEN-LAST:event_Button_SaveActionPerformed

    private void Button_LoadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_LoadMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_LoadMouseClicked

    private void Button_LoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_LoadActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            loadFromTextFile(fileChooser.getSelectedFile());
        }
    }//GEN-LAST:event_Button_LoadActionPerformed

    private void saveToTextFile(File file) {
        try (FileWriter writer = new FileWriter(file)) {
            DefaultTableModel model = (DefaultTableModel) Main_Table.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                Object resultObj = model.getValueAt(i, 3);
                double result = (resultObj instanceof Number) ? ((Number)resultObj).doubleValue() :
                        (resultObj != null && !resultObj.toString().isEmpty()) ?
                                Double.parseDouble(resultObj.toString()) : 0.0;

                writer.write(String.format("%f;%f;%f;%f%n",
                        Double.parseDouble(model.getValueAt(i, 0).toString()),
                        Double.parseDouble(model.getValueAt(i, 1).toString()),
                        Double.parseDouble(model.getValueAt(i, 2).toString()),
                        result));
            }
            JOptionPane.showMessageDialog(this, "Данные сохранены в текстовый файл",
                    "Успех", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ошибка сохранения: " + e.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadFromTextFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            DefaultTableModel model = (DefaultTableModel) Main_Table.getModel();
            model.setRowCount(0);
            listR.clear();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    double low = Double.parseDouble(parts[0].replace(',', '.'));
                    double high = Double.parseDouble(parts[1].replace(',', '.'));
                    double step = Double.parseDouble(parts[2].replace(',', '.'));
                    double result = Double.parseDouble(parts[3].replace(',', '.'));

                    model.addRow(new Object[]{low, high, step, result});
                    listR.add(new Rect_integral(high, low, step, result));
                }
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ошибка загрузки: " + e.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadFile(File file) {
    try {
        if (isBinaryFile(file)) {
            loadFromBinaryFile(file);
        } else {
            loadFromTextFile(file);
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this,
            "Не удалось определить формат файла: " + e.getMessage(),
            "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}

    private boolean isBinaryFile(File file) throws IOException {
        try (InputStream is = new FileInputStream(file)) {
            int firstByte = is.read();
            return firstByte == 0xAC || firstByte == 0xED; // Начало Java сериализованного файла
        }
    }




    public static double computeIntegral(double LowLim, double UpLim, double step) {
        double start, h;
        double sumS = 0;
        start = LowLim;
        do {
            h = Math.min(step, (UpLim - start));
            sumS += h * 0.5 * (Math.sin(start) + Math.sin(start + h));
            start += h;
        } while (start < UpLim);
        return sumS;
    }
   
    public static void main(String args[]) {
      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Fill;
    private javax.swing.JButton Button_Load;
    private javax.swing.JButton Button_LoadBin;
    private javax.swing.JButton Button_Save;
    private javax.swing.JButton Button_SaveBin;
    private javax.swing.JButton Button_add;
    private javax.swing.JButton Button_delete;
    private javax.swing.JButton Button_rezult;
    private javax.swing.JButton Clear_Button;
    private javax.swing.JTable Main_Table;
    private javax.swing.JTextField TextBox_HighGran;
    private javax.swing.JTextField TextBox_LowGran;
    private javax.swing.JTextField TextBox_hod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
