package accessdata.inventario;

import accessdata.utils.UtilsDate;
import accessdata.utils.UtilsSql;
import entidad.constantes.ConstantLogger;
import entidad.constantes.Constants;
import entidad.constantes.sqlconstant.SqlConstant;
import entidad.entitys.inventario.ListPrice;
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
public class AccessListPrice {

    private final String[] NAME_FIELDS = {"idlistprice", "name_list", "description", "price", "active", "date_create",
                                          "date_update", "fk_idproduct"};
    String result = "Error: ";
    String abbreviation = "list";

    public String callSaveListPrice(ListPrice listPrice) throws ParseException {
        try (Connection conn = ConfigurationDb.getConnection()) {

            String sql = UtilsSql.queryCreate(SqlConstant.LIST_PRICE)
                    + NAME_FIELDS[1].concat(Constants.COMMA)
                    + NAME_FIELDS[2].concat(Constants.COMMA)
                    + NAME_FIELDS[3].concat(Constants.COMMA)
                    + NAME_FIELDS[4].concat(Constants.COMMA)
                    + NAME_FIELDS[5].concat(Constants.COMMA)
                    + NAME_FIELDS[6].concat(Constants.COMMA)
                    + NAME_FIELDS[7]
                    + ") VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, listPrice.getName());
                stmt.setString(2, listPrice.getDescription());
                stmt.setDouble(3, listPrice.getPrice());
                stmt.setBoolean(4, listPrice.getIsActive());
                stmt.setTimestamp(5, Timestamp.valueOf(UtilsDate.getDateNow()));
                stmt.setTimestamp(6, Timestamp.valueOf(UtilsDate.getDateNow()));

                int response = stmt.executeUpdate();
                if (response > Constants.ZERO) {
                    log.info(ConstantLogger.LOG_SUCCESS_QUERY_INSERT, listPrice);
                    result = SqlConstant.SUCCESS_PROCESS;
                } else {
                    log.error(ConstantLogger.LOG_ERROR_QUERY_INSERT, listPrice.getName(), response);
                    result = SqlConstant.ERROR_PROCESS;
                }
                stmt.close();
                ConfigurationDb.closeConnection();
            } catch (SQLException ex) {
                log.error(ConstantLogger.LOG_ERROR_QUERY_INSERT, ex.getMessage());
                result = result.concat(ex.getMessage());
            }
        } catch (SQLException e) {
            log.error(ConstantLogger.LOG_ERROR_QUERY_INSERT, e.getMessage());
            result = result.concat(e.getMessage());
        }
        return result;
    }

    public String callUpdateListPrice(ListPrice listPrice) {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     UtilsSql.queryUpdate(SqlConstant.LIST_PRICE, abbreviation)
                             + NAME_FIELDS[1].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                             + NAME_FIELDS[2].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                             + NAME_FIELDS[3].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                             + NAME_FIELDS[4].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                             + NAME_FIELDS[5].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                             + NAME_FIELDS[6].concat(SqlConstant.UPDATE_VALUE)
                             + String.format(SqlConstant.UPDATE_WHERE, abbreviation,
                             NAME_FIELDS[0], SqlConstant.UPDATE_VALUE))) {

            stmt.setString(1, listPrice.getName());
            stmt.setString(2, listPrice.getDescription());
            stmt.setDouble(3, listPrice.getPrice());
            stmt.setBoolean(4, listPrice.getIsActive());
            stmt.setTimestamp(5, Timestamp.valueOf(UtilsDate.getDateNow()));
            stmt.setTimestamp(6, Timestamp.valueOf(UtilsDate.getDateNow()));
            stmt.setInt(7, listPrice.getIdListPrice());

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_UPDATE, SqlConstant.LIST_PRICE);
                result = SqlConstant.SUCCESS_PROCESS;
            } else {
                log.info(ConstantLogger.LOG_ERROR_QUERY_UPDATE, listPrice.toString());
                result = SqlConstant.ERROR_PROCESS;
            }
        } catch (SQLException e) {
            log.error(ConstantLogger.LOG_ERROR_QUERY_UPDATE, e.getMessage());
            result = SqlConstant.ERROR_PROCESS.concat(e.getMessage());
        }
        return result;
    }


    public String callDeleteListPrice(ListPrice listPrice) {
        try (Connection conn = ConfigurationDb.getConnection()) {

            String sql = UtilsSql.queryDetele(SqlConstant.LIST_PRICE, abbreviation)
                    + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.UPDATE_VALUE);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, listPrice.getIdListPrice());

                if (stmt.executeUpdate() > Constants.ZERO) {
                    log.info(ConstantLogger.LOG_SUCCESS_QUERY_DELETE, SqlConstant.LIST_PRICE);
                    result = SqlConstant.SUCCESS_PROCESS;
                } else {
                    log.info(ConstantLogger.LOG_ERROR_QUERY_DELETE, SqlConstant.LIST_PRICE);
                    result = SqlConstant.ERROR_PROCESS;
                }
                stmt.close();
                ConfigurationDb.closeConnection();

            } catch (SQLException ex) {
                log.info(ConstantLogger.LOG_ERROR_QUERY_DELETE, ex.getMessage());
                result = result.concat(ex.getMessage());
            }
        } catch (SQLException e) {
            log.info(ConstantLogger.LOG_ERROR_CONNECTION);
            result = result.concat(e.getMessage());
        }

        return result;
    }

    public ListPrice callFindListPrice(ListPrice listPrice) throws ParseException {
        ListPrice listPriceFind = null;

        try (Connection conn = ConfigurationDb.getConnection()) {

            String namesFields = String.join(Constants.COMMA, NAME_FIELDS);

            String sql = UtilsSql.queryFindById(namesFields, SqlConstant.LIST_PRICE, abbreviation)
                    + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.UPDATE_VALUE);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, listPrice.getIdListPrice());

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    listPriceFind = ListPrice.builder()
                            .idListPrice(rs.getInt(0))
                            .name(rs.getString(1))
                            .description(rs.getString(2))
                            .price(rs.getDouble(3))
                            .isActive(rs.getObject(NAME_FIELDS[4], Boolean.class))
                            .dateCreate(rs.getObject(NAME_FIELDS[5], LocalDateTime.class))
                            .dateUpdate(rs.getObject(NAME_FIELDS[6], LocalDateTime.class))
                            .fk_idProduct(rs.getInt(7))
                            .build();
                }

                rs.close();
                stmt.close();
                ConfigurationDb.closeConnection();

                if (Objects.isNull(listPriceFind)) {
                    log.error(ConstantLogger.LOG_ERROR_FIND_NOT_FOUND);
                } else {
                    log.error(ConstantLogger.LOG_SUCCESS_QUERY_FIND_ID, listPriceFind.getIdListPrice());
                }
            } catch (SQLException ex) {
                log.info(ConstantLogger.LOG_ERROR_QUERY_FIND_ID, ex.getMessage());
                result = result.concat(ex.getMessage());
            }
        } catch (SQLException e) {
            log.info(ConstantLogger.LOG_ERROR_CONNECTION);
            result = result.concat(e.getMessage());
        }

        return listPriceFind;
    }

    public List<ListPrice> callFindListPrice(ListPrice category, int page, int size) throws ParseException {
        List<ListPrice> listPriceList = new ArrayList<>();

        try (Connection conn = ConfigurationDb.getConnection()) {

            String namesFields = String.join(Constants.COMMA, NAME_FIELDS);

            String sql = UtilsSql.queryFindById(namesFields, SqlConstant.LIST_PRICE, abbreviation)
                    + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.UPDATE_VALUE)
                    + " LIMIT ? OFFSET ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, category.getIdListPrice());
                stmt.setInt(2, size);
                stmt.setInt(3, (page - 1) * size);

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    ListPrice categoryFind = ListPrice.builder()
                            .idListPrice(rs.getInt(0))
                            .name(rs.getString(1))
                            .description(rs.getString(2))
                            .price(rs.getDouble(3))
                            .isActive(rs.getObject(NAME_FIELDS[4], Boolean.class))
                            .dateCreate(rs.getObject(NAME_FIELDS[5], LocalDateTime.class))
                            .dateUpdate(rs.getObject(NAME_FIELDS[6], LocalDateTime.class))
                            .fk_idProduct(rs.getInt(7))
                            .build();
                    listPriceList.add(categoryFind) ;
                }
                stmt.close();
                rs.close();
            } catch (SQLException ex) {
                log.info(ConstantLogger.LOG_ERROR_QUERY_FIND_ID, ex.getMessage());
            }
        } catch (SQLException e) {
            log.info(ConstantLogger.LOG_ERROR_CONNECTION);
        }
        return listPriceList;
    }
}
