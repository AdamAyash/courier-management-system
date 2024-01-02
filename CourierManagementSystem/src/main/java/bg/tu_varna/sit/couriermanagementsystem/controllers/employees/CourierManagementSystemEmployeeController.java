package bg.tu_varna.sit.couriermanagementsystem.controllers.employees;
import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.CourierManagementSystemController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogResult;
import bg.tu_varna.sit.couriermanagementsystem.controllers.clients.ClientsTableViewController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.common.PeriodDialogController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.orders.OrdersTableViewController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.orders.TrackOrderDialogController;
import bg.tu_varna.sit.couriermanagementsystem.stages.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

import java.io.IOException;

/**/
public class CourierManagementSystemEmployeeController extends CourierManagementSystemController
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
    private void OnClientsButtonClicked()
    {
       ClientsTableViewController clientsTableViewController = new ClientsTableViewController();
        if(!clientsTableViewController.loadData())
        {
            MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
            return;
        }

        Tab newTab = new Tab();
        newTab.setText(Messages.CLIENTS_TITLE);
        newTab.setContent(clientsTableViewController.getTableView());

        _listControl.getTabs().add(newTab);
    }

    @FXML
    private void OnOrdersButtonClicked()
    {
        OrdersTableViewController ordersTableViewController = new OrdersTableViewController();
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
    private void OnOrdersReportButtonClicked()
    {

        PeriodDialogController periodDialogController = new PeriodDialogController();

        StageManager stageManager = new StageManager("PeriodDialog.fxml", Messages.PERIOD_TITLE,
                periodDialogController, PeriodDialogController.class);

        try
        {
            stageManager.showStage();
        }
        catch (IOException exception)
        {
            _logger.error(exception.getMessage());
        }

        if(periodDialogController.getDialogResult() == DialogResult.DIALOG_RESULT_CANCEL)
            return;

        OrdersReportTableViewController ordersTableViewController =
                new OrdersReportTableViewController(periodDialogController.getFromDate(), periodDialogController.getToDate());
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
}
