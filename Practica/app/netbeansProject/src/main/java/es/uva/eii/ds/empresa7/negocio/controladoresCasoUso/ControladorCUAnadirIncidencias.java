package es.uva.eii.ds.empresa7.negocio.controladoresCasoUso;


import es.uva.eii.ds.empresa7.negocio.modelos.Entrega;
import es.uva.eii.ds.empresa7.negocio.modelos.Incidencia;
import es.uva.eii.ds.empresa7.negocio.modelos.ListadoIncidencias;
import es.uva.eii.ds.empresa7.persistencia.daos.IncidenciaDAO;
import es.uva.eii.ds.empresa7.negocio.modelos.Session;
import es.uva.eii.ds.empresa7.negocio.modelos.TipoIncidencia;
import es.uva.eii.ds.empresa7.negocio.modelos.RepositorioDatos;
import es.uva.eii.ds.empresa7.servicioscomunes.Repositorio;
import java.util.ArrayList;



/**
 * Clase que gestiona la creación y registro de incidencias en el sistema.
 * @author enrmart,fracura,carlgar
 */
public class ControladorCUAnadirIncidencias {
    private ListadoIncidencias listado;
    private int siguienteIdIncidencia;
    private Session sesion;
    private Repositorio almacen;
    


    /**
     * Constructor de ControladorCUAnadirIncidencias que recibe como parámetro un objeto de la clase CtrlVistaIncidencias.
     */
    public ControladorCUAnadirIncidencias() {
        this.listado=new ListadoIncidencias();
        this.siguienteIdIncidencia = IncidenciaDAO.devuelveId();
        this.sesion=Session.getInstance();
        this.almacen=RepositorioDatos.getInstance();
    }

    
     /**
      * Metodo que crea una nueva incidencia, la añade al listado y muestra en vista todo el listado.
      * @param descInc descripcion de la incidencia
      * @param tipoInc tipo de la incidencia
      * @param importe importe asociado
      * @return listado de incidencias
      */
    public ArrayList<String> registrarIncidencia(String descInc, TipoIncidencia tipoInc, double importe){         
        Incidencia incidencia = new Incidencia(siguienteIdIncidencia, descInc, tipoInc, importe, (Entrega)almacen.getValor("entrega"));   
        this.siguienteIdIncidencia++;
        listado.anadirIncidencia(incidencia);
        return listado.getDatosListado();
    }
    /**
     * Metodo que guarda el listado para que se pueda usar en el posterior registro de la devolucion
     */
    public void registrarInformacion(){
        this.almacen.setValor("listado", listado);
    }
}
