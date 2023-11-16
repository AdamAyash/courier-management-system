package bg.tu_varna.sit.couriermanagementsystem.database.tables.citiestable;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.BaseTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.Column;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.DataMap;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.cities.Cities;



/**/
public class CitiesTable extends BaseTable<Cities>
{
    //-------------------------
    //Constants:
    //-------------------------
    public enum CitiesTableColumns
    {
        ID("ID"),
        NAME("NAME"),
        STATE("STATE");

        private String _columnName;

        CitiesTableColumns(String columnName)
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
    public CitiesTable() {}

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
            _dataMap = new DataMap(Cities.class, "CITIES");
            _dataMap.addNewColumn(new Column(CitiesTableColumns.ID.getColumnName(),                 "_ID",                  _dataMap));
            _dataMap.addNewColumn(new Column(CitiesTableColumns.NAME.getColumnName(),               "_name",                _dataMap));
            _dataMap.addNewColumn(new Column(CitiesTableColumns.STATE.getColumnName(),              "_state",               _dataMap));
        }
        catch (NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
        }
    }
}
