package bg.tu_varna.sit.couriermanagementsystem.controllers;

import bg.tu_varna.sit.couriermanagementsystem.CourierManagementSystem;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.BaseController;
import bg.tu_varna.sit.couriermanagementsystem.userauthentication.UserAuthentication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/*Контролер за логин формата*/
public class LoginFormController extends BaseController
{
    //-------------------------
    //Constants:
    //-------------------------
    private final String UNSUCCESSFUL_LOGIN_MESSAGE = "You have entered an invalid username or password.";

    //-------------------------
    //Members:
    //-------------------------
    @FXML
    private TextField _username;
    @FXML
    private PasswordField _password;
    @FXML
    private Label _loginFormOutput;

    private UserAuthentication _userAuthentication;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public LoginFormController()
    {
    }

    //-------------------------
    //Methods:
    //-------------------------

    @FXML
    private void OnLoginButtonClicked()
    {
        String username = _username.getText();
        String password = _password.getText();

        if(!_userAuthentication.authenticateUser(username, password))
        {
            _loginFormOutput.setText(UNSUCCESSFUL_LOGIN_MESSAGE);
            return;
        }

        if(_userAuthentication.getCurrentlyLoggedUser() == null)
        {
            //TO DO съобщение
            return;
        }

        sceneManager.switchScene("CourierManagementSystemAdminView.fxml", CourierManagementSystem.class);
    }

    //-------------------------
    //Overrides:
    //-------------------------
    @Override
    public boolean InitializeController()
    {
        _userAuthentication = UserAuthentication.getInstance();

        return true;
    }
}
