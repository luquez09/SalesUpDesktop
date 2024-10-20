package accessdata.utils;

import entidad.constantes.Constants;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class UtilsValidateFields {
    StringBuilder stringBuilder = new StringBuilder();

    public String validateString(int length, String name, String value) {
        if (!value.isEmpty() && value.length() > length) {
            stringBuilder.append("El campo " + name + "No puede superar mas de " + length + "caracter(es)\n");
        }

        return Objects.isNull(stringBuilder) || stringBuilder.isEmpty() ? Constants.EMPTY : stringBuilder.toString();
    }

    public String validateObligatory(int length, String name, String value) {
        if (value.isEmpty() || value.isBlank()) {
            stringBuilder.append("El campo " + name + " es obligatorio.\n");
        } else {
            validateString(length, name, value);
        }

        return Objects.isNull(stringBuilder) || stringBuilder.isEmpty() ? Constants.EMPTY : stringBuilder.toString();
    }
}
