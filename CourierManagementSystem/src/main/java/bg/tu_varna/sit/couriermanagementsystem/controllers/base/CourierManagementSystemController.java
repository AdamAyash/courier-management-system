package bg.tu_varna.sit.couriermanagementsystem.controllers.base;

import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.loginform.LoginFormController;
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
    protected  UserAuthentication _userAuthentication;

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
        if(MessageBox.confirmation(Messages.LOG_OUT_QUESTION))
            sceneManager.reloadScene("LoginForm.fxml", LoginFormController.class);
    }

    //-------------------------
    //Overrides:
    //-------------------------
    @Override
    public boolean InitializeController()
    {
        _userAuthentication = UserAuthentication.getInstance();
        _currentUser.setText("Welcome " + _userAuthentication.getCurrentlyLoggedUser().getUsername());

        return true;
    }
}
