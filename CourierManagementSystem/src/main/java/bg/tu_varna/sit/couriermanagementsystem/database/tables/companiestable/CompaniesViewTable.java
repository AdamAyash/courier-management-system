package bg.tu_varna.sit.couriermanagementsystem.database.tables.companiestable;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.BaseTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.Column;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.DataMap;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies.CompaniesView;

/**/
public class CompaniesViewTable extends BaseTable<CompaniesView>
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    public enum CompaniesViewTableColumns
    {
        ID("ID"),
        NAME("NAME"),
        EGFN("EGFN"),
        PHONE_NUMBER("PHONE_NUMBER"),
        EMAIL("EMAIL"),
        CITY_NAME("CITY_NAME"),
        STATE("STATE"),
        POST_CODE("POST_CODE");

        private String _columnName;

        CompaniesViewTableColumns(String columnName) {_columnName = columnName;}

        public String getColumnName()
        {
            return this._columnName;
        }
    };

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------

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
            _dataMap = new DataMap(CompaniesView.class, "VI_COMPANIES");
            _dataMap.addNewColumn(new Column(CompaniesViewTable.CompaniesViewTableColumns.ID.getColumnName(), "_ID", _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesViewTable.CompaniesViewTableColumns.NAME.getColumnName(), "_name", _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesViewTable.CompaniesViewTableColumns.EGFN.getColumnName(), "_EGFN", _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesViewTable.CompaniesViewTableColumns.PHONE_NUMBER.getColumnName(), "_phoneNumber", _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesViewTable.CompaniesViewTableColumns.EMAIL.getColumnName(), "_email", _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesViewTableColumns.CITY_NAME.getColumnName(), "_cityName", _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesViewTableColumns.STATE.getColumnName(), "_state", _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesViewTableColumns.POST_CODE.getColumnName(), "_postCode", _dataMap));
        }
        catch (NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
        }
    }
}
