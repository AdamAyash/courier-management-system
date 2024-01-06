package bg.tu_varna.sit.couriermanagementsystem.database.connection;

import bg.tu_varna.sit.couriermanagementsystem.configmanager.ConfigManager;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    /*Референция към класа за логване на грешки*/
    private static final Logger _logger = LogManager.getLogger();

    /*Интервал за тестване на връзката към базата при стартиране на приложението*/
    private final int _DATABASE_CONNECTION_TIMEOUT = 30;

    private static final int _DEFAULT_CONNECTION_POOL_SIZE = 10;

    private final int GET_CONNECTION_OFFSET = 1;

    //-------------------------
    //Members:
    //-------------------------
    private static DatabaseConnectionPool _databaseConnectionPoolInstance = null;
    private static SQLServerDataSource _dataSource;
    private static List<Connection> _connectionPool;
    private static List<Connection> _currentlyUsedConnections;
    private static ConfigManager _configManager;

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    private DatabaseConnectionPool()
    {
    }


    //-------------------------
    //Methods:
    //-------------------------
    public static synchronized DatabaseConnectionPool getInstance()
    {
        try
        {
            if(_databaseConnectionPoolInstance == null)
            {
                _databaseConnectionPoolInstance = new DatabaseConnectionPool();
                _configManager = new ConfigManager();
                _connectionPool = new ArrayList<>();
                _currentlyUsedConnections = new ArrayList<>();
            }
        }
        catch (IOException exception)
        {
            _logger.error(exception.getMessage());
        }

        return _databaseConnectionPoolInstance;
    }

    private boolean testDatabaseConnection()
    {
        try
        {
            Connection databaseConnection = _dataSource.getConnection();

            if(!databaseConnection.isValid(_DATABASE_CONNECTION_TIMEOUT))
            {
                databaseConnection.close();
                return false;
            }
            databaseConnection.close();
        }
        catch (SQLException exception)
        {
            _logger.error(exception.getMessage());
            return false;
        }
        return true;
    }

    public boolean connectToDatabase()
    {

        _dataSource = new SQLServerDataSource();
        _dataSource.setUser(_configManager.getProperties().getProperty("User"));
        _dataSource.setPassword(_configManager.getProperties().getProperty("Password"));
        _dataSource.setServerName(_configManager.getProperties().getProperty("SQLServerInstance"));
        _dataSource.setPortNumber(Integer.parseInt(_configManager.getProperties().getProperty("Port")));
        _dataSource.setDatabaseName(_configManager.getProperties().getProperty("Catalog"));
        _dataSource.setTrustServerCertificate(true);

        if(!testDatabaseConnection())
            return false;

        createConnections();
        return true;
    }

    private static Connection createConnection()
    {
        try
        {
            return _dataSource.getConnection();
        }
        catch (SQLServerException exception)
        {
            _logger.error(exception.getMessage());
            return null;
        }
    }

    private static void createConnections()
    {
        for(int index = 0; index < _DEFAULT_CONNECTION_POOL_SIZE; index++)
        {
            _connectionPool.add(createConnection());
        }
    }

    public boolean releaseConnection(Connection connection)
    {
        _connectionPool.add(connection);
        return _currentlyUsedConnections.remove(connection);
    }

    public Connection getConnection()
    {
        Connection databaseConnection;

        if(_currentlyUsedConnections.size() == _DEFAULT_CONNECTION_POOL_SIZE)
        {
            databaseConnection = createConnection();
        }
        else
        {
            databaseConnection = _connectionPool.remove(_connectionPool.size() - GET_CONNECTION_OFFSET);
        }

        try
        {
            if(!databaseConnection.isValid(_DATABASE_CONNECTION_TIMEOUT))
                databaseConnection = createConnection();
        }
        catch (SQLException exception)
        {
            _logger.error(exception.getMessage());
        }
        _currentlyUsedConnections.add(databaseConnection);
        return databaseConnection;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
