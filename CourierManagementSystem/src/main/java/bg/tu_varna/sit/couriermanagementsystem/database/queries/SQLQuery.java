package bg.tu_varna.sit.couriermanagementsystem.database.queries;

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
    private  String _tableName;
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
                return  " = ";
            }
            default -> {
            throw new SQLException();
        }
        }
    }

    public String generateSQLStatement() throws SQLException
    {
        String sqlStatement = "SELECT * FROM " + _tableName;

        switch (_lockType)
        {
            case READ_ONLY -> sqlStatement += " WITH(NOLOCK) ";
            case UPDATE ->  sqlStatement += " WITH(UPDLOCK) ";
        }

        if(_sqlCriteriaList.size() > 0)
        {
            sqlStatement+= " WHERE ";
        }

        for(int index = 0; index < _sqlCriteriaList.size(); index++)
        {
            SQLCriteria sqlCriteria = _sqlCriteriaList.get(index);

            sqlStatement += sqlCriteria.getColumnName();
            sqlStatement += getComparisonTypeOperator(sqlCriteria.getComparisonType());
            sqlStatement += sqlCriteria.getKey();

            if(index != _sqlCriteriaList.size() - 1)
                sqlStatement += " AND ";
        }
        return sqlStatement;
    }

    //-------------------------
    //Overrides:
    //-------------------------

}

