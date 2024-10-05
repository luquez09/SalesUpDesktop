package negocio.inventario;

import accessdata.inventario.AccessListPrice;
import entidad.entitys.inventario.ListPrice;

import java.text.ParseException;
import java.util.List;

public class LogicalListPrice {

    AccessListPrice accessListPrice = new AccessListPrice();

    private String addListPrice(ListPrice listPrice) throws ParseException {
        return accessListPrice.callSaveListPrice(listPrice);
    }

    private String updateListPrice(ListPrice listPrice) {
        return accessListPrice.callUpdateListPrice(listPrice);
    }

    private String deleteListPrice(ListPrice listPrice) {
        return accessListPrice.callDeleteListPrice(listPrice);
    }

    private List<ListPrice> findAllListPrice() throws ParseException {
        return accessListPrice.callFindListPrice();
    }
}
