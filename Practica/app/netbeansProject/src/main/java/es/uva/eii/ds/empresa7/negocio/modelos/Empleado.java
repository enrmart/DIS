package es.uva.eii.ds.empresa7.negocio.modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.json.JSONObject;

/**
 * Clase Empleado.
 * @author enrmart,fracura,carlgar
 */
public class Empleado implements Cloneable{
    private String dni;
    private String nombre;
    private LocalDate fechaInicioEmpresa;
    private Disponibilidad disponibilidad;
    private Rol rol;
    private Punto punto;
    
    /**
     * Constructor Empleado a partir de un Json.
     * @param json del empleado a crear
     */
    public Empleado(String json) {
            JSONObject combinedJson = new JSONObject(json);
            JSONObject empleadoJSON = combinedJson.getJSONObject("empleado");
            this.nombre = empleadoJSON.getString("nombre");
            this.dni = empleadoJSON.getString("Nif");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.fechaInicioEmpresa=LocalDate.parse(empleadoJSON.getString("FechaInicioEnEmpresa"), formatter);
            this.disponibilidad = Enum.valueOf(Disponibilidad.class, empleadoJSON.getString("Disponibilidad"));
            this.rol = Enum.valueOf(Rol.class, empleadoJSON.getString("Rol"));
            this.punto= new Punto(json);
    }

    /**
     * Verifica si el objeto está activo.
     * 
     * @return true si estadisponible, false en caso contrario.
     */
    public boolean estaActivo() {
        return this.disponibilidad.equals(Disponibilidad.Trabajando);
    }
    
    
    /**
     * Devuelve el valor del rol.
     *
     * @return un entero correspondiente al Rol
     */
    public Rol getRol(){
        return this.rol;
    }
    
    /**
     * Devuelve el valor del nombre.
     *
     * @return un string correspondiente al nombre del empleado
     */
    public String getNombre(){
        return this.nombre;
    }
    /**
     * Devuelve el punto al que esta asignado el empleado
     *
     * @return un entero correspondiente al punto
     * @throws CloneNotSupportedException  si se intenta clonar una clase que no tenga implementado cloneable
     */
    public Punto getPunto() throws CloneNotSupportedException{
        return punto.clone();
    }
    
    /**
     * Devuelve el valor del dni.
     *
     * @return un string correspondiente al dni del empleado
     */
    public String getDni(){
        return this.dni;
    }

    /**
     * Implementacion de Cloneable
     * @return Empleado
     * @throws CloneNotSupportedException si se intenta clonar una clase que no tenga implementado cloneable
     */
    @Override
    public Empleado clone() throws CloneNotSupportedException {
        try {
            return (Empleado) super.clone();
        } catch (CloneNotSupportedException e) {
            // Manejo de excepción en caso de no ser clonable
            return null;
        }
    }
    
}
