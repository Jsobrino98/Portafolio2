package DAO;

import Modelo.Alquiler;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface AlquilerDAO {

    int registrarAlquiler(Alquiler alquiler) throws SQLException;
    List<Alquiler> obterTodosAlquileres () throws SQLException;
    String actualizarAlquilerDevolucion(int idAlquiler, Date devolucion) throws SQLException;
    int eliminarAlquiler (int id) throws SQLException;
    Alquiler consultarAlquilerPorID (int id) throws SQLException;
}
