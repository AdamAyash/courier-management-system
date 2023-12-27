package bg.tu_varna.sit.couriermanagementsystem.controllers.clients;

import bg.tu_varna.sit.couriermanagementsystem.Tasks.ClientNotificationsTask;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.CourierManagementSystemController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.orders.OrdersTableViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

/**/
public class CourierManagementSystemClientController extends CourierManagementSystemController
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

    //-------------------------
    //Methods:
    //-------------------------
    @FXML
    private void OnOrdersButtonClicked()
    {
        OrdersTableViewController ordersTableViewController = new OrdersTableViewController();
        Tab newTab = new Tab();
        newTab.setText(Messages.ORDERS_TITLE);
        newTab.setContent(ordersTableViewController.getTableView());

        _listControl.getTabs().add(newTab);
    }

    //-------------------------
    //Overrides:
    //-------------------------


    @Override
    public boolean InitializeController()
    {
        if(!super.InitializeController())
            return false;

        ClientNotificationsTask clientNotificationsTask = new ClientNotificationsTask(_userAuthentication.getCurrentlyLoggedUser());
        clientNotificationsTask.run();
        return true;
    }
}
