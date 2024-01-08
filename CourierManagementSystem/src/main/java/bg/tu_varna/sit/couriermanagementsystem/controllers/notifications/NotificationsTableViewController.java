package bg.tu_varna.sit.couriermanagementsystem.controllers.notifications;

import bg.tu_varna.sit.couriermanagementsystem.controllers.base.SmartTableViewController;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.ComparisonTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.LockTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLCriteria;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLQuery;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.notificationstable.NotificationsTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.notifications.Notifications;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.Users;
import bg.tu_varna.sit.couriermanagementsystem.userauthentication.UserAuthentication;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.List;

public class NotificationsTableViewController extends SmartTableViewController<Notifications, Notifications>
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public NotificationsTableViewController()
    {
        super(new NotificationsTable());
    }

    //-------------------------
    //Methods:
    //-------------------------

    //-------------------------
    //Overrides:
    //-------------------------

    @Override
    public void setContextMenu()
    {
    }

    @Override
    protected void InitTableViewColumns()
    {
        TableColumn<Notifications, String> notificationIDColumn = new TableColumn<>("ID");
        notificationIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Notifications, String>  orderIDColumn = new TableColumn<>("Order ID");
        orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("orderID"));

        TableColumn<Notifications, String> messageColumn = new TableColumn<>("Message");
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));

        _tableView.getColumns().add(notificationIDColumn);
        _tableView.getColumns().add(orderIDColumn);
        _tableView.getColumns().add(messageColumn);
    }

    protected boolean refreshTableView()
    {
        List<Notifications> recordsList = new ArrayList<>();

        UserAuthentication userAuthentication = UserAuthentication.getInstance();
        Users currentlyLoggedUser = userAuthentication.getCurrentlyLoggedUser();

        SQLQuery selectNotificationsByUserID = new SQLQuery(_table.getTableName(), LockTypes.READ_ONLY);
        selectNotificationsByUserID.addCriteria(new SQLCriteria(NotificationsTable.NotificationsTableColumns.USER_ID.getColumnName(), ComparisonTypes.EQUALS,
                currentlyLoggedUser.getID()));

        if(!_table.selectAllRecordsWhere(recordsList, selectNotificationsByUserID))
        {
            return false;
        }

        _tableView.getItems().clear();

        if(!_tableView.getItems().addAll(recordsList) && recordsList.size() > 0)
            return false;

        return true;
    }

}
