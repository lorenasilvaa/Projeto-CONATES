package conates.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresqlJDBCConnection implements JDBCConnectionFactory {

    private final static String dbDriver = "org.postgresql.Driver";
    private final static String dbURL = "jdbc:postgresql://localhost:5432/conatesbd";
    private final static String user = "postgres";
    private final static String pass = "postgres";

    public PostgresqlJDBCConnection() {
    }

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(dbDriver);
        return DriverManager.getConnection(dbURL, user, pass);
    }
}
