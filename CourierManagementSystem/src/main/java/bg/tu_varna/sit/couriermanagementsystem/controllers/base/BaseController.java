package bg.tu_varna.sit.couriermanagementsystem.controllers.base;

import bg.tu_varna.sit.couriermanagementsystem.scenes.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseController
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    protected static final Logger _logger = LogManager.getLogger();
    protected SceneManager sceneManager = null;

    //-------------------------
    //Constructor/Destructor:
    //-------------------------

    //-------------------------
    //Methods:
    //-------------------------
    public abstract boolean InitializeController();
    public void setSceneManager(SceneManager sceneManager)
    { // if SceneManager and BaseController are in different packages, change visibility
        this.sceneManager = sceneManager;
    }
    @FXML
    private void OnCloseButton()
    {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void OnMinimizeButton()
    {
        sceneManager.getStage().setIconified(true);
    }

    //-------------------------
    //Overrides:
    //-------------------------
}

