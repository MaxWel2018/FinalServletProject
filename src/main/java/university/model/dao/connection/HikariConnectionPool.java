package university.model.dao.connection;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;
import university.model.dao.exception.DataBaseRuntimeException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class HikariConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(HikariDataSource.class);

    private static final String DB_USERNAME = "dataSource.user";
    private static final String DB_PASSWORD = "dataSource.password";
    private static final String DB_URL = "dataSource.url";

    private static Properties properties = new Properties();
    private static HikariDataSource dataSource;
    private static File file = new File("C:\\FinalServletProject\\src\\main\\resources\\bd-config.properties");

    static {
        try {
            properties = new Properties();
            properties.load(new FileInputStream(file));
            dataSource = new HikariDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl(properties.getProperty(DB_URL));
            dataSource.setUsername(properties.getProperty(DB_USERNAME));
            dataSource.setPassword(properties.getProperty(DB_PASSWORD));

            dataSource.setMinimumIdle(5);
            dataSource.setMaximumPoolSize(10);
            dataSource.setAutoCommit(false);
            dataSource.setLoginTimeout(5);

        } catch (IOException | SQLException e) {
            LOGGER.error("Connection Failed" + e);
        }
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }

    public  Connection getConnection()  {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Connection failed" +e);
            throw new DataBaseRuntimeException(e);
        }

    }
}
