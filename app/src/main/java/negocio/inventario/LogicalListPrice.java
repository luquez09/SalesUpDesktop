package negocio.inventario;

import accessdata.inventario.AccessListPrice;
import entidad.constantes.Constants;
import entidad.entitys.inventario.ListPrice;

import java.awt.*;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

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

    public List<ListPrice> findAllListPrice() throws ParseException {
        return accessListPrice.callFindAllListPrice();
    }

    public String validateData(ListPrice listPrice) {
        String result = Constants.EMPTY;

        if (listPrice.getPrice() == null) {
            result = result + "Precio es requerido\n";
        }
        if (!(listPrice.getPrice() == null) && listPrice.getPrice() < 100) {
            result = result + "Precio no valido\n";
        }
        if (listPrice.getName().isEmpty()) {
            result = result + "Nombre es requerido\n";
        }

        if (result.equals(Constants.EMPTY)) {
            return "201";
        } else {
            return "Error: \n" + result;
        }
    }
}
