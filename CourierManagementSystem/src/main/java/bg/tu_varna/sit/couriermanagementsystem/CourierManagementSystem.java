package bg.tu_varna.sit.couriermanagementsystem;

import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.loginform.LoginFormController;
import bg.tu_varna.sit.couriermanagementsystem.database.connection.DatabaseConnectionPool;
import bg.tu_varna.sit.couriermanagementsystem.scenes.SceneManager;
import javafx.application.Application;
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
            MessageBox.Error(Messages.DATABASE_CONNECTION_ERROR_MESSAGE);
            logger.error(Messages.DATABASE_CONNECTION_ERROR_MESSAGE);
            return;
        }

        SceneManager sceneManager = new SceneManager(stage);
        sceneManager.switchScene("LoginForm.fxml", LoginFormController.class);
        stage.setTitle(Messages.APPLICATION_NAME);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}
