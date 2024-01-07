package bg.tu_varna.sit.couriermanagementsystem.scenes;

import bg.tu_varna.sit.couriermanagementsystem.CourierManagementSystem;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.BaseController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*Клаз за отговорен за смяна на различните екрани и техните контролери*/
public class SceneManager
{
    //-------------------------
    //Constants:
    //-------------------------
    private final Stage rootStage;
    private static final Logger _logger = LogManager.getLogger();
    private static final Map<String, Scene> _scenesMap = new HashMap<>();
    private  final String CSS_FILE_NAME = "style/style.css";
    private double xOffset;
    private double yOffset;

    //-------------------------
    //Members:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public SceneManager(Stage rootStage)
    {
        if (rootStage == null) {
            throw new IllegalArgumentException();
        }
        xOffset = 0;
        yOffset = 0;

        this.rootStage = rootStage;
    }

    //-------------------------
    //Methods:
    //-------------------------

    private Scene getScene(String path, Class callerClass)
    {
        FXMLLoader loader = new FXMLLoader(callerClass.getResource(path));
        try {
            Parent parent = loader.load();
            BaseController controller = loader.getController();

            parent.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });
            parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    rootStage.setX(event.getScreenX() - xOffset);
                    rootStage.setY(event.getScreenY() - yOffset);
                }
            });

            if(controller != null)
            {
                controller.setSceneManager(this);
                controller.InitializeController();
            }

            Scene newScene = new Scene(parent);
            newScene.getStylesheets().add(CourierManagementSystem.class.getResource(CSS_FILE_NAME).toExternalForm());

            return newScene;
        }
        catch (IOException exception)
        {
            _logger.error(exception.getMessage());
            return null;
        }
    }

    public void switchScene(String path, Class callerClass)
    {
        Scene scene = _scenesMap.computeIfAbsent(path, action -> getScene(path, callerClass));
        rootStage.setScene(scene);
    }

    public void reloadScene(String path, Class callerClass)
    {
        Scene scene = getScene(path, callerClass);
        rootStage.setScene(scene);
    }

    public Stage getStage()
    {
        return rootStage;
    }

    public void exitStage()
    {
        rootStage.close();
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
