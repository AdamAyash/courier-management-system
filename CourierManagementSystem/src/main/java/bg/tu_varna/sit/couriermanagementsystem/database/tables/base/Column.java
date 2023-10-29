package bg.tu_varna.sit.couriermanagementsystem.database.tables.base;


import java.lang.reflect.Field;

/**/
public class Column
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private String _columnName;
    private String _fieldName;
    private Field _field;
    private DataMap _dataMap;

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public Column(String columnName, String fieldName, DataMap dataMap) throws NoSuchFieldException
    {
        this._columnName = columnName;
        this._fieldName = fieldName;
        this._dataMap = dataMap;
        initField();
    }

    //-------------------------
    //Methods:
    //-------------------------

    public String getFieldName()
    {
        return this._fieldName;
    }
    public String getColumnName()
    {
        return  this._columnName;
    }
    private void initField()
    {
        try
        {
            _field = _dataMap.getDomainCLass().getDeclaredField(getFieldName());
            _field.setAccessible(true);
        }
        catch (NoSuchFieldException e){
           e.printStackTrace();
        }
    }

    public void setField(Object domainObject, Object columnValue) throws IllegalAccessException
    {
        _field.set(domainObject, columnValue);
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
