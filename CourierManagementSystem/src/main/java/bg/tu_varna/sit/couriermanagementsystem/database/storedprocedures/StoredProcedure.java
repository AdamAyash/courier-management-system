package bg.tu_varna.sit.couriermanagementsystem.database.storedprocedures;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.DatabaseObject;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoredProcedure extends DatabaseObject
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private String _storedProcedureName;
    private List<Parameter> _parameterList;

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
        _parameterList = new ArrayList<>();
    }

    //-------------------------
    //Methods:
    //-------------------------

    public void addParameter(Parameter parameter)
    {
        _parameterList.add(parameter);
    }

    private String getStoredProcedureDynamicSQL()
    {
        StringBuilder dynamicSQL = new StringBuilder();
        dynamicSQL.append("EXEC" );
        dynamicSQL.append(" ");
        dynamicSQL.append(_storedProcedureName);
        dynamicSQL.append(" ");

        for(int index = 0; index < _parameterList.size(); index++)
        {
            Parameter currentParameter = _parameterList.get(index);
            dynamicSQL.append(currentParameter.getParameterType());

            if(index != _parameterList.size() - 1)
            dynamicSQL.append(",");
        }

        return dynamicSQL.toString();
    }

    public boolean executeStoredProcedure()
    {
        try
        {
            OpenLocalConnection();
            StartTransaction();

            CallableStatement executeProcedureStatement = _databaseConnection.prepareCall(getStoredProcedureDynamicSQL());
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
            if(!CloseLocalConnection()) return false;
        }
        return true;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
