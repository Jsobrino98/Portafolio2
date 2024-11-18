package DAO;

import ConexionDB.ConexionDB;
import Modelo.Cliente;
import Modelo.Videojuego;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase ClienteDAOImpl, con constructores y métodos Getters, Setters y toString
 *
 * @author Jorge Sobrino
 */
public class ClienteDAOImpl implements ClienteDAO{
    /**
     * Metodo para agregar un cliente
     * Pasando un cliente por parámetro
     * Devolve un enteiro
     */
    @Override
    public int agregarCliente(Cliente c) throws SQLException {
        Date fecha = Date.valueOf(LocalDate.now());
        int respuesta = 0;
        String sql = "INSERT INTO clientes(nombre,email,fecha_registro)" +
                "VALUES(?,?,?)";
        try (Connection conexion = ConexionDB.getConexion()) {
            PreparedStatement pst = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, c.getNombre());
            pst.setString(2, c.getEmail());
            pst.setDate(3, fecha);

            pst.executeUpdate();

            ResultSet claves = pst.getGeneratedKeys();
            if (claves.next()) {
                respuesta = (int) claves.getLong(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return respuesta;
    }

    /**
     * Metodo para consultar un cliente
     * Pasando un id por parámetro
     * Devolve un cliente
     */
    @Override
    public Cliente consultarClienteID(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id=?";
        Cliente c = null;

        try (Connection conexion = ConexionDB.getConexion()) {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                c = new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4)
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     * Metodo para obter todos os clientes
     * Devolve unha lista de clientes
     */

    @Override
    public List<Cliente> obtenerTodosClientes() throws SQLException {
        List<Cliente> listado = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        //Decirle por sql que devuelva todos
        try (Connection conexion = ConexionDB.getConexion()) {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4)

                );

                listado.add(cliente);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return listado;
    }
    /**
     * Método para eliminar un cliente
     * Pasando un id por parametro
     * Devolve un enteiro
     */
    @Override
    public int eliminarClientePorId(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id=?";
        int respuesta = 0;

        try (Connection conexion = ConexionDB.getConexion()) {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            respuesta = pst.executeUpdate();
            if (respuesta!=0){
                System.out.println("Eliminado con exito!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Metodo para actualizar un cliente
     * Pasando un id, e un cliente por parámetro
     * Devolve unha resposta en forma de String
     */
    @Override
    public String actualizarClientePorId(int id, Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nombre=?, email=?, fecha_Registro=? WHERE id=?";
        String respuesta;


        // Establecer la conexión y preparar el PreparedStatement
        try (Connection conexion = ConexionDB.getConexion()) {
            PreparedStatement pst = conexion.prepareStatement(sql);

            // Establecemos los valores en el PreparedStatement en el orden correcto
            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getEmail());
            pst.setDate(3, cliente.getFecha_registro());
            pst.setInt(4, id);  // Usamos el ID que se pasa como parámetro

            // Ejecutamos la actualización

            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas > 0) {
                respuesta = "Cliente actualizado correctamente.";
            } else {
                respuesta = "No se encontró un Cliente con el ID especificado.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            respuesta = "Error al actualizar el Cliente: " + e.getMessage();
        }

        return respuesta;
    }

}
