package bg.tu_varna.sit.couriermanagementsystem.database.tables.base;

import bg.tu_varna.sit.couriermanagementsystem.database.connection.DatabaseConnectionPool;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;

/*Абстрактен клас описващ таблица от базата данни*/
public abstract class BaseTable<RecordType>
{
    //-------------------------
    //Constants:
    //-------------------------
    protected static final Logger _logger = LogManager.getLogger();

    //-------------------------
    //Members:
    //-------------------------
    private Connection _databaseConnection;

    //Дали сме в активна транзакция
    private boolean _isTransactionActive;
    protected DataMap _dataMap;

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    protected BaseTable()
    {
        loadDataMap();
    }

    //-------------------------
    //Methods:
    //-------------------------

    protected abstract void loadDataMap();

    public boolean selectAllRecords(List<RecordType> recordsList)
    {
        OpenConnection();
        try
        {
            Statement statement = _databaseConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            SQLQuery sqlQuery = new SQLQuery(_dataMap.getTableName());

            ResultSet queryResult = statement.executeQuery(sqlQuery.generateSQLStatement());
            queryResult.beforeFirst();

            while (queryResult.next())
            {
                RecordType record = (RecordType) _dataMap.getDomainCLass().newInstance();
                mapDomainObjectFields(queryResult, record);

                if(record == null)
                    return false;

                recordsList.add(record);
            }
        }
        catch (SQLException exception)
        {
            _logger.error(exception.getMessage());
            return false;
        }
        catch (InstantiationException exception)
        {
            _logger.error(exception.getMessage());
            return false;
        }
        catch (IllegalAccessException exception)
        {
            _logger.error(exception.getMessage());
            return false;
        }

        if(!CloseConnection())
            return false;

        return true;
    }

    public boolean selectRecordWhere(RecordType record, SQLQuery sqlQuery)
    {
        OpenConnection();

        try
        {
            Statement statement = _databaseConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet queryResult = statement.executeQuery(sqlQuery.generateSQLStatement());

            if(!queryResult.first())
                return false;

            mapDomainObjectFields(queryResult, record);

            if(record == null)
                return false;

        }
        catch (SQLException exception)
        {
            _logger.error(exception.getMessage());
            return false;
        }

        if(!CloseConnection())
            return false;

        return true;
    }

    public boolean insertRecord(RecordType newRecord)
    {
       try
       {
           OpenConnection();
           StartTransaction();

           Statement statement = _databaseConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
           ResultSet newRecordQuery = statement.executeQuery("SELECT * FROM " + _dataMap.getTableName());
           newRecordQuery.moveToInsertRow();
           mapDomainObjectToNewRecord(newRecordQuery, newRecord);
           newRecordQuery.insertRow();
       }
       catch (SQLException exception)
       {
           _logger.error(exception.getMessage());
           return  false;
       }
       finally
       {
           if(!CloseConnection())
               return false;
       }
       return  true;
    }

    private void mapDomainObjectFields(ResultSet resultSet, RecordType record)
    {
        try
        {
            for(int index = 0; index < _dataMap.getColumns().size(); index++)
            {
                Column column = _dataMap.getColumns().get(index);
                Object columnValue = resultSet.getObject(column.getColumnName());
                column.setField(record, columnValue);
            }
        }
        catch (IllegalAccessException exception)
        {
            _logger.error(exception.getMessage());
        }
        catch (SQLException exception)
        {
            _logger.error(exception.getMessage());
        }
    }

    private void mapDomainObjectToNewRecord(ResultSet resultSet, RecordType record)
    {
        try
        {
            for(int index = 0; index < _dataMap.getColumns().size(); index++)
            {
                Column column = _dataMap.getColumns().get(index);
                Field field = record.getClass().getDeclaredField(column.getFieldName());
                field.setAccessible(true);
                Object columnValue;
                columnValue = field.get(record);
                resultSet.updateObject(column.getColumnName(), columnValue);
            }
        }
        catch (SQLException exception)
        {
            _logger.error(exception.getMessage());
        }
        catch (NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
        }
        catch (IllegalAccessException exception)
        {
            _logger.error(exception.getMessage());
        }
    }

    public String getTableName()
    {
        return _dataMap.getTableName();
    }

    private void StartTransaction() throws SQLException
    {
        _databaseConnection.setAutoCommit(false);
        _isTransactionActive = true;
    }

    private boolean CloseTransaction()
    {
        try
        {
            if(_isTransactionActive)
                _databaseConnection.commit();
        }
        catch (SQLException exception)
        {
            _logger.error(exception.getMessage());
            return  false;
        }
        return  true;
    }

    private void OpenConnection()
    {
        final DatabaseConnectionPool databaseConnectionPool =
                DatabaseConnectionPool.getInstance();

        _databaseConnection = databaseConnectionPool.getConnection();
    }

    private boolean CloseConnection()
    {
        try
        {
            if(!CloseTransaction())
                _databaseConnection.rollback();

        }
        catch (SQLException exception)
        {
            _logger.error(exception.getMessage());
            return  false;
        }

        final DatabaseConnectionPool databaseConnectionPool =
                DatabaseConnectionPool.getInstance();

        if(!databaseConnectionPool.releaseConnection(_databaseConnection))
            return false;

        return true;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
