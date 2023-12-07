package bg.tu_varna.sit.couriermanagementsystem.domainobjects.clients;

import bg.tu_varna.sit.couriermanagementsystem.database.connection.DatabaseConnectionPool;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.clientstable.ClientsTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.employees.EmployeesTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.userstable.UsersTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.employees.EmployeeDetails;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.AccessRights;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.Users;
import bg.tu_varna.sit.couriermanagementsystem.userauthentication.UserAuthentication;

import java.sql.Connection;

/**/
public class ClientsData
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
    public ClientsData()
    {
    }

    //-------------------------
    //Methods:
    //-------------------------
    public boolean insertClient(ClientsDetails clientsDetails)
    {
        DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        Connection connection = databaseConnectionPool.getConnection();

        //Хеширане на паролата на новия потребител;
        UserAuthentication userAuthentication = UserAuthentication.getInstance();
        final String newClientHashedPassword =  userAuthentication.hashPassword(clientsDetails.getClientAccount().getPassword());

        Users newUserRecord = new Users();
        newUserRecord.setUsername(clientsDetails.getClientAccount().getUsername());
        newUserRecord.setPassword(newClientHashedPassword);
        newUserRecord.setAccess(AccessRights.CLIENT);

        UsersTable usersTable = new UsersTable(connection);
        if(!usersTable.insertRecord(newUserRecord))
            return false;

        clientsDetails.getClientRecord().setUserID(newUserRecord.getID());

        ClientsTable clientsTable = new ClientsTable(connection);
        if(!clientsTable.insertRecord(clientsDetails.getClientRecord()))
            return false;

        if(!clientsTable.CommitTransaction())
            return false;

        if(!databaseConnectionPool.releaseConnection(connection))
            return false;

        return true;
    }

    public boolean updateClient(ClientsDetails clientsDetails)
    {
        DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        Connection connection = databaseConnectionPool.getConnection();

        //Хеширане на паролата на новия потребител;
        UserAuthentication userAuthentication = UserAuthentication.getInstance();
        final String newClientHashedPassword =  userAuthentication.hashPassword(clientsDetails.getClientAccount().getPassword());

        Users userRecord = new Users();
        UsersTable usersTable = new UsersTable(connection);
        if(!usersTable.selectRecordByID(userRecord, clientsDetails.getClientRecord().getUserID()))
            return false;

        userRecord.setPassword(newClientHashedPassword);
        userRecord.setUsername(clientsDetails.getClientAccount().getUsername());

        if(!usersTable.updateRecord(userRecord))
            return false;

        ClientsTable clientsTable = new ClientsTable(connection);
        if(!clientsTable.updateRecord(clientsDetails.getClientRecord()))
            return false;

        if(!clientsTable.CommitTransaction())
            return false;

        if(!databaseConnectionPool.releaseConnection(connection))
            return false;

        return true;
    }

    public boolean deleteClient(ClientsDetails clientsDetails)
    {
        DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        Connection connection = databaseConnectionPool.getConnection();

        ClientsTable clientsTable = new ClientsTable(connection);
        if(!clientsTable.deleteRecord(clientsDetails.getClientRecord()))
            return false;

        UsersTable usersTable = new UsersTable(connection);
        if(!usersTable.deleteRecord(clientsDetails.getClientAccount()))
            return false;

        if(!usersTable.CommitTransaction())
            return false;

        if(!databaseConnectionPool.releaseConnection(connection))
            return false;

        return true;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
