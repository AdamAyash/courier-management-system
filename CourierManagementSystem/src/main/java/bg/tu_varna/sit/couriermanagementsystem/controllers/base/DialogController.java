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
    @FXML
    private Button _closeButton;
    protected DialogMode _dialogMode;
    protected DialogResult _dialogResult;

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
    public abstract void setDataToControls();
    public abstract void setControlsToData();
    public abstract boolean validateControls();
    public abstract boolean LoadData();

    private void CloseWindow()
    {
        Stage currentWindow =  (Stage)_applyButton.getScene().getWindow();
        currentWindow.close();
    }

    protected void setControls()
    {
        if(_dialogMode == DialogMode.DIALOG_MODE_PREVIEW)
            _applyButton.setVisible(false);
    }

    public DialogResult getDialogResult()
    {
        return _dialogResult;
    }

    @FXML
    protected void OnApplyClicked()
    {
        if(!validateControls())
            return;

        _dialogResult = DialogResult.DIALOG_RESULT_APPLY;

        setControlsToData();
        CloseWindow();
    }

    @FXML
    public void OnCloseClicked()
    {
        _dialogResult = DialogResult.DIALOG_RESULT_CANCEL;
        CloseWindow();
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

        if(_dialogMode != DialogMode.DIALOG_MODE_INSERT)
        setDataToControls();

        return true;
    }
}
