package negocio.users;

import accessdata.users.AccessEmployee;
import entidad.constantes.Constants;
import entidad.entitys.usuarios.Employee;
import entidad.entitys.usuarios.Role;
import negocio.utils.UtilsValidation;
import org.apache.logging.log4j.core.impl.ContextAnchor;

import java.text.ParseException;
import java.util.List;

public class LogicalEmployee {

    private AccessEmployee accessEmployee = new AccessEmployee();
    String result;

    public String callAddEmployee(Employee employee) throws ParseException {
        result = validateData(employee);
        if (result.isEmpty()) {
            return result = accessEmployee.callSaveEmployee(employee);
        }

        return result;
    }

    public String callUpdateEmployee(Employee employee) throws ParseException {
        result = validateData(employee);
        if (result.isEmpty()) {
            return result = accessEmployee.callUpdateEmployee(employee);
        }

        return result;
    }

    public String callDeleteEmployee(int idEmployee) {
        return accessEmployee.callDeleteEmployee(idEmployee);
    }

    public List<Employee> callFindAllEmployess() {
        return accessEmployee.callFindAllEmployee();
    }

    public String validateData(Employee employee) {
        String result;
        result = UtilsValidation.validateString(
                employee.getNameEmployee(), 100, "Nombre", true);
        result += UtilsValidation.validateString(
                employee.getPhoneNumberEmployee(), 12, "Celular", true);
        result += UtilsValidation.validateString(
                employee.getPasswordEmployee(), 10, "Contraseña", true);
        result += UtilsValidation.validateString(
                employee.getUserEmployee(), 20, "Usuario", true);
        result += UtilsValidation.validateString(
                employee.getAddressEmployee(), 200, "Direccion", false);
        result += UtilsValidation.validateString(
                employee.getIdentification(), 11, "Identificacion", true);
        return result.isEmpty() ? Constants.EMPTY : Constants.ERROR.concat(Constants.BREAK_LINE).concat(result);
    }
}
