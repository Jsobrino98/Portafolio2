package Modelo;

import java.sql.Date;

/**
 * Clase Alquiler, con constructores y métodos Getters, Setters y toString
 *
 * @author Jorge Sobrino
 */
public class Alquiler {
    private int id;
    private int cliente_id;
    private int videojuego_id;
    private Date fecha_alquiler;
    private Date fecha_devolucion;

    /**
     * Constructor vacío de la clase
     */
    public Alquiler() {
    }
    /**
     * Constructor con id y fecha de devolucion pasados por parametro
     */
    public Alquiler(int id, Date fecha_devolucion) {
        this.id = id;
        this.fecha_devolucion = fecha_devolucion;
    }

    /**
     * Constructor completo de la clase Alquiler
     * @param id
     * @param cliente_id
     * @param videojuego_id
     * @param fecha_alquiler
     * @param fecha_devolucion
     */

    public Alquiler(int id, int cliente_id, int videojuego_id, Date fecha_alquiler, Date fecha_devolucion) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.videojuego_id = videojuego_id;
        this.fecha_alquiler = fecha_alquiler;
        this.fecha_devolucion = fecha_devolucion;
    }

    /**
     * Métodos Getters y Setters de la clase
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getVideojuego_id() {
        return videojuego_id;
    }

    public void setVideojuego_id(int videojuego_id) {
        this.videojuego_id = videojuego_id;
    }

    public Date getFecha_alquiler() {
        return fecha_alquiler;
    }

    public void setFecha_alquiler(Date fecha_alquiler) {
        this.fecha_alquiler = fecha_alquiler;
    }

    public Date getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    /**
     * Método toString de la clase Alquiler
     */

    @Override
    public String toString() {
        return "Alquiler{" +
                "id=" + id +
                ", cliente_id=" + cliente_id +
                ", videojuego_id=" + videojuego_id +
                ", fecha_alquiler=" + fecha_alquiler +
                ", fecha_devolucion=" + fecha_devolucion +
                '}';
    }
}
