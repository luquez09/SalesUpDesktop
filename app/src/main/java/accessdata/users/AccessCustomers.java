package accessdata.users;

import accessdata.utils.UtilsSql;
import accessdata.utils.UtilsValidateCodeError;
import entidad.constantes.ConstantLogger;
import entidad.constantes.Constants;
import entidad.constantes.sqlconstant.SqlConstant;
import entidad.entitys.usuarios.Customers;
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
public class AccessCustomers {

    private final String[] NAME_FIELDS = {"idcustomers", "name_customers", "direction", "phone",
                                          "type_identification", "identification"};
    String result = "Error: ";
    String abbreviation = "c";


    public String callSaveCustomer(Customers customers) throws ParseException {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UtilsSql.queryCreate(SqlConstant.CUSTOMERS)
                     + NAME_FIELDS[1].concat(Constants.COMMA)
                     + NAME_FIELDS[2].concat(Constants.COMMA)
                     + NAME_FIELDS[3].concat(Constants.COMMA)
                     + NAME_FIELDS[4].concat(Constants.COMMA)
                     + NAME_FIELDS[5]
                     + ") VALUES (?, ?, ?, ?, ?)")) {

            stmt.setString(1, customers.getName());
            stmt.setString(2, customers.getAddress());
            stmt.setString(3, customers.getPhone());
            stmt.setString(4, customers.getTypeDocument());
            stmt.setString(5, customers.getNumberDocument());

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_INSERT, SqlConstant.CUSTOMERS);
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

    public String callUpdateCustomers(Customers customers) {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UtilsSql.queryUpdate(SqlConstant.CUSTOMERS, abbreviation)
                 + NAME_FIELDS[1].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[2].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[3].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[4].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[5].concat(SqlConstant.VALUE)
                 + String.format(SqlConstant.WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.VALUE))) {

            stmt.setString(1, customers.getName());
            stmt.setString(2, customers.getAddress());
            stmt.setString(3, customers.getPhone());
            stmt.setString(4, customers.getTypeDocument());
            stmt.setString(5, customers.getNumberDocument());

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_UPDATE, SqlConstant.CUSTOMERS);
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

    public String callDeleteCustomer(int idCustomer) {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UtilsSql.queryDetele(SqlConstant.CUSTOMERS, abbreviation)
                    + String.format(SqlConstant.WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.VALUE))) {

            stmt.setInt(1, idCustomer);

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_DELETE, SqlConstant.CUSTOMERS);
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

    public Customers callFindCustomer(Customers customers) throws ParseException {
        Customers customerSearch = null;
        String namesFields = String.join(Constants.COMMA, NAME_FIELDS);

        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
             UtilsSql.queryFindById(namesFields, SqlConstant.CUSTOMERS, abbreviation)
                                        + String.format(SqlConstant.WHERE,
                                        abbreviation, NAME_FIELDS[0],
                                        SqlConstant.VALUE))) {

            stmt.setInt(1, customers.getIdCustomers());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                customerSearch = Customers.builder()
                        .idCustomers(rs.getInt(1))
                        .name(rs.getString(2))
                        .address(rs.getString(3))
                        .phone(rs.getString(4))
                        .typeDocument(rs.getString(5))
                        .numberDocument(rs.getString(6))
                        .build();
            }

            rs.close();
            stmt.close();
            ConfigurationDb.closeConnection();

            if (Objects.isNull(customerSearch)) {
                log.error(ConstantLogger.LOG_ERROR_FIND_NOT_FOUND);
            } else {
                log.error(ConstantLogger.LOG_SUCCESS_QUERY_FIND_ID, customerSearch.getIdCustomers());
            }
        } catch (SQLException e) {
            log.error(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
            log.error(ConstantLogger.LOG_ERROR_STATE_SQL, e.getSQLState());
            result = result.concat(UtilsValidateCodeError.validateMessageError(e.getSQLState()));
            result = result.concat(e.getMessage());
            JOptionPane.showMessageDialog(null, result);
        }

        return customerSearch;
    }

    public List<Customers> callFindCustomers() throws ParseException {
        List<Customers> customersList = new ArrayList<>();
        String namesFields = String.join(Constants.COMMA, NAME_FIELDS);

        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
             UtilsSql.queryFindById(namesFields, SqlConstant.CUSTOMERS, SqlConstant.BLANC))) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Customers customers = Customers.builder()
                    .idCustomers(rs.getInt(1))
                    .name(rs.getString(2))
                    .address(rs.getString(3))
                    .phone(rs.getString(4))
                    .typeDocument(rs.getString(5))
                    .numberDocument(rs.getString(6))
                    .build();
                customersList.add(customers);
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
        return customersList;
    }
}
