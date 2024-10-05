package entidad.constantes;

public class ConstantLogger {

    public static final String LOG_ERROR_CONNECTION = "Error al conectar a la base de datos.";
    public static final String LOG_SUCCESS_QUERY_INSERT = "Registro con exito.";
    public static final String LOG_ERROR_QUERY_INSERT = "Error, no fue posible guardar.";

    public static final String LOG_SUCCESS_QUERY_UPDATE = "Registro {}, actualizado con exito";
    public static final String LOG_ERROR_QUERY_UPDATE = "Error al actualizar, Dto: {}";

    public static final String LOG_SUCCESS_QUERY_DELETE = "Registro {}, borrado con exito";
    public static final String LOG_ERROR_QUERY_DELETE = "Error al eliminar, error message: {}";

    public static final String LOG_SUCCESS_QUERY_FIND_ID = "Registro {}, Consultado con exito.";
    public static final String LOG_ERROR_QUERY_FIND_ID = "Error, no fue posible consultar el registro: {}";
    public static final String LOG_ERROR_FIND_NOT_FOUND = "Error, no se encontro registro.";

    public static final String LOG_ERROR_SQL = "No fue posible hacer la transaccion, Ocurrio un error causa: {}";
    public static final String LOG_ERROR_EXECUTE_SQL = "Error, al realizar la transaccion. Causa: {}";

}
