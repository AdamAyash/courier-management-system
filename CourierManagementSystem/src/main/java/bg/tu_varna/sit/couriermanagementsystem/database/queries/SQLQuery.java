package bg.tu_varna.sit.couriermanagementsystem.database.queries;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*Клас за генериране на sql заявки*/
public class SQLQuery
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private final List<SQLCriteria> _sqlCriteriaList;
    private String _tableName;
    private LockTypes _lockType;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public SQLQuery(String tableName, LockTypes lockType)
    {
        _sqlCriteriaList = new ArrayList<>();
        _tableName = tableName;
        _lockType = lockType;
    }

    //-------------------------
    //Methods:
    //-------------------------
    public void addCriteria(SQLCriteria sqlCriteria)
    {
        _sqlCriteriaList.add(sqlCriteria);
    }

    private String getComparisonTypeOperator(ComparisonTypes comparisonType) throws SQLException
    {
        switch (comparisonType)
        {
            case EQUALS ->
            {
                return " = ";
            }
            case BETWEEN ->
            {
                return " BETWEEN ";
            }
            default ->
            {
                throw new SQLException();
            }
        }
    }

    private void processKey(StringBuilder sqlStatement, Object key)
    {
        if(key instanceof String)
        {
            sqlStatement.append("\'");
            sqlStatement.append(key);
            sqlStatement.append("\'");
        }
        else
        {
            sqlStatement.append(key);
        }
    }


    public String generateSQLStatement() throws SQLException
    {
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM " + _tableName);

        switch (_lockType)
        {
            case READ_ONLY -> sqlStatement.append(" WITH(NOLOCK) ");
            case UPDATE -> sqlStatement.append(" WITH(UPDLOCK) ");
        }

        if(_sqlCriteriaList.size() > 0)
        {
            sqlStatement.append(" WHERE ");
        }

        for(int index = 0; index < _sqlCriteriaList.size(); index++)
        {
            SQLCriteria sqlCriteria = _sqlCriteriaList.get(index);

            ComparisonTypes comparisonType = sqlCriteria.getComparisonType();

            sqlStatement.append(sqlCriteria.getColumnName());
            sqlStatement.append(getComparisonTypeOperator(comparisonType));

            if(sqlCriteria.getKeysList() != null)
            {
                for(int keyIndex = 0; keyIndex < sqlCriteria.getKeysList().size(); keyIndex++)
                {
                    processKey(sqlStatement,sqlCriteria.getKeysList().get(keyIndex));

                    if(keyIndex !=  sqlCriteria.getKeysList().size() - 1)
                        sqlStatement.append(" AND ");
                }
                continue;
            }
        processKey(sqlStatement, sqlCriteria.getKey());

        if(index != _sqlCriteriaList.size() - 1)
            sqlStatement.append(" AND ");
    }
        return sqlStatement.toString();
}

//-------------------------
//Overrides:
//-------------------------

}

