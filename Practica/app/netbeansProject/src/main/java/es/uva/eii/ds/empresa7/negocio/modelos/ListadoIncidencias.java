package es.uva.eii.ds.empresa7.negocio.modelos;

import java.util.ArrayList;


/**
 *  Clase que guarda un listado de incidencias.
 * @author enrmart,fracura,carlgar
 */
public class ListadoIncidencias {

    private ArrayList<Incidencia> listado;

    public ListadoIncidencias() {
        listado = new ArrayList<>();
    }
    /**
     * Devuelve un ArrayList
     * @return un ArrayList de String
     */
    public ArrayList<Incidencia> getListado(){
        return new ArrayList<>(this.listado);
    }
    
    /**
     * Metodo que añade una incidencia al listado
     * @param inc con la incidencia a añadir.
     */
    public void anadirIncidencia(Incidencia inc){       
        this.listado.add(inc);
    }
    
    /**
     * Metodo que devuelve el Arraylist de incidencias en formato String.
     * @return incidencias en un arrayList de String
     */
    public ArrayList<String> getDatosListado() {
        ArrayList<String>incidencias=new ArrayList<>();
        for (int i = 0; i < listado.size(); i++){
            Incidencia in=listado.get(i);
            incidencias.add(in.getDatos());
        }
        return incidencias;
    }
}

