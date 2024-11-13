package controlador;

import DAO.*;
import Modelo.Alquiler;
import Modelo.Cliente;
import Modelo.Videojuego;
import Vista.Vista;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
    Date fecha = Date.valueOf(LocalDate.now());

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
     * Introducir los datos de los clientes y retorno un cliente ya con esos datos
     */

    private Videojuego datosVideojuego(){
        Videojuego v = new Videojuego();

        String titulo = vista.obtenerString("Introduce titulo: ");
        String genero = vista.obtenerString("Introduce género: ");
        String plataforma = vista.obtenerString("Introduce plataforma: ");
        int copias = vista.obtenerEntero("Introduce copias disponibles: ");

        v.setTitulo(titulo);
        v.setGenero(genero);
        v.setPlataforma(plataforma);
        v.setCopias_disponibles(copias);

        return v;
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
                case 1 -> {videojuegosDAO.agregarVideojuego(datosVideojuego());
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
                    videojuegosDAO.actualizarVideojuegoPorId(
                            vista.obtenerEntero("(Actualizar) Introduce ID: "),
                            datosVideojuego());
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
     * Introducir los datos de los clientes y retorno un cliente ya con esos datos
     */

    private Cliente datosClientes(){
        Cliente c = new Cliente();

        String nombre = vista.obtenerString("Introduce nombre: ");
        String email = vista.obtenerString("Introduce email: ");
        Date fecha = Date.valueOf(LocalDate.now());

        c.setNombre(nombre);
        c.setEmail(email);
        c.setFecha_registro(fecha);

        return c;
    }


    /**
     * Método para mostrar y gestionar el submenú de Clientes
     */
    private void gestionarClientes() throws SQLException {
        Date fecha = Date.valueOf(LocalDate.now());
        ClienteDAOImpl clienteDAO = new ClienteDAOImpl();
        boolean volver = false;
        while (!volver) {
            vista.mostrarMenuClientes();
            int opcion = vista.obtenerEntero("Opción");

            switch (opcion) {
                case 1 -> {
                    clienteDAO.agregarCliente(datosClientes());
                }
                case 2 -> {
                    Cliente cliente = clienteDAO.consultarClienteID(vista.obtenerEntero("Introduce ID"));
                    System.out.println(cliente);
                }
                case 3 -> {
                    List<Cliente> clientes = clienteDAO.obtenerTodosClientes();
                    if (clientes.isEmpty()) {
                        vista.mostrarMensaje("No se encontraron clientes.");
                    } else {
                        for (Cliente c : clientes) {
                            System.out.println(c);
                        }
                    }
                }
                case 4 -> {
                    clienteDAO.actualizarClientePorId(
                            vista.obtenerEntero("(Actualizar) Introduce ID: "),
                            datosClientes());
                }
                case 5 -> {
                    clienteDAO.eliminarClientePorId(vista.obtenerEntero("(Eliminar) Introduce ID: "));
                }
                case 0 -> volver = true;
                default -> vista.mostrarMensaje("Opción no válida. Intentar de nuevo.");
            }
        }
    }

    private Alquiler datosAlquiler(){
        Alquiler a = new Alquiler();


        int clienteID = vista.obtenerEntero("Introduce o id do Cliente: ");
        int videojuegoID = vista.obtenerEntero("Introduce o id do Videojuego: ");

        a.setCliente_id(clienteID);
        a.setVideojuego_id(videojuegoID);
        a.setFecha_alquiler(fecha);


        return a;
    }

    /**
     * Método para mostrar y gestionar el submenú de Alquileres
     */
    private void gestionarAlquileres() throws SQLException {
        AlquilerDAO alquilerDAO = new AlquilerDAOImpl();
        boolean volver = false;
        while (!volver) {
            vista.mostrarMenuAlquileres();
            int opcion = vista.obtenerEntero("Opción");

            switch (opcion) {
                case 1 -> {
                    alquilerDAO.registrarAlquiler(datosAlquiler());
                }
                case 2 -> vista.mostrarMensaje("Opción para consultar un alquiler por ID seleccionada.");
                case 3 -> {
                    List<Alquiler> alquileres = alquilerDAO.obterTodosAlquileres();
                    if (alquileres.isEmpty()) {
                        vista.mostrarMensaje("No se encontraron alquileres.");
                    } else {
                        for (Alquiler a : alquileres) {
                            System.out.println(a);
                        }
                    }
                }
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
