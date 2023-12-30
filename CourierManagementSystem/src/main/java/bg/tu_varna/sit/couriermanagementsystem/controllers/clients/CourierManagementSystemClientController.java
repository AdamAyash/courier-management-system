package bg.tu_varna.sit.couriermanagementsystem.controllers.clients;

import bg.tu_varna.sit.couriermanagementsystem.Tasks.ClientNotificationsTask;
import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.CourierManagementSystemController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogResult;
import bg.tu_varna.sit.couriermanagementsystem.controllers.common.PeriodDialogController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.orders.OrdersDialogController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.orders.OrdersTableViewController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.orders.TrackOrderDialogController;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLQuery;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.orders.OrdersTable;
import bg.tu_varna.sit.couriermanagementsystem.stages.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

import java.io.IOException;

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
        ClientOrdersTableViewController ordersTableViewController = new ClientOrdersTableViewController();
        if(!ordersTableViewController.loadData())
        {
            MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
            return;
        }

        Tab newTab = new Tab();
        newTab.setText(Messages.ORDERS_TITLE);
        newTab.setContent(ordersTableViewController.getTableView());

        _listControl.getTabs().add(newTab);
    }

    @FXML
    private void OnTrackOrderClicked()
    {
        TrackOrderDialogController trackOrderDialogController = new TrackOrderDialogController();

        StageManager stageManager = new StageManager("TrackOrderDialog.fxml", Messages.TRACK_ORDER_TITLE,
                trackOrderDialogController, TrackOrderDialogController.class);

        try
        {
            stageManager.showStage();
        }
        catch (IOException exception)
        {
            _logger.error(exception.getMessage());
        }

        if(trackOrderDialogController.getDialogResult() == DialogResult.DIALOG_RESULT_CANCEL)
            return;

        ClientOrdersTableViewController ordersTableViewController = new ClientOrdersTableViewController(trackOrderDialogController.getOrderID());
        if(!ordersTableViewController.loadData())
        {
            MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
            return;
        }

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
