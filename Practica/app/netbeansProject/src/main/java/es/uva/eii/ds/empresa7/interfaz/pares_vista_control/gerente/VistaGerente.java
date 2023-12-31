package es.uva.eii.ds.empresa7.interfaz.pares_vista_control.gerente;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *  Clase de la vista Gerente
 * @author franc,enrmart,carlgar
 */
public class VistaGerente extends javax.swing.JFrame {

    private final CtrlVistaGerente control;
    
    
    /**
     * Constructor VistaGerente
     */
    public VistaGerente(){
        this.control=new CtrlVistaGerente(this);
        initComponents();
        configuraPantalla();
        
        bienvenida1.setFont(new Font("Tahoma", Font.BOLD, 14));
        bienvenida2.setFont(new Font("Tahoma", Font.BOLD, 14));
        Punto.setFont(new Font("Tahoma", Font.BOLD, 14));
        String nombre=control.procesaEventoPedirNombre();
        int p=control.procesaEventoPedirNumeroPunto();
        muestraBienvenida(nombre,p);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bienvenida1 = new javax.swing.JLabel();
        mostrarVehiculos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaVehiculos = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        informacionPunto = new javax.swing.JTextArea();
        Cancelar = new javax.swing.JButton();
        Punto = new javax.swing.JTextField();
        bienvenida2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(670, 490));

        bienvenida1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        mostrarVehiculos.setText("Mostrar vehiculos");
        mostrarVehiculos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mostrarVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarVehiculosActionPerformed(evt);
            }
        });

        listaVehiculos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaVehiculosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listaVehiculos);

        informacionPunto.setEditable(false);
        informacionPunto.setColumns(20);
        informacionPunto.setRows(5);
        jScrollPane2.setViewportView(informacionPunto);

        Cancelar.setText("Cancelar");
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });

        Punto.setEditable(false);

        bienvenida2.setText("Punto:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(115, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(bienvenida1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bienvenida2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Punto, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(108, 108, 108))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(113, 113, 113)))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(mostrarVehiculos))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(bienvenida1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bienvenida2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Punto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mostrarVehiculos)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        control.procesaEventoCancelar();
    }//GEN-LAST:event_CancelarActionPerformed

    private void listaVehiculosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaVehiculosMouseClicked
        try {
            control.procesaEventoCocheSeleccionado();
        } catch (SQLException ex) {
            Logger.getLogger(VistaGerente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_listaVehiculosMouseClicked

    private void mostrarVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarVehiculosActionPerformed

        control.procesaEventoMostrarVehiculos();

    }//GEN-LAST:event_mostrarVehiculosActionPerformed

    private void muestraBienvenida(String nombre,int punto){
        bienvenida1.setText("Bienvenido Gerente, "+ nombre);
        Punto.setText(String.valueOf(punto));
    }
    /**
     * Recibe la lista de vehiculos y la envia a la vista de Gerente
     * @param lista lista de vehiculos a mostrar
     */
    void muestraVehiculos(ArrayList<String> lista){
        mostrarVehiculos.setEnabled(false);
        DefaultListModel<String> model = new DefaultListModel<>();
        if(lista.isEmpty()){
            model.addElement("No existe ningun coche en del punto Seleccionado que se encuentre en otro lugar");
        }else{
            model.addAll(lista);
            listaVehiculos.setModel(model);
        }
    }
    /**
     * Metodo que muestra los deatos del punto en el que esta situado el vehiculo seleccionado
     * @param datosPunto con los datos a mostrar
     */
    void muestraDatosPuntoSeleccionado(String datosPunto){
        informacionPunto.setText("Datos del punto: " + "\r\n" + datosPunto);
    }
    
    /**
     * Devuelve el String con los datos del vehiculo seleccionado
     * 
     * @return un string correspondiente al coche seleccionado
     */
    public int getCocheSeleccionado(){
        return listaVehiculos.getSelectedIndex();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancelar;
    private javax.swing.JTextField Punto;
    private javax.swing.JLabel bienvenida1;
    private javax.swing.JLabel bienvenida2;
    private javax.swing.JTextArea informacionPunto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaVehiculos;
    private javax.swing.JButton mostrarVehiculos;
    // End of variables declaration//GEN-END:variables

    private void configuraPantalla() {
        // Obtener el tamaño de la pantalla
        Dimension pantallaTamano = Toolkit.getDefaultToolkit().getScreenSize();

        // Obtener el tamaño del componente
        Dimension componenteTamano = getPreferredSize();

        // Calcular la coordenada x para que el componente esté en el centro de la pantalla
        int x = (pantallaTamano.width - componenteTamano.width) / 2;

        // Calcular la coordenada y par a que el componente esté en el centro de la pantalla
        int y = (pantallaTamano.height - componenteTamano.height) / 2;

        // Establecer la ubicación y el tamaño del componente
        setBounds(x, y, componenteTamano.width, componenteTamano.height);
    }
}
