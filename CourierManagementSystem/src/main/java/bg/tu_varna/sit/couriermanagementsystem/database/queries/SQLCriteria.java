package bg.tu_varna.sit.couriermanagementsystem.database.queries;

import java.util.List;

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
    private List<Key> _keysList = null;

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

    public SQLCriteria(String columnName, ComparisonTypes comparisonType,  List<Key> keysList)
    {
        _columnName = columnName;
        _comparisonType = comparisonType;
        _keysList = keysList;
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
    public List<Key> getKeysList(){return _keysList;}

    //-------------------------
    //Overrides:
    //-------------------------
}
