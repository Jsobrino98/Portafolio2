package DAO;

import Modelo.Videojuego;
import org.xml.sax.SAXException;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz para implementar los metodos sobre Videojuegos
 *
 * @author Jorge Sobrino
 * @version 1.0
 */
public interface VideojuegosDAO {

    int agregarVideojuego (Videojuego v) throws SQLException;
    Videojuego consultarVideojuegoID (int id) throws SQLException;
    List<Videojuego> obtenerTodosVideojuegos() throws SQLException;
    int eliminarVideojuegoPorId (int id) throws SQLException;
    String actualizarVideojuegoPorId(int id, Videojuego videojuego) throws SQLException;

}
