package bg.tu_varna.sit.couriermanagementsystem.database.tables.companiestable;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.BaseTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.Column;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.DataMap;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies.Companies;

import java.sql.Connection;

/*Табличен клас за фирма*/
public class CompaniesTable extends BaseTable<Companies>
{
    //-------------------------
    //Constants:
    //-------------------------
    public enum CompaniesTableColumns
    {
        ID("ID"),
        UPDATE_COUNTER("UPDATE_COUNTER"),
        NAME("NAME"),
        EGFN("EGFN"),
        CITY_ID("CITY_ID"),
        PHONE_NUMBER("PHONE_NUMBER"),
        EMAIL("EMAIL"),
        DATE_ESTABLISHED("DATE_ESTABLISHED");

        private String _columnName;

        CompaniesTableColumns(String columnName) {_columnName = columnName;}

        public String getColumnName()
        {
            return this._columnName;
        }
    };

    //-------------------------
    //Members:
    //-------------------------

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public CompaniesTable()
    {
    }

    public CompaniesTable(Connection connection)
    {
        super(connection);
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
            _dataMap = new DataMap(Companies.class, "COMPANIES");
            _dataMap.addNewColumn(new Column(CompaniesTableColumns.ID.getColumnName(),                      "_ID",                   _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesTableColumns.UPDATE_COUNTER.getColumnName(),          "_updateCounter",        _dataMap, true));
            _dataMap.addNewColumn(new Column(CompaniesTableColumns.NAME.getColumnName(),                    "_name",                 _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesTableColumns.EGFN.getColumnName(),                    "_EGFN",                 _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesTableColumns.CITY_ID.getColumnName(),                 "_cityID",               _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesTableColumns.PHONE_NUMBER.getColumnName(),            "_phoneNumber",          _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesTableColumns.EMAIL.getColumnName(),                   "_email",                _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesTableColumns.DATE_ESTABLISHED.getColumnName(),         "_dateEstablished",     _dataMap));
        }
        catch (NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
        }
    }
}
