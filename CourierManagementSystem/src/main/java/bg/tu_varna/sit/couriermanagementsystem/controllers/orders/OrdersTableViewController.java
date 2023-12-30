package bg.tu_varna.sit.couriermanagementsystem.controllers.orders;

import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogMode;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogResult;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.SmartTableViewController;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.ComparisonTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.LockTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLCriteria;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLQuery;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.employees.EmployeesTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.orders.OrdersTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.orders.OrdersViewTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.employees.Employees;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.orders.Orders;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.orders.OrdersView;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.Users;
import bg.tu_varna.sit.couriermanagementsystem.stages.StageManager;
import bg.tu_varna.sit.couriermanagementsystem.userauthentication.UserAuthentication;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.List;

/**/
public class OrdersTableViewController extends SmartTableViewController<Orders, OrdersView>
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
    public OrdersTableViewController()
    {
        super(new OrdersViewTable());
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
        super.setContextMenu();

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

        _menuItemInsert.setOnAction(action ->
        {
            Orders newOrderRecord = new Orders();
            OrdersDialogController ordersDialogController = new OrdersDialogController(newOrderRecord, DialogMode.DIALOG_MODE_INSERT);

            StageManager stageManager =
                    new StageManager("OrdersDialog.fxml", Messages.ORDERS_TITLE, ordersDialogController, OrdersDialogController.class);

            if(!OpenDialog(stageManager))
                return;

            if(ordersDialogController.getDialogResult() != DialogResult.DIALOG_RESULT_APPLY)
                return;

            OrdersTable ordersTable = new OrdersTable();
            if(!ordersTable.insertRecord(newOrderRecord))
            {
                MessageBox.error(Messages.INSERT_RECORD_FAILED_MESSAGE);
                return;
            }

                MessageBox.information(Messages.SUCCESSFULLY_ADDED_NEW_RECORD_MESSAGE);

            if(!refreshTableView())
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
        });

        _menuItemUpdate.setOnAction(action ->
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

            OrdersDialogController ordersDialogController = new OrdersDialogController(ordersRecord, DialogMode.DIALOG_MODE_UPDATE);

            StageManager stageManager =
                    new StageManager("OrdersDialog.fxml", Messages.ORDERS_TITLE, ordersDialogController, OrdersDialogController.class);

            if(!OpenDialog(stageManager))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            if(!ordersTable.updateRecord(ordersRecord))
            {
                MessageBox.error(Messages.UPDATE_RECORD_FAILED_MESSAGE);
                return;
            }

            if(!refreshTableView())
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
        });

        _menuItemDelete.setOnAction(action ->
        {

            OrdersView ordersViewRecord = _tableView.getSelectionModel().getSelectedItem();

            if(ordersViewRecord == null)
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

           final OrdersTable ordersTable = new OrdersTable();

            Orders ordersRecord = new Orders();
            if(!ordersTable.selectRecordByID(ordersRecord, ordersViewRecord.getOrderID()))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            if(!MessageBox.confirmation(Messages.DELETE_RECORD_QUESTION))
                return;

            if(!ordersTable.deleteRecord(ordersRecord))
            {
                MessageBox.error(Messages.DELETE_RECORD_FAILED_MESSAGE);
                return;
            }

            if(!refreshTableView())
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
        });
    }

    @Override
    public void InitTableViewColumns()
    {
        TableColumn<OrdersView, String> orderIDColumn = new TableColumn<>("ID");
        orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("orderID"));

        TableColumn<OrdersView, String> orderStatusColumn = new TableColumn<>("Order status");
        orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

        TableColumn<OrdersView, String> orderTypeColumn = new TableColumn<>("Order type");
        orderTypeColumn.setCellValueFactory(new PropertyValueFactory<>("orderType"));

        TableColumn<OrdersView, String> orderPriceColumn = new TableColumn<>("Price");
        orderPriceColumn.setCellValueFactory(new PropertyValueFactory<>("orderPrice"));

        TableColumn<OrdersView, String>  companyNameColumn = new TableColumn<>("Company");
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));

        TableColumn<OrdersView, String>  companyEGFNColumn = new TableColumn<>("Company EGFN");
        companyEGFNColumn.setCellValueFactory(new PropertyValueFactory<>("companyEGFN"));

        TableColumn<OrdersView, String>  officeNameColumn = new TableColumn<>("Office");
        officeNameColumn.setCellValueFactory(new PropertyValueFactory<>("officeName"));

        TableColumn<OrdersView, String>  employeeFullNameColumn = new TableColumn<>("Employee name");
        employeeFullNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeFullName"));

        TableColumn<OrdersView, String>  clientFullNameColumn = new TableColumn<>("Client name");
        clientFullNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientFullName"));

        TableColumn<OrdersView, String>  clientFullUCNColumn = new TableColumn<>("Client UCN");
        clientFullUCNColumn.setCellValueFactory(new PropertyValueFactory<>("clientUCN"));

        TableColumn<OrdersView, String>  dateRegisteredColumn = new TableColumn<>("Date registered");
        dateRegisteredColumn.setCellValueFactory(new PropertyValueFactory<>("dateRegistered"));

        TableColumn<OrdersView, String>  deliveryDateColumn = new TableColumn<>("Delivery date");
        deliveryDateColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));


        _tableView.getColumns().add(orderIDColumn);
        _tableView.getColumns().add(orderStatusColumn);
        _tableView.getColumns().add(orderTypeColumn);
        _tableView.getColumns().add(orderPriceColumn);
        _tableView.getColumns().add(companyNameColumn);
        _tableView.getColumns().add(companyEGFNColumn);
        _tableView.getColumns().add(officeNameColumn);
        _tableView.getColumns().add(employeeFullNameColumn);
        _tableView.getColumns().add(clientFullNameColumn);
        _tableView.getColumns().add(dateRegisteredColumn);
        _tableView.getColumns().add(deliveryDateColumn);
    }

    @Override
    protected boolean refreshTableView()
    {
        List<OrdersView> recordsList = new ArrayList<>();

        Users currentlyLoggedUser;
       final UserAuthentication userAuthentication = UserAuthentication.getInstance();
        currentlyLoggedUser = userAuthentication.getCurrentlyLoggedUser();

        if(currentlyLoggedUser == null)
            return false;

        final EmployeesTable employeesTable = new EmployeesTable();

        SQLQuery selectCurrentlyLoggedEmployee = new SQLQuery(employeesTable.getTableName(), LockTypes.READ_ONLY);
        selectCurrentlyLoggedEmployee.addCriteria(new SQLCriteria(EmployeesTable.EmployeesTableColumns.USER_ID.getColumnName(),
                ComparisonTypes.EQUALS ,currentlyLoggedUser.getID()));

        Employees currentlyLoggedEmployee = new Employees();
        if(!employeesTable.selectRecordWhere(currentlyLoggedEmployee, selectCurrentlyLoggedEmployee))
            return false;

        if(currentlyLoggedEmployee == null)
            return  false;

        SQLQuery selectAllOrdersManagedByCurrentEmployee = new SQLQuery(_table.getTableName(), LockTypes.READ_ONLY);
        selectAllOrdersManagedByCurrentEmployee.addCriteria(new SQLCriteria(OrdersViewTable.OrdersViewTableColumns.EMPLOYEE_ID.getColumnName(),
                ComparisonTypes.EQUALS, currentlyLoggedEmployee.getID()));

        if(!_table.selectAllRecordsWhere(recordsList, selectAllOrdersManagedByCurrentEmployee))
            return false;

        _tableView.getItems().clear();

        if(!_tableView.getItems().addAll(recordsList) && recordsList.size() > 0)
            return false;

        return true;
    }

    @Override
    public boolean loadData()
    {
        if(!refreshTableView())
        {
            MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
            return false;
        }

        return true;
    }
}
