package bg.tu_varna.sit.couriermanagementsystem.controllers.companies;

import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogMode;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogResult;
import bg.tu_varna.sit.couriermanagementsystem.controllers.companies.offices.OfficeDialogController;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.citiestable.CitiesTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.officestable.OfficesTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.cities.Cities;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies.CompaniesDetails;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.offices.Offices;
import bg.tu_varna.sit.couriermanagementsystem.stages.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.sql.Date;
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
    private CompaniesDetails _companyDetails;
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
    @FXML
    private TableView<Offices> _officesTableView;
    @FXML
    private DatePicker _dateEstablished;
    private MenuItem _menuItemPreview;
    private MenuItem _menuItemInsert;
    private MenuItem _menuItemUpdate;
    private MenuItem _menuItemDelete;


    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public CompanyDialogController(CompaniesDetails companyDetails, DialogMode dialogMode)
    {
        super(dialogMode);
        _companyDetails = companyDetails;
    }

    //-------------------------
    //Methods:
    //-------------------------

    private void setContextMenu()
    {
        ContextMenu contextMenu = new ContextMenu();
        _menuItemPreview = new MenuItem("Preview");
        _menuItemInsert = new MenuItem("Insert");
        _menuItemUpdate = new MenuItem("Update");
        _menuItemDelete = new MenuItem("Delete");

        contextMenu.getItems().add(_menuItemPreview);
        contextMenu.getItems().add(_menuItemInsert);
        contextMenu.getItems().add(_menuItemUpdate);
        contextMenu.getItems().add(_menuItemDelete);


        _menuItemPreview.setOnAction(action ->
        {
            Offices office = _officesTableView.getSelectionModel().getSelectedItem();

            if(office == null)
                return;

            OfficesTable officesTable = new OfficesTable();
            if(!officesTable.selectRecordByID(office, office.getID()))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            OfficeDialogController officeDialogController = new OfficeDialogController(office, DialogMode.DIALOG_MODE_PREVIEW);

            StageManager stageManager =
                    new StageManager("OfficeDialog.fxml", "Offices", officeDialogController, OfficeDialogController.class);
            try
            {
                stageManager.showStage();
            }
            catch (IOException exception)
            {
                _logger.error(exception.getMessage());
            }
        });

        _menuItemUpdate.setOnAction(action ->
        {
            if(_dialogMode == DialogMode.DIALOG_MODE_PREVIEW)
                return;

            Offices office = _officesTableView.getSelectionModel().getSelectedItem();

            if(office == null)
                return;

            final OfficesTable officesTable = new OfficesTable();

            if(!officesTable.selectRecordByID(office, office.getID()))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            OfficeDialogController officeDialogController = new OfficeDialogController(office, DialogMode.DIALOG_MODE_UPDATE);

            StageManager stageManager =
                    new StageManager("OfficeDialog.fxml", "Offices", officeDialogController, OfficeDialogController.class);
            try
            {
                stageManager.showStage();
            }
            catch (IOException exception)
            {
                _logger.error(exception.getMessage());
            }

            if(officeDialogController.getDialogResult() != DialogResult.DIALOG_RESULT_APPLY)
                return;

            _officesTableView.refresh();

        });

        _menuItemInsert.setOnAction(action ->
        {
            if(_dialogMode == DialogMode.DIALOG_MODE_PREVIEW)
                return;

            Offices newOfficeRecord = new Offices();
            OfficeDialogController officeDialogController = new OfficeDialogController(newOfficeRecord, DialogMode.DIALOG_MODE_INSERT);

            StageManager stageManager =
                    new StageManager("OfficeDialog.fxml", "Offices", officeDialogController, OfficeDialogController.class);
            try
            {
                stageManager.showStage();
            }
            catch (IOException exception)
            {
                _logger.error(exception.getMessage());
            }

            if(officeDialogController.getDialogResult() != DialogResult.DIALOG_RESULT_APPLY)
                return;

            newOfficeRecord.setCompanyID(_companyDetails.getCompaniesRecord().getID());

            _companyDetails.getOfficesList().add(newOfficeRecord);
            _officesTableView.getItems().add(newOfficeRecord);

        });

        _menuItemDelete.setOnAction(action ->
        {
            if(_dialogMode == DialogMode.DIALOG_MODE_PREVIEW)
                return;

            MessageBox.confirmation(Messages.DELETE_RECORD_QUESTION);

            Offices office = _officesTableView.getSelectionModel().getSelectedItem();
            if(!_companyDetails.getOfficesList().remove(office))
            {

            }
            _officesTableView.getItems().remove(office);
        });

        _officesTableView.setContextMenu(contextMenu);
    }

    private void setOfficesTableView()
    {
        TableColumn<Offices, String> officesIDColumn = new TableColumn<>("ID");
        officesIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Offices, String> officesNameColumn = new TableColumn<>("Office name");
        officesNameColumn.setCellValueFactory(new PropertyValueFactory<>("officeName"));

        TableColumn<Offices, String> officesAddressColumn = new TableColumn<>("Address");
        officesAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Offices, String> officesDateEstablishedColumn = new TableColumn<>("Date Established");
        officesDateEstablishedColumn.setCellValueFactory(new PropertyValueFactory<>("dateEstablished"));

        _officesTableView.getColumns().add(officesIDColumn);
        _officesTableView.getColumns().add(officesNameColumn);
        _officesTableView.getColumns().add(officesAddressColumn);
        _officesTableView.getColumns().add(officesDateEstablishedColumn);

        _officesTableView.getItems().addAll(_companyDetails.getOfficesList());
        setContextMenu();
    }

    //-------------------------
    //Overrides:
    //-------------------------

    @Override
    public void setControls()
    {
        super.setControls();
        setOfficesTableView();

        if(_dialogMode == DialogMode.DIALOG_MODE_PREVIEW)
        {
            _companyName.setDisable(true);
            _citiesComboBox.setDisable(true);
            _dateEstablished.setDisable(true);
            _email.setDisable(true);
            _phoneNumber.setDisable(true);
            _EGFN.setDisable(true);
            _menuItemDelete.setDisable(true);
            _menuItemInsert.setDisable(true);
            _menuItemUpdate.setDisable(true);
        }
    }

    @Override
    public boolean setDataToControls()
    {
        _companyName.setText(_companyDetails.getCompaniesRecord().getName());
        _EGFN.setText(_companyDetails.getCompaniesRecord().getEGFN());
        _email.setText(_companyDetails.getCompaniesRecord().getEmail());
        _phoneNumber.setText(_companyDetails.getCompaniesRecord().getPhoneNumber());
        _dateEstablished.setValue(_companyDetails.getCompaniesRecord().getDateEstablished().toLocalDate());

        return true;
    }

    @Override
    public boolean validateControls()
    {
        if(_companyName.getText().isBlank())
        {
            MessageBox.warning(Messages.INVALID_FIELD_MESSAGE + "\"Company name.\"");
            return false;
        }

        if(_EGFN.getText().isBlank())
        {
            MessageBox.warning(Messages.INVALID_FIELD_MESSAGE + "\"EGFN.\"");
            return false;
        }

        if(_email.getText().isBlank())
        {
            MessageBox.warning(Messages.INVALID_FIELD_MESSAGE + "\"Email address.\"");
            return false;
        }

        if(_phoneNumber.getText().isBlank())
        {
            MessageBox.warning(Messages.INVALID_FIELD_MESSAGE + "\"Phone number.\"");
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
    public void setControlsToData()
    {
        _companyDetails.getCompaniesRecord().setName(_companyName.getText());
        _companyDetails.getCompaniesRecord().setEGFN(_EGFN.getText());

        Cities city = _citiesComboBox.getValue();
        if(city == null)
            return;

        _companyDetails.getCompaniesRecord().setCityID(city.getID());
        _companyDetails.getCompaniesRecord().setEmail(_email.getText());
        _companyDetails.getCompaniesRecord().setPhoneNumber(_phoneNumber.getText());
        _companyDetails.getCompaniesRecord().setDateEstablished(Date.valueOf(_dateEstablished.getValue()));
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
            if(currentCity.getID() == _companyDetails.getCompaniesRecord().getCityID())
                _citiesComboBox.setValue(currentCity);
        }
        return true;
    }
}
