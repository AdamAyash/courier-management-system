package bg.tu_varna.sit.couriermanagementsystem.controllers.clients;

import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogMode;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogResult;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.SmartTableViewController;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.clientstable.ClientsTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.companiestable.CompaniesTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.userstable.UsersTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.clients.Clients;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.clients.ClientsData;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.clients.ClientsDetails;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies.Companies;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.Users;
import bg.tu_varna.sit.couriermanagementsystem.stages.StageManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.List;

/**/
public class ClientsTableViewController extends SmartTableViewController<Clients, Clients>
{

    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private List<Companies> _companiesList;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public ClientsTableViewController()
    {
        super(new ClientsTable());

        _menuItemPreview.setOnAction(action ->
        {
            Clients clientRecord = _tableView.getSelectionModel().getSelectedItem();

            if(clientRecord == null)
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            if(!_table.selectRecordByID(clientRecord, clientRecord.getID()))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            Users clientAccount = new Users();
            UsersTable usersTable = new UsersTable();

            if(!usersTable.selectRecordByID(clientAccount, clientRecord.getUserID()))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }
            ClientsDetails clientsDetails = new ClientsDetails(clientAccount, clientRecord);
            ClientDialogController clientDialogController = new ClientDialogController(clientsDetails, DialogMode.DIALOG_MODE_PREVIEW);
            StageManager stageManager =
                    new StageManager("ClientsDialog.fxml", Messages.CLIENTS_TITLE, clientDialogController, ClientDialogController.class);

            OpenDialog(stageManager);
        });

        _menuItemInsert.setOnAction(action ->
        {
            ClientsDetails clientsDetails = new ClientsDetails();
            ClientDialogController clientDialogController = new ClientDialogController(clientsDetails, DialogMode.DIALOG_MODE_INSERT);

            StageManager stageManager =
                    new StageManager("ClientsDialog.fxml", Messages.CLIENTS_TITLE, clientDialogController, ClientDialogController.class);

            if(!OpenDialog(stageManager))
                return;

            if(clientDialogController.getDialogResult() != DialogResult.DIALOG_RESULT_APPLY)
                return;

            ClientsData clientsData = new ClientsData();
            if(!clientsData.insertClient(clientsDetails))
            {
                MessageBox.error(Messages.INSERT_RECORD_FAILED_MESSAGE);
                return;
            }

                MessageBox.information(Messages.SUCCESSFULLY_ADDED_NEW_RECORD_MESSAGE);


            if(!refreshTableView())
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
            }

        });

        _menuItemUpdate.setOnAction(action ->
        {
            Clients clientRecord = _tableView.getSelectionModel().getSelectedItem();

            if(clientRecord == null)
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            if(!_table.selectRecordByID(clientRecord, clientRecord.getID()))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            Users clientAccount = new Users();
            UsersTable usersTable = new UsersTable();

            if(!usersTable.selectRecordByID(clientAccount, clientRecord.getUserID()))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            ClientsDetails clientsDetails = new ClientsDetails(clientAccount, clientRecord);
            ClientDialogController clientDialogController = new ClientDialogController(clientsDetails, DialogMode.DIALOG_MODE_UPDATE);

            StageManager stageManager =
                    new StageManager("ClientsDialog.fxml", Messages.CLIENTS_TITLE, clientDialogController, ClientDialogController.class);

            if(!OpenDialog(stageManager))
                return;

            if(clientDialogController.getDialogResult() != DialogResult.DIALOG_RESULT_APPLY)
                return;

            ClientsData clientsData = new ClientsData();
            if(!clientsData.updateClient(clientsDetails))
            {
                MessageBox.error(Messages.UPDATE_RECORD_FAILED_MESSAGE);
                return;
            }

            if(!refreshTableView())
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }
        });

        _menuItemDelete.setOnAction(action ->
        {
            Clients clientRecord = _tableView.getSelectionModel().getSelectedItem();

            if(clientRecord == null)
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            if(!_table.selectRecordByID(clientRecord, clientRecord.getID()))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            Users clientAccount = new Users();
            UsersTable usersTable = new UsersTable();

            if(!usersTable.selectRecordByID(clientAccount, clientRecord.getUserID()))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            ClientsDetails clientsDetails = new ClientsDetails(clientAccount, clientRecord);
            ClientsData clientsData = new ClientsData();

            if(!MessageBox.confirmation(Messages.DELETE_RECORD_QUESTION))
                return;

            if(!clientsData.deleteClient(clientsDetails))
            {
                MessageBox.error(Messages.DELETE_RECORD_FAILED_MESSAGE);
                return;
            }

            if(!refreshTableView())
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

        });
    }

    //-------------------------
    //Methods:
    //-------------------------

    //-------------------------
    //Overrides:
    //-------------------------

    @Override
    public void setContextMenu()
    {
        super.setContextMenu();

        if(!refreshTableView())
            MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
    }

    @Override
    protected void InitTableViewColumns()
    {
        TableColumn<Clients, String> clientIDColumn = new TableColumn<>("ID");
        clientIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Clients, String> clientFirstNameColumn = new TableColumn<>("First name");
        clientFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Clients, String> clientMiddleNameColumn = new TableColumn<>("Middle name");
        clientMiddleNameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));

        TableColumn<Clients, String> clientLastNameColumn = new TableColumn<>("Last name");
        clientLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Clients, String> clientUCNColumn = new TableColumn<>("UCN");
        clientUCNColumn.setCellValueFactory(new PropertyValueFactory<>("UCN"));

        TableColumn<Clients, String> clientCompanyColumn = new TableColumn<>("Company");
        clientCompanyColumn.setCellValueFactory(client ->
        {
            var filteredCompaniesList = _companiesList.stream().filter(company -> company.getID() == client.getValue().getCompanyID()).toList();
             return new SimpleStringProperty(filteredCompaniesList.get(0).toString());
        });

        TableColumn<Clients, String> clientAddressColumn = new TableColumn<>("Address");
        clientAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Clients, String> clientPhoneNumberColumn = new TableColumn<>("Phone number");
        clientPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        _tableView.getColumns().add(clientIDColumn);
        _tableView.getColumns().add(clientFirstNameColumn);
        _tableView.getColumns().add(clientMiddleNameColumn);
        _tableView.getColumns().add(clientLastNameColumn);
        _tableView.getColumns().add(clientUCNColumn);
        _tableView.getColumns().add(clientCompanyColumn);
        _tableView.getColumns().add(clientAddressColumn);
        _tableView.getColumns().add(clientPhoneNumberColumn);
    }

    @Override
    protected boolean loadData()
    {
        _companiesList = new ArrayList<>();

        CompaniesTable companiesTable = new CompaniesTable();
        if(!companiesTable.selectAllRecords(_companiesList))
        {
            MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
            return false;
        }

        if(!refreshTableView())
        {
            MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
            return false;
        }

        return true;
    }
}
