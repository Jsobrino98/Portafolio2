package DAO;

import Modelo.Cliente;
import Modelo.Videojuego;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {
    int agregarCliente (Cliente cliente) throws SQLException;
    Cliente consultarClienteID (int id) throws SQLException;
    List<Cliente> obtenerTodosClientes() throws SQLException;
    int eliminarClientePorId (int id) throws SQLException;
    String actualizarClientePorId(int id, Cliente cliente) throws SQLException;

}
