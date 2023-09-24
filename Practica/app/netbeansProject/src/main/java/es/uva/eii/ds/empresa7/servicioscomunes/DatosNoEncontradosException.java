package es.uva.eii.ds.empresa7.servicioscomunes;

/**
 *  Excepcion de datos no encontrados
 * @author enrmart,francu,carlgar
 */
public class DatosNoEncontradosException extends Exception {
    public DatosNoEncontradosException(String mensaje){
        super(mensaje);
    }
}
