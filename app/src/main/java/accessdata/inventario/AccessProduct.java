package accessdata.inventario;

import accessdata.utils.UtilsDate;
import accessdata.utils.UtilsSql;
import accessdata.utils.UtilsValidateCodeError;
import entidad.constantes.ConstantLogger;
import entidad.constantes.Constants;
import entidad.constantes.sqlconstant.SqlConstant;
import entidad.entitys.inventario.ListPrice;
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

    private AccessListPrice accessListPrice = new AccessListPrice();

    private final String[] NAME_FIELDS =
            {"idproduct", "name_product", "description", "path_image", "quantity", "date_create", "date_update",
             "fk_idcategory", "fk_idsupplier", "fk_idstorage", "code"};
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
                + NAME_FIELDS[9].concat(Constants.COMMA)
                + NAME_FIELDS[10]
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

                stmt.setString(1, product.getName());
                stmt.setString(2, product.getDescription());
                stmt.setString(3, product.getPathImage());
                stmt.setLong(4, product.getQuantity());
                stmt.setTimestamp(5, Timestamp.valueOf(UtilsDate.getDateNow()));
                stmt.setTimestamp(6, Timestamp.valueOf(UtilsDate.getDateNow()));
                stmt.setInt(7, product.getFk_category());
                stmt.setInt(8, product.getFk_supplier());
                stmt.setInt(9, product.getFk_storage());
                stmt.setString(10, product.getCodeProduct());

                ResultSet resultSet = stmt.executeQuery();

                if (resultSet.next()) {
                    result = accessListPrice.callSaveListPrice(this.addPriceDefault(resultSet, product));
                    result = SqlConstant.SUCCESS_PROCESS
                            .concat("Producto")
                            .concat(Constants.BREAK_LINE)
                            .concat(result)
                            .concat("Precio.");
                    log.info(ConstantLogger.LOG_SUCCESS_QUERY_INSERT, SqlConstant.PRODUCT);
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

    private ListPrice addPriceDefault(ResultSet resultSet, Product product) throws SQLException {
        return ListPrice.builder()
                .name("Precio normal")
                .price(product.getPriceDefault())
                .description("Precio normal de venta.")
                .isActive(true)
                .fk_idProduct(resultSet.getInt("idproduct"))
                .build();
    }

    public String callUpdateProduct(Product product) {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 UtilsSql.queryUpdate(SqlConstant.PRODUCT, abbreviation)
                 + NAME_FIELDS[1].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[2].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[3].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[4].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[5].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[6].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[7].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[8].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[9].concat(SqlConstant.VALUE).concat(Constants.COMMA)
                 + NAME_FIELDS[10].concat(SqlConstant.VALUE)
                 + String.format(SqlConstant.WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.VALUE))) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setString(3, product.getPathImage());
            stmt.setLong(4, product.getQuantity());
            stmt.setTimestamp(5, Timestamp.valueOf(UtilsDate.getDateNow()));
            stmt.setTimestamp(6, Timestamp.valueOf(UtilsDate.getDateNow()));
            stmt.setInt(7, product.getFk_category());
            stmt.setInt(8, product.getFk_supplier());
            stmt.setInt(9, product.getFk_storage());
            stmt.setString(10, product.getCodeProduct());
            stmt.setInt(11, product.getIdProduct());

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_UPDATE, SqlConstant.PRODUCT);
                result = SqlConstant.SUCCESS_UPDATE.concat(Constants.BREAK_LINE);
            }

            stmt.close();
            ConfigurationDb.closeConnection();

        } catch (SQLException e) {
            log.error(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage().concat(Constants.BREAK_LINE));
            log.error(ConstantLogger.LOG_ERROR_STATE_SQL, e.getSQLState().concat(Constants.BREAK_LINE));

            result = result.concat(UtilsValidateCodeError.validateMessageError(e.getSQLState()))
                    .concat(Constants.BREAK_LINE);
            result = result.concat(e.getMessage());
        }

        return result;
    }

    public String callDeleteProduct(int product) {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UtilsSql.queryDetele(SqlConstant.PRODUCT, abbreviation)
                 + String.format(SqlConstant.WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.VALUE))) {

                stmt.setInt(1, product);

                if (stmt.executeUpdate() > Constants.ZERO) {
                    log.info(ConstantLogger.LOG_SUCCESS_QUERY_DELETE, SqlConstant.PRODUCT);
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

    public Product callFindProduct(Product product) throws ParseException {
        Product productFind = null;
        String namesFields = String.join(Constants.COMMA, NAME_FIELDS);

        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt =
                 conn.prepareStatement(UtilsSql.queryFindById(namesFields, SqlConstant.PRODUCT, abbreviation)
                 + String.format(SqlConstant.WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.VALUE))) {

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
            log.error(ConstantLogger.LOG_ERROR_STATE_SQL, e.getSQLState());

            result = result.concat(UtilsValidateCodeError.validateMessageError(e.getSQLState()));
            result = result.concat(e.getMessage());
            JOptionPane.showMessageDialog(null, result);
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
                    .codeProduct(rs.getString(11))
                    .build();
                productList.add(productFind);
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
        return productList;
    }
}
