package DAO;

import Modelo.Cliente;
import Modelo.Videojuego;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz para implementar los metodos sobre ClienteDAOImpl
 *
 * @author Jorge Sobrino
 * @version 1.0
 */
public interface ClienteDAO {
    /**
     * Metodo para agregar cliente
     * @param cliente
     * @return int
     * @throws SQLException
     */
    int agregarCliente (Cliente cliente) throws SQLException;

    /**
     * Consultar cliente por ID
     * @param id
     * @return Cliente
     * @throws SQLException
     */
    Cliente consultarClienteID (int id) throws SQLException;

    /**
     * Obter lista de todos os clientes
     * @return Lista de clientes
     * @throws SQLException
     */
    List<Cliente> obtenerTodosClientes() throws SQLException;

    /**
     * Eliminar un cliente por id
     * @param id
     * @return int
     * @throws SQLException
     */
    int eliminarClientePorId (int id) throws SQLException;

    /**
     * Actualizar un cliente por ID
     * @param id
     * @param cliente
     * @return String
     * @throws SQLException
     */
    String actualizarClientePorId(int id, Cliente cliente) throws SQLException;

}
