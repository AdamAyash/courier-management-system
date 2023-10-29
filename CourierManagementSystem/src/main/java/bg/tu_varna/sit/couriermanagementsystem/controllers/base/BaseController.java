package bg.tu_varna.sit.couriermanagementsystem.controllers.base;

import bg.tu_varna.sit.couriermanagementsystem.scenes.SceneManager;

public class BaseController
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    SceneManager sceneManager = null;

    //-------------------------
    //Constructor/Destructor:
    //-------------------------

    //-------------------------
    //Methods:
    //-------------------------

    //-------------------------
    //Overrides:
    //-------------------------

    public void setSceneManager(SceneManager sceneManager)
    { // if SceneManager and BaseController are in different packages, change visibility
        this.sceneManager = sceneManager;
    }
}

