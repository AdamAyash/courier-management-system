package bg.tu_varna.sit.couriermanagementsystem.database.tables.base;

import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;
import bg.tu_varna.sit.couriermanagementsystem.database.connection.DatabaseConnectionPool;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.ComparisonTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.LockTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLCriteria;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLQuery;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.counterstable.CountersTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.DomainObject;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.UpdatableDomainObject;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.counters.Counters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*Абстрактен клас описващ таблица от базата данни*/
public abstract class BaseTable<RecordType extends DomainObject>
{
    //-------------------------
    //Constants:
    //-------------------------
    private final int INVALID_ID = -1;
    protected static final Logger _logger = LogManager.getLogger();

    //-------------------------
    //Members:
    //-------------------------
    private Connection _databaseConnection;

    private boolean _isLocalSession;

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
        _isLocalSession = true;
    }

    protected BaseTable(Connection databaseConnection)
    {
        _databaseConnection = databaseConnection;
        _isLocalSession = false;
        loadDataMap();
    }

    //-------------------------
    //Methods:
    //-------------------------
    protected abstract void loadDataMap();

    public boolean selectAllRecords(List<RecordType> recordsList)
    {
        OpenLocalConnection();
        try
        {
            Statement statement = _databaseConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            SQLQuery sqlQuery = new SQLQuery(_dataMap.getTableName(), LockTypes.READ_ONLY);

            ResultSet queryResult = statement.executeQuery(sqlQuery.generateSQLStatement());
            queryResult.beforeFirst();

            while (queryResult.next())
            {
                RecordType record = (RecordType) _dataMap.getDomainCLass().newInstance();

                if(!mapResultSetToRecord(queryResult, record))
                    return false;

                if(record == null)
                    return false;

                recordsList.add(record);
            }
        }
        catch (InstantiationException | IllegalAccessException | SQLException exception)
        {
            _logger.error(exception.getMessage());
            return false;
        }
        finally
        {
            if(!CloseLocalConnection())
                return false;
        }
        return true;
    }

    public boolean selectAllRecordsWhere(List<RecordType> recordsList, final SQLQuery sqlQuery)
    {
        OpenLocalConnection();
        try
        {
            Statement statement = _databaseConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet queryResult = statement.executeQuery(sqlQuery.generateSQLStatement());
            queryResult.beforeFirst();

            while (queryResult.next())
            {
                RecordType record = (RecordType) _dataMap.getDomainCLass().newInstance();

                if(!mapResultSetToRecord(queryResult, record))
                {
                    Rollback();
                    return false;
                }

                if(record == null)
                    return false;

                recordsList.add(record);
            }
        }
        catch (SQLException | InstantiationException | IllegalAccessException exception)
        {
            _logger.error(exception.getMessage());
            return false;
        }
        if(!CloseLocalConnection())
            return false;

        return true;
    }

    public boolean selectRecordWhere(RecordType record, final SQLQuery sqlQuery)
    {
        OpenLocalConnection();

        try
        {
            Statement statement = _databaseConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet queryResult = statement.executeQuery(sqlQuery.generateSQLStatement());

            if(!queryResult.first())
                return false;

            if(!mapResultSetToRecord(queryResult, record))
            {
                Rollback();
                return false;
            }

            if(record == null)
                return false;

        }
        catch (SQLException exception)
        {
            _logger.error(exception.getMessage());
            return false;
        }

        if(!CloseLocalConnection())
            return false;

        return true;
    }

    public boolean selectRecordByID(RecordType record, final int ID)
    {
        OpenLocalConnection();

        try
        {
            Statement statement = _databaseConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            Column priamryKeyColumn = _dataMap.getPrimaryKeyColumn();

            if(priamryKeyColumn == null)
                return false;

            SQLQuery sqlQuery = new SQLQuery(getTableName(), LockTypes.READ_ONLY);
            sqlQuery.addCriteria(new SQLCriteria<Integer>(priamryKeyColumn.getColumnName(), ComparisonTypes.EQUALS, ID));

            ResultSet queryResult = statement.executeQuery(sqlQuery.generateSQLStatement());

            if(!queryResult.first())
                return false;

            if(!mapResultSetToRecord(queryResult, record))
            {
                Rollback();
                return false;
            }

            if(record == null)
                return false;

        }
        catch (SQLException exception)
        {
            _logger.error(exception.getMessage());
            return false;
        }
        finally
        {
            if(!CloseLocalConnection())
                return false;
        }

        return true;
    }

    public boolean updateRecord(RecordType newRecord)
    {
        try
        {
            OpenLocalConnection();
            StartTransaction();

            SQLQuery sqlQuery = FormSQLQueryByPrimaryKey(newRecord, LockTypes.UPDATE);

            if(sqlQuery == null)
                return false;

            RecordType databaseRecord = (RecordType) _dataMap.getDomainCLass().newInstance();
            Statement selectDatabaseRecordStatement = _databaseConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet databaseRecordResultSet = selectDatabaseRecordStatement.executeQuery(sqlQuery.generateSQLStatement());

            if(!databaseRecordResultSet.first())
            {
                Rollback();
                return false;
            }

            if(!mapResultSetToRecord(databaseRecordResultSet, databaseRecord))
            {
                Rollback();
                return false;
            }

            if(newRecord instanceof UpdatableDomainObject)
            {
                if(!((UpdatableDomainObject) newRecord).CompareUpdateCounter((UpdatableDomainObject) databaseRecord))
                {
                    Rollback();
                    return false;
                }
                ((UpdatableDomainObject) newRecord).incrementCounter();
            }

            Statement updateStatement = _databaseConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSetUpdate = updateStatement.executeQuery(sqlQuery.generateSQLStatement());

            if(!resultSetUpdate.next())
            {
                Rollback();
                return false;
            }

            updateResultSet(resultSetUpdate, newRecord);

            resultSetUpdate.updateRow();
        }
        catch (SQLException | IllegalAccessException | NoSuchFieldException | InstantiationException exception)
        {
            _logger.error(exception.getMessage());
            Rollback();
            return false;
        }
        finally
        {
            if(!CloseLocalConnection())
                return false;
        }
        return true;
    }

    private int generateUniqueIdentifier()
    {
        final CountersTable countersTable = new CountersTable(_databaseConnection);

        SQLQuery selectTableCounter = new SQLQuery(countersTable.getTableName(), LockTypes.UPDATE);
        selectTableCounter.addCriteria(new SQLCriteria(CountersTable.CountersTableColumns.TABLE_NAME.getColumnName(), ComparisonTypes.EQUALS, getTableName()));
        selectTableCounter.addCriteria(new SQLCriteria(CountersTable.CountersTableColumns.PRIMARY_KEY_COLUMN_NAME.getColumnName(),
                ComparisonTypes.EQUALS, _dataMap.getPrimaryKeyColumn().getColumnName()));

        Counters counter = new Counters();
        if(!countersTable.selectRecordWhere(counter, selectTableCounter))
            return INVALID_ID;

        //Първо актуализираме текущата позиция на брояча
        counter.incrementID();

        if(!countersTable.updateRecord(counter))
            return INVALID_ID;

        return counter.getCurrentIndexPosition();
    }

    public boolean insertRecord(RecordType newRecord)
    {
        try
        {
            OpenLocalConnection();
            StartTransaction();

            Statement statement = _databaseConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            SQLQuery sqlQuery = new SQLQuery(_dataMap.getTableName(), LockTypes.UPDATE);

            ResultSet newRecordQuery = statement.executeQuery(sqlQuery.generateSQLStatement());
            newRecordQuery.moveToInsertRow();

            if(!mapNewRecordToResultSet(newRecordQuery, newRecord))
            {
                Rollback();
                return false;
            }
            newRecordQuery.insertRow();
        }
        catch (SQLException | IllegalAccessException | NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
            Rollback();
            return false;
        }
        finally
        {
            if(!CloseLocalConnection())
                return false;
        }
        return true;
    }

    public final boolean SynchronizeRecords(final List<RecordType> recordsList, final SQLQuery sqlQuery)
    {
        try
        {
            List<RecordType> databaseRecordsList = new ArrayList<>();
            if(!selectAllRecordsWhere(databaseRecordsList, sqlQuery))
                return false;

            for(int index = 0; index < databaseRecordsList.size(); index++)
            {
                RecordType databaseRecord = databaseRecordsList.get(index);
                if(databaseRecord == null)
                    return false;

                if(!recordsList.contains(databaseRecord))
                {
                    if(!deleteRecord(databaseRecord))
                    {
                        Rollback();
                        return false;
                    }
                }
            }

            for(int index = 0; index < recordsList.size(); index++)
            {
                RecordType record = recordsList.get(index);
                Field primaryKeyField = record.getClass().getDeclaredField(_dataMap.getPrimaryKeyColumn().getFieldName());
                primaryKeyField.setAccessible(true);
                if((int) primaryKeyField.get(record) == 0)
                {
                    if(!insertRecord(record))
                    {
                        Rollback();
                        return false;
                    }
                    continue;
                }
                if(!updateRecord(record))
                {
                    Rollback();
                    return false;
                }
            }
        }
        catch (NoSuchFieldException | IllegalAccessException exception)
        {
            _logger.error(exception.getMessage());
            Rollback();
        }
        return true;
    }

    public boolean deleteRecord(final RecordType record)
    {
        try
        {
            OpenLocalConnection();
            StartTransaction();

            Statement statement = _databaseConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            SQLQuery sqlQuery = FormSQLQueryByPrimaryKey(record, LockTypes.UPDATE);

            ResultSet resultSet = statement.executeQuery(sqlQuery.generateSQLStatement());

            if(!resultSet.first())
            {
                _logger.error(Messages.RECORD_DOES_NOT_EXIST);
                Rollback();
                return false;
            }
            resultSet.deleteRow();

        }
        catch (SQLException | IllegalAccessException | NoSuchFieldException exception)
        {
            _logger.error(exception.getMessage());
            Rollback();
            return false;
        }
        finally
        {
            if(!CloseLocalConnection())
                return false;
        }

        return true;
    }

    private void updateResultSet(ResultSet resultSet, final RecordType record) throws SQLException, NoSuchFieldException, IllegalAccessException
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

    private boolean mapResultSetToRecord(ResultSet resultSet, RecordType record)
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
        catch (IllegalAccessException | SQLException exception)
        {
            _logger.error(exception.getMessage());
            return false;
        }

        return true;
    }

    private boolean mapNewRecordToResultSet(ResultSet resultSet, RecordType record) throws SQLException, NoSuchFieldException, IllegalAccessException
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

            Object columnValue = null;
            columnValue = field.get(record);

            if(column.getIsPrimaryKeyColumn())
            {
                final int nextGeneratedID = generateUniqueIdentifier();

                if(nextGeneratedID > (int) columnValue)
                {
                    columnValue = nextGeneratedID;
                    field.set(record, columnValue);
                }
                else
                {
                    return false;
                }
            }

            if(!(columnValue != null))
                return false;

            resultSet.updateObject(column.getColumnName(), columnValue);
        }
        return true;
    }

    private SQLQuery FormSQLQueryByPrimaryKey(RecordType record, LockTypes lockTypes) throws NoSuchFieldException, IllegalAccessException
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

    public String getTableName()
    {
        return _dataMap.getTableName();
    }

    private void StartTransaction() throws SQLException
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

    private void OpenLocalConnection()
    {
        if(_databaseConnection != null)
            return;

        final DatabaseConnectionPool databaseConnectionPool =
                DatabaseConnectionPool.getInstance();

        _databaseConnection = databaseConnectionPool.getConnection();
    }

    private void Rollback()
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

    private boolean CloseLocalConnection()
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
