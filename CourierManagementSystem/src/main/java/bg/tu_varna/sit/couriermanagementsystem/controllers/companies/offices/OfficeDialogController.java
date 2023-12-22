package bg.tu_varna.sit.couriermanagementsystem.controllers.companies.offices;

import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogMode;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.citiestable.CitiesTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.cities.Cities;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.offices.Offices;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OfficeDialogController extends DialogController
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private Offices _officesRecord;
    @FXML
    private TextField _officeName;
    @FXML
    private TextField _address;
    @FXML
    private ComboBox<Cities> _citiesComboBox;
    @FXML
    private DatePicker _dateEstablished;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public OfficeDialogController(Offices officesRecord, DialogMode dialogMode)
    {
        super(dialogMode);
        _officesRecord = officesRecord;
    }

    //-------------------------
    //Methods:
    //-------------------------

    //-------------------------
    //Overrides:
    //-------------------------
    @Override
    public void setControls()
    {
        super.setControls();

        if(_dialogMode == DialogMode.DIALOG_MODE_PREVIEW)
        {
            _address.setDisable(true);
            _officeName.setDisable(true);
            _citiesComboBox.setDisable(true);
            _dateEstablished.setDisable(true);
        }
    }

    @Override
    public boolean setDataToControls()
    {
        _address.setText(_officesRecord.getAddress());
        _officeName.setText(_officesRecord.getOfficeName());
        _dateEstablished.setValue(_officesRecord.getDateEstablished().toLocalDate());

        return true;
    }

    @Override
    public void setControlsToData()
    {
        _officesRecord.setAddress(_address.getText());
        _officesRecord.setOfficeName(_officeName.getText());

        Cities cities =  _citiesComboBox.getSelectionModel().getSelectedItem();

        if(cities != null)
        _officesRecord.setCityID(cities.getID());
        _officesRecord.setDateEstablished(Date.valueOf(_dateEstablished.getValue()));
    }

    @Override
    public boolean validateControls()
    {
        return true;
    }

    @Override
    public boolean LoadData()
    {
        CitiesTable citiesTable = new CitiesTable();

        List<Cities> citiesList = new ArrayList<>();

        if(!citiesTable.selectAllRecords(citiesList))
            return false;

        if(!_citiesComboBox.getItems().addAll(citiesList))
            return false;

        for(int cityIndex = 0; cityIndex < citiesList.size(); cityIndex++)
        {
            Cities currentCity = citiesList.get(cityIndex);
            if(currentCity.getID() == _officesRecord.getCityID())
                _citiesComboBox.setValue(currentCity);
        }

        return true;
    }
}
