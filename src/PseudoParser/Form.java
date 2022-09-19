/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PseudoParser;

import analizadores.Analizador_Lexico;
import analizadores.Analizador_sintactico;
import arbol.AST;
import arbol.Nodo;
import arbol.Errores;
import arbol.translate_Go;
import arbol.translate_Python;
import arbol.Instruccion;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author josue
 */
public class Form extends javax.swing.JFrame {
   
    public String Text;
    /**
     * Creates new form Form
     */
    
    public Form() {
        initComponents();
    }
    class jpannelGrad extends JPanel{
        @Override
        protected void paintComponent(Graphics g){
             Graphics2D d2d = (Graphics2D) g;
             int width = getWidth();
             int height = getHeight();
             Color color = new Color(97,51,108);
             Color color2 = new Color( 90, 173, 252 );
             GradientPaint gp = new GradientPaint(1000,300,color,180,height,color2);
             d2d.setPaint(gp);
             d2d.fillRect(0, 0, width, height);
//             Boton exit
            jButton1.setOpaque(true);
            jButton1.setContentAreaFilled(false);
            jButton1.setBorderPainted(false);
            //             Boton Clean
            btClean.setOpaque(false);
            btClean.setContentAreaFilled(false);
            btClean.setBorderPainted(false);

        }
    }
    public class BackgroundMenuBar extends JMenuBar {
    Color bgColor= new Color(   187, 187, 187   );

    public void setColor(Color color) {
        bgColor=color;
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
}
    public  void setPyText(){
        translate_Python py = new translate_Python();
//        py.makeFile();
//        System.out.println(str);
        textArea2.setText(py.mainText+py.mainlastText);
        py.clean();
    }
    public void setGoText(){
        translate_Go go = new translate_Go();
        go.fText();
        textArea2.setText(go.mainText);
        go.clean();
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new jpannelGrad();
        btClean = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea2 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        btGolang = new javax.swing.JButton();
        btPython = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btStart = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        navBar = new BackgroundMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));

