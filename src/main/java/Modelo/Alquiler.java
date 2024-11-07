package Modelo;

import java.sql.Date;

public class Alquiler {
    private int id;
    private int cliente_id;
    private int videojuego_id;
    private Date fecha_alquiler;
    private Date fecha_devolucion;


    public Alquiler() {
    }

    public Alquiler(int id, int cliente_id, int videojuego_id, Date fecha_alquiler, Date fecha_devolucion) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.videojuego_id = videojuego_id;
        this.fecha_alquiler = fecha_alquiler;
        this.fecha_devolucion = fecha_devolucion;
    }

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
