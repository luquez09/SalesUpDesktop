package negocio.inventario;

import accessdata.inventario.AccessSupplier;
import entidad.constantes.Constants;
import entidad.entitys.inventario.Supplier;
import negocio.utils.UtilsValidation;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

public class LogicalSupplier {

    AccessSupplier accessSupplier = new AccessSupplier();

    public String addSupplier(Supplier supplier) throws ParseException {
        return accessSupplier.callSaveSupplier(supplier);
    }

    public String updateSupplier(Supplier supplier) {
        return accessSupplier.callUpdateSupplier(supplier);
    }

    public String deleteSupplier(Supplier supplier) {
        return accessSupplier.callDeleteSupplier(supplier);
    }

    public List<Supplier> findAllSupplier() throws ParseException {
        return accessSupplier.callFindAllSupplier();
    }

    public String validateSupplier(Supplier supplier) {
        String result;

        result = UtilsValidation.validateString(supplier.getName(), 100, "Nombre", true);
        result = result.concat(UtilsValidation.validateString(supplier.getPhoneNumber1(), 10, "Numero celular 1", true));
        result = result.concat(UtilsValidation.validateString(supplier.getName_social(), 200, "Nombre social", true));

        return result;
    }
}
