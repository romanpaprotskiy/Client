package Connection;

import javafx.scene.control.Alert;

import java.sql.*;

public class ConnectionDB implements ConnectionInterface {

    private Connection connection;

    public ConnectionDB(String host,int port,String login,String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + host + ":" + port + "/Customs?useSSL=no";
            connection = DriverManager.getConnection(url,login,password);
        } catch (ClassNotFoundException | SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in connection");
            alert.setContentText("Error" + e);
            alert.showAndWait();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connection;
    }


    @Override
    public void close() throws SQLException {
        if (connection != null) connection.close();
    }
}
