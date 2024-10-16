package accessdata.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UtilsValidateCodeError {


    public String validateMessageError(String errorCode) {
        String messageCode = "Error no identificado: " + errorCode;

        switch (errorCode) {
            case "23503" -> messageCode = "Error no se puede eliminar o actualizar debido a violacion de llave forania\n";
            case "42P01" -> messageCode = "Intento de seleccionar o actualizar una tabla que no existe.\n";
            case "42601" -> messageCode = "Error de sintaxis en la sentencia.\n";
            case "42501" -> messageCode = "No se tienen los permisos necesarios para realizar la operación SELECT.\n";
            case "22012" -> messageCode = "Intento de dividir por cero en una consulta.\n";
            case "42P02" -> messageCode = "Parametros faltantes para la sentencia Update.\n";
            case "22001" -> messageCode = "El valor a actualizar es demasiado largo para la columna objetivo.\n";
            case "42P18" -> messageCode = "Tipo de dato indeterminado en la definición de la tabla.\n";
            case "23505" -> messageCode = "Violación de restricción única al intentar eliminar un registro referenciado.\n";
        }

        return messageCode;
    }
}
