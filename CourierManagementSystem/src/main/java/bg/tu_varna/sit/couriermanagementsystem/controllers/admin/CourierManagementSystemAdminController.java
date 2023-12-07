package bg.tu_varna.sit.couriermanagementsystem.controllers.admin;

import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.CourierManagementSystemController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.companies.CompaniesTableViewController;
import bg.tu_varna.sit.couriermanagementsystem.controllers.employees.EmployeesTableViewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
public class CourierManagementSystemAdminController extends CourierManagementSystemController
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------

    //-------------------------
    //Methods:
    //-------------------------

    @FXML
    private void OnCompaniesButtonClick()
    {
        CompaniesTableViewController companiesTableViewController = new CompaniesTableViewController();

        Tab newTab = new Tab();
        newTab.setText(Messages.COMPANIES_TITLE);
        newTab.setContent(companiesTableViewController.getTableView());

        _listControl.getTabs().add(newTab);
    }

    @FXML
    private void OnEmployeesButtonClick()
    {
        EmployeesTableViewController employeesTableViewController = new EmployeesTableViewController();

        Tab newTab = new Tab();
        newTab.setText(Messages.EMPLOYEES_TITLE);
        newTab.setContent(employeesTableViewController.getTableView());

        _listControl.getTabs().add(newTab);
    }

    //-------------------------
    //Overrides:
    //-------------------------

}
