package DAO;

import ConexionDB.ConexionDB;
import Modelo.Videojuego;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideojuegosDAOImpl implements VideojuegosDAO {

    public List<Videojuego> obtenerTodosVideojuegos() throws SQLException {

        List<Videojuego> listado = new ArrayList<>();
        String sql = "SELECT * FROM videojuegos";

        //Decirle por sql que devuelva todos
        try (Connection conexion = ConexionDB.getConexion()) {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Videojuego vd = new Videojuego(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)

                );

                listado.add(vd);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return listado;
    }

    @Override
    public int agregarVideojuego(Videojuego videojuego) throws SQLException {
        int respuesta = 0;
        String sql = "INSERT INTO videojuegos(id,titulo,genero,plataforma,copias_disponibles)" +
                "VALUES(?,?,?,?,?)";
        try (Connection conexion = ConexionDB.getConexion()) {
            PreparedStatement pst = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            Videojuego v = new Videojuego();

            pst.setInt(1, v.getId());
            pst.setString(2, v.getTitulo());
            pst.setString(3, v.getGenero());
            pst.setString(4, v.getPlataforma());
            pst.setInt(5, v.getCopias_disponibles());

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

    public Videojuego consultarVideojuegoID(int id) throws SQLException {
        String sql = "SELECT * FROM videojuegos WHERE id=?";
        Videojuego v = null;

        try (Connection conexion = ConexionDB.getConexion()) {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                v = new Videojuego(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

    public int eliminarVideojuegoPorId(int id) throws SQLException {
        String sql = "DELETE FROM videojuegos WHERE id=?";
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

    public String actualizarVideojuegoPorId(Videojuego videojuego) throws SQLException {

        String sql = "UPDATE videojuegos SET titulo=?, genero=?, plataforma=?, copias_disponibles=? WHERE id=?";
        String respuesta;

        try (Connection conexion = ConexionDB.getConexion()) {
            PreparedStatement pst = conexion.prepareStatement(sql);

            // Establecemos los valores en el PreparedStatement en el orden correcto
            pst.setString(1, videojuego.getTitulo());
            pst.setString(2, videojuego.getGenero());
            pst.setString(3, videojuego.getPlataforma());
            pst.setInt(4, videojuego.getCopias_disponibles());
            pst.setInt(5, videojuego.getId());

            int filasAfectadas = pst.executeUpdate();
            if (filasAfectadas > 0) {
                respuesta = "Videojuego actualizado correctamente.";
            } else {
                respuesta = "No se encontró un videojuego con el ID especificado.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            respuesta = "Error al actualizar el videojuego: " + e.getMessage();
        }

        return respuesta;
    }


}
