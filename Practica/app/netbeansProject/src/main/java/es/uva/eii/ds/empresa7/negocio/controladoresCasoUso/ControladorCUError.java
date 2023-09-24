package es.uva.eii.ds.empresa7.negocio.controladoresCasoUso;

import es.uva.eii.ds.empresa7.negocio.modelos.RepositorioDatos;
import es.uva.eii.ds.empresa7.servicioscomunes.Repositorio;

/**
 * Clase que se encarga del controlador del caso de errores.
 * @author enrmart,fracura,carlgar
 */
public class ControladorCUError {

    /**
     * Hace el getter del error del repositorio
     * @return error a mostrar en pantalla
     */
    public String getError() {
        Repositorio al=RepositorioDatos.getInstance();
        return (String) al.getValor("error");}
    
}
