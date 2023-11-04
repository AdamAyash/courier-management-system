package bg.tu_varna.sit.couriermanagementsystem.database.tables.companiestable;


import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.BaseTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.Column;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.DataMap;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies.Companies;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.Users;

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
        PHONE_NUMBER("PHONE_NUMBER");

        private String _columnName;

        CompaniesTableColumns(String columnName)
        {
            _columnName = columnName;
        }

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
            _dataMap = new DataMap(Users.class, "USERS");
            _dataMap.addNewColumn(new Column(CompaniesTable.CompaniesTableColumns.ID.getColumnName(),              "_ID",                  _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesTable.CompaniesTableColumns.UPDATE_COUNTER.getColumnName(),  "_updateCounter",       _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesTable.CompaniesTableColumns.NAME.getColumnName(),             "_username",            _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesTable.CompaniesTableColumns.EGFN.getColumnName(),             "_password",            _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesTable.CompaniesTableColumns.CITY_ID.getColumnName(),          "_password",            _dataMap));
            _dataMap.addNewColumn(new Column(CompaniesTable.CompaniesTableColumns.PHONE_NUMBER.getColumnName(),    "_password",            _dataMap));
        }
        catch (NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
        }
    }
}
