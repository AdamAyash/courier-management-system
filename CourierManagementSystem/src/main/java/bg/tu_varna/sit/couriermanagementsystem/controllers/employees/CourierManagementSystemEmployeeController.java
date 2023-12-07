package bg.tu_varna.sit.couriermanagementsystem.controllers.employees;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.CourierManagementSystemController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.clients.ClientsTableViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

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
        Tab newTab = new Tab();
        newTab.setText(Messages.CLIENTS_TITLE);
        newTab.setContent(clientsTableViewController.getTableView());

        _listControl.getTabs().add(newTab);
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
