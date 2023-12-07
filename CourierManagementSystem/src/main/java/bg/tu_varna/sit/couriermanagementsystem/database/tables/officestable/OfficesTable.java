package bg.tu_varna.sit.couriermanagementsystem.database.tables.officestable;


import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.BaseTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.Column;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.DataMap;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.offices.Offices;

import java.sql.Connection;

/*Табличен клас за Офиси*/
public class OfficesTable extends BaseTable<Offices>
{
    //-------------------------
    //Constants:
    //-------------------------
    public enum OfficesTableColumns
    {
        ID("ID"),
        UPDATE_COUNTER("UPDATE_COUNTER"),
        NAME ("NAME"),
        COMPANY_ID("COMPANY_ID"),
        CITY_ID("CITY_ID"),
        ADDRESS("ADDRESS"),
        DATE_ESTABLISHED("DATE_ESTABLISHED");
        private String _columnName;

        OfficesTableColumns(String columnName)
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
    public OfficesTable(Connection databaseConnection)
    {
        super(databaseConnection);
    }

    public OfficesTable()
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
            _dataMap = new DataMap(Offices.class, "OFFICES");
            _dataMap.addNewColumn(new Column(OfficesTableColumns.ID.getColumnName(),                     "_ID"                ,  _dataMap));
            _dataMap.addNewColumn(new Column(OfficesTableColumns.UPDATE_COUNTER.getColumnName(),         "_updateCounter"     ,  _dataMap, true));
            _dataMap.addNewColumn(new Column(OfficesTableColumns.NAME.getColumnName(),                   "_officeName"        ,  _dataMap));
            _dataMap.addNewColumn(new Column(OfficesTableColumns.COMPANY_ID.getColumnName(),             "_companyID"         ,  _dataMap));
            _dataMap.addNewColumn(new Column(OfficesTableColumns.CITY_ID.getColumnName(),                "_cityID"            ,  _dataMap));
            _dataMap.addNewColumn(new Column(OfficesTableColumns.ADDRESS.getColumnName(),                "_address"           ,  _dataMap));
            _dataMap.addNewColumn(new Column(OfficesTableColumns.DATE_ESTABLISHED.getColumnName(),       "_dateEstablished"   ,  _dataMap));
        }
        catch (NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
        }
    }
}
