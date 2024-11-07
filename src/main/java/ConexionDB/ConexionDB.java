package ConexionDB;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase para gestionar la conexión con la Base de Datos
 *
 * @author Jorge Sobrino
 * @version 1.0
 */
public class ConexionDB {
    private static Connection conexion = null;

    public static Connection getConexion() throws SQLException {

        if (conexion == null || conexion.isClosed()) {
            //abrir el fichero de configuración
            Properties properties = new Properties();

            try (InputStream is = ConexionDB.class.getClassLoader().getResourceAsStream("db.ini")) {

                properties.load(is); //Cargar en properties todo el contenido del fichero

            } catch (IOException e) {
                e.printStackTrace();
            }
            //crear la conexion
            String url = properties.getProperty("db.url");
            String usuario = properties.getProperty("db.usuario");
            String clave = properties.getProperty("db.clave");
            conexion = DriverManager.getConnection(url, usuario, clave);
        }
        return conexion;
    }
}
