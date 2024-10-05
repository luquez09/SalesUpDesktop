package negocio.inventario;

import accessdata.inventario.AccessCategory;
import entidad.entitys.inventario.Category;

import java.text.ParseException;
import java.util.List;

/**
 * @author ivanl
 */
public class LogicalCategory {
    private AccessCategory accessCategory = new AccessCategory();

    private String validateValueField() {
        return "";
    }

    public String addCategory(Category category) throws ParseException {
        return accessCategory.callSaveCategory(category);
    }

    public List<Category> categoryList() throws ParseException {
        return accessCategory.callAllCategory();
    }

    public String updateCategory(Category category) {
        return accessCategory.callUpdateCategory(category);
    }

    public String deleteCategory(Category category) {
        return accessCategory.callDeleteCategory(category);
    }
}
