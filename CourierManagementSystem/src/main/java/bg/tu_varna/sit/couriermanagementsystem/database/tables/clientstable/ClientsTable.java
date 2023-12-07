package bg.tu_varna.sit.couriermanagementsystem.database.tables.clientstable;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.BaseTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.Column;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.DataMap;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.clients.Clients;

import java.sql.Connection;

public class ClientsTable extends BaseTable<Clients>
{
    //-------------------------
    //Constants:
    //-------------------------
    public enum ClientsTableColumns
    {
        ID("ID"),
        UPDATE_COUNTER("UPDATE_COUNTER"),
        FIRST_NAME("FIRST_NAME"),
        MIDDLE_NAME("MIDDLE_NAME"),
        LAST_NAME("LAST_NAME"),
        UCN("UCN"),
        COMPANY_ID("COMPANY_ID"),
        USER_ID("USER_ID"),
        PHONE_NUMBER("PHONE_NUMBER");

        private String _columnName;

        ClientsTableColumns(String columnName) {_columnName = columnName;}

        public String getColumnName()
        {
            return this._columnName;
        }
    };

    //-------------------------
    //Members:
    //-------------------------

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public ClientsTable(Connection connection)
    {
        super(connection);
    }

    public ClientsTable()
    {
    }

    //-------------------------
    //Methods:
    //-------------------------

    //------------------------
    //Overrides:
    //-------------------------
    @Override
    protected void loadDataMap()
    {
        try
        {
            _dataMap = new DataMap(Clients.class, "CLIENTS");
            _dataMap.addNewColumn(new Column(ClientsTableColumns.ID.getColumnName(),               "_ID",                   _dataMap));
            _dataMap.addNewColumn(new Column(ClientsTableColumns.UPDATE_COUNTER.getColumnName(),   "_updateCounter",        _dataMap, true));
            _dataMap.addNewColumn(new Column(ClientsTableColumns.FIRST_NAME.getColumnName(),       "_firstName",            _dataMap));
            _dataMap.addNewColumn(new Column(ClientsTableColumns.MIDDLE_NAME.getColumnName(),      "_middleName",           _dataMap));
            _dataMap.addNewColumn(new Column(ClientsTableColumns.LAST_NAME.getColumnName(),        "_lastName",             _dataMap));
            _dataMap.addNewColumn(new Column(ClientsTableColumns.UCN.getColumnName(),              "_UCN",                  _dataMap));
            _dataMap.addNewColumn(new Column(ClientsTableColumns.COMPANY_ID.getColumnName(),       "_companyID",            _dataMap));
            _dataMap.addNewColumn(new Column(ClientsTableColumns.USER_ID.getColumnName(),          "_userID",               _dataMap));
            _dataMap.addNewColumn(new Column(ClientsTableColumns.PHONE_NUMBER.getColumnName(),     "_phoneNumber",          _dataMap));

        }
        catch (NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
        }
    }
}
