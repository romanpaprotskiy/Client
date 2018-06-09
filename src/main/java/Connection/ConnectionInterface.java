package Connection;

import java.sql.*;

public interface ConnectionInterface {
    public Connection getConnection() throws SQLException;
    public void close() throws SQLException;
}
