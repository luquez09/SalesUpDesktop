package negocio.inventario;

import accessdata.inventario.AccessSupplier;
import entidad.entitys.inventario.Supplier;

import java.text.ParseException;
import java.util.List;

public class LogicalSupplier {

    AccessSupplier accessSupplier = new AccessSupplier();

    private String addSupplier(Supplier supplier) throws ParseException {
        return accessSupplier.callSaveSupplier(supplier);
    }

    private String updateSupplier(Supplier supplier) {
        return accessSupplier.callUpdateSupplier(supplier);
    }

    private String deleteSupplier(Supplier supplier) {
        return accessSupplier.callDeleteSupplier(supplier);
    }

    private List<Supplier> findAllSupplier() throws ParseException {
        return accessSupplier.callFindAllSupplier();
    }
}
