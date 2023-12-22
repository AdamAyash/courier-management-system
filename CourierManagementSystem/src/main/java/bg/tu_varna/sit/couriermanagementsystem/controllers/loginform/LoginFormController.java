package bg.tu_varna.sit.couriermanagementsystem.controllers.loginform;

import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.admin.CourierManagementSystemAdminController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.BaseController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.employees.CourierManagementSystemEmployeeController;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.AccessRights;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.Users;
import bg.tu_varna.sit.couriermanagementsystem.userauthentication.UserAuthentication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/*Контролер за логин формата*/
public class LoginFormController extends BaseController
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    @FXML
    private TextField _username;
    @FXML
    private PasswordField _password;
    @FXML
    private Label _loginFormOutput;

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

        UserAuthentication userAuthentication = UserAuthentication.getInstance();

        if(!userAuthentication.authenticateUser(username, password))
        {
            _loginFormOutput.setText(Messages.UNSUCCESSFUL_LOGIN_MESSAGE);
            return;
        }

        Users currentUser = userAuthentication.getCurrentlyLoggedUser();
        if(currentUser == null)
            return;

        switch (currentUser.getAccessID())
        {
            case 0:
                sceneManager.switchScene("CourierManagementSystemAdminView.fxml", CourierManagementSystemAdminController.class);
                break;
            case 1:
                sceneManager.switchScene("CourierManagementSystemEmployeeView.fxml", CourierManagementSystemEmployeeController.class);
                break;
            default:
                throw new RuntimeException();
        }
    }

    @FXML
    private void OnCloseButton()
    {
        sceneManager.exitStage();
    }

    //-------------------------
    //Overrides:
    //-------------------------

    @Override
    public boolean InitializeController()
    {
        return true;
    }
}
