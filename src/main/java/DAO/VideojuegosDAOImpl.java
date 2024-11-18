package DAO;

import ConexionDB.ConexionDB;
import Modelo.Cliente;
import Modelo.Videojuego;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase VideojuegoDAOImpl, con métodos para diferentes funciones dentro del programa
 *
 * @author Jorge Sobrino
 */
public class VideojuegosDAOImpl implements VideojuegosDAO {

    /**
     * Metodo para listar todos los videojuegos
     * Devuelve una lista de videojuegos
     */
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
    /**
     * Metodo para agregar un videojuego pasando un videojuego parametro
     * Devuelve un entero
     * Imprime un mensaje por pantalla, si pudo o no ser agregado
     */
    @Override
    public int agregarVideojuego(Videojuego v) throws SQLException {
        int respuesta = 0;
        String sql = "INSERT INTO videojuegos(id,titulo,genero,plataforma,copias_disponibles)" +
                "VALUES(?,?,?,?,?)";
        try (Connection conexion = ConexionDB.getConexion()) {
            PreparedStatement pst = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            pst.setInt(1, v.getId());
            pst.setString(2, v.getTitulo());
            pst.setString(3, v.getGenero());
            pst.setString(4, v.getPlataforma());
            pst.setInt(5, v.getCopias_disponibles());

            pst.executeUpdate();

            ResultSet claves = pst.getGeneratedKeys();
            if (claves.next()) {
                respuesta = (int) claves.getLong(1);
                System.out.println("VideoJuego agregado correctamente!");
            } else {
                System.out.println("Non se pudo agregar videojuego!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return respuesta;
    }
    /**
     * Metodo para buscar un videojuego pasando su ID como parametro
     * Devuelve un videojuego
     */
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
    /**
     * Metodo para eliminar un videojuego pasando su ID como parametro
     * Devuelve un numero, en caso de 0 NO ha sido eliminado
     * Imprime un mensaje por pantalla si ha sido eliminado
     */
    public int eliminarVideojuegoPorId(int id) throws SQLException {
        String sql = "DELETE FROM videojuegos WHERE id=?";
        int respuesta = 0;

        try (Connection conexion = ConexionDB.getConexion()) {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            respuesta = pst.executeUpdate();
            if (respuesta != 0) {
                System.out.println("Eliminado con exito!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }
    /**
     * Metodo para actualizar un videojuego pasando su ID, y los datos nuevos del videojuego por parametro
     * Devuelve un String
     * Imprime por pantalla un mensaje si pudo o no ser actualizado
     */
    public String actualizarVideojuegoPorId(int id, Videojuego videojuego) throws SQLException {
        // Sentencia SQL para actualizar el videojuego en la base de datos
        String sql = "UPDATE videojuegos SET titulo=?, genero=?, plataforma=?, copias_disponibles=? WHERE id=?";
        String respuesta;


        // Establecer la conexión y preparar el PreparedStatement
        try (Connection conexion = ConexionDB.getConexion()) {
            PreparedStatement pst = conexion.prepareStatement(sql);

            // Establecemos los valores en el PreparedStatement en el orden correcto
            pst.setString(1, videojuego.getTitulo());
            pst.setString(2, videojuego.getGenero());
            pst.setString(3, videojuego.getPlataforma());
            pst.setInt(4, videojuego.getCopias_disponibles());
            pst.setInt(5, id);  // Usamos el ID que se pasa como parámetro

            // Ejecutamos la actualización
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

    /**
     * Metodo para listar los videojuegos con copias disponibles
     * Devuelve una lista de videojuegos
     */
    public List<Videojuego> videojuegosDisponibles() {
        List<Videojuego> listado = new ArrayList<>();
        String sql = "SELECT * FROM videojuegos WHERE copias_disponibles > 0";

        //Decirle por sql que devuelva todos
        try (Connection conexion = ConexionDB.getConexion()) {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Videojuego videojuego = new Videojuego(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)
                );

                listado.add(videojuego);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return listado;
    }

    /**
     * Metodo para listar los videojuegos por el titulo
     * Devuelve una lista de videojuegos
     */
    public List<Videojuego> buscarVideoJuegoNombre(String nombre) {
        List<Videojuego> listado = new ArrayList<>();
        String sql = "SELECT * FROM videojuegos WHERE titulo like ?";

        Videojuego v = null;

        try (Connection conexion = ConexionDB.getConexion()) {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, nombre);
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

            listado.add(v);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listado;
    }

    /**
     * Metodo para listar los videojuegos de un cliente, pasando su ID por parametro
     * Devuelve una lista de videojuegos
     */
    public List<Videojuego> videojuegosPorCliente(int idCliente) {
        List<Videojuego> listado = new ArrayList<>();
        String sql = "SELECT v.id, v.titulo, v.genero, v.plataforma, v.copias_disponibles " +
                "FROM videojuegos v " +
                "JOIN alquileres a ON v.id = a.videojuego_id " +
                "WHERE a.cliente_id = ?";

        try (Connection conexion = ConexionDB.getConexion()) {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, idCliente);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Videojuego v = new Videojuego(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("genero"),
                        rs.getString("plataforma"),
                        rs.getInt("copias_disponibles")
                );
                listado.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listado;
    }

    /**
     * Metodo para listar los videojuegos alquilados pasando el nombre de la plataforma por parametro
     * Devuelve una lista de videojuegos
     */
    public List<Videojuego> videojuegosPopularesPlataforma(String plataforma) {
        List<Videojuego> listado = new ArrayList<>();
        String sql = "SELECT v.id, v.titulo, v.genero, v.plataforma, v.copias_disponibles, COUNT(a.videojuego_id) AS num_alquileres " +
                "FROM videojuegos v " +
                "JOIN alquileres a ON v.id = a.videojuego_id " +
                "WHERE v.plataforma LIKE ? " +
                "GROUP BY v.id, v.titulo, v.genero, v.plataforma, v.copias_disponibles " +
                "ORDER BY num_alquileres DESC";

        try (Connection conexion = ConexionDB.getConexion()) {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, plataforma);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Videojuego v = new Videojuego(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("genero"),
                        rs.getString("plataforma"),
                        rs.getInt("copias_disponibles")
                );
                listado.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listado;
    }




}
