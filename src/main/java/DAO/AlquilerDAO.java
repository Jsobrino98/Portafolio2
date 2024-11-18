package DAO;

import Modelo.Alquiler;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
/**
 * Interfaz para implementar los metodos sobre AlquileresDAOImpl
 *
 * @author Jorge Sobrino
 * @version 1.0
 */
public interface AlquilerDAO {

    /**
     * Registrar un alquiler
     * @param alquiler
     * @return int
     * @throws SQLException
     */
    int registrarAlquiler(Alquiler alquiler) throws SQLException;

    /**
     * Obter todos os alquileres
     * @return List<Alquiler>
     * @throws SQLException
     */
    List<Alquiler> obterTodosAlquileres () throws SQLException;

    /**
     * Actualizar a data de devolucion dun alquiler
     * @param idAlquiler
     * @param devolucion
     * @return String
     * @throws SQLException
     */
    String actualizarAlquilerDevolucion(int idAlquiler, Date devolucion) throws SQLException;

    /**
     * Eliminar un alquiler por ID
     * @param id
     * @return int
     * @throws SQLException
     */
    int eliminarAlquiler (int id) throws SQLException;

    /**
     * Consultar alquiler por ID
     * @param id
     * @return Alquiler
     * @throws SQLException
     */
    Alquiler consultarAlquilerPorID (int id) throws SQLException;
}
