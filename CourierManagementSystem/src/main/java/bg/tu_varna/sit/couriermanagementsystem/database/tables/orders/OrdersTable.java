package bg.tu_varna.sit.couriermanagementsystem.database.tables.orders;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.BaseTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.Column;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.DataMap;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.orders.Orders;

import java.sql.Connection;

public class OrdersTable extends BaseTable<Orders>
{
    //-------------------------
    //Constants:
    //-------------------------
    public enum OrdersTableColumns
    {
        ID("ID"),
        UPDATE_COUNTER("UPDATE_COUNTER"),
        OFFICE_ID("OFFICE_ID"),
        ORDER_TYPE_ID("ORDER_TYPE_ID"),
        EMPLOYEE_ID("EMPLOYEE_ID"),
        CLIENT_ID("CLIENT_ID"),
        DATE_REGISTERED("DATE_REGISTERED"),
        STATUS("STATUS");
        private String _columnName;
        OrdersTableColumns(String columnName)  {_columnName = columnName;}

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
    public OrdersTable()
    {
    }

    public OrdersTable(Connection connection)
    {
        super(connection);
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
            _dataMap = new DataMap(Orders.class, "ORDERS");
            _dataMap.addNewColumn(new Column(OrdersTableColumns.ID.getColumnName(),                    "_ID",                   _dataMap));
            _dataMap.addNewColumn(new Column(OrdersTableColumns.UPDATE_COUNTER.getColumnName(),        "_updateCounter",        _dataMap, true));
            _dataMap.addNewColumn(new Column(OrdersTableColumns.OFFICE_ID.getColumnName(),             "_officeID",             _dataMap));
            _dataMap.addNewColumn(new Column(OrdersTableColumns.ORDER_TYPE_ID.getColumnName(),         "_orderTypeID",          _dataMap));
            _dataMap.addNewColumn(new Column(OrdersTableColumns.EMPLOYEE_ID.getColumnName(),           "_employeeID",           _dataMap));
            _dataMap.addNewColumn(new Column(OrdersTableColumns.CLIENT_ID.getColumnName(),             "_clientID",             _dataMap));
            _dataMap.addNewColumn(new Column(OrdersTableColumns.DATE_REGISTERED.getColumnName(),       "_dateRegistered",       _dataMap));
            _dataMap.addNewColumn(new Column(OrdersTableColumns.STATUS.getColumnName(),                "_status",               _dataMap));
        }
        catch (NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
        }
    }
}
