package bg.tu_varna.sit.couriermanagementsystem.database.tables.userstable;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.BaseTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.Column;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.DataMap;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.Users;


public class UsersTable extends BaseTable<Users>
{
    //-------------------------
    //Constants:
    //-------------------------
    public enum UsersTableColumns
    {
        ID("ID"),
        UPDATE_COUNTER("UPDATE_COUNTER"),
        USERNAME("USERNAME"),
        PASSWORD("PASSWORD"),
        ACCESS_ID("ACCESS_ID");

        private String _columnName;

        UsersTableColumns(String columnName)
        {
            _columnName = columnName;
        }

        public String getColumnName()
        {
            return this._columnName;
        }
    };

    //-------------------------
    //Members:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public UsersTable()
    {
        super();
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
            _dataMap = new DataMap(Users.class, "USERS");
            _dataMap.addNewColumn(new Column(UsersTableColumns.ID.getColumnName(),              "_ID",                  _dataMap));
            _dataMap.addNewColumn(new Column(UsersTableColumns.UPDATE_COUNTER.getColumnName(),  "_updateCounter",       _dataMap));
            _dataMap.addNewColumn(new Column(UsersTableColumns.USERNAME.getColumnName(),        "_username",            _dataMap));
            _dataMap.addNewColumn(new Column(UsersTableColumns.PASSWORD.getColumnName(),        "_password",            _dataMap));
        }
        catch (NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
        }
    }
}
