package es.uva.eii.ds.empresa7.interfaz;

import es.uva.eii.ds.empresa7.interfaz.pares_vista_control.errores.VistaErrores;
import es.uva.eii.ds.empresa7.interfaz.pares_vista_control.attcliente.VistaAtencionCliente;
import es.uva.eii.ds.empresa7.interfaz.pares_vista_control.gerente.VistaGerente;
import es.uva.eii.ds.empresa7.interfaz.pares_vista_control.empleado.VistaIdentificarse;
import es.uva.eii.ds.empresa7.interfaz.pares_vista_control.attcliente.VistaIncidencias;
import es.uva.eii.ds.empresa7.interfaz.pares_vista_control.tecnico.VistaTecnico;



/**
 * Controlador de las interfaces del sistema se encarga de dar paso de unas a otras de la salida de las mismas.
 * @author enrmart,fracura,carlgar
 */
public class GestorInterfazUsuario {
    private static VistaIdentificarse vistaI=new VistaIdentificarse();
    private static VistaGerente vistaG;
    private static VistaAtencionCliente vistaAt;
    private static VistaIncidencias vistaIn;
    private static VistaTecnico vistaT;
    private static VistaErrores vistaE;
    /**
     * Main del controlador.
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaIdentificarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaIdentificarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaIdentificarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaIdentificarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                   vistaI.setVisible(true);
                } catch (Exception ex) {
                     
                }
            }
        });
        
    }
    
    /**
     * Genera una nueva vista "VistaGerente" a partir de una instancia existente de "VistaIdentificarse".
     */ 
    public static void generaVistaGerente(){
        if (vistaI.isActive()){
            java.awt.EventQueue.invokeLater(() -> {
                try {
                    vistaG = new VistaGerente();                 
                    vistaG.setVisible(true);
                    vistaI.setVisible(false);
                } catch (Exception ex) {
                    
                }
            });
        }
    }
    /**
     * Genera una nueva vista "VistaIdentificarse" a partir de una vista existente
     */
    public static void generaVistaIdentificarse(){
        if (vistaG.isActive()){
            java.awt.EventQueue.invokeLater(() -> {
                try {                
                    vistaG.setVisible(false);
                    vistaI.setVisible(true);
                } catch (Exception ex) {

                }
            });
        }else if(vistaAt.isActive()){
              java.awt.EventQueue.invokeLater(() -> {
                try {                
                    vistaAt.setVisible(false);
                    vistaI.setVisible(true);
                } catch (Exception ex) {

                }
            });
        }else if(vistaT.isActive()){
            java.awt.EventQueue.invokeLater(() -> {
                try {                
                    vistaT.setVisible(false);
                    vistaI.setVisible(true);
                } catch (Exception ex) {

                }
            });
        }
    }
    /**
     * Genera una nueva vista "VistaAtencionCliente" a partir de una vista existente que es la de identificarse.
     */
    public static void generaVistaAtCliente(){
        if (vistaI.isActive()){
            java.awt.EventQueue.invokeLater(() -> {
                try {
                    vistaAt = new VistaAtencionCliente();                 
                    vistaAt.setVisible(true);
                    vistaI.setVisible(false);
                } catch (Exception ex) {

                }
            });
        }
    }
    /**
     * Genera una nueva vista "VistaIncidencias" a partir de una vista existente atencionCliente.
     */
    public static void generaVistaIncidencias() {
         if (vistaAt.isActive()){
            java.awt.EventQueue.invokeLater(() -> {
                try {
                    vistaIn = new VistaIncidencias();                 
                    vistaIn.setVisible(true);
                    vistaAt.setVisible(false);
                } catch (Exception ex) {

                }
            });
        }
    }
    /**
     * Devuelve a la vista AtencionCliente desde la vista Incidencias.
     */
    public static void vueltaAVistaAtencionCliente() {
        if (vistaIn.isActive()){
            java.awt.EventQueue.invokeLater(() -> {
                try {                
                    vistaIn.setVisible(false);
                    vistaAt.setVisible(true);
                } catch (Exception ex) {

                }
            });
        }
    }
    
    /**
     * Genera la vistaTecnico desde la vista Identificarse.
     */
    public static void generaVistaTecnico() {
        if (vistaI.isActive()){
            java.awt.EventQueue.invokeLater(() -> {
                try {
                    vistaT = new VistaTecnico();                 
                    vistaT.setVisible(true);
                    vistaI.setVisible(false);
                } catch (Exception ex) {

                }
            });
        }
    }
    /**
     * Genera la vista de errores desde cualquier vista en la que se invoca un error.
     */
    public static void generaVistaErrores() {
        if(vistaI != null){
            java.awt.EventQueue.invokeLater(() -> {
                try { 
                    vistaI.setVisible(false);
                    vistaE=new VistaErrores();
                    vistaE.setVisible(true);
                    vistaI.setEnabled(false);
                } catch (Exception ex) {

                }
            });
        }else if (vistaG != null){
            java.awt.EventQueue.invokeLater(() -> {
                try {
                    vistaG.setVisible(false);
                    vistaE=new VistaErrores();
                    vistaE.setVisible(true);
                    vistaG.setEnabled(false);
                } catch (Exception ex) {

                }
            });
        }else if(vistaAt != null){
              java.awt.EventQueue.invokeLater(() -> {
                try {            
                    vistaAt.setVisible(false);
                    vistaE=new VistaErrores();
                    vistaE.setVisible(true);
                    vistaAt.setEnabled(false);
                } catch (Exception ex) {

                }
            });
        }else if(vistaT != null){
            java.awt.EventQueue.invokeLater(() -> {
                try {               
                    vistaT.setVisible(false);
                    vistaE=new VistaErrores();
                    vistaE.setVisible(true);
                    vistaT.setEnabled(false);
                } catch (Exception ex) {

                }
            });
        }
    }
    /**
     * Supone la finalizacion del programa por errores producidos
     */
    public static void cierraErrores() {
        vistaE.setVisible(false);
        vistaE.setEnabled(false);
        System.exit(0);
    }
}

