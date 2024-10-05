package accessdata.utils;

import entidad.constantes.ConstantDate;
import lombok.experimental.UtilityClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@UtilityClass
public class UtilsDate {


    public LocalDateTime getDateNow() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Bogota"));
        return now.toLocalDateTime();
    }

}
