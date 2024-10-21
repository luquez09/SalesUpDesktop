package accessdata.users;

import accessdata.utils.UtilsSql;
import accessdata.utils.UtilsValidateCodeError;
import entidad.constantes.ConstantLogger;
import entidad.constantes.Constants;
import entidad.constantes.sqlconstant.SqlConstant;
import entidad.entitys.usuarios.Employee;
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
import java.util.Objects;

@Log4j2
public class AccessEmployee {

    private final String[] NAME_FIELDS = {"idemployee", "name_employee", "address_employee", "phone",
                                          "password_employee", "user_employee", "fk_idrole", "identificacion"};
    String result = "Error: ";
    String abbreviation = "emp";

    public String callSaveEmployee(Employee employee) throws ParseException {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UtilsSql.queryCreate(SqlConstant.EMPLOYEE)
                     + NAME_FIELDS[1].concat(Constants.COMMA)
                     + NAME_FIELDS[2].concat(Constants.COMMA)
                     + NAME_FIELDS[3].concat(Constants.COMMA)
                     + NAME_FIELDS[4].concat(Constants.COMMA)
                     + NAME_FIELDS[5].concat(Constants.COMMA)
                     + NAME_FIELDS[6].concat(Constants.COMMA)
                     + NAME_FIELDS[7]
                     + ") VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            stmt.setString(1, employee.getNameEmployee());
            stmt.setString(2, employee.getAddressEmployee());
            stmt.setString(3, employee.getPhoneNumberEmployee());
            stmt.setString(4, employee.getPasswordEmployee());
            stmt.setString(5, employee.getUserEmployee());
            stmt.setInt(6, employee.getFkRoleEmployee());
            stmt.setString(7, employee.getIdentification());

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_INSERT, SqlConstant.EMPLOYEE);
                result = SqlConstant.SUCCESS_PROCESS;
            } else {
                log.info(ConstantLogger.LOG_ERROR_QUERY_INSERT, Constants.ONE_NEG);
                result = SqlConstant.ERROR_PROCESS;
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

    public String callUpdateEmployee(Employee employee) throws ParseException {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                UtilsSql.queryUpdate(SqlConstant.EMPLOYEE, abbreviation)
                 + NAME_FIELDS[1].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[2].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[3].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[4].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[5].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[6].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[7].concat(SqlConstant.VALUE)
                 + String.format(SqlConstant.WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.VALUE))) {

            stmt.setString(1, employee.getNameEmployee());
            stmt.setString(2, employee.getAddressEmployee());
            stmt.setString(3, employee.getPhoneNumberEmployee());
            stmt.setString(4, employee.getPasswordEmployee());
            stmt.setString(5, employee.getUserEmployee());
            stmt.setInt(6, employee.getFkRoleEmployee());
            stmt.setString(7, employee.getIdentification());
            stmt.setInt(8, employee.getIdEmployee());

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_INSERT, SqlConstant.EMPLOYEE);
                result = SqlConstant.SUCCESS_PROCESS;
            }

            stmt.close();
            ConfigurationDb.closeConnection();

        } catch (SQLException e) {
            log.error(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
            log.error(ConstantLogger.LOG_ERROR_STATE_SQL, e.getSQLState());
            result = result.concat(UtilsValidateCodeError.validateMessageError(e.getSQLState()))
                    .concat(Constants.BREAK_LINE);
            result = result.concat(e.getMessage());
        }

        return result;
    }

    public String callDeleteEmployee(int idEmployee) {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UtilsSql.queryDetele(SqlConstant.EMPLOYEE, abbreviation)
                + String.format(SqlConstant.WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.VALUE))) {

            stmt.setInt(1, idEmployee);

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_DELETE, SqlConstant.EMPLOYEE);
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

    public Employee callFindEmployee(Employee employee) throws ParseException {
        Employee findEmployee = null;
        String namesFields = String.join(Constants.COMMA, NAME_FIELDS);

        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt =
                 conn.prepareStatement(UtilsSql.queryFindById(namesFields, SqlConstant.EMPLOYEE, abbreviation)
                   + String.format(SqlConstant.WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.VALUE))) {

            stmt.setInt(1, employee.getIdEmployee());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                findEmployee = Employee.builder()
                    .idEmployee(employee.getIdEmployee())
                    .nameEmployee(employee.getNameEmployee())
                    .userEmployee(employee.getUserEmployee())
                    .passwordEmployee(employee.getPasswordEmployee())
                    .addressEmployee(employee.getAddressEmployee())
                    .phoneNumberEmployee(employee.getPhoneNumberEmployee())
                    .fkRoleEmployee(employee.getFkRoleEmployee())
                    .build();
            }

            rs.close();
            stmt.close();
            ConfigurationDb.closeConnection();

            if (Objects.isNull(findEmployee)) {
                log.error(ConstantLogger.LOG_ERROR_FIND_NOT_FOUND);
            } else {
                log.error(ConstantLogger.LOG_SUCCESS_QUERY_FIND_ID, Constants.ONE_NEG);
            }
        } catch (SQLException e) {
            log.error(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
            log.error(ConstantLogger.LOG_ERROR_STATE_SQL, e.getSQLState());
            result = result.concat(UtilsValidateCodeError.validateMessageError(e.getSQLState()));
            result = result.concat(e.getMessage());
        }

        return findEmployee;
    }

    public List<Employee> callFindAllEmployee() {
        List<Employee> employeeList = new ArrayList<>();
        String namesFields = String.join(Constants.COMMA, NAME_FIELDS);

        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 UtilsSql.queryFindAll(namesFields, SqlConstant.EMPLOYEE))) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Employee findEmployee = Employee.builder()
                    .idEmployee(rs.getInt(1))
                    .nameEmployee(rs.getString(2))
                    .userEmployee(rs.getString(3))
                    .passwordEmployee(rs.getString(4))
                    .addressEmployee(rs.getString(5))
                    .phoneNumberEmployee(rs.getString(6))
                    .fkRoleEmployee(rs.getInt(7))
                    .identification(rs.getString(8))
                    .build();

                employeeList.add(findEmployee);
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
        return employeeList;
    }
}
