package bg.tu_varna.sit.couriermanagementsystem.stages;


import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


/**/
public class StageManager
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private String _fxmlFilePath;

    private String _title;
    private BaseController _baseController;
    private Class _callerClass;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public StageManager(final String path, final String title, BaseController baseController, Class callerClass)
    {
        _fxmlFilePath = path;
        _baseController = baseController;
        _callerClass = callerClass;
        _title = title;
    }

    public void showStage() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(_callerClass.getResource(_fxmlFilePath));
        fxmlLoader.setController(_baseController);
        Parent parent = fxmlLoader.load();

        if(!_baseController.InitializeController())
        {
            MessageBox.error(Messages.PROCESS_DATA_ERROR_MESSAGE);
            return;
        }

        Scene newScene = new Scene(parent);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.setResizable(false);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setTitle(_title);
        newStage.showAndWait();
    }

    //-------------------------
    //Methods:
    //-------------------------

    //-------------------------
    //Overrides:
    //-------------------------
}
