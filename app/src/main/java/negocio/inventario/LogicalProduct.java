package negocio.inventario;

import accessdata.inventario.AccessProduct;
import accessdata.utils.UtilsValidateFields;
import entidad.constantes.Constants;
import entidad.entitys.inventario.Product;

import java.text.ParseException;
import java.util.List;

public class LogicalProduct {

    AccessProduct accessProduct = new AccessProduct();

    public String addProduct(Product product) throws ParseException {
        String result = validateData(product);
        if (result.isEmpty() || result.isBlank()) {
            return accessProduct.callSaveProduct(product);
        } else {
            result += Constants.ERROR.concat(Constants.BREAK_LINE);
            return result;
        }
    }

    public String updateProduct(Product product) throws ParseException {
        String result = validateData(product);
        if (result.isEmpty() || result.isBlank()) {
            return accessProduct.callUpdateProduct(product);
        } else {
            result += Constants.ERROR.concat(Constants.BREAK_LINE);
            return result;
        }
    }

    public String deleteProduct(int product) throws ParseException {
        return accessProduct.callDeleteProduct(product);
    }

    public List<Product> findAllProduct() throws ParseException {
        return accessProduct.callFindAllProduct();
    }

    private String validateData(Product product) {
        String resultErrors;

        resultErrors = UtilsValidateFields.validateObligatory(100, "Nombre", product.getName());
        resultErrors += UtilsValidateFields.validateString(150, "Descripcion", product.getDescription());
        resultErrors += UtilsValidateFields.validateString(200, "Ruta Imagen", product.getPathImage());
        resultErrors += UtilsValidateFields.validateString(20, "Codigo producto", product.getCodeProduct());

        return resultErrors;
    }
}
