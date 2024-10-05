package accessdata.inventario;

import accessdata.utils.UtilsSql;
import entidad.constantes.ConstantLogger;
import entidad.constantes.Constants;
import entidad.constantes.sqlconstant.SqlConstant;
import entidad.entitys.inventario.Supplier;
import lombok.extern.log4j.Log4j2;
import accessdata.configurations.ConfigurationDb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2
public class AccessSupplier {

    private final String[] NAME_FIELDS = {"idsupplier", "name_supplier", "name_social", "direccion", "phonenumber1",
                                          "phonenumber2", "description"};
    String result = "Error: ";
    String abbreviation = "sup";

    public String callSaveSupplier(Supplier supplier) throws ParseException {
        try (Connection conn = ConfigurationDb.getConnection()) {

            String sql = UtilsSql.queryCreate(SqlConstant.SUPPLIER)
                    + NAME_FIELDS[1].concat(Constants.COMMA)
                    + NAME_FIELDS[2].concat(Constants.COMMA)
                    + NAME_FIELDS[3].concat(Constants.COMMA)
                    + NAME_FIELDS[4].concat(Constants.COMMA)
                    + NAME_FIELDS[5].concat(Constants.COMMA)
                    + NAME_FIELDS[6]
                    + ") VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, supplier.getName());
                stmt.setString(2, supplier.getName_social());
                stmt.setString(3, supplier.getDireccion());
                stmt.setString(4, supplier.getPhoneNumber1());
                stmt.setString(5, supplier.getPhoneNumber2());
                stmt.setString(6, supplier.getDescription());
                int response = stmt.executeUpdate();
                if (response > Constants.ZERO) {
                    log.info(ConstantLogger.LOG_SUCCESS_QUERY_INSERT, supplier);
                    result = SqlConstant.SUCCESS_PROCESS;
                } else {
                    log.error(ConstantLogger.LOG_ERROR_QUERY_INSERT, supplier.getName(), response);
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

    public String callUpdateSupplier(Supplier supplier) {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 UtilsSql.queryUpdate(SqlConstant.SUPPLIER, abbreviation)
                     + NAME_FIELDS[1].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                     + NAME_FIELDS[2].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                     + NAME_FIELDS[3].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                     + NAME_FIELDS[4].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                     + NAME_FIELDS[5].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                     + NAME_FIELDS[6].concat(SqlConstant.UPDATE_VALUE)
                     + String.format(SqlConstant.UPDATE_WHERE, abbreviation,
                         NAME_FIELDS[0], SqlConstant.UPDATE_VALUE))) {

            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getName_social());
            stmt.setString(3, supplier.getDireccion());
            stmt.setString(4, supplier.getPhoneNumber1());
            stmt.setString(5, supplier.getPhoneNumber2());
            stmt.setString(6, supplier.getDescription());
            stmt.setInt(7, supplier.getIdSupplier());

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_UPDATE, SqlConstant.SUPPLIER);
                result = SqlConstant.SUCCESS_PROCESS;
            } else {
                log.info(ConstantLogger.LOG_ERROR_QUERY_UPDATE, supplier.toString());
                result = SqlConstant.ERROR_PROCESS;
            }
        } catch (SQLException e) {
            log.error(ConstantLogger.LOG_ERROR_QUERY_UPDATE, e.getMessage());
            result = SqlConstant.ERROR_PROCESS.concat(e.getMessage());
        }
        return result;
    }


    public String callDeleteSupplier(Supplier category) {
        try (Connection conn = ConfigurationDb.getConnection()) {

            String sql = UtilsSql.queryDetele(SqlConstant.SUPPLIER, abbreviation)
                    + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.UPDATE_VALUE);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, category.getIdSupplier());

                if (stmt.executeUpdate() > Constants.ZERO) {
                    log.info(ConstantLogger.LOG_SUCCESS_QUERY_DELETE, SqlConstant.SUPPLIER);
                    result = SqlConstant.SUCCESS_PROCESS;
                } else {
                    log.info(ConstantLogger.LOG_ERROR_QUERY_DELETE, SqlConstant.SUPPLIER);
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

    public Supplier callFindSupplier(Supplier category) throws ParseException {
        Supplier categoryFind = null;

        try (Connection conn = ConfigurationDb.getConnection()) {

            String namesFields = String.join(Constants.COMMA, NAME_FIELDS);

            String sql = UtilsSql.queryFindById(namesFields, SqlConstant.SUPPLIER, abbreviation)
                    + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.UPDATE_VALUE);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, category.getIdSupplier());

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    categoryFind = Supplier.builder()
                        .idSupplier(rs.getInt(1))
                        .name(rs.getString(2))
                        .name_social(rs.getString(3))
                        .direccion(rs.getString(4))
                        .phoneNumber1(rs.getString(5))
                        .phoneNumber2(rs.getString(6))
                        .description(rs.getString(7))
                        .build();
                }

                rs.close();
                stmt.close();
                ConfigurationDb.closeConnection();

                if (Objects.isNull(categoryFind)) {
                    log.error(ConstantLogger.LOG_ERROR_FIND_NOT_FOUND);
                } else {
                    log.error(ConstantLogger.LOG_SUCCESS_QUERY_FIND_ID, categoryFind.getIdSupplier());
                }
            } catch (SQLException ex) {
                log.info(ConstantLogger.LOG_ERROR_QUERY_FIND_ID, ex.getMessage());
                result = result.concat(ex.getMessage());
            }
        } catch (SQLException e) {
            log.info(ConstantLogger.LOG_ERROR_CONNECTION);
            result = result.concat(e.getMessage());
        }

        return categoryFind;
    }

    public List<Supplier> callFindSupplier(Supplier category, int page, int size) throws ParseException {
        List<Supplier> supplierList = new ArrayList<>();

        try (Connection conn = ConfigurationDb.getConnection()) {

            String namesFields = String.join(Constants.COMMA, NAME_FIELDS);

            String sql = UtilsSql.queryFindById(namesFields, SqlConstant.SUPPLIER, abbreviation)
                    + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.UPDATE_VALUE)
                    + " LIMIT ? OFFSET ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, category.getIdSupplier());
                stmt.setInt(2, size);
                stmt.setInt(3, (page - 1) * size);

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Supplier categoryFind = Supplier.builder()
                            .idSupplier(rs.getInt(1))
                            .name(rs.getString(2))
                            .name_social(rs.getString(3))
                            .direccion(rs.getString(4))
                            .phoneNumber1(rs.getString(5))
                            .phoneNumber2(rs.getString(6))
                            .description(rs.getString(7))
                            .build();
                    supplierList.add(categoryFind);
                }
                stmt.close();
                rs.close();
            } catch (SQLException ex) {
                log.info(ConstantLogger.LOG_ERROR_QUERY_FIND_ID, ex.getMessage());
            }
        } catch (SQLException e) {
            log.info(ConstantLogger.LOG_ERROR_CONNECTION);
        }
        return supplierList;
    }
}
