package DAO;

import Modelo.Alquiler;

import java.sql.SQLException;
import java.util.List;

public interface AlquilerDAO {

    int registrarAlquiler(Alquiler alquiler) throws SQLException;
    List<Alquiler> obterTodosAlquileres () throws SQLException;
    String actualizarAlquiler (int idAlquiler) throws SQLException;
    int eliminarAlquiler (int id) throws SQLException;

}
