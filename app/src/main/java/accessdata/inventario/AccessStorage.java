package accessdata.inventario;

import accessdata.utils.UtilsDate;
import accessdata.utils.UtilsSql;
import entidad.constantes.ConstantLogger;
import entidad.constantes.Constants;
import entidad.constantes.sqlconstant.SqlConstant;
import entidad.entitys.inventario.Store;
import entidad.entitys.inventario.Store;
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
public class AccessStorage {
    private final String[] NAME_FIELDS = {"idstore", "name_storage", "description", "location_store"};

    String result = "Error: ";
    String abbreviation = "st";

    public String callSaveStorage(Store store) throws ParseException {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UtilsSql.queryCreate(SqlConstant.STORAGE)
                     + NAME_FIELDS[1].concat(Constants.COMMA)
                     + NAME_FIELDS[2].concat(Constants.COMMA)
                     + NAME_FIELDS[3]
                     + ") VALUES (?, ?, ?)")) {

            stmt.setString(1, store.getName());
            stmt.setString(2, store.getDescription());
            stmt.setString(3, store.getUbication());

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_INSERT, SqlConstant.STORAGE);
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

    public String callUpdateStore(Store store) {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 UtilsSql.queryUpdate(SqlConstant.STORAGE, abbreviation)
                     + NAME_FIELDS[1].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                     + NAME_FIELDS[2].concat(SqlConstant.UPDATE_VALUE).concat(Constants.COMMA)
                     + NAME_FIELDS[3].concat(SqlConstant.UPDATE_VALUE)
                     + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0], SqlConstant.UPDATE_VALUE))) {

            stmt.setString(1, store.getName());
            stmt.setString(2, store.getDescription());
            stmt.setString(3, store.getUbication());
            stmt.setInt(4, store.getIdStore());

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_UPDATE, SqlConstant.STORAGE);
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

    public String callDeleteStore(Store store) {
        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
             UtilsSql.queryDetele(SqlConstant.STORAGE, abbreviation)
                     + String.format(SqlConstant.UPDATE_WHERE, abbreviation, NAME_FIELDS[0],
                     SqlConstant.UPDATE_VALUE))) {

            stmt.setInt(1, store.getIdStore());

            if (stmt.executeUpdate() > Constants.ZERO) {
                log.info(ConstantLogger.LOG_SUCCESS_QUERY_DELETE);
                result = SqlConstant.SUCCESS_PROCESS;
            } else {
                log.info(ConstantLogger.LOG_ERROR_QUERY_DELETE);
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

    public List<Store> callFindStore() throws ParseException {
        List<Store> storeList = new ArrayList<>();
        String namesFields = String.join(Constants.COMMA, NAME_FIELDS);

        try (Connection conn = ConfigurationDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UtilsSql.queryFindAll(namesFields, SqlConstant.STORAGE))) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Store dtoStore = Store.builder()
                        .idStore(rs.getInt(1))
                        .name(rs.getString(2))
                        .description(rs.getString(3))
                        .ubication(rs.getString(4))
                        .build();
                storeList.add(dtoStore);
            }
            stmt.close();
            rs.close();
            ConfigurationDb.closeConnection();
        } catch (SQLException e) {
            log.info(ConstantLogger.LOG_ERROR_EXECUTE_SQL, e.getMessage());
            JOptionPane.showMessageDialog(null, Constants.ERROR_SQL + e.getMessage());
        }
        return storeList;
    }
}
