package es.uva.eii.ds.empresa7.interfaz.pares_vista_control.empleado;

import es.uva.eii.ds.empresa7.interfaz.GestorInterfazUsuario;
import es.uva.eii.ds.empresa7.negocio.controladoresCasoUso.ControladorCUIdentificarse;
import es.uva.eii.ds.empresa7.negocio.modelos.RepositorioDatos;
import es.uva.eii.ds.empresa7.negocio.modelos.Rol;
import es.uva.eii.ds.empresa7.servicioscomunes.Repositorio;
import es.uva.eii.ds.empresa7.servicioscomunes.DatosNoEncontradosException;
import java.sql.SQLException;



/**
 * Controlador de la vista de identificarse.
 * @author enrmart,francu,carlgar
 */
public class CtrlVistaIdentificarse {
    private VistaIdentificarse vista;
    private ControladorCUIdentificarse control;
    
    /**
     * Constructor del controlador de la vista
     * @param pantalla {@link VistaIdentificarse} que es la entrada de datos
     */
    public CtrlVistaIdentificarse(VistaIdentificarse pantalla){
        this.vista=pantalla;
        this.control = new ControladorCUIdentificarse();
    }
    /**
     * Recupera el nombre de usuario y la contraseña de la vista y llama al método 
     * IdentificarEmpleado del objeto Control si ambos son no vacíos.
     */
    void procesaEventoIdentificarse(){
        char [] d=vista.getDNI();
        char [] p= vista.getPassword();
        Rol rol;
        if(this.compruebaCharArrayNoVacio(d)&& this.compruebaCharArrayNoVacio(p)){
            try {
                rol = control.IdentificarEmpleado(p,d);
                muestraOpciones(rol);
            } catch (SQLException | DatosNoEncontradosException ex) {
                Repositorio al=RepositorioDatos.getInstance();
                al.setValor("error", ex.getMessage());
               GestorInterfazUsuario.generaVistaErrores();
            }
        }else{
            String error="Alguno de los campos esta vacio";
            Repositorio al=RepositorioDatos.getInstance();
            al.setValor("error", error);
            GestorInterfazUsuario.generaVistaErrores();
    
        }
    }
    /**
     * Comprueba si un arreglo de caracteres es vacío o no.
     *
     * @param comprobable el arreglo de caracteres a comprobar
     * 
     * @return verdadero si el arreglo de caracteres no está vacío, falso en caso contrario.
     */
   public boolean compruebaCharArrayNoVacio(char[] comprobable){
       return comprobable.length>0;
   }
   
   /**
    * Muestra las opciones disponibles para un usuario con un rol determinado.
    *
    * @param rol el rol del usuario.
    */
   public void muestraOpciones(Rol rol){
       switch(rol){
           case GerenteDePunto:
               GestorInterfazUsuario.generaVistaGerente();
               break;
           case AtencionEnPunto:
               GestorInterfazUsuario.generaVistaAtCliente();
               break;
           case TecnicoEnPunto:
               GestorInterfazUsuario.generaVistaTecnico();
               break;
       }        
   }
}
