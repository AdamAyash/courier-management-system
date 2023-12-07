package bg.tu_varna.sit.couriermanagementsystem.controllers.clients;

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
    public void setDataToControls()
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
    }

    @Override
    public boolean validateControls()
    {
        return true;
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
