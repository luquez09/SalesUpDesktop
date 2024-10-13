package negocio.utils;

import entidad.constantes.Constants;
import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class UtilsDate {

    public String getDateFormated(String dateOriginal) {
        DateTimeFormatter formatoOriginal = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        LocalDateTime fecha = LocalDateTime.parse(dateOriginal, formatoOriginal);
        DateTimeFormatter nuevoFormato = DateTimeFormatter.ofPattern("EEE dd MMM yyyy hh:mm a");
        return fecha.format(nuevoFormato);
    }


}
