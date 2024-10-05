package accessdata.configurations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ConfigurationDb {

    private static Connection connection;
    private static String URL = "jdbc:postgresql://localhost:5432/dev_sales_administrator";
    private static String USER = "postgres";
    private static String PASSWORD = "admin";

    private static void initConnection () {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            if (Objects.isNull(connection)) {
                log.error("Error al conectar");
            } else {
                log.info("Conectado a base de datos.");
            }
        } catch (Exception e) {
            log.error("Excception generada: {}", e.getMessage());
        }
    }

    public static Connection getConnection() {
        if (Objects.isNull(connection)) {
            initConnection();
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (!Objects.isNull(connection)) {
            connection.close();
        }
    }
}
