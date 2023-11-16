package bg.tu_varna.sit.couriermanagementsystem.stages;


import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
            Alert processDataError = new Alert(Alert.AlertType.ERROR, Messages.PROCESS_DATA_ERROR_MESSAGE);
            processDataError.setTitle(Messages.APPLICATION_NAME);
            processDataError.show();

            return;
        }

        Scene newScene = new Scene(parent);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.initModality(Modality.APPLICATION_MODAL);
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
