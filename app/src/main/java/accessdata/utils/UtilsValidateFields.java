package accessdata.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UtilsValidateFields {
    StringBuilder stringBuilder;

    public String validateString(int length, String name, String value) {
        if (!value.isEmpty() && value.length() > length) {
            stringBuilder.append("El campo " + name + "No puede superar mas de " + length + "caracter(es)\n");
        }

        return stringBuilder.toString();
    }

    public String validateObligatory(int length, String name, String value) {
        if (value.isEmpty() || value.isBlank()) {
            stringBuilder.append("El campo " + name + " es obligatorio.\n");
        } else {
            validateString(length, name, value);
        }

        return stringBuilder.toString();
    }
}
