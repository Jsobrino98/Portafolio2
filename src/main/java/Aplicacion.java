import controlador.Controlador;
import Vista.Vista;

import java.sql.SQLException;

/**
 * TIENDA GAMING 1.0
 * Clase principal del programa de gestión de videojuegos
 * @author Jorge Sobrino
 * @version 1.0
 */
public class Aplicacion {
    public static void main(String[] args) {
        try {


            Vista vista = new Vista();
            Controlador controlador = new Controlador(vista);
            controlador.iniciar();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
