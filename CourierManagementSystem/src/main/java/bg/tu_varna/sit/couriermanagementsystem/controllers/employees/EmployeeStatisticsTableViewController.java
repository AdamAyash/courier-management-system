package bg.tu_varna.sit.couriermanagementsystem.controllers.employees;

import bg.tu_varna.sit.couriermanagementsystem.controllers.base.SmartTableViewController;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.employees.EmployeeStatisticsTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.employees.EmployeeStatistics;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeeStatisticsTableViewController extends SmartTableViewController<EmployeeStatistics, EmployeeStatistics>
{

    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public EmployeeStatisticsTableViewController()
    {
        super(new EmployeeStatisticsTable());
    }

    //-------------------------
    //Methods:
    //-------------------------

    //-------------------------
    //Overrides:
    //-------------------------
    @Override
    protected void InitTableViewColumns()
    {
        TableColumn<EmployeeStatistics, String> employeeIDColumn = new TableColumn<>("Employee ID");
        employeeIDColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));

        TableColumn<EmployeeStatistics, String> employeeFirstNameColumn = new TableColumn<>("First name");
        employeeFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeFirstName"));

        TableColumn<EmployeeStatistics, String> employeeLastNameColumn = new TableColumn<>("Last name");
        employeeLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeLastName"));

        TableColumn<EmployeeStatistics, String> employeeUCNColumn = new TableColumn<>("UCN");
        employeeUCNColumn.setCellValueFactory(new PropertyValueFactory<>("UCN"));

        TableColumn<EmployeeStatistics, String> employeeCompanyIDColumn = new TableColumn<>("Company ID");
        employeeCompanyIDColumn.setCellValueFactory(new PropertyValueFactory<>("companyID"));

        TableColumn<EmployeeStatistics, String> employeeCompanyNameColumn = new TableColumn<>("Company name");
        employeeCompanyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));

        TableColumn<EmployeeStatistics, String> employeeTotalOrdersColumn = new TableColumn<>("Total orders");
        employeeTotalOrdersColumn.setCellValueFactory(new PropertyValueFactory<>("totalOrders"));

        _tableView.getColumns().add(employeeIDColumn);
        _tableView.getColumns().add(employeeFirstNameColumn);
        _tableView.getColumns().add(employeeLastNameColumn);
        _tableView.getColumns().add(employeeUCNColumn);
        _tableView.getColumns().add(employeeCompanyIDColumn);
        _tableView.getColumns().add(employeeCompanyNameColumn);
        _tableView.getColumns().add(employeeTotalOrdersColumn);
    }
}
