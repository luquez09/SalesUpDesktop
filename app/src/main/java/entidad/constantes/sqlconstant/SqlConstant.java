package entidad.constantes.sqlconstant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constants to create sql statements.
 *
 * @author ivanluquez
 * @since 21/09/2024
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SqlConstant {

    public static final String INSERT_INTO = "INSERT INTO \"schComercio\".%s (";
    public static final String UPDATE_INTO = "UPDATE \"schComercio\".%s %s SET ";
    public static final String DELETE_INTO = "DELETE FROM \"schComercio\".%s %s";
    public static final String FIND_INTO = "SELECT %s FROM \"schComercio\".%s %s";
    public static final String FIND_ALL = "SELECT %s FROM \"schComercio\".%s";
    public static final String FIND_ALL_JOIN = "SELECT %s FROM \"schComercio\".%s JOIN \"schComercio\".%s ON %s";

    public static final String VALUE = "= ?";
    public static final String WHERE = " WHERE %s.%s %s";

    public static final String BLANC = "";

    //Constant Inventario
    public static final String CATEGORY = "category";
    public static final String SUPPLIER = "supplier";
    public static final String LIST_PRICE = "listprice";
    public static final String PRODUCT = "product";
    public static final String STORAGE = "storage";

    //Constants Users
    public static final String ROLE = "role_employee";
    public static final String EMPLOYEE = "employee";
    public static final String HISTORY = "history_employe";
    public static final String CUSTOMERS = "customers";


    //Constants general
    public static final String SUCCESS_PROCESS = "Accion procesada, con exito.";
    public static final String ERROR_PROCESS = "Error al realizar la operacion.\n";

    public static final String SUCCESS_DELETE = "Registro Eliminado, con exito.";
    public static final String ERROR_DELETE = "Error, no fue posible eliminar.\n";

    public static final String SUCCESS_UPDATE = "Registro Actualizado, con exito.";
    public static final String ERROR_UPDATE = "Error no fue posible actualizar.\n";

    public static final String CLOSED_CONNECTION = "La conexion con la base de datos no fue posible.";

}
