package bg.tu_varna.sit.couriermanagementsystem.controllers.base;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public abstract class DialogController extends BaseController
{

    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    @FXML
    private Button _applyButton;
    protected DialogMode _dialogMode;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public DialogController(DialogMode dialogMode)
    {
        _dialogMode = dialogMode;
    }

    //-------------------------
    //Methods:
    //-------------------------
    public abstract void setControls();
    public abstract void setDataToControls();
    public abstract void setControlsToData();
    public abstract boolean validateControls();
    public abstract boolean LoadData();

    @FXML
    public void OnApplyClicked()
    {
        if(!validateControls())
            return;

        setControlsToData();

        Stage currentWindow =  (Stage)_applyButton.getScene().getWindow();
        currentWindow.close();
    }

    //-------------------------
    //Overrides:
    //-------------------------
    @Override
    public boolean InitializeController()
    {
        setControls();

        if(!LoadData())
            return false;

        setDataToControls();

        return true;
    }
}
