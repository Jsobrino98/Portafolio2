import controlador.Controlador;
import Vista.Vista;

/**
 * TIENDA GAMING 1.0
 * Clase principal del programa de gestión de videojuegos
 * @author Jorge Sobrino
 * @version 1.0
 */
public class Aplicacion {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Controlador controlador = new Controlador(vista);
        controlador.iniciar();
    }
}
