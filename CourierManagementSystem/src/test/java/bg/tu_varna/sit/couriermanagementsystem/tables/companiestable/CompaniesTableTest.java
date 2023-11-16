package bg.tu_varna.sit.couriermanagementsystem.tables.companiestable;

import bg.tu_varna.sit.couriermanagementsystem.CourierManagementSystem;
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

        CompaniesTable companiesTable = new CompaniesTable();
        List<Companies> companiesList = new ArrayList<>();
        assertTrue(companiesTable.selectAllRecords(companiesList));
    }


    @Test
    public void updateCompaniesTest()
    {
        final DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        databaseConnectionPool.connectToDatabase();

        CompaniesTable companiesTable = new CompaniesTable();
        List<Companies> companiesList = new ArrayList<>();
        companiesTable.selectAllRecords(companiesList);

        Companies company = companiesList.get(0);

        company.setPhoneNumber("024444");

        assertTrue(companiesTable.updateRecord(companiesList.get(0), company));
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
