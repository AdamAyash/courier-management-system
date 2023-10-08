package bg.tu_varna.sit.couriermanagementsystem;

import bg.tu_varna.sit.couriermanagementsystem.database.connection.DatabaseConnectionPool;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CourierManagementSystem extends Application
{
    @Override
    public void start(Stage stage) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("UserAuthentication.fxml"));

        Scene s = new Scene(loader.load());
        stage.setScene(s);
        stage.show();
    }

    public static void main(String[] args)
    {
        try
        {
            DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
            if (!databaseConnectionPool.connectToDatabase())
                System.out.println("Connection failed");
            else
                System.out.println("Connection successfully");

          Connection connection =  databaseConnectionPool.getConnection();

        } catch (IOException e)
        {
            throw new RuntimeException(e);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        launch();
    }
}
