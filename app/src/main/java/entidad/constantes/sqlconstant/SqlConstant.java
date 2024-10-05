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

    public static final String INSERT_INTO = "INSERT INTO \"schComercio\".%s(";
    public static final String UPDATE_INTO = "UPDATE \"schComercio\".%s %s SET ";
    public static final String DELETE_INTO = "DELETE FROM \"schComercio\".%s %s";
    public static final String FIND_INTO = "SELECT %s FROM \"schComercio\".%s %s";

    public static final String UPDATE_VALUE = "= ?";
    public static final String UPDATE_WHERE = " WHERE %s.%s %s";

    public static final String BLANCK = "";

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
    public static final String SUCCESS_PROCESS = "SUCCESS_PROCESS";
    public static final String ERROR_PROCESS = "ERROR_PROCESS";

}
