package bg.tu_varna.sit.couriermanagementsystem.database.tables.notificationstable;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.BaseTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.Column;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.DataMap;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.notifications.Notifications;
public class NotificationsTable extends BaseTable<Notifications>
{
    //-------------------------
    //Constants:
    //-------------------------
    public enum NotificationsTableColumns
    {
        ID("ID"),
        USER_ID("USER_ID"),
        ORDER_ID("ORDER_ID"),
        MESSAGE("MESSAGE"),
        REGISTER_DATE("REGISTER_DATE"),
        SEEN_BY_USER("SEEN_BY_USER");
        private String _columnName;
        NotificationsTableColumns(String columnName)
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
    public NotificationsTable()
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
            _dataMap = new DataMap(Notifications.class, "NOTIFICATIONS");
            _dataMap.addNewColumn(new Column(NotificationsTableColumns.ID.getColumnName(),               "_ID",                    _dataMap));
            _dataMap.addNewColumn(new Column(NotificationsTableColumns.USER_ID.getColumnName(),          "_userID",                _dataMap));
            _dataMap.addNewColumn(new Column(NotificationsTableColumns.ORDER_ID.getColumnName(),         "_orderID",               _dataMap));
            _dataMap.addNewColumn(new Column(NotificationsTableColumns.MESSAGE.getColumnName(),          "_message",               _dataMap));
            _dataMap.addNewColumn(new Column(NotificationsTableColumns.REGISTER_DATE.getColumnName(),    "_registerDate",          _dataMap));
            _dataMap.addNewColumn(new Column(NotificationsTableColumns.SEEN_BY_USER.getColumnName(),     "_seenByUser",            _dataMap));
        }
        catch (NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
        }
    }
}
