package bg.tu_varna.sit.couriermanagementsystem.controllers;

import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogMode;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.citiestable.CitiesTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.cities.Cities;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies.Companies;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

/**/
public class CompanyDialogController extends DialogController
{

    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private Companies _companyRecord;
    @FXML
    private TextField _companyName;
    @FXML
    private TextField _phoneNumber;
    @FXML
    private TextField _EGFN;
    @FXML
    private ComboBox<Cities> _citiesComboBox;
    @FXML
    private TextField _email;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public CompanyDialogController(Companies companyRecord, DialogMode dialogMode)
    {
        super(dialogMode);
        _companyRecord = companyRecord;
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

    }

    @Override
    public void setDataToControls()
    {
        _companyName.setText(_companyRecord.getName());
        _EGFN.setText(_companyRecord.getEGFN());
        _email.setText(_companyRecord.getEmail());
        _phoneNumber.setText(_companyRecord.getPhoneNumber());
    }

    @Override
    public void setControlsToData()
    {
        _companyRecord.setName(_companyName.getText());
        _companyRecord.setEGFN(_EGFN.getText());

        Cities city = _citiesComboBox.getValue();
        if(city == null)
            return;

        _companyRecord.setCityID(city.getID());
        _companyRecord.setEmail(_email.getText());
        _companyRecord.setPhoneNumber(_phoneNumber.getText());
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

        if(!_citiesComboBox.getItems().setAll(citiesList))
            return false;

        for(int cityIndex = 0; cityIndex < citiesList.size(); cityIndex++)
        {
            Cities currentCity = citiesList.get(cityIndex);
           if(currentCity.getID() == _companyRecord.getCityID())
                _citiesComboBox.setValue(currentCity);
        }


        return true;
    }
}
