package bg.tu_varna.sit.couriermanagementsystem.database.tables.base;

import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.database.connection.DatabaseConnectionPool;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.ComparisonTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.LockTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLCriteria;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLQuery;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.UpdatableDomainObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;

/*Абстрактен клас описващ таблица от базата данни*/
public abstract class BaseTable<DomainObject>
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

    //Дали използваме локална сесия
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

    public boolean selectAllRecords(List<DomainObject> recordsList)
    {
        OpenConnection();
        try
        {
            Statement statement = _databaseConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            SQLQuery sqlQuery = new SQLQuery(_dataMap.getTableName(), LockTypes.READ_ONLY);

            ResultSet queryResult = statement.executeQuery(sqlQuery.generateSQLStatement());
            queryResult.beforeFirst();

            while (queryResult.next())
            {
                DomainObject record = (DomainObject) _dataMap.getDomainCLass().newInstance();
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

    public boolean selectRecordWhere(DomainObject record, SQLQuery sqlQuery)
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

    public boolean updateRecord(DomainObject oldRecord, DomainObject newRecord)
    {
        try
        {
            OpenConnection();
            StartTransaction();

            SQLQuery sqlQuery = FormSQLQueryByPrimaryKey(oldRecord, LockTypes.UPDATE);

            if(sqlQuery == null)
                throw new SQLException();

            DomainObject databaseRecord = (DomainObject) _dataMap.getDomainCLass().newInstance();
            Statement selectDatabaseRecordStatement = _databaseConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet databaseRecordResultSet = selectDatabaseRecordStatement.executeQuery(sqlQuery.generateSQLStatement());

            if(!databaseRecordResultSet.first())
                return false;

            mapDomainObjectFields(databaseRecordResultSet, databaseRecord);

            if(oldRecord instanceof UpdatableDomainObject)
            {
               if(! ((UpdatableDomainObject) oldRecord).CompareUpdateCounter((UpdatableDomainObject)databaseRecord))
               {
                   return false;
               }
                ((UpdatableDomainObject)newRecord).incrementCounter();
            }

            Statement updateStatement = _databaseConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSetUpdate = updateStatement.executeQuery(sqlQuery.generateSQLStatement());

            if(!resultSetUpdate.next())
                return false;

            mapDomainObjectToNewRecord(resultSetUpdate, newRecord);

            resultSetUpdate.updateRow();
        }
        catch (SQLException exception)
        {
            _logger.error(exception.getMessage());
            return false;
        }
        catch (NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
            return false;
        }
        catch (IllegalAccessException exception)
        {
            _logger.error(exception.getMessage());
            return false;
        }
        catch (InstantiationException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            if(!CloseConnection())
                return false;
        }
        return true;
    }

    public boolean insertRecord(DomainObject newRecord)
    {
        try
        {
            OpenConnection();
            StartTransaction();

            Statement statement = _databaseConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            SQLQuery sqlQuery = new SQLQuery(_dataMap.getTableName(), LockTypes.UPDATE);

            ResultSet newRecordQuery = statement.executeQuery(sqlQuery.generateSQLStatement());
            newRecordQuery.moveToInsertRow();

            mapDomainObjectToNewRecord(newRecordQuery, newRecord);

            newRecordQuery.insertRow();
        }
        catch (SQLException exception)
        {
            _logger.error(exception.getMessage());
            return false;
        }
        catch (NoSuchFieldException e)
        {
            throw new RuntimeException(e);
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            if(!CloseConnection())
                return false;
        }
        return true;
    }

    public boolean deleteRecord(final int ID)
    {
        try
        {
            OpenConnection();
            StartTransaction();

            Statement statement = _databaseConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            SQLQuery sqlQuery = new SQLQuery(_dataMap.getTableName(), LockTypes.UPDATE);
            sqlQuery.addCriteria(new SQLCriteria("ID", ComparisonTypes.EQUALS, ID));

            ResultSet resultSet = statement.executeQuery(sqlQuery.generateSQLStatement());

            if(!resultSet.first())
            {
                _logger.error(Messages.RECORD_DOES_NOT_EXIST + ID);
                return false;
            }

            resultSet.deleteRow();

        }
        catch (SQLException exception)
        {
            _logger.error(exception.getMessage());
            return false;
        }
        finally
        {
            if(!CloseConnection())
                return false;
        }

        return true;
    }

    private void mapDomainObjectFields(ResultSet resultSet, DomainObject record)
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

    private void mapDomainObjectToNewRecord(ResultSet resultSet, DomainObject record) throws SQLException, NoSuchFieldException, IllegalAccessException
    {
        for(int index = 0; index < _dataMap.getColumns().size(); index++)
        {
            Column column = _dataMap.getColumns().get(index);
            Field field = null;

            if(column.getIsInheritedField())
                field = record.getClass().getSuperclass().getDeclaredField(column.getFieldName());
            else
                field = record.getClass().getDeclaredField(column.getFieldName());

            field.setAccessible(true);
            Object columnValue;
            columnValue = field.get(record);
            resultSet.updateObject(column.getColumnName(), columnValue);
        }
    }

    private SQLQuery FormSQLQueryByPrimaryKey(DomainObject record, LockTypes lockTypes) throws NoSuchFieldException, IllegalAccessException
    {
        SQLQuery sqlQuery = null;

        for(int index = 0; index < _dataMap.getColumns().size(); index++)
        {
            Column column = _dataMap.getColumns().get(index);

            Field field = null;

            if(column.getIsInheritedField())
                field = record.getClass().getSuperclass().getDeclaredField(column.getFieldName());
            else
                field = record.getClass().getDeclaredField(column.getFieldName());

            if(field.isAnnotationPresent(PrimaryKey.class))
            {
                sqlQuery = new SQLQuery(getTableName(), lockTypes);
                field.setAccessible(true);
                sqlQuery.addCriteria(new SQLCriteria(column.getColumnName(), ComparisonTypes.EQUALS, field.get(record)));
            }
        }

        return sqlQuery;
    }

    protected String getTableName()
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
            return false;
        }
        return true;
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
            return false;
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
