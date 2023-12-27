package bg.tu_varna.sit.couriermanagementsystem.controllers.companies;
import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogMode;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.DialogResult;
import bg.tu_varna.sit.couriermanagementsystem.controllers.base.SmartTableViewController;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.ComparisonTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.LockTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLCriteria;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLQuery;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.companiestable.CompaniesTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.companiestable.CompaniesViewTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.officestable.OfficesTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies.Companies;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies.CompaniesData;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies.CompaniesDetails;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies.CompaniesView;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.offices.Offices;
import bg.tu_varna.sit.couriermanagementsystem.stages.StageManager;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.List;

/**/
public class CompaniesTableViewController extends SmartTableViewController<Companies, CompaniesView>
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
    public CompaniesTableViewController()
    {
        super(new CompaniesViewTable());
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
            CompaniesView companyView = _tableView.getSelectionModel().getSelectedItem();
            Companies companyRecord = new Companies();

            if(companyView == null)
                return;

            CompaniesTable companiesTable = new CompaniesTable();

            if(!companiesTable.selectRecordByID(companyRecord, companyView.getID()))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            OfficesTable officesTable = new OfficesTable();
            List<Offices> officesList = new ArrayList<>();

            SQLQuery sqlQuery = new SQLQuery(officesTable.getTableName(), LockTypes.READ_ONLY);
            sqlQuery.addCriteria(new SQLCriteria(OfficesTable.OfficesTableColumns.COMPANY_ID.getColumnName(), ComparisonTypes.EQUALS, companyRecord.getID()));

            if(!officesTable.selectAllRecordsWhere(officesList, sqlQuery))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            CompaniesDetails companyDetails = new CompaniesDetails(companyRecord, officesList);
            CompanyDialogController companyDialogController = new CompanyDialogController(companyDetails, DialogMode.DIALOG_MODE_PREVIEW);

            StageManager stageManager =
                    new StageManager("CompanyDialog.fxml",  Messages.COMPANIES_TITLE, companyDialogController, CompanyDialogController.class);

          OpenDialog(stageManager);

        });

        _menuItemUpdate.setOnAction(action ->
        {
            CompaniesView companyView =  _tableView.getSelectionModel().getSelectedItem();

            if(companyView == null)
                return;

            CompaniesTable companiesTable = new CompaniesTable();
            Companies company = new Companies();

            if(!companiesTable.selectRecordByID(company, companyView.getID()))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            OfficesTable officesTable = new OfficesTable();
            List<Offices> officesList = new ArrayList<>();

            SQLQuery sqlQuery = new SQLQuery(officesTable.getTableName(), LockTypes.READ_ONLY);
            sqlQuery.addCriteria(new SQLCriteria(OfficesTable.OfficesTableColumns.COMPANY_ID.getColumnName(), ComparisonTypes.EQUALS, company.getID()));

            if(!officesTable.selectAllRecordsWhere(officesList, sqlQuery))
            {
               MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            CompaniesDetails companyDetails = new CompaniesDetails(company, officesList);
            CompanyDialogController companyDialogController = new CompanyDialogController(companyDetails, DialogMode.DIALOG_MODE_UPDATE);
            StageManager stageManager =
                    new StageManager("CompanyDialog.fxml",  Messages.COMPANIES_TITLE, companyDialogController, CompanyDialogController.class);

            if(!OpenDialog(stageManager))
                return;

                if(companyDialogController.getDialogResult() != DialogResult.DIALOG_RESULT_APPLY)
                    return;

                CompaniesData companiesData = new CompaniesData();
                if(!companiesData.updateCompany(companyDetails))
                {
                    MessageBox.error(Messages.UPDATE_RECORD_FAILED_MESSAGE );
                    return;
                }


                if(!refreshTableView())
                    MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
        });

       _menuItemInsert.setOnAction(action ->
        {
            CompaniesDetails newCompaniesDetails = new CompaniesDetails();

            CompanyDialogController companyDialogController = new CompanyDialogController(newCompaniesDetails, DialogMode.DIALOG_MODE_INSERT);
            StageManager stageManager =
                    new StageManager("CompanyDialog.fxml", Messages.COMPANIES_TITLE, companyDialogController, CompanyDialogController.class);

            if(!OpenDialog(stageManager))
                return;

                if(companyDialogController.getDialogResult() != DialogResult.DIALOG_RESULT_APPLY)
                    return;

                CompaniesData companiesData = new CompaniesData();

                if(!companiesData.insertCompany(newCompaniesDetails))
                {
                    MessageBox.error(Messages.INSERT_RECORD_FAILED_MESSAGE);
                }

                if(!refreshTableView())
                    MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
        });

        _menuItemDelete.setOnAction(action ->
        {
            CompaniesView companyView = _tableView.getSelectionModel().getSelectedItem();

            if(companyView == null)
                return;

            Companies company = new Companies();
            CompaniesTable companiesTable = new CompaniesTable();

            if(!companiesTable.selectRecordByID(company, companyView.getID()))
            {
                MessageBox.error(Messages.RECORD_DOES_NOT_EXIST_MESSAGE);
                return;
            }

            OfficesTable officesTable = new OfficesTable();
            List<Offices> officesList = new ArrayList<>();

            SQLQuery sqlQuery = new SQLQuery(officesTable.getTableName(), LockTypes.READ_ONLY);
            sqlQuery.addCriteria(new SQLCriteria(OfficesTable.OfficesTableColumns.COMPANY_ID.getColumnName(), ComparisonTypes.EQUALS, company.getID()));

            if(!officesTable.selectAllRecordsWhere(officesList, sqlQuery))
            {
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
                return;
            }

            if(!MessageBox.confirmation(Messages.DELETE_RECORD_QUESTION))
                return;

            CompaniesDetails companiesDetails = new CompaniesDetails(company, officesList);
            CompaniesData companiesData = new CompaniesData();

            if(!companiesData.deleteCompany(companiesDetails))
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
        TableColumn<CompaniesView, String> companyIDColumn = new TableColumn<>("ID");
        companyIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<CompaniesView, String> companyNameColumn = new TableColumn<>("Name");
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<CompaniesView, String> companyEGFNColumn = new TableColumn<>("EGFN");
        companyEGFNColumn.setCellValueFactory(new PropertyValueFactory<>("EGFN"));

        TableColumn<CompaniesView, String> companyPhoneNumberColumn = new TableColumn<>("Phone Number");
        companyPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<CompaniesView, String> companyEmailColumn = new TableColumn<>("Email address");
        companyEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<CompaniesView, String> companyCityColumn = new TableColumn<>("City name");
        companyCityColumn.setCellValueFactory(new PropertyValueFactory<>("cityName"));

        TableColumn<CompaniesView, String> companyStateColumn = new TableColumn<>("State");
        companyStateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));

       _tableView.getColumns().add(companyIDColumn);
       _tableView.getColumns().add(companyNameColumn);
       _tableView.getColumns().add(companyEGFNColumn);
       _tableView.getColumns().add(companyPhoneNumberColumn);
       _tableView.getColumns().add(companyEmailColumn);
       _tableView.getColumns().add(companyCityColumn);
       _tableView.getColumns().add(companyStateColumn);
    }


    @Override
    public boolean loadData()
    {
        List<CompaniesView> companiesViewList = new ArrayList<>();

        if(!_table.selectAllRecords(companiesViewList))
        {
            MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
            return false;
        }

        if(!refreshTableView())
        {
            MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
            return false;
        }
        return true;
    }

}
