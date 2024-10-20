package accessdata.inventario;

import accessdata.utils.UtilsDate;
import accessdata.utils.UtilsSql;
import accessdata.utils.UtilsValidateCodeError;
import entidad.constantes.ConstantLogger;
import entidad.constantes.Constants;
import entidad.constantes.sqlconstant.SqlConstant;
import entidad.entitys.inventario.ListPrice;
import lombok.extern.log4j.Log4j2;
import accessdata.configurations.ConfigurationDb;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class AccessListPrice {

    private final String[] NAME_FIELDS = {"idlistprice", "name_list", "description", "price", "active", "date_create",
                                          "date_update", "fk_idproduct"};
    private final String[] NAME_JOIN = {"list.idlistprice", "list.name_list", "list.description", "list.price",
            "list.active", "list.date_create", "list.date_update", "list.fk_idproduct", "prod.name_product"};
    String result = "Error: ";
    String abbreviation = "list";
    String abbreviationProduct = "prod";

    public String callSaveListPrice(ListPrice listPrice) {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UtilsSql.queryCreate(SqlConstant.LIST_PRICE)
                     + NAME_FIELDS[1].concat(Constants.COMMA)
                     + NAME_FIELDS[2].concat(Constants.COMMA)
                     + NAME_FIELDS[3].concat(Constants.COMMA)
                     + NAME_FIELDS[4].concat(Constants.COMMA)
                     + NAME_FIELDS[5].concat(Constants.COMMA)
                     + NAME_FIELDS[6].concat(Constants.COMMA)
                     + NAME_FIELDS[7]
                     + ") VALUES (?, ?, ?, ?, ?, ?, ?)")) {

                stmt.setString(1, listPrice.getName());
                stmt.setString(2, listPrice.getDescription());
                stmt.setDouble(3, listPrice.getPrice());
                stmt.setBoolean(4, listPrice.getIsActive());
                stmt.setTimestamp(5, Timestamp.valueOf(UtilsDate.getDateNow()));
                stmt.setTimestamp(6, Timestamp.valueOf(UtilsDate.getDateNow()));
                stmt.setInt(7, listPrice.getFk_idProduct());

                int response = stmt.executeUpdate();
                if (response > Constants.ZERO) {
                    log.info(ConstantLogger.LOG_SUCCESS_QUERY_INSERT, listPrice);
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

    public String callUpdateListPrice(ListPrice listPrice) {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 UtilsSql.queryUpdate(SqlConstant.LIST_PRICE, abbreviation)
                     + NAME_FIELDS[1].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                     + NAME_FIELDS[2].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                     + NAME_FIELDS[3].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                     + NAME_FIELDS[4].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                     + NAME_FIELDS[6].concat(SqlConstant.VALUE)
                     + String.format(SqlConstant.WHERE, abbreviation,
                     NAME_FIELDS[0], SqlConstant.VALUE))) {

            stmt.setString(1, listPrice.getName());
            stmt.setString(2, listPrice.getDescription());
            stmt.setDouble(3, listPrice.getPrice());
            stmt.setBoolean(4, listPrice.getIsActive());
            stmt.setTimestamp(5, Timestamp.valueOf(UtilsDate.getDateNow()));
            stmt.setInt(6, listPrice.getIdListPrice());

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_UPDATE);
                result = SqlConstant.SUCCESS_UPDATE;
            } else {
                log.info(ConstantLogger.LOG_ERROR_QUERY_UPDATE, listPrice.toString());
                result = SqlConstant.ERROR_UPDATE;
            }
        } catch (SQLException e) {
            log.error(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
            log.error(ConstantLogger.LOG_ERROR_STATE_SQL, e.getSQLState());

            result = result.concat(UtilsValidateCodeError.validateMessageError(e.getSQLState()));
            result = result.concat(e.getMessage());
        }
        return result;
    }

    public String callDeleteListPrice(int idListPrice) {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UtilsSql.queryDetele(SqlConstant.LIST_PRICE, abbreviation)
                 + String.format(SqlConstant.WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.VALUE))) {

            stmt.setInt(1, idListPrice);

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_DELETE);
                result = SqlConstant.SUCCESS_DELETE;
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

    public List<ListPrice> callFindListPrice(int idProduct) {
        List<ListPrice> listPriceList = new ArrayList<>();

        String namesFields = String.join(Constants.COMMA, NAME_FIELDS);
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     UtilsSql.queryFindById(namesFields, SqlConstant.LIST_PRICE, abbreviation)
                     + String.format(SqlConstant.WHERE, abbreviation, NAME_FIELDS[7], SqlConstant.VALUE)
             )) {
            stmt.setInt(1, idProduct);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ListPrice categoryFind = ListPrice.builder()
                        .idListPrice(rs.getInt(1))
                        .name(rs.getString(2))
                        .description(rs.getString(3))
                        .price(rs.getDouble(4))
                        .isActive(rs.getObject(NAME_FIELDS[4], Boolean.class))
                        .dateCreate(rs.getObject(NAME_FIELDS[5], LocalDateTime.class))
                        .dateUpdate(rs.getObject(NAME_FIELDS[6], LocalDateTime.class))
                        .fk_idProduct(rs.getInt(8))
                        .build();
                listPriceList.add(categoryFind) ;
            }
            stmt.close();
            rs.close();
            ConfigurationDb.closeConnection();
        } catch (SQLException e) {
            log.error(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
            log.error(ConstantLogger.LOG_ERROR_STATE_SQL, e.getSQLState());

            result = result.concat(UtilsValidateCodeError.validateMessageError(e.getSQLState()));
            result = result.concat(e.getMessage());
            JOptionPane.showMessageDialog(null, result);
        }
        return listPriceList;
    }

    public List<ListPrice> callFindAllListPrice() {
        List<ListPrice> listPriceList = new ArrayList<>();
        String namesFields = String.join(Constants.COMMA, NAME_JOIN);
        String joinTable = "list.fk_idproduct = prod.idproduct";

        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 UtilsSql.queryFindAllJoin(namesFields,
                     SqlConstant.LIST_PRICE.concat(Constants.SPACE_BLANC).concat(abbreviation),
                     SqlConstant.PRODUCT.concat(Constants.SPACE_BLANC).concat(abbreviationProduct), joinTable))) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ListPrice categoryFind = ListPrice.builder()
                        .idListPrice(rs.getInt(1))
                        .name(rs.getString(2))
                        .description(rs.getString(3))
                        .price(rs.getDouble(4))
                        .isActive(rs.getObject(NAME_FIELDS[4], Boolean.class))
                        .dateCreate(rs.getObject(NAME_FIELDS[5], LocalDateTime.class))
                        .dateUpdate(rs.getObject(NAME_FIELDS[6], LocalDateTime.class))
                        .fk_idProduct(rs.getInt(8))
                        .nameProduct(rs.getString(9))
                        .build();
                listPriceList.add(categoryFind) ;
            }
            stmt.close();
            rs.close();
            ConfigurationDb.closeConnection();
        } catch (SQLException e) {
            log.error(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
            log.error(ConstantLogger.LOG_ERROR_STATE_SQL, e.getSQLState());

            result = result.concat(UtilsValidateCodeError.validateMessageError(e.getSQLState()));
            result = result.concat(e.getMessage());
            JOptionPane.showMessageDialog(null, result);
        }
        return listPriceList;
    }
}
