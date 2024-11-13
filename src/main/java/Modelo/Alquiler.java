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
        return "¡Alquiler!\n" +
                "============================================\n" +
                "🌀 Alquiler # " + id + "\n" +
                "🎮 Videojuego: " + obtenerNombreVideojuego() + "\n" +
                "👤 Cliente: " + obtenerNombreCliente() + "\n" +
                "📅 Fecha de Alquiler: " + formatoFecha(fecha_alquiler) + "\n" +
                "📅 Fecha de Devolución: " + formatoFecha(fecha_devolucion) + "\n" +
                "============================================\n" +
                "¡Que el juego comience y la aventura sea épica! 🎮✨";
    }

    // Método auxiliar para formatear la fecha de manera más legible
    private String formatoFecha(java.sql.Date fecha) {
        java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
        return formato.format(fecha);
    }

    // Simulación de obtención del nombre del videojuego
    private String obtenerNombreVideojuego() {
        // Aquí deberías implementar la lógica para obtener el nombre del videojuego
        return "Mundo Mágico: La Aventura Final";
    }

    // Simulación de obtención del nombre del cliente
    private String obtenerNombreCliente() {
        // Aquí deberías implementar la lógica para obtener el nombre del cliente
        return "Sir Arthur el Valiente";
    }

}
