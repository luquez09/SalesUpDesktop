package negocio.inventario;

import accessdata.inventario.AccessStorage;
import entidad.entitys.inventario.Store;

import java.text.ParseException;
import java.util.List;

public class LogicalStorage {

    AccessStorage accessStorage = new AccessStorage();

    public String addStorage(Store store) throws ParseException {
        return accessStorage.callSaveStorage(store);
    }
    public String updateStorage(Store store) {
        return accessStorage.callUpdateStore(store);
    }
    public String deleteStorage(Store store) {
        return accessStorage.callDeleteStore(store);
    }
    public List<Store> findAllStorage() throws ParseException {
        return accessStorage.callFindStore();
    }

}
