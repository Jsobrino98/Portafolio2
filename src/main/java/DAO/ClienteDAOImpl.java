package DAO;

import ConexionDB.ConexionDB;
import Modelo.Cliente;
import Modelo.Videojuego;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO{
    @Override
    public int agregarCliente(Cliente c) throws SQLException {
        Date fecha = Date.valueOf(LocalDate.now());
        int respuesta = 0;
        String sql = "INSERT INTO clientes(id,nombre,email,fecha_registro)" +
                "VALUES(?,?,?,?)";
        try (Connection conexion = ConexionDB.getConexion()) {
            PreparedStatement pst = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            Cliente cliente = new Cliente();

            pst.setInt(1, cliente.getId());
            pst.setString(2, cliente.getNombre());
            pst.setString(3, cliente.getEmail());
            pst.setDate(4, fecha);

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

    @Override
    public List<Cliente> obtenerTodosClientes() throws SQLException {

        List<Cliente> listado = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

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

    @Override
    public String actualizarClientePorId(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nombre=?, email=?, fechaRegsitro=? WHERE id=?";
        String respuesta;

        try (Connection conexion = ConexionDB.getConexion()) {
            PreparedStatement pst = conexion.prepareStatement(sql);

            // Establecemos los valores en el PreparedStatement en el orden correcto
            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getEmail());
            pst.setDate(3, cliente.getFecha_registro());
            pst.setInt(5, cliente.getId());

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
