package bg.tu_varna.sit.couriermanagementsystem.database;

import bg.tu_varna.sit.couriermanagementsystem.database.connection.DatabaseConnectionPool;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.ComparisonTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.LockTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLCriteria;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLQuery;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/*Клас за тестване на връзката към базата данни*/
class SQLQueryTest
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
    public void generateSelectQueryForCitiesTable() throws SQLException
    {
        DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        SQLQuery sqlQuery = new SQLQuery("CITIES", LockTypes.READ_ONLY);

        assertEquals(sqlQuery.generateSQLStatement(), "SELECT * FROM CITIES WITH(NOLOCK) ");
    }

    @Test
    public void generateSelectQueryForCitiesTableWithWhereClause() throws SQLException
    {
        DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        SQLQuery sqlQuery = new SQLQuery("CITIES", LockTypes.READ_ONLY);
        sqlQuery.addCriteria(new SQLCriteria("NAME", ComparisonTypes.EQUALS, "Варна"));

        assertEquals(sqlQuery.generateSQLStatement(), "SELECT * FROM CITIES WITH(NOLOCK)  WHERE NAME = 'Варна'");
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
