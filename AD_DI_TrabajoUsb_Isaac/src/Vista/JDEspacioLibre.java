/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.MetodosManejoUSB;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author isaac
 */
public class JDEspacioLibre extends javax.swing.JDialog {

    /**
     * Creates new form JDEspacioLibre
     */
    public JDEspacioLibre(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtfRutaArchivoACopiar = new javax.swing.JTextField();
        jbRutaCopiar = new javax.swing.JButton();
        jbEspacioDisponible = new javax.swing.JButton();
        jlEspacioLibre = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jbRutaCopiar.setText("Seleccionar");
        jbRutaCopiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbRutaCopiarMouseClicked(evt);
            }
        });

        jbEspacioDisponible.setText("Calcular espacio libre");
        jbEspacioDisponible.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbEspacioDisponibleMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jtfRutaArchivoACopiar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbRutaCopiar))
                            .addComponent(jbEspacioDisponible)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jlEspacioLibre)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfRutaArchivoACopiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbRutaCopiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbEspacioDisponible)
                .addGap(18, 18, 18)
                .addComponent(jlEspacioLibre)
                .addContainerGap(157, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbRutaCopiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbRutaCopiarMouseClicked
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int seleccion = fileChooser.showOpenDialog(jtfRutaArchivoACopiar);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File ficheroSeleccionado = fileChooser.getSelectedFile();
            jtfRutaArchivoACopiar.setText(ficheroSeleccionado.getAbsolutePath());
        } else if (seleccion == JFileChooser.ERROR_OPTION) {
            //jlError.setText("Error, vuelva a intentarlo");
        }
    }//GEN-LAST:event_jbRutaCopiarMouseClicked

    private void jbEspacioDisponibleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbEspacioDisponibleMouseClicked
        try {
            MetodosManejoUSB m = new MetodosManejoUSB("", "");
            File file = new File(jtfRutaArchivoACopiar.getText());
            jlEspacioLibre.setText("Quedan " + m.espacioTotalDispositivo(file) + " MB disponibles");
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbEspacioDisponibleMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbEspacioDisponible;
    private javax.swing.JButton jbRutaCopiar;
    private javax.swing.JLabel jlEspacioLibre;
    private javax.swing.JTextField jtfRutaArchivoACopiar;
    // End of variables declaration//GEN-END:variables
}
