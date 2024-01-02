package bg.tu_varna.sit.couriermanagementsystem.controllers.employees;

import bg.tu_varna.sit.couriermanagementsystem.controllers.orders.OrdersTableViewController;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.ComparisonTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.LockTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLCriteria;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLQuery;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.employees.EmployeesTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.orders.OrdersViewTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.employees.Employees;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.orders.OrdersView;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.Users;
import bg.tu_varna.sit.couriermanagementsystem.userauthentication.UserAuthentication;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**/
public class OrdersReportTableViewController extends OrdersTableViewController
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private Date _dateFrom;
    private Date _dateTo;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public OrdersReportTableViewController(Date dateFrom, Date dateTo)
    {
        super();
        _dateFrom = dateFrom;
        _dateTo = dateTo;
    }

    //-------------------------
    //Methods:
    //-------------------------

    //-------------------------
    //Overrides:
    //-------------------------
    @Override
    protected boolean refreshTableView()
    {
        List<OrdersView> recordsList = new ArrayList<>();

        Users currentlyLoggedUser;
        final UserAuthentication userAuthentication = UserAuthentication.getInstance();
        currentlyLoggedUser = userAuthentication.getCurrentlyLoggedUser();

        if(currentlyLoggedUser == null)
            return false;

        final EmployeesTable employeesTable = new EmployeesTable();

        SQLQuery selectCurrentlyLoggedEmployee = new SQLQuery(employeesTable.getTableName(), LockTypes.READ_ONLY);
        selectCurrentlyLoggedEmployee.addCriteria(new SQLCriteria(EmployeesTable.EmployeesTableColumns.USER_ID.getColumnName(),
                ComparisonTypes.EQUALS ,currentlyLoggedUser.getID()));

        Employees currentlyLoggedEmployee = new Employees();
        if(!employeesTable.selectRecordWhere(currentlyLoggedEmployee, selectCurrentlyLoggedEmployee))
            return false;

        if(currentlyLoggedEmployee == null)
            return  false;

        List<String> periodList = new ArrayList<>();
        periodList.add(_dateFrom.toString());
        periodList.add(_dateTo.toString());

        SQLQuery selectAllOrdersByCompanyAndPeriod = new SQLQuery(_table.getTableName(), LockTypes.READ_ONLY);
        selectAllOrdersByCompanyAndPeriod.addCriteria(new SQLCriteria(OrdersViewTable.OrdersViewTableColumns.COMPANY_ID.getColumnName(),
                ComparisonTypes.EQUALS, currentlyLoggedEmployee.getCompanyID()));
        selectAllOrdersByCompanyAndPeriod.addCriteria(new SQLCriteria(OrdersViewTable.OrdersViewTableColumns.DATE_REGISTERED.getColumnName(),
                ComparisonTypes.BETWEEN, periodList));

        if(!_table.selectAllRecordsWhere(recordsList, selectAllOrdersByCompanyAndPeriod))
            return false;

        _tableView.getItems().clear();

        if(!_tableView.getItems().addAll(recordsList) && recordsList.size() > 0)
            return false;

        return true;
    }
}
