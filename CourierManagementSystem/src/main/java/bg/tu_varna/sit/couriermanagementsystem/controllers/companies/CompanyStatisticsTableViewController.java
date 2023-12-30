package bg.tu_varna.sit.couriermanagementsystem.controllers.companies;

import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.SmartTableViewController;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.companiestable.CompaniesStatisticsTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies.CompanyStatistics;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class CompanyStatisticsTableViewController extends SmartTableViewController<CompanyStatistics, CompanyStatistics>
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
    public CompanyStatisticsTableViewController()
    {
        super(new CompaniesStatisticsTable());
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
        TableColumn<CompanyStatistics, String> companyIDColumn = new TableColumn<>("Company ID");
        companyIDColumn.setCellValueFactory(new PropertyValueFactory<>("companyID"));

        TableColumn<CompanyStatistics, String> companyNameColumn = new TableColumn<>("Company name");
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));

        TableColumn<CompanyStatistics, String> totalClientsColumn = new TableColumn<>("Total clients");
        totalClientsColumn.setCellValueFactory(new PropertyValueFactory<>("totalClients"));

        TableColumn<CompanyStatistics, String> totalEmployeesColumn = new TableColumn<>("Total employees");
        totalEmployeesColumn.setCellValueFactory(new PropertyValueFactory<>("totalEmployees"));

        TableColumn<CompanyStatistics, String> totalAmountColumn = new TableColumn<>("Total amount");
        totalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        _tableView.getColumns().add(companyIDColumn);
        _tableView.getColumns().add(companyNameColumn);
        _tableView.getColumns().add(totalClientsColumn);
        _tableView.getColumns().add(totalEmployeesColumn);
        _tableView.getColumns().add(totalAmountColumn);
    }

    @Override
    public boolean loadData()
    {
        if(!refreshTableView())
        {
            MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
            return false;
        }
        return true;
    }
}
