package bg.tu_varna.sit.couriermanagementsystem.database.tables.companiestable;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.BaseTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.Column;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.DataMap;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies.Companies;

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
        EMAIL("EMAIL");

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
            _dataMap.addNewColumn(new Column(CompaniesTable.CompaniesTableColumns.ID.getColumnName(),               "_ID",                   _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesTable.CompaniesTableColumns.UPDATE_COUNTER.getColumnName(),   "_updateCounter",        _dataMap, true));
            _dataMap.addNewColumn(new Column(CompaniesTable.CompaniesTableColumns.NAME.getColumnName(),             "_name",                 _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesTable.CompaniesTableColumns.EGFN.getColumnName(),             "_EGFN",                 _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesTable.CompaniesTableColumns.CITY_ID.getColumnName(),          "_cityID",               _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesTable.CompaniesTableColumns.PHONE_NUMBER.getColumnName(),     "_phoneNumber",          _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesTable.CompaniesTableColumns.EMAIL.getColumnName(),            "_email",                _dataMap));
        }
        catch (NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
        }
    }
}
