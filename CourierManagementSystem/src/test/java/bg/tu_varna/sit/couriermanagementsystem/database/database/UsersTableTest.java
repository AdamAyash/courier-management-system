package bg.tu_varna.sit.couriermanagementsystem.database.database;

import bg.tu_varna.sit.couriermanagementsystem.database.connection.DatabaseConnectionPool;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.userstable.UsersTable;
import org.junit.jupiter.api.Test;
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
        final DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        databaseConnectionPool.connectToDatabase();

        UsersTable usersTable = new UsersTable();
        assertTrue(usersTable.selectAllRecords(new ArrayList<>()));
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
