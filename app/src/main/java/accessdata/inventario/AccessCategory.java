package accessdata.inventario;

import accessdata.utils.UtilsDate;
import accessdata.utils.UtilsSql;
import entidad.constantes.ConstantLogger;
import entidad.constantes.Constants;
import entidad.constantes.sqlconstant.SqlConstant;
import entidad.entitys.inventario.Category;
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
public class AccessCategory {
    private final String[] NAME_FIELDS = {"Idcategory", "name_category", "description", "date_create", "date_update"};
    String result = "Error: ";
    String abbreviation = "cat";

    public String callSaveCategory(Category category) throws ParseException {
        try (Connection conn = ConfigurationDb.getConnection()) {

            String sql = UtilsSql.queryCreate(SqlConstant.CATEGORY)
                    + NAME_FIELDS[1].concat(Constants.COMMA)
                    + NAME_FIELDS[2].concat(Constants.COMMA)
                    + NAME_FIELDS[3].concat(Constants.COMMA)
                    + NAME_FIELDS[4].concat(Constants.COMMA)
                    + ") VALUES (?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, category.getName());
                stmt.setString(2, category.getDescription());
                stmt.setTimestamp(3, Timestamp.valueOf(UtilsDate.getDateNow()));
                stmt.setTimestamp(4, Timestamp.valueOf(UtilsDate.getDateNow()));

                if (stmt.executeUpdate() > Constants.ZERO) {
                    log.info(ConstantLogger.LOG_SUCCESS_QUERY_INSERT, SqlConstant.CATEGORY);
                    result = SqlConstant.SUCCESS_PROCESS;
                } else {
                    log.info(ConstantLogger.LOG_ERROR_QUERY_INSERT, SqlConstant.CATEGORY);
                    result = SqlConstant.ERROR_PROCESS;
                }
                stmt.close();
                ConfigurationDb.closeConnection();

            } catch (SQLException ex) {
                log.info(ConstantLogger.LOG_ERROR_QUERY_INSERT, SqlConstant.CATEGORY);
                result = result.concat(ex.getMessage());
            }
        } catch (SQLException e) {
            log.info(ConstantLogger.LOG_ERROR_CONNECTION);
            result = result.concat(e.getMessage());
        }

        return result;
    }

    public String callUpdateCategory(Category category) {
        try (Connection conn = ConfigurationDb.getConnection()) {

            String sql = UtilsSql.queryUpdate(SqlConstant.CATEGORY, abbreviation)
                    + NAME_FIELDS[1].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                    + NAME_FIELDS[2].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                    + NAME_FIELDS[4].concat(SqlConstant.UPDATE_VALUE)
                    + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.UPDATE_VALUE);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, category.getName());
                stmt.setString(2, category.getDescription());
                stmt.setTimestamp(3, Timestamp.valueOf(UtilsDate.getDateNow()));
                stmt.setInt(4, category.getIdCategory());

                if (stmt.executeUpdate() > Constants.ZERO) {
                    log.info(ConstantLogger.LOG_SUCCESS_QUERY_UPDATE, SqlConstant.CATEGORY);
                    result = SqlConstant.SUCCESS_PROCESS;
                } else {
                    log.info(ConstantLogger.LOG_ERROR_QUERY_UPDATE, SqlConstant.CATEGORY);
                    result = SqlConstant.ERROR_PROCESS;
                }
                stmt.close();
                ConfigurationDb.closeConnection();

            } catch (SQLException ex) {
                log.info(ConstantLogger.LOG_ERROR_QUERY_UPDATE, ex.getMessage());
                result = result.concat(ex.getMessage());
            }
        } catch (SQLException e) {
            log.info(ConstantLogger.LOG_ERROR_CONNECTION);
            result = result.concat(e.getMessage());
        }

        return result;
    }

    public String callDeleteCategory(Category category) {
        try (Connection conn = ConfigurationDb.getConnection()) {

            String sql = UtilsSql.queryDetele(SqlConstant.CATEGORY, abbreviation)
                    + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.UPDATE_VALUE);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, category.getIdCategory());

                if (stmt.executeUpdate() > Constants.ZERO) {
                    log.info(ConstantLogger.LOG_SUCCESS_QUERY_DELETE, SqlConstant.CATEGORY);
                    result = SqlConstant.SUCCESS_PROCESS;
                } else {
                    log.info(ConstantLogger.LOG_ERROR_QUERY_DELETE, SqlConstant.CATEGORY);
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

    public Category callFindCategory(Category category) throws ParseException {
        Category categoryFind = null;

        try (Connection conn = ConfigurationDb.getConnection()) {

            String namesFields = String.join(Constants.COMMA, NAME_FIELDS);

            String sql = UtilsSql.queryFindById(namesFields, SqlConstant.CATEGORY, abbreviation)
                    + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.UPDATE_VALUE);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, category.getIdCategory());

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    categoryFind = Category.builder()
                        .idCategory(rs.getInt(1))
                        .name(rs.getString(2))
                        .description(rs.getString(3))
                        .dateCreate(rs.getObject(NAME_FIELDS[3], LocalDateTime.class))
                        .dateUpdate(rs.getObject(NAME_FIELDS[4], LocalDateTime.class))
                        .build();
                }

                rs.close();
                stmt.close();
                ConfigurationDb.closeConnection();

                if (Objects.isNull(categoryFind)) {
                    log.error(ConstantLogger.LOG_ERROR_FIND_NOT_FOUND);
                } else {
                    log.error(ConstantLogger.LOG_SUCCESS_QUERY_FIND_ID, categoryFind.getIdCategory());
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

    public List<Category> callFindCategory(Category category, int page, int size) throws ParseException {
        List<Category> categories = new ArrayList<>();

        try (Connection conn = ConfigurationDb.getConnection()) {

            String namesFields = String.join(Constants.COMMA, NAME_FIELDS);

            String sql = UtilsSql.queryFindById(namesFields, SqlConstant.CATEGORY, abbreviation)
                    + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.UPDATE_VALUE)
                    + " LIMIT ? OFFSET ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, category.getIdCategory());
                stmt.setInt(2, size);
                stmt.setInt(3, (page - 1) * size);

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Category categoryFind = Category.builder()
                            .idCategory(rs.getInt(1))
                            .name(rs.getString(2))
                            .description(rs.getString(3))
                            .dateCreate(rs.getObject(NAME_FIELDS[3], LocalDateTime.class))
                            .dateUpdate(rs.getObject(NAME_FIELDS[4], LocalDateTime.class))
                            .build();
                    categories.add(categoryFind);
                }
                stmt.close();
                rs.close();
            } catch (SQLException ex) {
                log.info(ConstantLogger.LOG_ERROR_QUERY_FIND_ID, ex.getMessage());
            }
        } catch (SQLException e) {
            log.info(ConstantLogger.LOG_ERROR_CONNECTION);
        }
        return categories;
    }
}
