package bg.tu_varna.sit.couriermanagementsystem.tables.companiestable;

import bg.tu_varna.sit.couriermanagementsystem.database.connection.DatabaseConnectionPool;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.companiestable.CompaniesTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies.Companies;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompaniesTableTest
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

    @Test
    public void selectAllCompaniesTest()
    {
        final DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        databaseConnectionPool.connectToDatabase();

        final CompaniesTable companiesTable = new CompaniesTable();
        List<Companies> companiesList = new ArrayList<>();
        assertTrue(companiesTable.selectAllRecords(companiesList));
    }

    @Test
    public void insertCompaniesTest()
    {
        final DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        databaseConnectionPool.connectToDatabase();

        final CompaniesTable companiesTable = new CompaniesTable();

        List<Companies> companiesList = new ArrayList<>();
        companiesTable.selectAllRecords(companiesList);

        final String phoneNumber = "0882712568";

        Companies company = new Companies();
        assertTrue(companiesTable.updateRecord(company));
    }

    @Test
    public void updateCompaniesTest()
    {
        final DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        databaseConnectionPool.connectToDatabase();

        final CompaniesTable companiesTable = new CompaniesTable();

        List<Companies> companiesList = new ArrayList<>();
        companiesTable.selectAllRecords(companiesList);

        final String phoneNumber = "0882712568";

        Companies company = companiesList.get(0);
        company.setPhoneNumber(phoneNumber);

        assertTrue(companiesTable.updateRecord(company));
    }





    //-------------------------
    //Overrides:
    //-------------------------
}
