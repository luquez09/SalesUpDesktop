package accessdata.users;

import accessdata.utils.UtilsSql;
import accessdata.utils.UtilsValidateCodeError;
import entidad.constantes.ConstantLogger;
import entidad.constantes.Constants;
import entidad.constantes.sqlconstant.SqlConstant;
import entidad.entitys.usuarios.Role;
import lombok.extern.log4j.Log4j2;
import accessdata.configurations.ConfigurationDb;
import javax.swing.*;
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
            }
            stmt.close();
            ConfigurationDb.closeConnection();
        } catch (SQLException e) {
            log.error(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
            log.error(ConstantLogger.LOG_ERROR_STATE_SQL, e.getSQLState());

            result = result.concat(UtilsValidateCodeError.validateMessageError(e.getSQLState()));
            result = result.concat(e.getMessage());
        }
        return result;
    }

    public String callUpdateRole(Role role) {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
             UtilsSql.queryUpdate(SqlConstant.ROLE, abbreviation)
                 + NAME_FIELDS[1].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[2].concat(SqlConstant.VALUE)
                 + String.format(SqlConstant.WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.VALUE))) {

            stmt.setString(1, role.getCode());
            stmt.setString(2, role.getDescription());
            stmt.setInt(3, role.getIdRole());

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_UPDATE, SqlConstant.ROLE);
                result = SqlConstant.SUCCESS_PROCESS;
            }
            stmt.close();
            ConfigurationDb.closeConnection();

        } catch (SQLException e) {
            log.error(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
            log.error(ConstantLogger.LOG_ERROR_STATE_SQL, e.getSQLState());
            result = result.concat(UtilsValidateCodeError.validateMessageError(e.getSQLState()));
            result = result.concat(e.getMessage());
        }
        return result;
    }

    public String callDeleteRole(int role) {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UtilsSql.queryDetele(SqlConstant.ROLE, abbreviation)
                 + String.format(SqlConstant.WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.VALUE))) {

            stmt.setInt(1, role);

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_DELETE, SqlConstant.ROLE);
                result = SqlConstant.SUCCESS_PROCESS;
            }
            stmt.close();
            ConfigurationDb.closeConnection();

        } catch (SQLException e) {
            log.error(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
            log.error(ConstantLogger.LOG_ERROR_STATE_SQL, e.getSQLState());
            result = result.concat(UtilsValidateCodeError.validateMessageError(e.getSQLState()));
            result = result.concat(e.getMessage());
        }

        return result;
    }

    public List<Role> callFindAllRoles() throws ParseException {
        List<Role> categories = new ArrayList<>();
        String namesFields = String.join(Constants.COMMA, NAME_FIELDS);

        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
             UtilsSql.queryFindAll(namesFields, SqlConstant.ROLE))) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Role itemRole = Role.builder()
                        .idRole(rs.getInt(1))
                        .code(rs.getString(2))
                        .description(rs.getString(3))
                        .build();
                categories.add(itemRole);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            log.error(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
            log.error(ConstantLogger.LOG_ERROR_STATE_SQL, e.getSQLState());
            result = result.concat(UtilsValidateCodeError.validateMessageError(e.getSQLState()));
            result = result.concat(e.getMessage());
            JOptionPane.showMessageDialog(null, result);
        }
        return categories;
    }
}
