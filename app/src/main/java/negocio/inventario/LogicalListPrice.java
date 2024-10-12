package negocio.inventario;

import accessdata.inventario.AccessListPrice;
import entidad.entitys.inventario.ListPrice;

import java.text.ParseException;
import java.util.List;

/**
 * @author ivanl
 */
public class LogicalListPrice {

    AccessListPrice accessListPrice = new AccessListPrice();

    public String addListPrice(ListPrice listPrice) throws ParseException {
        return accessListPrice.callSaveListPrice(listPrice);
    }

    public String updateListPrice(ListPrice listPrice) {
        return accessListPrice.callUpdateListPrice(listPrice);
    }

    public String deleteListPrice(ListPrice listPrice) {
        return accessListPrice.callDeleteListPrice(listPrice);
    }

    public List<ListPrice> findAllListPriceByProduct(int idProduct) throws ParseException {
        return accessListPrice.callFindListPrice(idProduct);
    }
}
