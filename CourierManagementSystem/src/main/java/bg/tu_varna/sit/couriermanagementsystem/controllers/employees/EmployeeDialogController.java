package bg.tu_varna.sit.couriermanagementsystem.controllers.employees;

import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogMode;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.citiestable.CitiesTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.companiestable.CompaniesTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.cities.Cities;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies.Companies;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.employees.EmployeeDetails;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**/
public class EmployeeDialogController extends DialogController
{

    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private EmployeeDetails _employeeDetails;
    @FXML
    private TextField _firstName;
    @FXML
    private TextField _middleName;
    @FXML
    private TextField _lastName;
    @FXML
    private TextField _UCN;
    @FXML
    private TextField _userName;
    @FXML
    private TextField _password;
    @FXML
    private TextField _phoneNumber;
    @FXML
    private TextField _emailAddress;
    @FXML
    private ComboBox<String> _gender;
    @FXML
    private ComboBox<Cities> _citiesComboBox;
    @FXML
    private ComboBox<Companies> _companiesComboBox;
    @FXML
    private DatePicker _dateOfBirth;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public EmployeeDialogController(EmployeeDetails employeeDetails, DialogMode dialogMode)
    {
        super(dialogMode);
        _employeeDetails = employeeDetails;
    }

    //-------------------------
    //Methods:
    //-------------------------

    //-------------------------
    //Overrides:
    //-------------------------
    @Override
    public void setDataToControls()
    {
         List<Cities> citiesList = _citiesComboBox.getItems();

         Cities citiesRecord = new Cities();
        for(Cities city: citiesList)
        {
            if(city.getID() == _employeeDetails.getEmployeeRecord().getCityID())
                citiesRecord = city;
        }

        List<Companies> companiesList = _companiesComboBox.getItems();

        Companies companyRecord = new Companies();
        for(Companies company : companiesList)
        {
            if(company.getID() == _employeeDetails.getEmployeeRecord().getCompanyID())
                companyRecord = company;
        }

        _companiesComboBox.setValue(companyRecord);

        _citiesComboBox.setValue(citiesRecord);
        _dateOfBirth.setValue(_employeeDetails.getEmployeeRecord().getDateOfBirth().toLocalDate());
        _userName.setText(_employeeDetails.getEmployeeAccount().getUsername());
        _password.setText(_employeeDetails.getEmployeeAccount().getPassword());
        _firstName.setText(_employeeDetails.getEmployeeRecord().getFirstName());
        _lastName.setText(_employeeDetails.getEmployeeRecord().getLastName());
        _gender.setValue(_employeeDetails.getEmployeeRecord().getGender() == 0 ? Messages.MALE_MESSAGE : Messages.FEMALE_MESSAGE);
        _phoneNumber.setText(_employeeDetails.getEmployeeRecord().getPhoneNumber());
        _emailAddress.setText(_employeeDetails.getEmployeeRecord().getEmail());
        _middleName.setText(_employeeDetails.getEmployeeRecord().getMiddleName());
        _UCN.setText(_employeeDetails.getEmployeeRecord().getUCN());
    }

    @Override
    public void setControlsToData()
    {
        _employeeDetails.getEmployeeRecord().setFirstName(_firstName.getText());
        _employeeDetails.getEmployeeRecord().setLastName(_lastName.getText());
        _employeeDetails.getEmployeeRecord().setGender((short)(_gender.getSelectionModel().getSelectedItem() == Messages.MALE_MESSAGE ? 0 : 1));
        _employeeDetails.getEmployeeRecord().setCityID(_citiesComboBox.getValue().getID());
        _employeeDetails.getEmployeeRecord().setDateOfBirth(Date.valueOf(_dateOfBirth.getValue()));
        _employeeDetails.getEmployeeAccount().setUsername(_userName.getText());
        _employeeDetails.getEmployeeAccount().setPassword(_password.getText());
        _employeeDetails.getEmployeeRecord().setCompanyID(_companiesComboBox.getSelectionModel().getSelectedItem().getID());
        _employeeDetails.getEmployeeRecord().setPhoneNumber(_phoneNumber.getText());
        _employeeDetails.getEmployeeRecord().setEmail(_emailAddress.getText());
        _employeeDetails.getEmployeeRecord().setUCN(_UCN.getText());
        _employeeDetails.getEmployeeRecord().setMiddleName(_middleName.getText());

    }

