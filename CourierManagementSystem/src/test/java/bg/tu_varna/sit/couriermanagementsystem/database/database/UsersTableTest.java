package bg.tu_varna.sit.couriermanagementsystem.database.database;

import bg.tu_varna.sit.couriermanagementsystem.database.connection.DatabaseConnectionPool;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.ComparisonTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLCriteria;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLQuery;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.userstable.UsersTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.Users;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*Клас за тестване на табличният клас с потребители */
public class UsersTableTest
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
    public void selectAllUsersTest()
    {
        DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        databaseConnectionPool.connectToDatabase();

        UsersTable usersTable = new UsersTable();
        assertTrue(usersTable.selectAllRecords(new ArrayList<>()));
    }
    @Test
    public void selectUserWhereTest() throws SQLException
    {
        DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        databaseConnectionPool.connectToDatabase();

        UsersTable usersTable = new UsersTable();
        Users user = new Users();
        SQLQuery sqlQuery = new SQLQuery(usersTable.getTableName());
        sqlQuery.addCriteria(new SQLCriteria(UsersTable.UsersTableColumns.ID.getColumnName(), ComparisonTypes.EQUALS, 1));
        assertTrue(usersTable.selectRecordWhere(user, sqlQuery));
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
