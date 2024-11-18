package DAO;

import Modelo.Videojuego;
import org.xml.sax.SAXException;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz para implementar los metodos sobre VideojuegosDAOImpl
 *
 * @author Jorge Sobrino
 * @version 1.0
 */
public interface VideojuegosDAO {

    /**
     * Agregar un videojuego
     * @param videojuego
     * @return int
     * @throws SQLException
     */
    int agregarVideojuego (Videojuego videojuego) throws SQLException;

    /**
     * Consultar un videojuego por ID
     * @param id
     * @return Videojuego
     * @throws SQLException
     */
    Videojuego consultarVideojuegoID (int id) throws SQLException;

    /**
     * Obter unha lista con todos os videojuegos
     * @return List<Videojuego>
     * @throws SQLException
     */
    List<Videojuego> obtenerTodosVideojuegos() throws SQLException;

    /**
     * Eliminar un videojuego por ID
     * @param id
     * @return int
     * @throws SQLException
     */
    int eliminarVideojuegoPorId (int id) throws SQLException;

    /**
     * Actualizar un videojuego por ID
     * @param id
     * @param videojuego
     * @return String
     * @throws SQLException
     */
    String actualizarVideojuegoPorId(int id, Videojuego videojuego) throws SQLException;

}
