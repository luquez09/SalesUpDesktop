package negocio.users;

import accessdata.users.AccessCustomers;
import entidad.entitys.usuarios.Customers;
import entidad.entitys.usuarios.Employee;
import negocio.utils.UtilsValidation;

import java.text.ParseException;

public class LogicalCustomers {

    private AccessCustomers accessCustomers = new AccessCustomers();
    String result;

    private String callAddCustomer(Customers customers) throws ParseException {
        result = validateData(customers);
        if (result.isEmpty()) {
            return result = accessCustomers.callSaveCustomer(customers);
        }

        return result;
    }

    private String callUpdateCustomer(Customers customers) {
        result = validateData(customers);
        if (result.isEmpty()) {
            return result = accessCustomers.callUpdateCustomers(customers);
        }

        return result;
    }

    private String callDeleteCustomers(int idCustomer) {
        return accessCustomers.callDeleteCustomer(idCustomer);
    }

    public String validateData(Customers customers) {
        String result;
        result = UtilsValidation.validateString(
                customers.getName(), 100, "Nombre", true);
        result += UtilsValidation.validateString(
                customers.getAddress(), 100, "Direccion", false);
        result += UtilsValidation.validateString(
                customers.getAddress(), 12, "Celular", true);
        result += UtilsValidation.validateString(
                customers.getNumberDocument(), 11, "Identificacion", false);

        return result;
    }
}
