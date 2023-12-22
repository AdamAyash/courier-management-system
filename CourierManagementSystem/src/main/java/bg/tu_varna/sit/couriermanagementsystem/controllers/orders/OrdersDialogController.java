package bg.tu_varna.sit.couriermanagementsystem.controllers.orders;

import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogMode;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.ComparisonTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.LockTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLCriteria;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLQuery;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.clientstable.ClientsTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.employees.EmployeesTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.officestable.OfficesTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.orders.ordertypes.OrderTypesTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.clients.Clients;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.employees.Employees;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.offices.Offices;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.orders.OrderStatuses;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.orders.Orders;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.orders.ordertypes.OrderTypes;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.Users;
import bg.tu_varna.sit.couriermanagementsystem.userauthentication.UserAuthentication;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**/
public class OrdersDialogController extends DialogController
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    @FXML
    private ComboBox<Offices> _officeComboBox;
    @FXML
    private ComboBox<OrderTypes> _orderTypeComboBox;
    @FXML
    private ComboBox<Clients> _clientComboBox;
    @FXML
    private ComboBox<OrderStatuses> _statusComboBox;
    @FXML
    private DatePicker _dateRegistered;
    @FXML
    private DatePicker _deliveryDate;

    private Orders _orderRecord;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public OrdersDialogController(Orders orderRecord, DialogMode dialogMode)
    {
        super(dialogMode);
        _orderRecord = orderRecord;
    }

    //-------------------------
    //Methods:
    //-------------------------

    //-------------------------
    //Overrides:
    //-------------------------
    @Override
    protected void setControls()
    {
        super.setControls();
        if(_dialogMode == DialogMode.DIALOG_MODE_PREVIEW)
        {
            _officeComboBox.setDisable(true);
            _orderTypeComboBox.setDisable(true);
            _statusComboBox.setDisable(true);
            _clientComboBox.setDisable(true);
            _dateRegistered.setDisable(true);
            _deliveryDate.setDisable(true);
        }

        if(_dialogMode == DialogMode.DIALOG_MODE_INSERT)
        {
            _statusComboBox.setValue(OrderStatuses.NOT_DELIVERED);
            _statusComboBox.setDisable(true);
        }
    }

    @Override
    public boolean setDataToControls()
    {
        final List<Clients> clientsList = new ArrayList<>();
        final ClientsTable clientsTable = new ClientsTable();

        if(!clientsTable.selectAllRecords(clientsList))
            return false;

        for(Clients client : clientsList)
        {
            if(_orderRecord.getClientID() == client.getID())
            {
                _clientComboBox.setValue(client);
                break;
            }
        }

        final List<Offices> officesList = new ArrayList<>();
        final OfficesTable officesTable = new OfficesTable();

        if(!officesTable.selectAllRecords(officesList))
            return false;

        for(Offices office : officesList)
        {
            if(_orderRecord.getOfficeID() == office.getID())
            {
                _officeComboBox.setValue(office);
                break;
            }
        }

        final List<OrderTypes> orderTypesList = new ArrayList<>();
        final OrderTypesTable orderTypesTable = new OrderTypesTable();

        if(!orderTypesTable.selectAllRecords(orderTypesList))
            return false;

        for(OrderTypes orderTypes : orderTypesList)
        {
            if(orderTypes.getID() == _orderRecord.getOrderTypeID())
            {
                _orderTypeComboBox.setValue(orderTypes);
                break;
            }
        }

        for(OrderStatuses orderStatuses : OrderStatuses.values())
        {
            if(orderStatuses.getID() == _orderRecord.getStatus())
            {
                _statusComboBox.setValue(orderStatuses);
                break;
            }
        }

        _dateRegistered.setValue(_orderRecord.getDateRegistered().toLocalDate());
        _deliveryDate.setValue(_orderRecord.getDeliveryDate().toLocalDate());

        return true;
     }

    @Override
    public void setControlsToData()
    {
        UserAuthentication userAuthentication = UserAuthentication.getInstance();
        Users currentlyLoggedUser = userAuthentication.getCurrentlyLoggedUser();

        Employees currentlyLoggedEmployeeRecord = new Employees();
        EmployeesTable employeesTable = new EmployeesTable();

        SQLQuery sqlQuery = new SQLQuery(employeesTable.getTableName(), LockTypes.READ_ONLY);
        sqlQuery.addCriteria(new SQLCriteria(EmployeesTable.EmployeesTableColumns.USER_ID.getColumnName(), ComparisonTypes.EQUALS, currentlyLoggedUser.getID()));

        if(!employeesTable.selectRecordWhere(currentlyLoggedEmployeeRecord, sqlQuery))
        {
            MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
            return;
        }

        _orderRecord.setClientID(_clientComboBox.getSelectionModel().getSelectedItem().getID());
        _orderRecord.setOfficeID(_officeComboBox.getSelectionModel().getSelectedItem().getID());
        _orderRecord.setOrderTypeID(_orderTypeComboBox.getSelectionModel().getSelectedItem().getID());
        _orderRecord.setStatus((short)_statusComboBox.getSelectionModel().getSelectedItem().getID());
        _orderRecord.setDateRegistered(Date.valueOf(_dateRegistered.getValue()));
        _orderRecord.setDeliveryDate(Date.valueOf(_deliveryDate.getValue()));
        _orderRecord.setEmployeeID(currentlyLoggedEmployeeRecord.getID());
        _orderRecord.setCompanyID(currentlyLoggedEmployeeRecord.getCompanyID());
    }

    @Override
    public boolean validateControls()
    {
        return true;
    }

    @Override
    public boolean LoadData()
    {
        final OfficesTable officesTable  = new OfficesTable();
        final List<Offices> officesList = new ArrayList<>();

        if(!officesTable.selectAllRecords(officesList))
            return false;

        final OrderTypesTable orderTypesTable = new OrderTypesTable();
        final List<OrderTypes> orderTypesList = new ArrayList<>();

        if(!orderTypesTable.selectAllRecords(orderTypesList))
           return false;

        final ClientsTable clientsTable = new ClientsTable();
        final List<Clients> clientsList = new ArrayList<>();

        if(!clientsTable.selectAllRecords(clientsList))
            return false;

        _officeComboBox.getItems().addAll(officesList);
        _clientComboBox.getItems().addAll(clientsList);
        _orderTypeComboBox.getItems().addAll(orderTypesList);

        for(OrderStatuses orderStatus: OrderStatuses.values())
        {
            _statusComboBox.getItems().add(orderStatus);
        }

        return true;
    }
}
