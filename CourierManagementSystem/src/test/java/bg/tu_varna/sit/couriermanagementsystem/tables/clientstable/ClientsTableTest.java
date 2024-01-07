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
    private final ClientsTable _clientsTable = new ClientsTable();
    private final DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
    //-------------------------
    //Members:
    //-------------------------
    private Clients _clientRecord;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public ClientsTableTest()
    {
        
    }

    //-------------------------
    //Methods:
    //-------------------------

    @Test
    public void selectAllClientsTest()
    {

        databaseConnectionPool.connectToDatabase();

        List<Clients> clientsList = new ArrayList<>();

        assertTrue(_clientsTable.selectAllRecords(clientsList));
    }

    @Test
    public void insertClientTest()
    {
        final DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        databaseConnectionPool.connectToDatabase();

        List<Clients> clientsList = new ArrayList<>();

        assertTrue(_clientsTable.selectAllRecords(clientsList));
    }



    //-------------------------
    //Overrides:
    //-------------------------
}
