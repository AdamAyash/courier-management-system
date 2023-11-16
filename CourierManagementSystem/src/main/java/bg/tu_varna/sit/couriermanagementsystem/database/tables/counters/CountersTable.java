package bg.tu_varna.sit.couriermanagementsystem.database.tables.counters;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.BaseTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.Column;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.DataMap;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.citiestable.CitiesTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.cities.Cities;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.counters.Counters;

/*Табличен клас за системния брояч*/
public class CountersTable extends BaseTable<Counters>
{
    //-------------------------
    //Constants:
    //-------------------------
    public enum CountersTableColumns
    {
        COUNTER_NAME("COUNTER_NAME"),
        TABLE_NAME("TABLE_NAME"),
        PRIMARY_KEY_COLUMN_NAME("PRIMARY_KEY_COLUMN_NAME"),
        START_INDEX("START_INDEX"),
        CURRENT_INDEX_POSITION("CURRENT_INDEX_POSITION"),
        INDEX_STEP("INDEX_STEP"),
        DESCRIPTION("DESCRIPTION");
        private String _columnName;

        CountersTableColumns(String columnName)
        {
            _columnName = columnName;
        }

        public String getColumnName() {return this._columnName;}
    }

    //-------------------------
    //Members:
    //-------------------------

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    protected CountersTable(boolean supportUpdateCounter)
    {

    }

    //-------------------------
    //Methods:
    //-------------------------

    //-------------------------
    //Overrides:
    //-------------------------
    @Override
    protected void loadDataMap()
    {
        try
        {
            _dataMap = new DataMap(Counters.class, "COUNTERS");
            _dataMap.addNewColumn(new Column(CountersTableColumns.COUNTER_NAME.getColumnName(),              "_counterName",              _dataMap));
            _dataMap.addNewColumn(new Column(CountersTableColumns.TABLE_NAME.getColumnName(),                "_tableName",                _dataMap));
            _dataMap.addNewColumn(new Column(CountersTableColumns.PRIMARY_KEY_COLUMN_NAME.getColumnName(),   "_primaryKeyColumnName",     _dataMap));
            _dataMap.addNewColumn(new Column(CountersTableColumns.START_INDEX.getColumnName(),               "_startIndex",               _dataMap));
            _dataMap.addNewColumn(new Column(CountersTableColumns.CURRENT_INDEX_POSITION.getColumnName(),    "_currentIndexPosition",     _dataMap));
            _dataMap.addNewColumn(new Column(CountersTableColumns.INDEX_STEP.getColumnName(),                "_indexStep",                _dataMap));
            _dataMap.addNewColumn(new Column(CountersTableColumns.DESCRIPTION.getColumnName(),               "_description",              _dataMap));
        }
        catch (NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
        }
    }
}
