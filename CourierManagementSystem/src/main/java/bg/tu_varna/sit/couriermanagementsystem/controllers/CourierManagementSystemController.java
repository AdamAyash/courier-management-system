package bg.tu_varna.sit.couriermanagementsystem.controllers;

import bg.tu_varna.sit.couriermanagementsystem.controllers.base.BaseController;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.List;

public class CourierManagementSystemController extends BaseController
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    @FXML
    private TabPane _tabPane;

    //-------------------------
    //Constructor/Destructor:
    //-------------------------

    //-------------------------
    //Methods:
    //-------------------------

    @FXML
    private void OnCitiesRegisterClick()
    {
//        List<Cities> citiesList = new ArrayList<>();
//        CitiesTable citiesTable = new CitiesTable();
//        Cities newCity = new Cities();
//        newCity.setID(2);
//        newCity.setName("sadadad");
//        newCity.setState("sadadad");
//        citiesTable.selectAllRecords(citiesList);
//        citiesTable.insertRecord(newCity);
//
//        TableView<Cities> citiesTableView = new TableView<>();
//
//        TableColumn<Cities, String> cityNameColumn = new TableColumn<>("Град");
//        cityNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//
//        TableColumn<Cities, String> stateNameColumn = new TableColumn<>("Област");
//        stateNameColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
//
//        citiesTableView.getColumns().add(cityNameColumn);
//        citiesTableView.getColumns().add(stateNameColumn);
//
//
//        for(int i = 0; i < citiesList.size(); i++){
//            citiesTableView.getItems().add(citiesList.get(i));
//        }
//
//        Tab citiesTab = new Tab("Градове");
//        citiesTab.setContent(citiesTableView);
//        //_tabPane.getTabs().add(citiesTab);
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
