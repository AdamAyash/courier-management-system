package bg.tu_varna.sit.couriermanagementsystem.database.tables.employees;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.BaseTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.Column;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.DataMap;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.employees.Employees;

import java.sql.Connection;

/**/
public class EmployeesTable extends BaseTable<Employees>
{
    //-------------------------
    //Constants:
    //-------------------------
    public enum EmployeesTableColumns
    {
        ID("ID"),
        UPDATE_COUNTER("UPDATE_COUNTER"),
        FIRST_NAME("FIRST_NAME"),
        MIDDLE_NAME("MIDDLE_NAME"),
        LAST_NAME("LAST_NAME"),
        UCN("UCN"),
        DATE_OF_BIRTH("DATE_OF_BIRTH"),
        GENDER("GENDER"),
        TELEPHONE_NUMBER("TELEPHONE_NUMBER"),
        USER_ID("USER_ID"),
        CITY_ID("CITY_ID"),
        COMPANY_ID("COMPANY_ID"),
        EMAIL("EMAIL");
        private String _columnName;
        EmployeesTableColumns(String columnName)
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

    public EmployeesTable(Connection connection)
    {
        super(connection);
    }
    public EmployeesTable()
    {

    }

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
            _dataMap = new DataMap(Employees.class, "EMPLOYEES");
            _dataMap.addNewColumn(new Column(EmployeesTableColumns.ID.getColumnName(),              "_ID",                         _dataMap));
            _dataMap.addNewColumn(new Column(EmployeesTableColumns.UPDATE_COUNTER.getColumnName(),  "_updateCounter",              _dataMap, true));
            _dataMap.addNewColumn(new Column(EmployeesTableColumns.FIRST_NAME.getColumnName(),      "_firstName",                  _dataMap));
            _dataMap.addNewColumn(new Column(EmployeesTableColumns.MIDDLE_NAME.getColumnName(),     "_middleName",                _dataMap));
            _dataMap.addNewColumn(new Column(EmployeesTableColumns.EMAIL.getColumnName(),           "_email",                      _dataMap));
            _dataMap.addNewColumn(new Column(EmployeesTableColumns.LAST_NAME.getColumnName(),       "_lastName",                   _dataMap));
            _dataMap.addNewColumn(new Column(EmployeesTableColumns.UCN.getColumnName(),             "_UCN",                        _dataMap));
            _dataMap.addNewColumn(new Column(EmployeesTableColumns.DATE_OF_BIRTH.getColumnName(),   "_dateOfBirth",                _dataMap));
            _dataMap.addNewColumn(new Column(EmployeesTableColumns.GENDER.getColumnName(),          "_gender",                     _dataMap));
            _dataMap.addNewColumn(new Column(EmployeesTableColumns.TELEPHONE_NUMBER.getColumnName(),"_phoneNumber",                _dataMap));
            _dataMap.addNewColumn(new Column(EmployeesTableColumns.USER_ID.getColumnName(),         "_userID",                     _dataMap));
            _dataMap.addNewColumn(new Column(EmployeesTableColumns.CITY_ID.getColumnName(),         "_cityID",                     _dataMap));
            _dataMap.addNewColumn(new Column(EmployeesTableColumns.COMPANY_ID.getColumnName(),      "_companyID",                  _dataMap));
            _dataMap.addNewColumn(new Column(EmployeesTableColumns.UCN.getColumnName(),             "_UCN",                        _dataMap));

        }
        catch (NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
        }
    }

}
