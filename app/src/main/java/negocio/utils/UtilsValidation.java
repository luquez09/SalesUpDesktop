package negocio.utils;

import entidad.constantes.Constants;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UtilsValidation {

    public String validateString(String value, int length, String nameField, boolean obligatory) {
        String errors;
        if (obligatory && value.isEmpty()) {
            errors = "El campo " + nameField + " obligatorio\n";
            return errors;
        }

        if (!value.isEmpty() && value.length() > length) {
            return "El campo " + nameField + " supera la longitud permitida de " + length +"\n";
        }

        return Constants.EMPTY;
    }

    public String validateNumeric(int value, int length, String nameField, boolean obligatory) {
        String errors;

        if (obligatory && value < 0) {
            errors = "El campo " + nameField + " obligatorio\n";
            return errors;
        }

        if (value > length) {
            return "El campo " + nameField + " supera la longitud permitida de " + length + "\n";
        }

        return Constants.EMPTY;
    }

}
