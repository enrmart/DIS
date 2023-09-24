package es.uva.eii.ds.empresa7.servicioscomunes;

/**
 * Interfaz del Repositorio.
 * @author enrmart,francu,carlgar
 */
public interface Repositorio {
    
    /**
     * Metodo de SetValor.
     * @param idValor con el id
     * @param valor con el valor
     */
    public void setValor(String idValor,Object valor);
    /**
     * Getter del valor asociado a un id.
     * @param idValor id del valor a getear
     * @return valor asociado al id
     */
    public Object getValor(String idValor);
    
}
