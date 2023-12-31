package bg.tu_varna.sit.couriermanagementsystem.scenes;

import bg.tu_varna.sit.couriermanagementsystem.CourierManagementSystem;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private static final Map<String, Scene> _scenesMap =new HashMap<>();
    private  final String CSS_FILE_NAME = "style/style.css";

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
        this.rootStage = rootStage;
    }

    //-------------------------
    //Methods:
    //-------------------------
    public void switchScene(String path, Class callerClass)
    {
        Scene scene = _scenesMap.computeIfAbsent(path, p ->
        {
            FXMLLoader loader = new FXMLLoader(callerClass.getResource(p));
            try {
                Parent parent = loader.load();
                BaseController controller = loader.getController();

                if(controller != null)
                {
                    controller.setSceneManager(this);
                    controller.InitializeController();
                }

                return new Scene(parent);
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        });
        scene.getStylesheets().add(CourierManagementSystem.class.getResource(CSS_FILE_NAME).toExternalForm());
        rootStage.setScene(scene);
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
