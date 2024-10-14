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
        if (!Objects.isNull(listPrice)) {
            result = "Errores: \n";
            result = listPrice.getPrice() == null ? result + "Precio es requerido\n" : Constants.EMPTY;
            result = !(listPrice.getPrice() == null) && listPrice.getPrice() < 100 ?
                    result + "Precio no valido\n" : result + Constants.EMPTY;
            result = listPrice.getName().isEmpty() ? result + "Nombre es requerido\n" : result + Constants.EMPTY;
        }
        return result;
    }
}
