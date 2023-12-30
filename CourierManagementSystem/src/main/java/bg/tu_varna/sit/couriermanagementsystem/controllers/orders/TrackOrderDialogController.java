package bg.tu_varna.sit.couriermanagementsystem.controllers.orders;

import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogMode;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TrackOrderDialogController extends DialogController
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    @FXML
    private TextField _orderID;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public TrackOrderDialogController()
    {
        super(DialogMode.DIALOG_MODE_INSERT);
    }

    //-------------------------
    //Methods:
    //-------------------------

    public int getOrderID()
    {
        return Integer.parseInt(_orderID.getText());
    }

    //-------------------------
    //Overrides:
    //-------------------------
    @Override
    public boolean setDataToControls()
    {
        return false;
    }

    @Override
    public void setControlsToData()
    {

    }

    @Override
    public boolean validateControls()
    {
        if(_orderID.getText().isBlank())
            return false;

        return true;
    }
}
