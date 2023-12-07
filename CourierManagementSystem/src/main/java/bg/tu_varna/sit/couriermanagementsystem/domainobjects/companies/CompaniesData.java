package bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies;

import bg.tu_varna.sit.couriermanagementsystem.database.connection.DatabaseConnectionPool;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.ComparisonTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.LockTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLCriteria;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLQuery;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.companiestable.CompaniesTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.officestable.OfficesTable;
import java.sql.Connection;

/**/
public class CompaniesData
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

    //-------------------------
    //Methods:
    //-------------------------


    public boolean insertCompany(CompaniesDetails companiesDetails)
    {
        DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        Connection connection = databaseConnectionPool.getConnection();

        CompaniesTable companiesTable = new CompaniesTable(connection);

        if(!companiesTable.insertRecord(companiesDetails.getCompaniesRecord()))
            return false;


        OfficesTable officesTable = new OfficesTable(connection);
        for(int index = 0; index < companiesDetails.getOfficesList().size(); index++)
        {
            companiesDetails.getOfficesList().get(index).setCompanyID(companiesDetails.getCompaniesRecord().getID());
            if(!officesTable.insertRecord(companiesDetails.getOfficesList().get(index)))
                return false;
        }

        if(!companiesTable.CommitTransaction())
            return false;

        if(!databaseConnectionPool.releaseConnection(connection))
            return false;

        return true;
    }

    public boolean updateCompany(CompaniesDetails companiesDetails)
    {
        DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        Connection connection = databaseConnectionPool.getConnection();

        CompaniesTable companiesTable = new CompaniesTable(connection);

        if(!companiesTable.updateRecord(companiesDetails.getCompaniesRecord()))
            return false;

        OfficesTable officesTable = new OfficesTable(connection);

        SQLQuery sqlQuery = new SQLQuery(officesTable.getTableName(), LockTypes.UPDATE);
        sqlQuery.addCriteria(new SQLCriteria(OfficesTable.OfficesTableColumns.COMPANY_ID.getColumnName(), ComparisonTypes.EQUALS,
                companiesDetails.getCompaniesRecord().getID()));

        if(!officesTable.SynchronizeRecords(companiesDetails.getOfficesList(), sqlQuery))
            return false;

        if(!companiesTable.CommitTransaction())
            return false;

        if(!databaseConnectionPool.releaseConnection(connection))
            return false;

        return  true;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
