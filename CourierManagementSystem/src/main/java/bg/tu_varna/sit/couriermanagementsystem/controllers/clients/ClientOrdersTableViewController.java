package bg.tu_varna.sit.couriermanagementsystem.controllers.clients;

import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogMode;
import bg.tu_varna.sit.couriermanagementsystem.controllers.orders.OrdersDialogController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.orders.OrdersTableViewController;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.ComparisonTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.LockTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLCriteria;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLQuery;
import bg.tu_varna.sit.couriermanagementsystem.database.storedprocedures.Parameter;
import bg.tu_varna.sit.couriermanagementsystem.database.storedprocedures.StoredProcedure;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.clientstable.ClientsTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.orders.OrdersTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.orders.OrdersViewTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.clients.Clients;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.orders.OrderStatuses;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.orders.Orders;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.orders.OrdersView;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.Users;
import bg.tu_varna.sit.couriermanagementsystem.stages.StageManager;
import bg.tu_varna.sit.couriermanagementsystem.userauthentication.UserAuthentication;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import java.util.ArrayList;
import java.util.List;

/**/
public class ClientOrdersTableViewController extends OrdersTableViewController
{
    //-------------------------
    //Constants:
    //-------------------------

    private final int INVALID_ORDER_ID = -1;
    private final String SP_REJECT_ORDER = "SP_REJECT_ORDER";

    //-------------------------
    //Members:
    //-------------------------
    private int _orderID;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public ClientOrdersTableViewController()
    {
        super();
        _orderID = INVALID_ORDER_ID;
    }

    public ClientOrdersTableViewController(int orderID)
    {
        super();
        _orderID = orderID;
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
        _contextMenu = new ContextMenu();

        MenuItem menuItemRejectOrder = new MenuItem("Reject order");

        _menuItemPreview = new MenuItem("Preview");
        _menuItemRefresh = new MenuItem("Refresh");

        _menuItemRefresh.setOnAction(action ->
        {
            if(!refreshTableView())
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
        });

        _contextMenu.getItems().add(_menuItemPreview);
        _contextMenu.getItems().add(_menuItemRefresh);
        _contextMenu.getItems().add(menuItemRejectOrder);

        _tableView.setContextMenu(_contextMenu);

        _menuItemPreview.setOnAction(action ->
        {
            Orders ordersRecord = new Orders();
            final OrdersView ordersViewRecord = _tableView.getSelectionModel().getSelectedItem();

            if(ordersViewRecord == null)
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            final OrdersTable ordersTable = new OrdersTable();
            if(!ordersTable.selectRecordByID(ordersRecord, ordersViewRecord.getOrderID()))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            OrdersDialogController ordersDialogController = new OrdersDialogController(ordersRecord, DialogMode.DIALOG_MODE_PREVIEW);

            StageManager stageManager =
                    new StageManager("OrdersDialog.fxml", Messages.ORDERS_TITLE, ordersDialogController, OrdersDialogController.class);

            OpenDialog(stageManager);

        });

        menuItemRejectOrder.setOnAction(action ->
        {
            OrdersView ordersView = _tableView.getSelectionModel().getSelectedItem();

            if(ordersView == null)
                return;

            if(ordersView.getOrderStatus() == OrderStatuses.REJECTED.getStatusName())
            {
                MessageBox.information(Messages.CANNOT_REJECT_DELIVERED_ORDER_MESSAGE);
                return;
            }

            final UserAuthentication userAuthentication = UserAuthentication.getInstance();

            StoredProcedure storedProcedure = new StoredProcedure(SP_REJECT_ORDER);
            storedProcedure.addParameter(new Parameter<Integer>(ordersView.getOrderID()));
            storedProcedure.addParameter(new Parameter<Integer>(userAuthentication.getCurrentlyLoggedUser().getID()));

            if(!storedProcedure.executeStoredProcedure())
            {
                MessageBox.error(Messages.UPDATE_RECORD_FAILED_MESSAGE);
                return;
            }
        });
    }

    @Override
    protected boolean refreshTableView()
    {
        final UserAuthentication userAuthentication = UserAuthentication.getInstance();
        Users currentlyLoggedUser = userAuthentication.getCurrentlyLoggedUser();

        final ClientsTable clientsTable = new ClientsTable();
        Clients currentlyLoggedClient = new Clients();

        SQLQuery selectClientByUserIDQuery = new SQLQuery(clientsTable.getTableName(), LockTypes.READ_ONLY);
        selectClientByUserIDQuery.addCriteria(new SQLCriteria(ClientsTable.ClientsTableColumns.USER_ID.getColumnName(), ComparisonTypes.EQUALS, currentlyLoggedUser.getID()));

        if(!clientsTable.selectRecordWhere(currentlyLoggedClient, selectClientByUserIDQuery))
            return false;

        SQLQuery selectOrdersByClientQuery = new SQLQuery(_table.getTableName(), LockTypes.READ_ONLY);
        selectOrdersByClientQuery.addCriteria(new SQLCriteria(OrdersViewTable.OrdersViewTableColumns.CLIENT_ID.getColumnName(),
                ComparisonTypes.EQUALS, currentlyLoggedClient.getID()));

        if(_orderID != INVALID_ORDER_ID)
        {
            selectOrdersByClientQuery.addCriteria(new SQLCriteria(OrdersViewTable.OrdersViewTableColumns.ORDER_ID.getColumnName(),
                    ComparisonTypes.EQUALS, _orderID));
        }

        List<OrdersView> ordersViewList = new ArrayList<>();
        if(!_table.selectAllRecordsWhere(ordersViewList, selectOrdersByClientQuery))
        {
            MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
            return false;
        }

        _tableView.getItems().clear();

        if(!_tableView.getItems().addAll(ordersViewList) && ordersViewList.size() > 0)
            return false;

        return true;
    }
}

