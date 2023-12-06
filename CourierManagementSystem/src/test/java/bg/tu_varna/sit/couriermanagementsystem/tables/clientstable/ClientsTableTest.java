package bg.tu_varna.sit.couriermanagementsystem.tables.clientstable;

import bg.tu_varna.sit.couriermanagementsystem.database.connection.DatabaseConnectionPool;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.clientstable.ClientsTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.companiestable.CompaniesTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.clients.Clients;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies.Companies;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientsTableTest
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
    public void selectAllClientsTest()
    {
        final DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        databaseConnectionPool.connectToDatabase();

        ClientsTable clientsTable = new ClientsTable();
        List<Clients> clientsList = new ArrayList<>();
        assertTrue(clientsTable.selectAllRecords(clientsList));
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
