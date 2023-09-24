package es.uva.eii.ds.empresa7.negocio.modelos;


/**
 *La clase Incidencia representa una incidencia en una entrega.
 *@author enrmart,fracura,carlgar
 */
public class Incidencia implements Cloneable {
    private int idIncidencia;
    private TipoIncidencia tipo;
    private String descripcion;
    private double importe;
    private Entrega entrega;
    


    /**
     * Constructor de la clase Incidencia.
     * 
     * @param idInc el ID de la incidencia
     * @param descripcionInc la descripción de la incidencia
     * @param tipoInc el tipo de la incidencia
     * @param importeInc el importe asociado a la incidencia
     * @param entrega entrega asociada a la incidencia
     */
    public Incidencia(int idInc, String descripcionInc, TipoIncidencia tipoInc, double importeInc,Entrega entrega){
        this.idIncidencia = idInc;
        this.descripcion = descripcionInc;
        this.tipo = tipoInc;
        this.entrega=entrega;
        this.importe = importeInc;
    }
    
     /**
     * Obtiene el ID de la incidencia.
     * 
     * @return el ID de la incidencia
     */
    public int getIdIncidencia() {
        return idIncidencia;
    }
 
    /**
     * Obtiene la descripción de la incidencia.
     * 
     * @return la descripción de la incidencia
     */
    public String getDescripcion() {
    return descripcion;
    }

    /**
     * Obtiene el tipo de la incidencia.
     * 
     * @return el tipo de la incidencia
     */
    public TipoIncidencia getTipo() {
        return tipo;
    }

    /**
     * Obtiene el importe asociado a la incidencia.
     * 
     * @return el importe asociado a la incidencia
     */
    public double getImporte() {
        return importe;
    }

     /**
     * Establece el importe asociado a la incidencia.
     * 
     * @param importe el nuevo importe asociado a la incidencia
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }

    /**
     * Obtiene el ID de la entrega asociada a la incidencia.
     * 
     * @return el ID de la entrega asociada a la incidencia
     * @throws CloneNotSupportedException  si se intenta clonar una clase que no tenga implementado cloneable
     */
    public Entrega getEntrega() throws CloneNotSupportedException {
        return (Entrega) entrega.clone();
    }

    /**
     * Metodo que te devuelve los datos de la incidencia en un String con los campos.
     * 
     * @return string de los datos.
     */
    public String getDatos(){
        String datos = "Tipo: " + getTipo().name() + ", descripcion: " + getDescripcion() + ", importe: " + getImporte() + "\r\n";
        return datos;
    }
    
     /**
     * Implementacion de Cloneable
     * @return Incidencia
     * @throws CloneNotSupportedException si se intenta clonar una clase que no tenga implementado cloneable
     */
    @Override
    public Incidencia clone() throws CloneNotSupportedException {
        try {
            return (Incidencia) super.clone();
        } catch (CloneNotSupportedException e) {
            // Manejo de excepción en caso de no ser clonable
            return null;
        }
    }
    

}
