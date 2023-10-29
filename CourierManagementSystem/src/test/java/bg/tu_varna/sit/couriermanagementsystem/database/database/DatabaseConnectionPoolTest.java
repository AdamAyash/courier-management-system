package bg.tu_varna.sit.couriermanagementsystem.database.database;

import bg.tu_varna.sit.couriermanagementsystem.database.connection.DatabaseConnectionPool;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/*Клас за тестване на връзката към базата данни*/
class DatabaseConnectionPoolTest
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------

    //-------------------------
    //Methods:
    //-------------------------
    @Test
    public void getConnectionTest() throws SQLException, IOException
    {
        final int CONNECTION_TIMEOUT = 10;
        DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        databaseConnectionPool.connectToDatabase();

         assertTrue(databaseConnectionPool.getConnection().isValid(CONNECTION_TIMEOUT));
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
