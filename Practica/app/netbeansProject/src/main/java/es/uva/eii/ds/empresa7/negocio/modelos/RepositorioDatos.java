package es.uva.eii.ds.empresa7.negocio.modelos;

import es.uva.eii.ds.empresa7.servicioscomunes.Repositorio;
import java.util.HashMap;

/**
 * Esta clase define una serie de propiedades para un objeto de tipo "RepositorioDatos".
 * @author enrmart,fracura,carlgar
 */
public class RepositorioDatos implements Repositorio{
    private static RepositorioDatos instancia;
    
    /**
     * Metodo que devuelve una instancia del respositorio
     * @return instancia del repositorio
     */
    public static RepositorioDatos getInstance() {
        if (instancia == null) {
            instancia = new RepositorioDatos();
        }
        return instancia;
    }
    
    //La instancia de la clase
    private HashMap<String,Object> valores;
    
    private RepositorioDatos() {
        valores=new HashMap<>();
    }
     /**
     * Metodo setea un valor en un id dado
     * @param idValor id del valor a setear
     * @param valor objeto a setear en ese id
     */
    @Override
    public void setValor(String idValor, Object valor) {
        valores.put(idValor, valor);
    }
    /**
     * Getter del valor de un id.
     * @param idValor id del que quieres hacer get
     * @return valor del id dado
     */
    @Override
    public Object getValor(String idValor) {
        return valores.get(idValor);
    }
    
}
