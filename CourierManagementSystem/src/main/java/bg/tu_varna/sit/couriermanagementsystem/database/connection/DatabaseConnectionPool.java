package bg.tu_varna.sit.couriermanagementsystem.database.connection;

import bg.tu_varna.sit.couriermanagementsystem.configmanager.ConfigManager;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*Singleton клас  за създаване на връзка към базата данни*/
public final class DatabaseConnectionPool
{
    //-------------------------
    //Constants:
    //-------------------------

    /*Интервал за тестване на връзката към базата при стартиране на приложението*/
    private final int _DATABASE_CONNECTION_TIMEOUT = 30;

    private static final int _DEFAULT_CONNECTION_POOL_SIZE = 10;

    private final int _GET_CONNECTION_OFFSET = 1;

    //-------------------------
    //Members:
    //-------------------------
    private static DatabaseConnectionPool _databaseConnectionPoolInstance = null;
    private static SQLServerDataSource _dataSource;

    private static List<Connection> _connectionPool;
    private static List<Connection> _currentlyUsedConnections;
    private static ConfigManager _configManager;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    private DatabaseConnectionPool()
    {
    }


    //-------------------------
    //Methods:
    //-------------------------
    public static synchronized DatabaseConnectionPool getInstance() throws IOException, SQLServerException
    {
        if (_databaseConnectionPoolInstance == null)
        {
            _databaseConnectionPoolInstance = new DatabaseConnectionPool();
            _configManager = new ConfigManager();
            _connectionPool = new ArrayList<>();
            _currentlyUsedConnections = new ArrayList<>();
        }

        return _databaseConnectionPoolInstance;
    }

    private boolean testDatabaseConnection() throws SQLException
    {
        Connection databaseConnection = _dataSource.getConnection();

        if (!databaseConnection.isValid(_DATABASE_CONNECTION_TIMEOUT))
        {
            databaseConnection.close();
            return false;
        }

        databaseConnection.close();
        return true;
    }

    public boolean connectToDatabase() throws SQLException
    {
        _dataSource = new SQLServerDataSource();
        _dataSource.setUser(_configManager.getProperties().getProperty("User"));
        _dataSource.setPassword(_configManager.getProperties().getProperty("Password"));
        _dataSource.setServerName(_configManager.getProperties().getProperty("SQLServerInstance"));
        _dataSource.setPortNumber(Integer.parseInt(_configManager.getProperties().getProperty("Port")));
        _dataSource.setDatabaseName(_configManager.getProperties().getProperty("Catalog"));
        _dataSource.setTrustServerCertificate(true);

        if (!testDatabaseConnection())
            return false;

        createConnections();

        return true;
    }

    private static Connection createConnection() throws SQLServerException
    {
        return _dataSource.getConnection();
    }

    private static void createConnections() throws SQLServerException
    {
        for (int index = 0; index < _DEFAULT_CONNECTION_POOL_SIZE; index++)
        {
            _connectionPool.add(createConnection());
        }
    }

    public boolean releaseCollection(Connection connection)
    {
        _connectionPool.add(connection);
        return _currentlyUsedConnections.remove(connection);
    }

    public Connection getConnection() throws SQLException
    {
        Connection databaseConnection;

        if (_currentlyUsedConnections.size() == _DEFAULT_CONNECTION_POOL_SIZE)
        {
            databaseConnection = getConnection();
        }
        else
        {
            databaseConnection = _connectionPool.remove(_connectionPool.size() - _GET_CONNECTION_OFFSET);
        }

        if(!databaseConnection.isValid(_DATABASE_CONNECTION_TIMEOUT))
            databaseConnection = getConnection();

        _currentlyUsedConnections.add(databaseConnection);
        return databaseConnection;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
