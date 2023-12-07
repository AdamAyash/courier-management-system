package bg.tu_varna.sit.couriermanagementsystem.controllers.base;

import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.userauthentication.UserAuthentication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;

public class CourierManagementSystemController extends BaseController
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    @FXML
    private Label _currentUser;
    @FXML
    protected TabPane _listControl;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------

    //-------------------------
    //Methods:
    //-------------------------
    @FXML
    private void OnLogOutButton()
    {
        if(MessageBox.Confirmation(Messages.LOG_OUT_QUESTION))
            sceneManager.exitStage();
    }

    //-------------------------
    //Overrides:
    //-------------------------
    @Override
    public boolean InitializeController()
    {
        UserAuthentication userAuthentication = UserAuthentication.getInstance();
        _currentUser.setText("Welcome " + userAuthentication.getCurrentlyLoggedUser().getUsername());

        return true;
    }
}
