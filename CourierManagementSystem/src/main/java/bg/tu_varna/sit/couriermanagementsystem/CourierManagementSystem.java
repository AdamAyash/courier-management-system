package bg.tu_varna.sit.couriermanagementsystem;

import bg.tu_varna.sit.couriermanagementsystem.database.connection.DatabaseConnectionPool;
import bg.tu_varna.sit.couriermanagementsystem.scenes.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class CourierManagementSystem extends Application
{
    protected static final Logger logger = LogManager.getLogger();
    @Override
    public void start(Stage stage)
    {
        DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        if(!databaseConnectionPool.connectToDatabase())
            return;

        SceneManager sceneManager = new SceneManager(stage);
        sceneManager.switchScene("LoginForm.fxml", getClass());
        stage.setTitle("Куриерска система");
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}
