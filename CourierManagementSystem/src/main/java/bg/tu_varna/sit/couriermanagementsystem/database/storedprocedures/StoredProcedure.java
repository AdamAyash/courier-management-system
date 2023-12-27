package bg.tu_varna.sit.couriermanagementsystem.database.storedprocedures;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.DatabaseObject;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class StoredProcedure extends DatabaseObject
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private String _storedProcedureName;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public StoredProcedure(String storedProcedureName)
    {
        _storedProcedureName = storedProcedureName;
        _isLocalSession = true;
    }

    //-------------------------
    //Methods:
    //-------------------------
    public boolean executeStoredProcedure()
    {
        try
        {
            OpenLocalConnection();
            StartTransaction();

            CallableStatement executeProcedureStatement = _databaseConnection.prepareCall("EXEC " + _storedProcedureName);
            executeProcedureStatement.executeUpdate();
        }
        catch (SQLException exception)
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

    //-------------------------
    //Overrides:
    //-------------------------
}
