package bg.tu_varna.sit.couriermanagementsystem.database.tables.base;

import bg.tu_varna.sit.couriermanagementsystem.database.connection.DatabaseConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.SQLException;

/**/
public class DatabaseObject
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    protected Connection _databaseConnection;
    protected boolean _isTransactionActive;

    protected static final Logger _logger = LogManager.getLogger();
    protected boolean _isLocalSession;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    protected DatabaseObject()
    {
        _isLocalSession = true;
    }

    protected DatabaseObject(Connection connection)
    {
        _databaseConnection = connection;
        _isLocalSession = false;
    }

    //-------------------------
    //Methods:
    //-------------------------
    protected void StartTransaction() throws SQLException
    {
        _databaseConnection.setAutoCommit(false);
        _isTransactionActive = true;
    }

    public boolean CommitTransaction()
    {
        try
        {
            if(_isTransactionActive)
                _databaseConnection.commit();
        }
        catch (SQLException exception)
        {
            _logger.error(exception.getMessage());
            Rollback();
            return false;
        }
        return true;
    }

    protected void OpenLocalConnection()
    {
        if(_databaseConnection != null)
            return;

        final DatabaseConnectionPool databaseConnectionPool =
                DatabaseConnectionPool.getInstance();

        _databaseConnection = databaseConnectionPool.getConnection();
    }

    protected void Rollback()
    {
        try
        {
            _isTransactionActive = false;
            _databaseConnection.rollback();
        }
        catch (SQLException exception)
        {
            _logger.error(exception);
        }
    }

    protected boolean CloseLocalConnection()
    {
        if(!_isLocalSession)
            return true;

        if(!CommitTransaction())
            Rollback();

        final DatabaseConnectionPool databaseConnectionPool =
                DatabaseConnectionPool.getInstance();

        if(!databaseConnectionPool.releaseConnection(_databaseConnection))
            return false;

        _databaseConnection = null;

        return true;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
