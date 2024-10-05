package accessdata.inventario;

import accessdata.utils.UtilsDate;
import accessdata.utils.UtilsSql;
import entidad.constantes.ConstantLogger;
import entidad.constantes.Constants;
import entidad.constantes.sqlconstant.SqlConstant;
import entidad.entitys.inventario.Product;
import lombok.extern.log4j.Log4j2;
import accessdata.configurations.ConfigurationDb;

import javax.swing.*;
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
public class AccessProduct {

    private final String[] NAME_FIELDS = {"idproduct", "name_product", "description", "path_image", "quantity",
                                          "date_create", "date_update", "fk_idcategory", "fk_idsupplier", "fk_idstorage"};
    String result = "Error: ";
    String abbreviation = "prod";

    public String callSaveProduct(Product product) throws ParseException {
        try (Connection conn = ConfigurationDb.getConnection();
            PreparedStatement stmt = conn.prepareStatement(UtilsSql.queryCreate(SqlConstant.PRODUCT)
                + NAME_FIELDS[1].concat(Constants.COMMA)
                + NAME_FIELDS[2].concat(Constants.COMMA)
                + NAME_FIELDS[3].concat(Constants.COMMA)
                + NAME_FIELDS[4].concat(Constants.COMMA)
                + NAME_FIELDS[5].concat(Constants.COMMA)
                + NAME_FIELDS[6].concat(Constants.COMMA)
                + NAME_FIELDS[7].concat(Constants.COMMA)
                + NAME_FIELDS[8].concat(Constants.COMMA)
                + NAME_FIELDS[9]
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

                stmt.setString(1, product.getName());
                stmt.setString(2, product.getDescription());
                stmt.setString(3, product.getPathImage());
                stmt.setLong(4, product.getQuantity());
                stmt.setTimestamp(5, Timestamp.valueOf(UtilsDate.getDateNow()));
                stmt.setTimestamp(6, Timestamp.valueOf(UtilsDate.getDateNow()));
                stmt.setInt(7, product.getFk_category());
                stmt.setInt(8, product.getFk_supplier());

                if (stmt.executeUpdate() > Constants.ZERO) {
                    log.info(ConstantLogger.LOG_SUCCESS_QUERY_INSERT, SqlConstant.PRODUCT);
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

    public String callUpdateProduct(Product product) {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 UtilsSql.queryUpdate(SqlConstant.PRODUCT, abbreviation)
                 + NAME_FIELDS[1].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[2].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[3].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[4].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[5].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[6].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[7].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[8].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[9].concat(SqlConstant.UPDATE_VALUE)
                 + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.UPDATE_VALUE))) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setString(3, product.getPathImage());
            stmt.setLong(4, product.getQuantity());
            stmt.setTimestamp(5, Timestamp.valueOf(UtilsDate.getDateNow()));
            stmt.setTimestamp(6, Timestamp.valueOf(UtilsDate.getDateNow()));
            stmt.setInt(7, product.getFk_category());
            stmt.setInt(8, product.getFk_supplier());
            stmt.setInt(9, product.getFk_storage());
            stmt.setInt(10, product.getIdProduct());

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_UPDATE, SqlConstant.PRODUCT);
                result = SqlConstant.SUCCESS_UPDATE;
            } else {
                log.info(ConstantLogger.LOG_ERROR_QUERY_UPDATE, Constants.ONE_NEG);
                result = SqlConstant.ERROR_UPDATE;
            }
            stmt.close();
            ConfigurationDb.closeConnection();

        } catch (SQLException e) {
            log.info(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
            result = result.concat(e.getMessage());
        }

        return result;
    }

    public String callDeleteProduct(Product product) {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UtilsSql.queryDetele(SqlConstant.PRODUCT, abbreviation)
                 + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.UPDATE_VALUE))) {

                stmt.setInt(1, product.getIdProduct());

                if (stmt.executeUpdate() > Constants.ZERO) {
                    log.info(ConstantLogger.LOG_SUCCESS_QUERY_DELETE, SqlConstant.PRODUCT);
                    result = SqlConstant.SUCCESS_DELETE;
                } else {
                    log.info(ConstantLogger.LOG_ERROR_QUERY_DELETE, SqlConstant.PRODUCT);
                    result = SqlConstant.ERROR_DELETE;
                }
                stmt.close();
                ConfigurationDb.closeConnection();

        } catch (SQLException e) {
            log.info(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
            result = result.concat(e.getMessage());
        }

        return result;
    }

    public Product callFindProduct(Product product) throws ParseException {
        Product productFind = null;
        String namesFields = String.join(Constants.COMMA, NAME_FIELDS);

        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt =
                 conn.prepareStatement(UtilsSql.queryFindById(namesFields, SqlConstant.PRODUCT, abbreviation)
                 + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.UPDATE_VALUE))) {

                stmt.setInt(1, product.getIdProduct());

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    productFind = Product.builder()
                        .idProduct(rs.getInt(1))
                        .name(rs.getString(2))
                        .description(rs.getString(3))
                        .pathImage(rs.getString(4))
                        .quantity(rs.getInt(5))
                        .dateCreate(rs.getObject(NAME_FIELDS[5], LocalDateTime.class))
                        .dateUpdate(rs.getObject(NAME_FIELDS[6], LocalDateTime.class))
                        .fk_category(rs.getInt(8))
                        .fk_supplier(rs.getInt(9))
                        .fk_storage(rs.getInt(10))
                        .build();
                }

                rs.close();
                stmt.close();
                ConfigurationDb.closeConnection();

                if (Objects.isNull(productFind)) {
                    log.error(ConstantLogger.LOG_ERROR_FIND_NOT_FOUND);
                } else {
                    log.info(ConstantLogger.LOG_ERROR_QUERY_FIND_ID, productFind.getIdProduct());
                }
        } catch (SQLException e) {
            log.error(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
            JOptionPane.showMessageDialog(null, Constants.ERROR_SQL + e.getMessage());
        }

        return productFind;
    }

    public List<Product> callFindAllProduct() throws ParseException {
        List<Product> productList = new ArrayList<>();
        String namesFields = String.join(Constants.COMMA, NAME_FIELDS);

        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     UtilsSql.queryFindAll(namesFields, SqlConstant.PRODUCT))) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product productFind = Product.builder()
                    .idProduct(rs.getInt(1))
                    .name(rs.getString(2))
                    .description(rs.getString(3))
                    .pathImage(rs.getString(4))
                    .quantity(rs.getInt(5))
                    .dateCreate(rs.getObject(NAME_FIELDS[5], LocalDateTime.class))
                    .dateUpdate(rs.getObject(NAME_FIELDS[6], LocalDateTime.class))
                    .fk_category(rs.getInt(8))
                    .fk_supplier(rs.getInt(9))
                    .fk_storage(rs.getInt(10))
                    .build();
                productList.add(productFind);
            }
            stmt.close();
            rs.close();
            ConfigurationDb.closeConnection();
        } catch (SQLException e) {
            log.info(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
            JOptionPane.showMessageDialog(null, Constants.ERROR_SQL + e.getMessage());
        }
        return productList;
    }
}
