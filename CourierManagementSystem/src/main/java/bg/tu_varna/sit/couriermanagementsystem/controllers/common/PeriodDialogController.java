package bg.tu_varna.sit.couriermanagementsystem.controllers.common;

import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogMode;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

import java.sql.Date;

/**/
public class PeriodDialogController extends DialogController
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    @FXML
    private DatePicker _fromDate;
    @FXML
    private DatePicker _toDate;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public PeriodDialogController()
    {
        super(DialogMode.DIALOG_MODE_INSERT);
    }

    //-------------------------
    //Methods:
    //-------------------------
    public Date getFromDate()
    {
        return Date.valueOf(_fromDate.getValue());
    }

    public Date getToDate()
    {
        return Date.valueOf(_toDate.getValue());
    }

    //-------------------------
    //Overrides:
    //-------------------------

    @Override
    public boolean setDataToControls()
    {
        return true;
    }

    @Override
    public void setControlsToData()
    {

    }

    @Override
    public boolean validateControls()
    {
        if(_toDate.getValue() == null)
            return false;

        if(_fromDate.getValue() == null)
            return false;

        return true;
    }

}
