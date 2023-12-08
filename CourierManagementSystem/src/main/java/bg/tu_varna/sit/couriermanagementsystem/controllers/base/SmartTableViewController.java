package bg.tu_varna.sit.couriermanagementsystem.controllers.base;

import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.BaseTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.DomainObject;
import bg.tu_varna.sit.couriermanagementsystem.stages.StageManager;
import javafx.scene.control.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**/
public abstract class SmartTableViewController<RecordType, ExtendedRecordType extends DomainObject>
{
    //-------------------------
    //Constants:
    //-------------------------
    protected final Logger _logger = LogManager.getLogger();

    //-------------------------
    //Members:
    //-------------------------
    protected TableView<ExtendedRecordType> _tableView;
    protected BaseTable<ExtendedRecordType> _table;
    protected ContextMenu _contextMenu;
    protected MenuItem _menuItemPreview;
    protected MenuItem _menuItemInsert;
    protected MenuItem _menuItemUpdate;
    protected MenuItem _menuItemDelete;
    protected MenuItem _menuItemRefresh;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public SmartTableViewController(BaseTable<ExtendedRecordType> extendedRecordTable)
    {
        _tableView = new TableView<>();
        _table = extendedRecordTable;

        if(!loadData())
            MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);

        InitTableViewColumns();
        setContextMenu();
    }

    //-------------------------
    //Methods:
    //-------------------------

    protected abstract void InitTableViewColumns();

    protected abstract boolean loadData();

    protected void setContextMenu()
    {
        _contextMenu = new ContextMenu();
        _menuItemPreview = new MenuItem("Preview");
        _menuItemInsert = new MenuItem("Insert");
        _menuItemUpdate = new MenuItem("Update");
        _menuItemDelete = new MenuItem("Delete");
        _menuItemRefresh = new MenuItem("Refresh");

        _menuItemRefresh.setOnAction(action ->
        {
            if(!refreshTableView())
                MessageBox.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
        });

        _contextMenu.getItems().add(_menuItemPreview);
        _contextMenu.getItems().add(_menuItemInsert);
        _contextMenu.getItems().add(_menuItemUpdate);
        _contextMenu.getItems().add(_menuItemDelete);
        _contextMenu.getItems().add(_menuItemRefresh);

        _tableView.setContextMenu(_contextMenu);
    }

    protected boolean OpenDialog(StageManager stageManager)
    {
        try
        {
            stageManager.showStage();
        }
        catch (IOException exception)
        {
            _logger.error(Messages.LOAD_RECORDS_FAILED_MESSAGE);
            return false;
        }
        return true;
    }

    protected boolean refreshTableView()
    {
        List<ExtendedRecordType> recordsList = new ArrayList<>();

        if(!_table.selectAllRecords(recordsList))
        {
            return false;
        }

        _tableView.getItems().clear();

        if(!_tableView.getItems().addAll(recordsList) && recordsList.size() > 0)
            return false;

        return true;
    }

    public TableView<ExtendedRecordType> getTableView()
    {
        return _tableView;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
