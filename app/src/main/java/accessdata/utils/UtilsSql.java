package accessdata.utils;

import entidad.constantes.sqlconstant.SqlConstant;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UtilsSql {

    public static String queryCreate(String table) {
        return String.format(SqlConstant.INSERT_INTO, table);
    }

    public static String queryUpdate(String table, String abbreviation) {
        return String.format(SqlConstant.UPDATE_INTO, table, abbreviation);
    }

    public static String queryDetele(String table, String abbreviation) {
        return String.format(SqlConstant.DELETE_INTO, table, abbreviation);
    }

    public static String queryFindById(String namesFields, String table, String abbreviation) {
        return String.format(SqlConstant.FIND_INTO, namesFields, table, abbreviation);
    }
}
