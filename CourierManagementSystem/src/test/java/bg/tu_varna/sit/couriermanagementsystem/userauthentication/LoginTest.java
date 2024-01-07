package bg.tu_varna.sit.couriermanagementsystem.userauthentication;
import bg.tu_varna.sit.couriermanagementsystem.database.connection.DatabaseConnectionPool;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginTest
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
    public void loginAsAdminExpectingSuccessTest()
    {
        DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        databaseConnectionPool.connectToDatabase();

        UserAuthentication userAuthentication = UserAuthentication.getInstance();
        assertTrue(userAuthentication.authenticateUser("admin", "password123"));
    }

    @Test
    public void loginAsAdminExpectingFalseTest()
    {
        DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        databaseConnectionPool.connectToDatabase();

        UserAuthentication userAuthentication = UserAuthentication.getInstance();
        assertFalse(userAuthentication.authenticateUser("admin1", "password123"));
    }


    //-------------------------
    //Overrides:
    //-------------------------
}
