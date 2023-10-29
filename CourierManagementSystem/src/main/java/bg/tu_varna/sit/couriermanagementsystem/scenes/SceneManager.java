package bg.tu_varna.sit.couriermanagementsystem.scenes;

import bg.tu_varna.sit.couriermanagementsystem.CourierManagementSystem;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class SceneManager
{
    //-------------------------
    //Constants:
    //-------------------------
    private final Stage rootStage;
    private final Map<String, Scene> scenes;
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
        scenes = new HashMap<>();
    }

    //-------------------------
    //Methods:
    //-------------------------
    public void switchScene(String path, Class callerClass)
    {
        Scene scene = scenes.computeIfAbsent(path, p ->
        {
            FXMLLoader loader = new FXMLLoader(callerClass.getResource(p));
            try {
                Parent parent = loader.load();
                BaseController controller = loader.getController();

                if(controller != null)
                controller.setSceneManager(this);

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
