package negocio.inventario;

import accessdata.inventario.AccessListPrice;
import accessdata.utils.UtilsValidateFields;
import entidad.constantes.Constants;
import entidad.entitys.inventario.ListPrice;
import java.text.ParseException;
import java.util.List;

/**
 * @author ivanl
 */
public class LogicalListPrice {

    AccessListPrice accessListPrice = new AccessListPrice();

    public String addListPrice(ListPrice listPrice) {
        String result = validateData(listPrice);

        if (result.isEmpty()) {
            return accessListPrice.callSaveListPrice(listPrice);
        } else {
            result += Constants.ERROR.concat(Constants.BREAK_LINE);
            return result;
        }
    }

    public String updateListPrice(ListPrice listPrice) {
        String result = validateData(listPrice);

        if (result.isEmpty()) {
            return accessListPrice.callUpdateListPrice(listPrice);
        } else {
            result += Constants.ERROR.concat(Constants.BREAK_LINE);
            return result;
        }
    }

    public String deleteListPrice(int idListPrice) {
        return accessListPrice.callDeleteListPrice(idListPrice);
    }

    public List<ListPrice> findAllListPriceByProduct(int idProduct) throws ParseException {
        return accessListPrice.callFindListPrice(idProduct);
    }

    public List<ListPrice> findAllListPrice() throws ParseException {
        return accessListPrice.callFindAllListPrice();
    }

    public String validateData(ListPrice listPrice) {
        String result;

        result = UtilsValidateFields.validateObligatory(100, "Nombre lsita", listPrice.getName());
        result += UtilsValidateFields.validateString(200, "Descripcion", listPrice.getDescription());

        return result;
    }
}
