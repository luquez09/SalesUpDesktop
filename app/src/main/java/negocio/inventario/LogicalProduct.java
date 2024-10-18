package negocio.inventario;

import accessdata.inventario.AccessProduct;
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
            return result;
        }
    }

    public String updateProduct(Product product) throws ParseException {
        return accessProduct.callUpdateProduct(product);
    }

    public String deleteProduct(Product product) throws ParseException {
        return accessProduct.callDeleteProduct(product);
    }

    public List<Product> findAllProduct() throws ParseException {
        return accessProduct.callFindAllProduct();
    }

    private String validateData(Product product) {
        StringBuilder errors = new StringBuilder();

        validateString(100, "Nombre", errors, product.getName());
        validateString(150, "Descripcion", errors, product.getDescription());
        validateString(200, "Ruta Imagen", errors, product.getPathImage());
        validateString(20, "Codigo producto", errors, product.getCodeProduct());
        validateObligatory("Nombre", errors, product.getName());

        return errors.toString();
    }

    private void validateString(int length, String name, StringBuilder stringBuilder, String value) {
        if (value.length() > length) {
            stringBuilder.append("El campo " + name + "No puede superar mas de " + length + "caracter(es)\n");
        }
    }

    private void validateObligatory(String name, StringBuilder stringBuilder, String value) {
        if (value.isEmpty() || value.isBlank()) {
            stringBuilder.append("El campo " + name + " es obligatorio.\n");
        }
    }
}
