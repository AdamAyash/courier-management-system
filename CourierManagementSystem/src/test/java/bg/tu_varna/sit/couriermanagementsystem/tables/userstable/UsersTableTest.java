package bg.tu_varna.sit.couriermanagementsystem.tables.userstable;

import bg.tu_varna.sit.couriermanagementsystem.database.connection.DatabaseConnectionPool;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.ComparisonTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.LockTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLCriteria;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLQuery;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.userstable.UsersTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.AccessRights;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.Users;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*Клас за тестване на табличният клас с потребители */
public class UsersTableTest
{
    //-------------------------
    //Constants:
    //-------------------------
    private final String TEST_USERNAME = "Test";
    private final String TEST_PASSWORD = "Test password";

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
    public void selectAllUsersTest()
    {
        final DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        databaseConnectionPool.connectToDatabase();

        UsersTable usersTable = new UsersTable();
        assertTrue(usersTable.selectAllRecords(new ArrayList<>()));
    }

    @Test
    public void insertUserTest()
    {
        final DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        databaseConnectionPool.connectToDatabase();

        final UsersTable usersTable = new UsersTable();

        Users newUser = new Users();
        newUser.setUsername(TEST_USERNAME);
        newUser.setPassword(TEST_PASSWORD);
        newUser.setAccess(AccessRights.ADMIN);

        assertTrue(usersTable.insertRecord(newUser));
    }

    @Test
    public void selectUserBySQLQueryTest()
    {
        final DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        databaseConnectionPool.connectToDatabase();

        final UsersTable usersTable = new UsersTable();

        SQLQuery sqlQuery = new SQLQuery(usersTable.getTableName(), LockTypes.READ_ONLY);
        sqlQuery.addCriteria(new SQLCriteria(UsersTable.UsersTableColumns.USERNAME.getColumnName(), ComparisonTypes.EQUALS, TEST_USERNAME));

        Users user = new Users();
        assertTrue(usersTable.selectRecordWhere(user, sqlQuery));
    }

    @Test
    public void updateUserTest()
    {
        final DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        databaseConnectionPool.connectToDatabase();

        final UsersTable usersTable = new UsersTable();

        SQLQuery sqlQuery = new SQLQuery(usersTable.getTableName(), LockTypes.READ_ONLY);
        sqlQuery.addCriteria(new SQLCriteria(UsersTable.UsersTableColumns.USERNAME.getColumnName(), ComparisonTypes.EQUALS,TEST_USERNAME));

        Users newUser = new Users();
        usersTable.selectRecordWhere(newUser, sqlQuery);

        newUser.setUsername("Test_Update");
        newUser.setPassword("Test_Update");
        newUser.setAccess(AccessRights.ADMIN);

        assertTrue(usersTable.updateRecord(newUser));
    }

    @Test
    public void deleteUserTest()
    {
        final DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        databaseConnectionPool.connectToDatabase();

        final UsersTable usersTable = new UsersTable();

        SQLQuery sqlQuery = new SQLQuery(usersTable.getTableName(), LockTypes.READ_ONLY);
        sqlQuery.addCriteria(new SQLCriteria(UsersTable.UsersTableColumns.USERNAME.getColumnName(), ComparisonTypes.EQUALS, "Test_Update"));

        Users newUser = new Users();
        usersTable.selectRecordWhere(newUser, sqlQuery);

        assertTrue(usersTable.deleteRecord(newUser));
    }
    //-------------------------
    //Overrides:
    //-------------------------
}
