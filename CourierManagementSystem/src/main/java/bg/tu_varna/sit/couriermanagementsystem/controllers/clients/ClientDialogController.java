package bg.tu_varna.sit.couriermanagementsystem.controllers.clients;

import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogMode;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.companiestable.CompaniesTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.clients.ClientsDetails;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies.Companies;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

/**/
public class ClientDialogController extends DialogController
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    @FXML
    private TextField _firstName;
    @FXML
    private TextField _middleName;
    @FXML
    private TextField _lastName;
    @FXML
    private TextField _UCN;
    @FXML
    private TextField _phoneNumber;
    @FXML
    private TextField _username;
    @FXML
    private PasswordField _password;
    @FXML
    private ComboBox<Companies> _companiesComboBox;
    @FXML
    private TextField _address;
    private List<Companies> _companiesList;
    private ClientsDetails _clientsDetails;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public ClientDialogController(ClientsDetails clientsDetails, DialogMode dialogMode)
    {
        super(dialogMode);
        _clientsDetails = clientsDetails;
        _companiesList = new ArrayList<>();
    }

    //-------------------------
    //Methods:
    //-------------------------

    //-------------------------
    //Overrides:
    //-------------------------

    @Override
    protected void setControls()
    {
        super.setControls();
        if(_dialogMode == DialogMode.DIALOG_MODE_PREVIEW)
        {
            _firstName.setDisable(true);
            _middleName.setDisable(true);
            _lastName.setDisable(true);
            _UCN.setDisable(true);
            _phoneNumber.setDisable(true);
            _username.setDisable(true);
            _password.setDisable(true);
            _companiesComboBox.setDisable(true);
            _address.setDisable(true);
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
        if(_username.getText().isBlank())
        {
            MessageBox.warning(Messages.INVALID_FIELD_MESSAGE + "\"Username.\"");
            return false;
        }

        if(_password.getText().isBlank())
        {
            MessageBox.warning(Messages.INVALID_FIELD_MESSAGE + "\"Password.\"");
            return false;
        }

        if(_companiesComboBox.getSelectionModel().isEmpty())
        {
            MessageBox.warning(Messages.INVALID_FIELD_MESSAGE + "\"Company.\"");
            return false;
        }

        if(_address.getText().isBlank())
        {
            MessageBox.warning(Messages.INVALID_FIELD_MESSAGE + "\"Address.\"");
            return false;
        }

        return true;
    }

    @Override
    public boolean setDataToControls()
    {

        List<Companies> companiesList = _companiesComboBox.getItems();

        Companies companyRecord = new Companies();
        for(Companies company : companiesList)
        {
            if(company.getID() == _clientsDetails.getClientRecord().getCompanyID())
                companyRecord = company;
        }


        _companiesComboBox.setValue(companyRecord);

        _firstName.setText(_clientsDetails.getClientRecord().getFirstName());
        _middleName.setText(_clientsDetails.getClientRecord().getMiddleName());
        _lastName.setText(_clientsDetails.getClientRecord().getLastName());
        _UCN.setText(_clientsDetails.getClientRecord().getUCN());
        _phoneNumber.setText(_clientsDetails.getClientRecord().getPhoneNumber());
        _username.setText(_clientsDetails.getClientAccount().getUsername());
        _password.setText(_clientsDetails.getClientAccount().getPassword());
        _address.setText(_clientsDetails.getClientRecord().getAddress());

        return true;
    }

    @Override
    public void setControlsToData()
    {
        _clientsDetails.getClientRecord().setFirstName(_firstName.getText());
        _clientsDetails.getClientRecord().setMiddleName(_middleName.getText());
        _clientsDetails.getClientRecord().setLastName(_lastName.getText());
        _clientsDetails.getClientRecord().setUCN(_UCN.getText());
        _clientsDetails.getClientRecord().setPhoneNumber(_phoneNumber.getText());
        _clientsDetails.getClientRecord().setCompanyID(_companiesComboBox.getSelectionModel().getSelectedItem().getID());
        _clientsDetails.getClientAccount().setUsername(_username.getText());
        _clientsDetails.getClientAccount().setPassword(_password.getText());
        _clientsDetails.getClientRecord().setAddress(_address.getText());
    }

    @Override
    public boolean LoadData()
    {
        final CompaniesTable companiesTable = new CompaniesTable();

        if(!companiesTable.selectAllRecords(_companiesList))
            return false;

        _companiesComboBox.getItems().addAll(_companiesList);

        return true;
    }
}