    @Override
    protected void setControls()
    {
        super.setControls();

        if(_dialogMode == DialogMode.DIALOG_MODE_PREVIEW)
        {
            _firstName.setDisable(true);
            _middleName.setDisable(true);
            _lastName.setDisable(true);
            _userName.setDisable(true);
            _password.setDisable(true);
            _UCN.setDisable(true);
            _phoneNumber.setDisable(true);
            _emailAddress.setDisable(true);
            _gender.setDisable(true);
            _dateOfBirth.setDisable(true);
            _companiesComboBox.setDisable(true);
        }
    }

    @Override
    public boolean validateControls()
    {
        if(_firstName.getText().isBlank())
        {
            MessageBox.warning(Messages.INVALID_FIELD_MESSAGE + "\"First name.\"");
            return false;
        }

        if(_middleName.getText().isBlank())
        {
            MessageBox.warning(Messages.INVALID_FIELD_MESSAGE + "\"Middle name.\"");
            return false;
        }

        if(_lastName.getText().isBlank())
        {
            MessageBox.warning(Messages.INVALID_FIELD_MESSAGE + "\"Last name.\"");
            return false;
        }

        if(_userName.getText().isBlank())
        {
            MessageBox.warning(Messages.INVALID_FIELD_MESSAGE + "\"Username.\"");
            return false;
        }

        if(_password.getText().isBlank())
        {
            MessageBox.warning(Messages.INVALID_FIELD_MESSAGE + "\"Password.\"");
            return false;
        }

        if(_UCN.getText().isBlank())
        {
            MessageBox.warning(Messages.INVALID_FIELD_MESSAGE + "\"UCN.\"");
            return false;
        }

        if(_phoneNumber.getText().isBlank())
        {
            MessageBox.warning(Messages.INVALID_FIELD_MESSAGE + "\"Phone number.\"");
            return false;
        }

        if(_emailAddress.getText().isBlank())
        {
            MessageBox.warning(Messages.INVALID_FIELD_MESSAGE + "\"Email address.\"");
            return false;
        }

        if(_gender.getSelectionModel().getSelectedItem().isEmpty())
        {
            MessageBox.warning(Messages.INVALID_FIELD_MESSAGE + "\"Gender.\"");
            return false;
        }

        if(_companiesComboBox.getSelectionModel().isEmpty())
        {
            MessageBox.warning(Messages.INVALID_FIELD_MESSAGE + "\"Company.\"");
            return false;
        }

        if(_citiesComboBox.getSelectionModel().isEmpty())
        {
            MessageBox.warning(Messages.INVALID_FIELD_MESSAGE + "\"City.\"");
            return false;
        }

        return true;
    }

    @Override
    public boolean LoadData()
    {
        final CitiesTable citiesTable = new CitiesTable();
        List<Cities>  citiesList = new ArrayList<>();

        if(!citiesTable.selectAllRecords(citiesList))
            return false;

        _citiesComboBox.getItems().addAll(citiesList);

        final CompaniesTable companiesTable = new CompaniesTable();
        List<Companies>  companiesList = new ArrayList<>();

        if(!companiesTable.selectAllRecords(companiesList))
            return false;

        _companiesComboBox.getItems().addAll(companiesList);

        _gender.getItems().add(Messages.MALE_MESSAGE);
        _gender.getItems().add(Messages.FEMALE_MESSAGE);

        return true;
    }
}
