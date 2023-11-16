package bg.tu_varna.sit.couriermanagementsystem;

import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.database.connection.DatabaseConnectionPool;
import bg.tu_varna.sit.couriermanagementsystem.scenes.SceneManager;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
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
        {
            Alert databaseConnectionFailed = new Alert(Alert.AlertType.ERROR ,Messages.DATABASE_CONNECTION_ERROR_MESSAGE);
            databaseConnectionFailed.setTitle(Messages.APPLICATION_NAME);
            logger.error(Messages.DATABASE_CONNECTION_ERROR_MESSAGE);
            databaseConnectionFailed.show();
            return;
        }

        SceneManager sceneManager = new SceneManager(stage);
        sceneManager.switchScene("LoginForm.fxml", getClass());
        stage.setTitle(Messages.APPLICATION_NAME);
        stage.setResizable(false);
        stage.show();


    }

    public static void main(String[] args)
    {
        launch();
    }
}
