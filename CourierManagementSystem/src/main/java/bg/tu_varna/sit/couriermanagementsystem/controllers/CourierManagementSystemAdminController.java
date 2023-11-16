package bg.tu_varna.sit.couriermanagementsystem.controllers;

import bg.tu_varna.sit.couriermanagementsystem.CourierManagementSystem;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.BaseController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogMode;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.companiestable.CompaniesTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies.Companies;
import bg.tu_varna.sit.couriermanagementsystem.stages.StageManager;
import bg.tu_varna.sit.couriermanagementsystem.userauthentication.UserAuthentication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourierManagementSystemAdminController extends BaseController
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    @FXML
    private Label _currentUser;
    @FXML
    private TabPane _listControl;
    private TableView<Companies> _companiesTableView = null;

    //-------------------------
    //Constructor/Destructor:
    //-------------------------

    //-------------------------
    //Methods:
    //-------------------------

    private void setContextMenu()
    {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItemPreview = new MenuItem("Preview");
        MenuItem menuItemInsert = new MenuItem("Insert");
        MenuItem menuItemUpdate = new MenuItem("Update");
        MenuItem menuItemDelete = new MenuItem("Delete");

        menuItemPreview.setOnAction(action ->
        {
            Companies company = _companiesTableView.getSelectionModel().getSelectedItem();

            if(company == null)
                return;

            CompanyDialogController companyDialogController = new CompanyDialogController(company, DialogMode.DIALOG_MODE_PREVIEW);
            StageManager stageManager =
                    new StageManager("CompanyDialog.fxml", "Companies", companyDialogController, CourierManagementSystem.class);
            try
            {
                stageManager.showStage();
            }
            catch (IOException exception)
            {
               _logger.error(exception.getMessage());
            }
        });

        menuItemInsert.setOnAction(action ->
        {
            Companies newCompaniesRecord = new Companies();
            CompanyDialogController companyDialogController = new CompanyDialogController(newCompaniesRecord, DialogMode.DIALOG_MODE_INSERT);
            StageManager stageManager =
                    new StageManager("CompanyDialog.fxml", "Companies", companyDialogController, CourierManagementSystem.class);
            try
            {
                stageManager.showStage();
                CompaniesTable companiesTable = new CompaniesTable();

                if(!companiesTable.insertRecord(newCompaniesRecord))
                {
                    Alert companiesInsertNewRecordFailed = new Alert(Alert.AlertType.ERROR, Messages.INSERT_RECORD_FAILED_MESSAGE);
                    companiesInsertNewRecordFailed.setTitle(Messages.APPLICATION_NAME);
                    companiesInsertNewRecordFailed.show();
                    return;
                }

                if(newCompaniesRecord == null)
                {
                    Alert companiesInsertNewRecordFailed = new Alert(Alert.AlertType.ERROR, Messages.INSERT_RECORD_FAILED_MESSAGE);
                    companiesInsertNewRecordFailed.setTitle(Messages.APPLICATION_NAME);
                    companiesInsertNewRecordFailed.show();
                    return;
                }

                _companiesTableView.getItems().add(newCompaniesRecord);

            }
            catch (IOException exception)
            {
                _logger.error(exception.getMessage());
                Alert companiesInsertNewRecordFailed = new Alert(Alert.AlertType.ERROR, Messages.INSERT_RECORD_FAILED_MESSAGE);
                companiesInsertNewRecordFailed.setTitle(Messages.APPLICATION_NAME);
                companiesInsertNewRecordFailed.show();
            }
        });

        menuItemDelete.setOnAction(action ->
        {
            CompaniesTable companiesTable = new CompaniesTable();
            Companies company = _companiesTableView.getSelectionModel().getSelectedItem();

            if(company == null)
                return;

            if(!companiesTable.deleteRecord(company.getID()))
            {
                Alert companiesInsertNewRecordFailed = new Alert(Alert.AlertType.ERROR, Messages.DELETE_RECORD_FAILED_MESSAGE);
                companiesInsertNewRecordFailed.setTitle(Messages.APPLICATION_NAME);
                companiesInsertNewRecordFailed.show();
                return;
            }


          final int selectedCompanyIndex =  _companiesTableView.getSelectionModel().getSelectedIndex();
          _companiesTableView.getItems().remove(selectedCompanyIndex);

        });

        contextMenu.getItems().add(menuItemPreview);
        contextMenu.getItems().add(menuItemInsert);
        contextMenu.getItems().add(menuItemUpdate);
        contextMenu.getItems().add(menuItemDelete);

        _listControl.setContextMenu(contextMenu);
    }

    @FXML
    private void OnCompaniesButtonClick()
    {
        if(!(_companiesTableView == null))
            return;

        List<Companies> companiesList = new ArrayList<>();
        CompaniesTable companiesTable = new CompaniesTable();

        if(!companiesTable.selectAllRecords(companiesList))
            return;

        _companiesTableView = new TableView<>();

        TableColumn<Companies, String> companyIDColumn = new TableColumn<>("ID");
        companyIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Companies, String> companyNameColumn = new TableColumn<>("Name");
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Companies, String> companyEGFNColumn = new TableColumn<>("EGFN");
        companyEGFNColumn.setCellValueFactory(new PropertyValueFactory<>("EGFN"));

        TableColumn<Companies, String> companyPhoneNumberColumn = new TableColumn<>("Phone Number");
        companyPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Companies, String> companyEmailColumn = new TableColumn<>("Email address");
        companyEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));


        _companiesTableView.getColumns().add(companyIDColumn);
        _companiesTableView.getColumns().add(companyNameColumn);
        _companiesTableView.getColumns().add(companyEGFNColumn);
        _companiesTableView.getColumns().add(companyPhoneNumberColumn);
        _companiesTableView.getColumns().add(companyEmailColumn);


      for(int i = 0; i < companiesList.size(); i++)
      {
          _companiesTableView.getItems().add(companiesList.get(i));
      }

        Tab citiesTab = new Tab("Companies");
        citiesTab.setContent(_companiesTableView);
        citiesTab.setClosable(true);
        _listControl.getTabs().add(citiesTab);
    }

    //-------------------------
    //Overrides:
    //-------------------------
    @Override
    public boolean InitializeController()
    {
        UserAuthentication userAuthentication = UserAuthentication.getInstance();
        _currentUser.setText("Welcome " + userAuthentication.getCurrentlyLoggedUser().getUsername());
        setContextMenu();

        return true;
    }
}
