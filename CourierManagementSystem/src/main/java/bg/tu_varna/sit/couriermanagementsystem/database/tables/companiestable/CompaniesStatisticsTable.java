package bg.tu_varna.sit.couriermanagementsystem.database.tables.companiestable;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.BaseTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.Column;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.DataMap;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies.CompanyStatistics;

/**/
public class CompaniesStatisticsTable extends BaseTable<CompanyStatistics>
{
    //-------------------------
    //Constants:
    //-------------------------
    public enum CompanyStatisticsTableColumns
    {
        COMPANY_ID("COMPANY_ID"),
        COMPANY_NAME("COMPANY_NAME"),
        TOTAL_CLIENTS("TOTAL_CLIENTS"),
        TOTAL_EMPLOYEES("TOTAL_EMPLOYEES"),
        TOTAL_AMOUNT("TOTAL_AMOUNT");
        private String _columnName;

        CompanyStatisticsTableColumns(String columnName)
        {
            _columnName = columnName;
        }

        public String getColumnName()
        {
            return this._columnName;
        }
    }

    ;

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
            _dataMap = new DataMap(CompanyStatistics.class, "VI_COMPANY_STATISTICS");
            _dataMap.addNewColumn(new Column(CompanyStatisticsTableColumns.COMPANY_ID.getColumnName(),      "_companyID",       _dataMap));
            _dataMap.addNewColumn(new Column(CompanyStatisticsTableColumns.COMPANY_NAME.getColumnName(),    "_companyName",     _dataMap));
            _dataMap.addNewColumn(new Column(CompanyStatisticsTableColumns.TOTAL_CLIENTS.getColumnName(),   "_totalClients",    _dataMap));
            _dataMap.addNewColumn(new Column(CompanyStatisticsTableColumns.TOTAL_EMPLOYEES.getColumnName(), "_totalEmployees",  _dataMap));
            _dataMap.addNewColumn(new Column(CompanyStatisticsTableColumns.TOTAL_AMOUNT.getColumnName(),    "_totalAmount",     _dataMap));

        }
        catch (NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
        }
    }
}
