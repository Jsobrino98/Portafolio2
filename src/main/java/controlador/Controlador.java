package controlador;

import DAO.VideojuegosDAO;
import DAO.VideojuegosDAOImpl;
import Modelo.Videojuego;
import Vista.Vista;

import java.sql.SQLException;
import java.util.List;

/**
 * Clase para gestionar el controlador de la aplicación
 * y las decisiones para manipular el DAO y los modelos.
 *
 * @author Jorge Sobrino
 * @version 1.0
 */
public class Controlador {
    /**
     * Scanner para gestionar la interacción
     */
    private final Vista vista;
    /**
     * Variable booleana a modo de bandera para controlar la salida/fin del programa.
     */
    private boolean salir;

    /**
     * Constructor del controlador que recibe una Vista
     *
     * @param vista la vista que va a gestionar el controlador para mostrar e interacturar con los usuarios
     */

    public Controlador(Vista vista) {
        this.vista = vista;
        this.salir = false;
    }

    /**
     * Método para iniciar el controlador mostrando el menú principal a través de la vista
     * y gestionar el menú principal
     */
    public void iniciar() throws SQLException {
        while (!salir) {
            vista.mostrarMenuPrincipal();
            int opcion = vista.obtenerEntero("Opción");

            switch (opcion) {
                case 1 -> gestionarVideojuegos();
                case 2 -> gestionarClientes();
                case 3 -> gestionarAlquileres();
                case 4 -> mostrarInformes();
                case 0 -> {
                    salir = true;
                    vista.mostrarMensaje("Saliendo de la aplicación...");
                }
                default -> vista.mostrarMensaje("Opción no válida. Intentar de nuevo.");
            }
        }
    }

    /**
     * Método para mostrar y gestionar el submenú de Videojuegos
     */
    private void gestionarVideojuegos() throws SQLException {
        VideojuegosDAOImpl videojuegosDAO = new VideojuegosDAOImpl();
        boolean volver = false;
        while (!volver) {
            vista.mostrarMenuVideojuegos();
            int opcion = vista.obtenerEntero("Opción");

            switch (opcion) {
                case 1 -> {
                    videojuegosDAO.agregarVideojuego(new Videojuego(
                            vista.obtenerEntero("Introduce o Id: "),
                            vista.obtenerString("Introduce o titulo: "),
                            vista.obtenerString("Introduce o genero:"),
                            vista.obtenerString("Introduce a plataforma:"),
                            vista.obtenerEntero("Introduce as copias disponibles: ")
                    ));
                }
                case 2 -> {
                    Videojuego v = videojuegosDAO.consultarVideojuegoID(vista.obtenerEntero("Introduce ID"));
                    System.out.println(v);
                }
                case 3 -> {
                    List<Videojuego> videojuegos = videojuegosDAO.obtenerTodosVideojuegos();
                    if (videojuegos.isEmpty()) {
                        vista.mostrarMensaje("No se encontraron videojuegos.");
                    } else {
                        for (Videojuego v : videojuegos) {
                            System.out.println(v);
                        }
                    }
                }
                case 4 -> {
                    videojuegosDAO.actualizarVideojuegoPorId(new Videojuego(
                            vista.obtenerEntero("(Actualizar) Introduce o ID: "),
                            vista.obtenerString("Introduce el nuevo titulo: "),
                            vista.obtenerString("Introduce el nuevo genero: "),
                            vista.obtenerString("Introduce la nueva plataforma: "),
                            vista.obtenerEntero("Introduce las copias disponibles: ")
                    ));
                }
                case 5 -> {
                    videojuegosDAO.eliminarVideojuegoPorId(vista.obtenerEntero("(Eliminar) Introduce ID: "));
                }
                case 0 -> volver = true;
                default -> vista.mostrarMensaje("Opción no válida. Intentar de nuevo.");
            }
        }
    }

    /**
     * Método para mostrar y gestionar el submenú de Clientes
     */
    private void gestionarClientes() {
        boolean volver = false;
        while (!volver) {
            vista.mostrarMenuClientes();
            int opcion = vista.obtenerEntero("Opción");

            switch (opcion) {
                case 1 -> vista.mostrarMensaje("Opción para registrar un nuevo cliente seleccionada.");
                case 2 -> vista.mostrarMensaje("Opción para consultar un cliente por ID seleccionada.");
                case 3 -> vista.mostrarMensaje("Opción para listar todos los clientes seleccionada.");
                case 4 -> vista.mostrarMensaje("Opción para actualizar un cliente seleccionada.");
                case 5 -> vista.mostrarMensaje("Opción para eliminar un cliente seleccionada.");
                case 0 -> volver = true;
                default -> vista.mostrarMensaje("Opción no válida. Intentar de nuevo.");
            }
        }
    }

    /**
     * Método para mostrar y gestionar el submenú de Alquileres
     */
    private void gestionarAlquileres() {
        boolean volver = false;
        while (!volver) {
            vista.mostrarMenuAlquileres();
            int opcion = vista.obtenerEntero("Opción");

            switch (opcion) {
                case 1 -> vista.mostrarMensaje("Opción para registrar un nuevo alquiler seleccionada.");
                case 2 -> vista.mostrarMensaje("Opción para consultar un alquiler por ID seleccionada.");
                case 3 -> vista.mostrarMensaje("Opción para listar todos los alquileres seleccionada.");
                case 4 -> vista.mostrarMensaje("Opción para actualizar un alquiler seleccionada.");
                case 5 -> vista.mostrarMensaje("Opción para eliminar un alquiler seleccionada.");
                case 0 -> volver = true;
                default -> vista.mostrarMensaje("Opción no válida. Intentar de nuevo.");
            }
        }
    }

    /**
     * Método para mostrar y gestionar el submenú de Informes
     */
    private void mostrarInformes() {
        boolean volver = false;
        while (!volver) {
            vista.mostrarMenuInformes();
            int opcion = vista.obtenerEntero("Opción");
            switch (opcion) {
                case 1 -> vista.mostrarMensaje("Opción para listar videojuegos más alquilados seleccionada.");
                case 2 -> vista.mostrarMensaje("Opción para buscar videojuegos por título o plataforma seleccionada.");
                case 3 -> vista.mostrarMensaje("Opción para ver clientes con más alquileres activos seleccionada.");
                case 4 ->
                        vista.mostrarMensaje("Opción para listar videojuegos alquilados por un cliente seleccionada.");
                case 5 -> vista.mostrarMensaje("Opción para Listar Videojuegos Más Populares por Plataforma");
                case 6 -> vista.mostrarMensaje("Opción para Listado de Videojuegos Disponibles para Alquiler");
                case 7 -> vista.mostrarMensaje("Opción para Historial de Alquileres de un Videojuego Específico");
                case 0 -> volver = true;
                default -> vista.mostrarMensaje("Opción no válida. Intentar de nuevo.");
            }
        }
    }
}
