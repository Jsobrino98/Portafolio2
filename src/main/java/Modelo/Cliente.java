package Modelo;

import java.sql.Date;
/**
 * Clase Cliente, con constructores y métodos Getters, Setters y toString
 *
 * @author Jorge Sobrino
 */
public class Cliente {
    private int id;
    private String nombre;
    private String email;
    private Date fecha_registro;

    /**
     * Constructor vacio de la clase Cliente
     */
    public Cliente() {}

    /**
     * Constructor completo de la clase Cliente
     * @param id
     * @param nombre
     * @param email
     * @param fecha_registro
     */
    public Cliente(int id, String nombre, String email, Date fecha_registro) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.fecha_registro = fecha_registro;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    /**
     * Método toString de la clase Cliente
     */
    @Override
    public String toString() {
        return "🌟✨ Cliente ✨🌟\n" +
                "===========================\n" +
                "🆔 ID: " + id + "\n" +
                "📛 Nombre: " + nombre + "\n" +
                "📧 Email: " + email + "\n" +
                "📅 Fecha de Registro: " + fecha_registro + "\n" +
                "===========================\n";
    }

}
