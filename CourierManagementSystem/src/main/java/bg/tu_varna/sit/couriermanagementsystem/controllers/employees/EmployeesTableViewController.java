package bg.tu_varna.sit.couriermanagementsystem.controllers.employees;

import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogMode;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogResult;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.SmartTableViewController;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.citiestable.CitiesTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.employees.EmployeesTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.userstable.UsersTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.cities.Cities;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.employees.EmployeeDetails;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.employees.Employees;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.employees.EmployeesData;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.Users;
import bg.tu_varna.sit.couriermanagementsystem.stages.StageManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.List;

/**/
public class EmployeesTableViewController extends SmartTableViewController<Employees, Employees>
{

    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private List<Cities> _citiesList;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public EmployeesTableViewController()
    {
        super(new EmployeesTable());
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

        _menuItemPreview.setOnAction(action ->
        {
           Employees employeeRecord =  _tableView.getSelectionModel().getSelectedItem();

            if(employeeRecord == null)
                return;

            if(!_table.selectRecordByID(employeeRecord, employeeRecord.getID()))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            UsersTable usersTable = new UsersTable();
            Users employeeUserAccount = new Users();

            if(!usersTable.selectRecordByID(employeeUserAccount, employeeRecord.getUserID()))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            EmployeeDetails employeeDetails = new EmployeeDetails(employeeRecord, employeeUserAccount);
            EmployeeDialogController employeeDialogController = new EmployeeDialogController(employeeDetails, DialogMode.DIALOG_MODE_PREVIEW);


                StageManager stageManager =
                        new StageManager("EmployeeDialog.fxml", Messages.EMPLOYEES_TITLE, employeeDialogController, EmployeeDialogController.class);

                OpenDialog(stageManager);

        });

        _menuItemInsert.setOnAction(action ->
        {
            EmployeeDetails employeeDetails = new EmployeeDetails();
            EmployeeDialogController employeeDialogController = new EmployeeDialogController(employeeDetails, DialogMode.DIALOG_MODE_INSERT);
            StageManager stageManager =
                    new StageManager("EmployeeDialog.fxml", Messages.EMPLOYEES_TITLE, employeeDialogController, EmployeeDialogController.class);

                if(!OpenDialog(stageManager))
                    return;

                if(employeeDialogController.getDialogResult() != DialogResult.DIALOG_RESULT_APPLY)
                    return;

                EmployeesData employeesData = new EmployeesData();
                if(!employeesData.insertEmployee(employeeDetails))
                {
                    MessageBox.error(Messages.INSERT_RECORD_FAILED_MESSAGE);
                }

                    MessageBox.information(Messages.SUCCESSFULLY_ADDED_NEW_RECORD_MESSAGE);

                if(!refreshTableView())
                    MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);

        });

        _menuItemUpdate.setOnAction(action ->
        {
            Employees employeeRecord =  _tableView.getSelectionModel().getSelectedItem();;

            if(employeeRecord == null)
                return;

            if(!_table.selectRecordByID(employeeRecord, employeeRecord.getID()))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            UsersTable usersTable = new UsersTable();
            Users employeeAccount = new Users();

            if(!usersTable.selectRecordByID(employeeAccount, employeeRecord.getUserID()))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            EmployeeDetails employeeDetails = new EmployeeDetails(employeeRecord, employeeAccount);

            EmployeeDialogController employeeDialogController = new EmployeeDialogController(employeeDetails, DialogMode.DIALOG_MODE_UPDATE);
            StageManager stageManager =
                    new StageManager("EmployeeDialog.fxml", Messages.EMPLOYEES_TITLE, employeeDialogController, EmployeeDialogController.class);

            if(!OpenDialog(stageManager))
                return;

                if(employeeDialogController.getDialogResult() != DialogResult.DIALOG_RESULT_APPLY)
                    return;

                EmployeesData employeesData = new EmployeesData();
                if(!employeesData.updateEmployee(employeeDetails))
                {
                    MessageBox.error(Messages.UPDATE_RECORD_FAILED_MESSAGE);
                }

                if(!refreshTableView())
                    MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
        });

        _menuItemDelete.setOnAction(action ->
        {
            Employees employeesRecord = _tableView.getSelectionModel().getSelectedItem();

            if(employeesRecord == null)
                return;

            if(!_table.selectRecordByID(employeesRecord,employeesRecord.getID()))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            UsersTable usersTable = new UsersTable();

            Users employeeAccount = new Users();
            if(!usersTable.selectRecordByID(employeeAccount, employeesRecord.getUserID()))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            if(!MessageBox.confirmation(Messages.DELETE_RECORD_QUESTION))
                return;

            EmployeeDetails employeeDetails = new EmployeeDetails(employeesRecord, employeeAccount);

            EmployeesData employeesData = new EmployeesData();
            if(!employeesData.deleteEmployee(employeeDetails))
            {
                MessageBox.error(Messages.DELETE_RECORD_FAILED_MESSAGE);
                return;
            }

            if(!refreshTableView())
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
        });
    }

    @Override
    protected void InitTableViewColumns()
    {
        TableColumn<Employees, String> employeeIDColumn = new TableColumn<>("ID");
        employeeIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Employees, String> employeeFirstNameColumn = new TableColumn<>("First name");
        employeeFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Employees, String> employeeMiddleNameColumn = new TableColumn<>("Middle name");
        employeeMiddleNameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));

        TableColumn<Employees, String> employeeLastNameColumn = new TableColumn<>("Last name");
        employeeLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Employees, String> employeeUCNColumn = new TableColumn<>("UCN");
        employeeUCNColumn.setCellValueFactory(new PropertyValueFactory<>("UCN"));

        TableColumn<Employees, String> employeeDateOfBirthColumn = new TableColumn<>("Date of birth");
        employeeDateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        TableColumn<Employees, String> employeeCityColumn = new TableColumn<>("City");
        employeeCityColumn.setCellValueFactory(employee->
        {
           var filteredCitiesList = _citiesList.stream().filter(city -> employee.getValue().getCityID() == city.getID()).toList();

           if(filteredCitiesList.size() != 1)
               throw new RuntimeException();

            return new SimpleStringProperty(filteredCitiesList.get(0).toString());
        });

        TableColumn<Employees, String> employeeTelephoneNumberColumn= new TableColumn<>("Telephone number");
        employeeTelephoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Employees, String> employeeEmailAddressColumn= new TableColumn<>("Email address");
        employeeEmailAddressColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        _tableView.getColumns().add(employeeIDColumn);
        _tableView.getColumns().add(employeeFirstNameColumn);
        _tableView.getColumns().add(employeeMiddleNameColumn);
        _tableView.getColumns().add(employeeLastNameColumn);
        _tableView.getColumns().add(employeeDateOfBirthColumn);
        _tableView.getColumns().add(employeeCityColumn);
        _tableView.getColumns().add(employeeTelephoneNumberColumn);
        _tableView.getColumns().add(employeeEmailAddressColumn);
    }

    @Override
    protected boolean loadData()
    {
        CitiesTable citiesTable = new CitiesTable();
        _citiesList = new ArrayList<>();
        if(!citiesTable.selectAllRecords(_citiesList))
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
