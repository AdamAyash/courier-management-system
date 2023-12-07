package bg.tu_varna.sit.couriermanagementsystem.controllers.base;

import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.scenes.SceneManager;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.ProtocolException;

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

    //-------------------------
    //Overrides:
    //-------------------------

}

