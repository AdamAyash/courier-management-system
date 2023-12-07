package bg.tu_varna.sit.couriermanagementsystem.domainobjects.counters;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.PrimaryKey;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.DomainObject;

/*Дискова структура за системния брояч*/
public final class Counters implements DomainObject
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private String _counterName;

    @PrimaryKey
    private String _tableName;
    private String _primaryKeyColumnName;
    private int _startIndex;
    private int _currentIndexPosition;
    private int _indexStep;
    private String _description;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public Counters()
    {

    }

    //-------------------------
    //Methods:
    //-------------------------

    public String getCounterName()
    {
        return _counterName;
    }

    public void setCounterName(String _counterName)
    {
        this._counterName = _counterName;
    }

    public String getTableName()
    {
        return _tableName;
    }

    public void setTableName(String _tableName)
    {
        this._tableName = _tableName;
    }

    public String getPrimaryKeyColumnName()
    {
        return _primaryKeyColumnName;
    }

    public void setPrimaryKeyColumnName(String _primaryKeyColumnName)
    {
        this._primaryKeyColumnName = _primaryKeyColumnName;
    }

    public int getStartIndex()
    {
        return _startIndex;
    }

    public void setStartIndex(int _startIndex)
    {
        this._startIndex = _startIndex;
    }

    public int getCurrentIndexPosition()
    {
        return _currentIndexPosition;
    }

    public void setCurrentIndexPosition(int _currentIndexPosition)
    {
        this._currentIndexPosition = _currentIndexPosition;
    }

    public int getIndexStep()
    {
        return _indexStep;
    }

    public void setIndexStep(int _indexStep)
    {
        this._indexStep = _indexStep;
    }

    public String getDescription()
    {
        return _description;
    }

    public void setDescription(String _description)
    {
        this._description = _description;
    }

    public void incrementID()
    {
        _currentIndexPosition += _indexStep;
    }
    //-------------------------
    //Overrides:
    //-------------------------
}
