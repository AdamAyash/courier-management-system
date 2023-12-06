package bg.tu_varna.sit.couriermanagementsystem.database.tables.clientstable;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.BaseTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.Column;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.DataMap;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.clients.Clients;

public class ClientsTable extends BaseTable<Clients>
{
    //-------------------------
    //Constants:
    //-------------------------
    public enum ClientsTableColumns
    {
        ID("ID"),
        UPDATE_COUNTER("UPDATE_COUNTER"),
        NAME("NAME"),
        UCN("UCN"),
        COMPANY_ID("COMPANY_ID"),
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
            _dataMap.addNewColumn(new Column(ClientsTable.ClientsTableColumns.ID.getColumnName(),               "_ID",                   _dataMap));
            _dataMap.addNewColumn(new Column(ClientsTable.ClientsTableColumns.UPDATE_COUNTER.getColumnName(),   "_updateCounter",        _dataMap, true));
            _dataMap.addNewColumn(new Column(ClientsTable.ClientsTableColumns.NAME.getColumnName(),             "_name",                 _dataMap));
            _dataMap.addNewColumn(new Column(ClientsTable.ClientsTableColumns.UCN.getColumnName(),              "_UCN",                  _dataMap));
            _dataMap.addNewColumn(new Column(ClientsTable.ClientsTableColumns.COMPANY_ID.getColumnName(),       "_companyID",            _dataMap));
            _dataMap.addNewColumn(new Column(ClientsTable.ClientsTableColumns.PHONE_NUMBER.getColumnName(),     "_phoneNumber",          _dataMap));

        }
        catch (NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
        }
    }
}
