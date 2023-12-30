package bg.tu_varna.sit.couriermanagementsystem.database.tables.employees;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.BaseTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.Column;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.DataMap;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.employees.EmployeeStatistics;

public class EmployeeStatisticsTable extends BaseTable<EmployeeStatistics>
{
    //-------------------------
    //Constants:
    //-------------------------
    public enum EmployeesStatisticsTableColumns
    {
        EMPLOYEE_ID("EMPLOYEE_ID"),
        FIRST_NAME("FIRST_NAME"),
        LAST_NAME("LAST_NAME"),
        EMPLOYEE_UCN("EMPLOYEE_UCN"),
        COMPANY_ID("COMPANY_ID"),
        COMPANY_NAME("COMPANY_NAME"),
        TOTAL_ORDERS("TOTAL_ORDERS");

        private String _columnName;
        EmployeesStatisticsTableColumns(String columnName)
        {
            _columnName = columnName;
        }

        public String getColumnName() {return this._columnName;}
    }

    //-------------------------
    //Members:
    //-------------------------

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------

    //-------------------------
    //Methods:
    //-------------------------

    //-------------------------
    //Overrides:
    //-------------------------

    @Override
    protected void loadDataMap()
    {
        try
        {
            _dataMap = new DataMap(EmployeeStatistics.class, "VI_EMPLOYEE_STATISTICS");
            _dataMap.addNewColumn(new Column(EmployeesStatisticsTableColumns.EMPLOYEE_ID.getColumnName(),           "_employeeID",                _dataMap));
            _dataMap.addNewColumn(new Column(EmployeesStatisticsTableColumns.FIRST_NAME.getColumnName(),            "_employeeFirstName",         _dataMap));
            _dataMap.addNewColumn(new Column(EmployeesStatisticsTableColumns.LAST_NAME.getColumnName(),             "_employeeLastName",          _dataMap));
            _dataMap.addNewColumn(new Column(EmployeesStatisticsTableColumns.EMPLOYEE_UCN.getColumnName(),          "_UCN",                       _dataMap));
            _dataMap.addNewColumn(new Column(EmployeesStatisticsTableColumns.COMPANY_ID.getColumnName(),            "_companyID",                 _dataMap));
            _dataMap.addNewColumn(new Column(EmployeesStatisticsTableColumns.COMPANY_NAME.getColumnName(),          "_companyName",               _dataMap));
            _dataMap.addNewColumn(new Column(EmployeesStatisticsTableColumns.TOTAL_ORDERS.getColumnName(),          "_totalOrders",               _dataMap));

        }
        catch (NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
        }
    }
}
