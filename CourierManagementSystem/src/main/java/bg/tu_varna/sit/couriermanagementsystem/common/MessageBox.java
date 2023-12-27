package bg.tu_varna.sit.couriermanagementsystem.common;

import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/*Клас спомагащ визуализация на съобщения към потребителя*/
public class MessageBox
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------

    //-------------------------
    //Methods:
    //-------------------------
    public static void error(String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setTitle(Messages.APPLICATION_NAME);
        alert.show();
    }
    public static boolean confirmation(String message)
    {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, message);
        confirmation.setTitle(Messages.APPLICATION_NAME);
        confirmation.showAndWait();

        if(!(confirmation.getResult() == ButtonType.OK))
            return false;

        return true;
    }

    public static void warning(String message)
    {
        Alert warning = new Alert(Alert.AlertType.WARNING, message);
        warning.setTitle(Messages.APPLICATION_NAME);
        warning.showAndWait();
    }

    public static void information(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.setTitle(Messages.APPLICATION_NAME);
        alert.show();
    }
    //-------------------------
    //Overrides:
    //-------------------------
}
