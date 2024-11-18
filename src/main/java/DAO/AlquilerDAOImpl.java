package DAO;

import ConexionDB.ConexionDB;
import Modelo.Alquiler;
import Modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase AlquilerDAOImpl, con constructores y métodos Getters, Setters y toString
 *
 * @author Jorge Sobrino
 */
public class AlquilerDAOImpl implements AlquilerDAO{

    /**
     * Metodo para agregar un registro
     * Pasando un alquiler por parámetro
     * Devolve un enteiro
     */
    @Override
    public int registrarAlquiler(Alquiler alquiler) throws SQLException {
        int respuesta = 0;
        String sql = "INSERT INTO alquileres(cliente_id, videojuego_id, fecha_alquiler, fecha_devolucion) " +
                "VALUES(?, ?, ?, ?)";

        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement pst = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Establecemos los parámetros del PreparedStatement
            pst.setInt(1, alquiler.getCliente_id());
            pst.setInt(2, alquiler.getVideojuego_id());
            pst.setDate(3, alquiler.getFecha_alquiler());
            pst.setDate(4, alquiler.getFecha_devolucion());

            // Ejecutamos la inserción
            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas > 0) {
                // Obtenemos la clave generada para el alquiler insertado
                try (ResultSet claves = pst.getGeneratedKeys()) {
                    if (claves.next()) {
                        respuesta = claves.getInt(1);  // Obtenemos el ID del alquiler insertado
                        System.out.println("Alquiler agregado correctamente con ID: " + respuesta);
                    } else {
                        System.out.println("No se pudo obtener la clave generada para el alquiler.");
                    }
                }
            } else {
                System.out.println("No se pudo agregar el alquiler.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al registrar el alquiler: " + e.getMessage());
        }

        return respuesta;
    }

    /**
     * Metodo para obter todos os alquileres
     * Devolve unha lista de alquileres
     */
    @Override
    public List<Alquiler> obterTodosAlquileres() throws SQLException {

        List<Alquiler> listado = new ArrayList<>();
        String sql = "SELECT * FROM alquileres";

        //Decirle por sql que devuelva todos
        try (Connection conexion = ConexionDB.getConexion()) {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                Alquiler a = new Alquiler(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getDate(5));

                listado.add(a);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return listado;
    }

    /**
     * Metodo para actualizar a fecha de devolución dun alquiler
     * Pasando un id e unha data de devolución por parametro
     * Devolve unha resposta en forma de String
     */

    @Override
    public String actualizarAlquilerDevolucion(int idAlquiler, Date devolucion) throws SQLException {
        String sql = "UPDATE alquileres SET fecha_devolucion=? WHERE id=?";
        String respuesta;


        // Establecer la conexión y preparar el PreparedStatement
        try (Connection conexion = ConexionDB.getConexion()) {
            PreparedStatement pst = conexion.prepareStatement(sql);

            // Establecemos los valores en el PreparedStatement en el orden correcto
            pst.setDate(1, devolucion);
            pst.setInt(2, idAlquiler);

            // Ejecutamos la actualización
            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas > 0) {
                respuesta = "Alquiler actualizado correctamente.";
            } else {
                respuesta = "No se encontró un Alquiler con el ID especificado.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            respuesta = "Error al actualizar el Alquiler: " + e.getMessage();
        }

        return respuesta;
    }

    /**
     * Metodo para eliminar un alquiler
     * Pasando un id por parámetro
     * Devolve un enteiro
     */
    @Override
    public int eliminarAlquiler(int id) throws SQLException {
        String sql = "DELETE FROM alquileres WHERE id=?";
        int respuesta = 0;

        // Establecer la conexión y preparar el PreparedStatement
        try (Connection conexion = ConexionDB.getConexion()) {
            PreparedStatement pst = conexion.prepareStatement(sql);

            // Establecemos los valores en el PreparedStatement en el orden correcto
            pst.setInt(1, id);;

            // Ejecutamos la actualización
            respuesta = pst.executeUpdate();

            if (respuesta != 0) {
                System.out.println("Alquiler eliminado correctamente ");
            } else {
                System.out.println("No se pudo eliminar el alquiler");;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return respuesta;
    }
    /**
     * Metodo para consultar un alquiler
     * Pasando un id por parámetro
     * Devolve un Alquiler
     */
    @Override
    public Alquiler consultarAlquilerPorID(int id) throws SQLException {
        String sql = "SELECT * FROM alquileres WHERE id=?";
        Alquiler a = null;

        try (Connection conexion = ConexionDB.getConexion()) {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                a = new Alquiler(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getDate(5)
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }


}
