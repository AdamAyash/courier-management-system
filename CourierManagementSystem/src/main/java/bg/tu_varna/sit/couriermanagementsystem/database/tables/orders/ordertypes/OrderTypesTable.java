package bg.tu_varna.sit.couriermanagementsystem.database.tables.orders.ordertypes;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.BaseTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.Column;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.DataMap;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.orders.ordertypes.OrderTypes;

public class OrderTypesTable extends BaseTable<OrderTypes>
{
    //-------------------------
    //Constants:
    //-------------------------

    public enum OrderTypesColumns
    {
        ID("ID"),
        NAME("NAME"),
        PRICE("PRICE");
        private String _columnName;
        OrderTypesColumns(String columnName)  {_columnName = columnName;}

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
            _dataMap = new DataMap(OrderTypes.class, "ORDER_TYPES");
            _dataMap.addNewColumn(new Column(OrderTypesColumns.ID.getColumnName(),                      "_ID",                   _dataMap));
            _dataMap.addNewColumn(new Column(OrderTypesColumns.NAME.getColumnName(),                    "_name",                 _dataMap));
            _dataMap.addNewColumn(new Column(OrderTypesColumns.PRICE.getColumnName(),                   "_price",                _dataMap));
        }
        catch (NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
        }
    }
}
