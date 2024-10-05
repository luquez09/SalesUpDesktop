package accessdata.users;

import accessdata.utils.UtilsSql;
import entidad.constantes.ConstantLogger;
import entidad.constantes.Constants;
import entidad.constantes.sqlconstant.SqlConstant;
import entidad.entitys.usuarios.Role;
import lombok.extern.log4j.Log4j2;
import accessdata.configurations.ConfigurationDb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class AccessRole {
    private final String[] NAME_FIELDS = {"idrole", "code", "description"};
    String result = "Error: ";
    String abbreviation = "r";

    public String callSaveRoleEmployee(Role role) throws ParseException {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UtilsSql.queryCreate(SqlConstant.ROLE)
                     + NAME_FIELDS[1].concat(Constants.COMMA)
                     + NAME_FIELDS[2]
                     + ") VALUES (?, ?)")) {
            stmt.setString(1, role.getCode());
            stmt.setString(2, role.getDescription());

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_INSERT, SqlConstant.ROLE);
                result = SqlConstant.SUCCESS_PROCESS;
            } else {
                log.info(ConstantLogger.LOG_ERROR_QUERY_INSERT, Constants.ONE_NEG);
                result = SqlConstant.ERROR_PROCESS;
            }
            stmt.close();
            ConfigurationDb.closeConnection();
        } catch (SQLException e) {
            log.info(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
            result = result.concat(e.getMessage());
        }
        return result;
    }

    public String callUpdateRole(Role role) {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
             UtilsSql.queryUpdate(SqlConstant.ROLE, abbreviation)
                 + NAME_FIELDS[1].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[9].concat(SqlConstant.UPDATE_VALUE)
                 + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.UPDATE_VALUE))) {

            stmt.setString(1, role.getCode());
            stmt.setString(2, role.getDescription());

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_UPDATE, SqlConstant.ROLE);
                result = SqlConstant.SUCCESS_PROCESS;
            } else {
                log.info(ConstantLogger.LOG_ERROR_QUERY_UPDATE, Constants.ONE_NEG);
                result = SqlConstant.ERROR_PROCESS;
            }
            stmt.close();
            ConfigurationDb.closeConnection();

        } catch (SQLException e) {
            log.info(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
            result = result.concat(e.getMessage());
        }
        return result;
    }

    public String callDeleteRole(Role role) {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UtilsSql.queryDetele(SqlConstant.ROLE, abbreviation)
                 + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.UPDATE_VALUE))) {

            stmt.setInt(1, role.getIdRole());

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_DELETE, SqlConstant.ROLE);
                result = SqlConstant.SUCCESS_PROCESS;
            } else {
                log.info(ConstantLogger.LOG_ERROR_QUERY_DELETE, Constants.ONE_NEG);
                result = SqlConstant.ERROR_PROCESS;
            }
            stmt.close();
            ConfigurationDb.closeConnection();

        } catch (SQLException e) {
            log.info(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
            result = result.concat(e.getMessage());
        }

        return result;
    }

    public List<Role> callFindRoles(Role role) throws ParseException {
        List<Role> categories = new ArrayList<>();
        String namesFields = String.join(Constants.COMMA, NAME_FIELDS);

        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
             UtilsSql.queryFindById(namesFields, SqlConstant.ROLE, abbreviation)
                     + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.ROLE))) {

            stmt.setInt(1, role.getIdRole());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Role itemRole = Role.builder()
                        .idRole(rs.getInt(0))
                        .code(rs.getString(1))
                        .description(rs.getString(2))
                        .build();
                categories.add(itemRole);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            log.info(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
        }
        return categories;
    }

}
