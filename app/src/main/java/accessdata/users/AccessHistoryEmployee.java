package accessdata.users;

import accessdata.utils.UtilsDate;
import accessdata.utils.UtilsSql;
import entidad.constantes.ConstantLogger;
import entidad.constantes.Constants;
import entidad.constantes.sqlconstant.SqlConstant;
import entidad.entitys.inventario.Product;
import entidad.entitys.usuarios.HistoryEmployee;
import lombok.extern.log4j.Log4j2;
import accessdata.configurations.ConfigurationDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2
public class AccessHistoryEmployee {

    private final String[] NAME_FIELDS = {"idhistor", "date_init", "date_finish", "description",
                                          "salary", "fk_idemployee"};
    String result = "Error: ";
    String abbreviation = "he";

    public String callSaveHistoryEmployee(HistoryEmployee employee) throws ParseException {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UtilsSql.queryCreate(SqlConstant.HISTORY)
                     + NAME_FIELDS[1].concat(Constants.COMMA)
                     + NAME_FIELDS[2].concat(Constants.COMMA)
                     + NAME_FIELDS[3].concat(Constants.COMMA)
                     + NAME_FIELDS[5].concat(Constants.COMMA)
                     + NAME_FIELDS[6]
                     + ") VALUES (?, ?, ?, ?, ?, ?)")) {

            stmt.setTimestamp(1, Timestamp.valueOf(UtilsDate.getDateNow()));
            stmt.setTimestamp(2, Timestamp.valueOf(UtilsDate.getDateNow()));
            stmt.setString(3, employee.getDescription());
            stmt.setDouble(4, employee.getSalary());
            stmt.setDouble(5, employee.getFkEmployee());

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_INSERT, SqlConstant.HISTORY);
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

    public String callUpdateHistoryEmployee(HistoryEmployee employee) {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
             UtilsSql.queryUpdate(SqlConstant.HISTORY, abbreviation)
                 + NAME_FIELDS[1].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[2].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[3].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[4].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[5]
                 + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.UPDATE_VALUE))) {

            stmt.setTimestamp(1, Timestamp.valueOf(UtilsDate.getDateNow()));
            stmt.setTimestamp(2, Timestamp.valueOf(UtilsDate.getDateNow()));
            stmt.setString(3, employee.getDescription());
            stmt.setDouble(4, employee.getSalary());
            stmt.setDouble(5, employee.getFkEmployee());

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_UPDATE, SqlConstant.HISTORY);
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

    public String callDeleteHistoryEmployee(HistoryEmployee employee) {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UtilsSql.queryDetele(SqlConstant.HISTORY, abbreviation)
                 + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.UPDATE_VALUE))) {

            stmt.setInt(1, employee.getIdHistoryEmployee());

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_DELETE, SqlConstant.HISTORY);
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

    public HistoryEmployee callFindHistoryEmployee(HistoryEmployee product) throws ParseException {
        HistoryEmployee listHistoryEmployee = null;
        String namesFields = String.join(Constants.COMMA, NAME_FIELDS);

        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(UtilsSql.queryFindById(namesFields, SqlConstant.HISTORY, abbreviation)
                             + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.UPDATE_VALUE))) {

            stmt.setInt(1, product.getIdHistoryEmployee());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                listHistoryEmployee = HistoryEmployee.builder()
                        .idHistoryEmployee(rs.getInt(1))
                        .dateInit(rs.getObject(NAME_FIELDS[1], LocalDateTime.class))
                        .dateFinish(rs.getObject(NAME_FIELDS[2], LocalDateTime.class))
                        .description(rs.getString(2))
                        .salary(rs.getDouble(3))
                        .fkEmployee(rs.getInt(4))
                        .build();
            }

            rs.close();
            stmt.close();
            ConfigurationDb.closeConnection();

            if (Objects.isNull(listHistoryEmployee)) {
                log.error(ConstantLogger.LOG_ERROR_FIND_NOT_FOUND);
            } else {
                log.error(ConstantLogger.LOG_SUCCESS_QUERY_FIND_ID, listHistoryEmployee.getIdHistoryEmployee());
            }
        } catch (SQLException e) {
            log.info(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
            result = result.concat(e.getMessage());
        }

        return listHistoryEmployee;
    }

    public List<HistoryEmployee> callFindProduct(HistoryEmployee employee, int page, int size) throws ParseException {
        List<HistoryEmployee> historyEmployeeList = new ArrayList<>();
        String namesFields = String.join(Constants.COMMA, NAME_FIELDS);

        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
             UtilsSql.queryFindById(namesFields, SqlConstant.HISTORY, abbreviation)
                 + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.UPDATE_VALUE)
                 + " LIMIT ? OFFSET ?")) {

            stmt.setInt(1, employee.getIdHistoryEmployee());
            stmt.setInt(2, size);
            stmt.setInt(3, (page - 1) * size);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HistoryEmployee historyEmployee = HistoryEmployee.builder()
                        .idHistoryEmployee(rs.getInt(1))
                        .dateInit(rs.getObject(NAME_FIELDS[1], LocalDateTime.class))
                        .dateFinish(rs.getObject(NAME_FIELDS[2], LocalDateTime.class))
                        .description(rs.getString(2))
                        .salary(rs.getDouble(3))
                        .fkEmployee(rs.getInt(4))
                        .build();
                historyEmployeeList.add(historyEmployee);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            log.info(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
        }
        return historyEmployeeList;
    }
}