        jPanel1.setBackground(null);
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 820));

        btClean.setForeground(new java.awt.Color(0, 0, 0));
        btClean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/eraser.png"))); // NOI18N
        btClean.setText("CLEAN");
        btClean.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btClean.setBorderPainted(false);
        btClean.setCursor(new java.awt.Cursor(java.awt.Cursor.S_RESIZE_CURSOR));
        btClean.setOpaque(false);
        btClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCleanActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(102, 102, 255));
        jLabel1.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Project 1 OLC1                                                                                                                                  By: iJosuer ");
        jLabel1.setOpaque(true);

        textArea2.setBackground(new java.awt.Color(204, 204, 204));
        textArea2.setColumns(20);
        textArea2.setRows(5);
        jScrollPane1.setViewportView(textArea2);

        jLabel2.setBackground(new java.awt.Color(204, 102, 0));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("0 Erros");
        jLabel2.setOpaque(true);

        btGolang.setBackground(new java.awt.Color(135, 177, 177));
        btGolang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/golang2.png"))); // NOI18N
        btGolang.setText("View Code ");
        btGolang.setCursor(new java.awt.Cursor(java.awt.Cursor.S_RESIZE_CURSOR));
        btGolang.setOpaque(false);
        btGolang.setPreferredSize(new java.awt.Dimension(132, 35));
        btGolang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGolangActionPerformed(evt);
            }
        });

        btPython.setBackground(new java.awt.Color(204, 153, 255));
        btPython.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/python.png"))); // NOI18N
        btPython.setText("View Code ");
        btPython.setToolTipText("");
        btPython.setCursor(new java.awt.Cursor(java.awt.Cursor.S_RESIZE_CURSOR));
        btPython.setPreferredSize(new java.awt.Dimension(132, 35));
        btPython.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPythonActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("OLC1_2S_2022<20200895>");

        btStart.setBackground(new java.awt.Color(153, 102, 0));
        btStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/start-button.png"))); // NOI18N
        btStart.setCursor(new java.awt.Cursor(java.awt.Cursor.S_RESIZE_CURSOR));
        btStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStartActionPerformed(evt);
            }
        });

        textArea1.setBackground(new java.awt.Color(204, 204, 204));
        textArea1.setColumns(20);
        textArea1.setRows(5);
        jScrollPane2.setViewportView(textArea1);

        jButton1.setBackground(new java.awt.Color(97, 51, 108));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit2.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 102, 0)));
        jButton1.setBorderPainted(false);
        jButton1.setOpaque(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(74, 74, 74)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(776, 776, 776)
                            .addComponent(btGolang, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(53, 53, 53)
                            .addComponent(btPython, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel3)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addComponent(btClean)
                            .addGap(441, 441, 441)
                            .addComponent(btStart, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2))
                .addContainerGap(62, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btStart, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btClean, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btPython, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGolang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        navBar.setBackground(new java.awt.Color(51, 255, 153));
        navBar.setForeground(new java.awt.Color(255, 102, 51));
        navBar.setOpaque(false);

        jMenu1.setText("File");
        jMenu1.setPreferredSize(new java.awt.Dimension(60, 30));
        jMenu1.setRequestFocusEnabled(false);

        jMenuItem1.setText("Open File");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setBackground(new java.awt.Color(255, 153, 51));
        jMenuItem2.setText("Save file as...");
        jMenu1.add(jMenuItem2);

        navBar.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(102, 102, 0));
        jMenu2.setText("Edit");
        jMenu2.setPreferredSize(new java.awt.Dimension(60, 30));

        jMenuItem3.setBackground(new java.awt.Color(255, 153, 51));
        jMenuItem3.setText("AST");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setBackground(new java.awt.Color(255, 153, 51));
        jMenuItem4.setText("Errors");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        navBar.add(jMenu2);

        jMenu3.setBackground(new java.awt.Color(102, 102, 0));
        jMenu3.setText("VIew");
        jMenu3.setPreferredSize(new java.awt.Dimension(60, 30));

        jMenuItem5.setBackground(new java.awt.Color(255, 153, 51));
        jMenuItem5.setText("User Manual");
        jMenu3.add(jMenuItem5);

        jMenuItem6.setBackground(new java.awt.Color(255, 153, 51));
        jMenuItem6.setText("Technical Manual");
        jMenu3.add(jMenuItem6);

        navBar.add(jMenu3);

        setJMenuBar(navBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1250, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStartActionPerformed
        Nodo raiz = null;
        String text  = textArea1.getText();
        try {
            Analizador_Lexico lexico = new Analizador_Lexico(
                    new BufferedReader(new StringReader(text))
            );
            Analizador_sintactico sintactico = new Analizador_sintactico(lexico);
            sintactico.parse();
                raiz = sintactico.getRaiz();
                System.out.println("Todo Correcto ");
        } catch (Exception ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        }   
        if(raiz != null){
            Instruccion inst = new Instruccion();
            inst.instrucciones(raiz);
            AST arbol = new AST(raiz);
            arbol.GraficarSintactico();
        }
//        JOptionPane.showMessageDialog(this.rootPane, "Proceso de compilacion DONE(:");
    }//GEN-LAST:event_btStartActionPerformed

    private void btPythonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPythonActionPerformed
        setPyText();
    }//GEN-LAST:event_btPythonActionPerformed

    private void btGolangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGolangActionPerformed
        setGoText();
    }//GEN-LAST:event_btGolangActionPerformed

    private void btCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCleanActionPerformed
        // TODO add your handling code here
        textArea1.setText("INICIO\n\nFIN");
        
    }//GEN-LAST:event_btCleanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        JOptionPane.showMessageDialog(this.rootPane, "Gracias por usar el programa:D");
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        JFileChooser j = new JFileChooser("C:\\Users\\josue\\Documents\\NetBeansProjects\\OLC1_Proyecto1");
        // Open and read save file
        int checkInput = j.showOpenDialog(null);

        if (checkInput == JFileChooser.APPROVE_OPTION) {
            File openedFile = j.getSelectedFile();

            try {
                FileReader fileReader = new FileReader(openedFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String string1 = "";
                StringBuilder string2 = new StringBuilder();

                while ((string1 = bufferedReader.readLine()) != null) {
                    string2.append(string1).append("\n");
                }
                textArea1.setText(string2.toString());
                bufferedReader.close();
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        String file_get_path = "C:\\Users\\josue\\Documents\\NetBeansProjects\\OLC1_Proyecto1\\Arbol_Sintactico.jpg" ;
        try {
            Desktop.getDesktop().open(new File(file_get_path));
        } catch (IOException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        Errores error = new Errores("", "", 0, 0);
        error.makeHTML();
        String file_get_path = "C:\\Users\\josue\\Documents\\NetBeansProjects\\OLC1_Proyecto1\\Errores_Lexicos.html" ;
        try {
            Desktop.getDesktop().open(new File(file_get_path));
        } catch (IOException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btClean;
    private javax.swing.JButton btGolang;
    private javax.swing.JButton btPython;
    private javax.swing.JButton btStart;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuBar navBar;
    private javax.swing.JTextArea textArea1;
    private javax.swing.JTextArea textArea2;
    // End of variables declaration//GEN-END:variables
}
