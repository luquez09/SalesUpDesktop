package negocio.inventario;

import accessdata.inventario.AccessProduct;
import entidad.entitys.inventario.Product;

import java.text.ParseException;
import java.util.List;

public class LogicalProduct {

    AccessProduct accessProduct = new AccessProduct();

    public String addProduct(Product product) throws ParseException {
        return accessProduct.callSaveProduct(product);
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


}
