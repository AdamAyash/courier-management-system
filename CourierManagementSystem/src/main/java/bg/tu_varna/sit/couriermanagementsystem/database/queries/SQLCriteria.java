package bg.tu_varna.sit.couriermanagementsystem.database.queries;

/**/
public class SQLCriteria<Key>
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private String _columnName;
    private ComparisonTypes _comparisonType;
    private Key _key;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public SQLCriteria(String columnName, ComparisonTypes comparisonType, Key key)
    {
        _columnName = columnName;
        _comparisonType = comparisonType;
        _key = key;
    }

    //-------------------------
    //Methods:
    //-------------------------
    public String getColumnName()
    {
        return _columnName;
    }

    public ComparisonTypes getComparisonType()
    {
        return _comparisonType;
    }

    public Key getKey()
    {
        return _key;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
