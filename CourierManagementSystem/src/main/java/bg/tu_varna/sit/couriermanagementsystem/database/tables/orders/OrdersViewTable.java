package bg.tu_varna.sit.couriermanagementsystem.database.tables.orders;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.BaseTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.Column;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.DataMap;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.orders.OrdersView;

/**/
public class OrdersViewTable extends BaseTable<OrdersView>
{
    //-------------------------
    //Constants:
    //-------------------------
    public enum OrdersViewTableColumns
    {

        ORDER_ID("ORDER_ID"),
        ORDER_STATUS("ORDER_STATUS"),
        ORDER_TYPE("ORDER_TYPE"),
        ORDER_PRICE("ORDER_PRICE"),
        OFFICE_NAME("OFFICE_NAME"),
        COMPANY_NAME("COMPANY_NAME"),
        COMPANY_BULSTAT("COMPANY_BULSTAT"),
        EMPLOYEE_ID("EMPLOYEE_ID"),
        EMPLOYEE_FULL_NAME("EMPLOYEE_FULL_NAME"),
        CLIENT_ID("CLIENT_ID"),
        CLIENT_FULL_NAME("CLIENT_FULL_NAME"),
        CLIENT_UCN("CLIENT_UCN"),
        DATE_REGISTERED("DATE_REGISTERED"),
        DELIVERY_DATE("DELIVERY_DATE");

        private String _columnName;
        OrdersViewTableColumns(String columnName) {_columnName = columnName;}

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
            _dataMap = new DataMap(OrdersView.class, "VI_ORDERS");
            _dataMap.addNewColumn(new Column(OrdersViewTableColumns.ORDER_ID.getColumnName(),           "_orderID",             _dataMap));
            _dataMap.addNewColumn(new Column(OrdersViewTableColumns.ORDER_STATUS.getColumnName(),       "_orderStatus",         _dataMap));
            _dataMap.addNewColumn(new Column(OrdersViewTableColumns.ORDER_TYPE.getColumnName(),         "_orderType",           _dataMap));
            _dataMap.addNewColumn(new Column(OrdersViewTableColumns.ORDER_PRICE.getColumnName(),        "_orderPrice",          _dataMap));
            _dataMap.addNewColumn(new Column(OrdersViewTableColumns.OFFICE_NAME.getColumnName(),        "_officeName",          _dataMap));
            _dataMap.addNewColumn(new Column(OrdersViewTableColumns.COMPANY_NAME.getColumnName(),       "_companyName",         _dataMap));
            _dataMap.addNewColumn(new Column(OrdersViewTableColumns.COMPANY_BULSTAT.getColumnName(),    "_companyEGFN",         _dataMap));
            _dataMap.addNewColumn(new Column(OrdersViewTableColumns.EMPLOYEE_ID.getColumnName(),        "_employeeID",          _dataMap));
            _dataMap.addNewColumn(new Column(OrdersViewTableColumns.EMPLOYEE_FULL_NAME.getColumnName(), "_employeeFullName",    _dataMap));
            _dataMap.addNewColumn(new Column(OrdersViewTableColumns.CLIENT_ID.getColumnName(),          "_clientID",            _dataMap));
            _dataMap.addNewColumn(new Column(OrdersViewTableColumns.CLIENT_FULL_NAME.getColumnName(),   "_clientFullName",      _dataMap));
            _dataMap.addNewColumn(new Column(OrdersViewTableColumns.CLIENT_UCN.getColumnName(),         "_clientUCN",           _dataMap));
            _dataMap.addNewColumn(new Column(OrdersViewTableColumns.DATE_REGISTERED.getColumnName(),    "_dateRegistered",      _dataMap));
            _dataMap.addNewColumn(new Column(OrdersViewTableColumns.DELIVERY_DATE.getColumnName(),      "_deliveryDate",        _dataMap));
        }
        catch (NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
        }
    }
}
