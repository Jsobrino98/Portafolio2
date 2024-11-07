package controlador;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Fechas {
    public static void main(String[] args) {

        System.out.println(LocalDate.now());
    //Obter a fecha actual
        Date fecha = Date.valueOf(LocalDate.now());
    //Usar o conversor que creamos abaixo
        String fechaNacimiento = "25/11/1998";
        System.out.println(conversorFecha(fechaNacimiento));
        Date fnSQL = Date.valueOf(conversorFecha(fechaNacimiento));
        System.out.println(fnSQL);



        //OUTRA FORMA DE FACELO (Pasar 3 Int)
        LocalDate ld = LocalDate.of(1998, 4, 2);
    }

    //Pasar dd/mm/aaaa  a AAAA-MM-DD
    public static LocalDate conversorFecha(String fecha) {
        final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate resultado;
        try {
            resultado = LocalDate.parse(fecha, FORMATO);
        }catch (DateTimeParseException d){
            return null;
        }
        return resultado;
    }
}
