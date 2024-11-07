package Vista;

import java.util.Scanner;

/**
 * Clase para gestionar la vista de la aplicación
 * y los mensajes de entrada/salida usuario
 *
 * @author Jorge Sobrino
 * @version 1.0
 */
public class Vista {
    /**
     * Scanner para gestionar la interacción
     */
    private final Scanner scanner;

    /**
     * Constructor de la clase Vista.
     */
    public Vista() {
        scanner = new Scanner(System.in);
    }

    /**
     * Método para mostrar el menú principal de la aplicación
     */
    public void mostrarMenuPrincipal() {
        mostrarMensaje("\n========= Tienda de Alquiler de Videojuegos =========");
        mostrarMensaje("==================  MENÚ PRINCIPAL   ================");

        mostrarMensaje("Seleccione una opción:");
        mostrarMensaje("1. Gestionar Videojuegos");
        mostrarMensaje("2. Gestionar Clientes");
        mostrarMensaje("3. Gestionar Alquileres");
        mostrarMensaje("4. Informes y Consultas");
        mostrarMensaje("0. Salir");
    }

    /**
     * Método para mostrar el menú de gestión de videojuegos
     */
    public void mostrarMenuVideojuegos() {
        limpiarConsola();
        mostrarMensaje("\n========= Gestión de Videojuegos =========");
        mostrarMensaje("Seleccione una opción:");
        mostrarMensaje("1. Agregar un nuevo videojuego");
        mostrarMensaje("2. Consultar un videojuego por ID");
        mostrarMensaje("3. Listar todos los videojuegos");
        mostrarMensaje("4. Actualizar un videojuego");
        mostrarMensaje("5. Eliminar un videojuego");
        mostrarMensaje("0. Volver al menú principal");
    }

    /**
     * Método para mostrar el menú de gestión de Cliente
     */
    public void mostrarMenuClientes() {
        mostrarMensaje("\n========= Gestión de Clientes =========");
        mostrarMensaje("Seleccione una opción:");
        mostrarMensaje("1. Registrar un nuevo cliente");
        mostrarMensaje("2. Consultar un cliente por ID");
        mostrarMensaje("3. Listar todos los clientes");
        mostrarMensaje("4. Actualizar un cliente");
        mostrarMensaje("5. Eliminar un cliente");
        mostrarMensaje("0. Volver al menú principal");
    }

    /**
     * Método para mostrar el menú de gestión de Alquileres
     */
    public void mostrarMenuAlquileres() {
        mostrarMensaje("\n========= Gestión de Alquileres =========");
        mostrarMensaje("Seleccione una opción:");
        mostrarMensaje("1. Registrar un nuevo alquiler");
        mostrarMensaje("2. Consultar un alquiler por ID");
        mostrarMensaje("3. Listar todos los alquileres");
        mostrarMensaje("4. Actualizar un alquiler (devolución)");
        mostrarMensaje("5. Eliminar un alquiler");
        mostrarMensaje("0. Volver al menú principal");
    }

    /**
     * Método para mostrar el menú de gestión de Informes
     */
    public void mostrarMenuInformes() {
        mostrarMensaje("\n========= Informes y Consultas =========");
        mostrarMensaje("Seleccione una opción:");
        mostrarMensaje("1. Listar videojuegos más alquilados");
        mostrarMensaje("2. Buscar videojuegos por título o plataforma");
        mostrarMensaje("3. Ver clientes con más alquileres activos");
        mostrarMensaje("4. Listar videojuegos alquilados actualmente por un cliente");
        mostrarMensaje("5. Listar Videojuegos Más Populares por Plataforma");
        mostrarMensaje("6. Listado de Videojuegos Disponibles para Alquiler");
        mostrarMensaje("7. Historial de Alquileres de un Videojuego Específico");
        mostrarMensaje("0. Volver al menú principal");
    }

    /* METODOS AUXILIARES */

    /**
     * Método para mostrar un mensaje en la consola y salto de carro
     *
     * @param mensaje cadena con el texto a mostrar
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Método para mostrar un mensaje en la consola y solicitar una cadena de texto
     *
     * @param mensaje cadena con el texto a mostrar
     * @return una cadena (String) con los datos introducidos por la consola hasta pulsar ENTER
     */
    public String obtenerString(String mensaje) {
        System.out.print(mensaje + ": ");
        return scanner.nextLine();
    }

    /**
     * Método para mostrar un mensaje en la consola y solicitar un entero
     *
     * @param mensaje cadena con el texto a mostrar
     * @return un número entero (int) con los datos introducidos por la consola hasta pulsar ENTER
     */
    public int obtenerEntero(String mensaje) {
        System.out.print(mensaje + ": ");
        while (!scanner.hasNextInt()) {
            mostrarMensaje("Por favor, ingrese un número válido.");
            System.out.print(mensaje + ": ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // limpiar el buffer
        return valor;
    }

    /**
     * Método para mostrar un mensaje en la consola y solicitar un doble
     *
     * @param mensaje cadena con el texto a mostrar
     * @return un número decimal (double) con los datos introducidos por la consola hasta pulsar ENTER
     */
    public double obtenerDoble(String mensaje) {
        System.out.print(mensaje + ": ");
        while (!scanner.hasNextDouble()) {
            mostrarMensaje("Por favor, ingrese un número decimal válido.");
            System.out.print(mensaje + ": ");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine(); // limpiar el buffer no necesario en doble pero no está demás.
        return valor;
    }


    public void limpiarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


}
