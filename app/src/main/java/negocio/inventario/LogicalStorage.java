package negocio.inventario;

import accessdata.inventario.AccessStorage;
import entidad.constantes.Constants;
import entidad.entitys.inventario.Store;
import negocio.utils.UtilsValidation;

import java.text.ParseException;
import java.util.List;

public class LogicalStorage {

    AccessStorage accessStorage = new AccessStorage();

    public String addStorage(Store store) throws ParseException {
        String result = validateField(store);
        if (result.isEmpty()) {
            return accessStorage.callSaveStorage(store);
        } else {
            return result;
        }
    }
    public String updateStorage(Store store) {
        String result = validateField(store);
        if (result.isEmpty()) {
            return accessStorage.callUpdateStore(store);
        } else {
            return result;
        }
    }
    public String deleteStorage(Store store) {
        return accessStorage.callDeleteStore(store);
    }
    public List<Store> findAllStorage() throws ParseException {
        return accessStorage.callFindStore();
    }

    private String validateField(Store store) {
        String result;

        result = UtilsValidation.validateString(store.getName(), 100, "Nombre", true);
        result = result + UtilsValidation.validateString(store.getDescription(), 100, "Descripcion", false);
        result = result + UtilsValidation.validateString(store.getUbication(), 100, "Ubicacion", false);

        return result;
    }

}
