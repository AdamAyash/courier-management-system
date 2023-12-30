package bg.tu_varna.sit.couriermanagementsystem.database.storedprocedures;


/**/
public class Parameter<ParameterType>
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private ParameterType _key;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public Parameter(ParameterType key)
    {
        _key = key;
    }

    //-------------------------
    //Methods:
    //-------------------------
    public ParameterType getParameterType()
    {
        return _key;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
