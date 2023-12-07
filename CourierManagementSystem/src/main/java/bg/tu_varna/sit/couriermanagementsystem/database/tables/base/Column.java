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
    private boolean _isInheritedField;
    private boolean _isPrimaryKeyColumn;

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public Column(String columnName, String fieldName, DataMap dataMap) throws NoSuchFieldException
    {
        this._columnName = columnName;
        this._fieldName = fieldName;
        this._dataMap = dataMap;
        _isPrimaryKeyColumn = false;
        initField();
    }

    public Column(String columnName, String fieldName, DataMap dataMap, boolean isInheritedField) throws NoSuchFieldException
    {
        this._columnName = columnName;
        this._fieldName = fieldName;
        this._dataMap = dataMap;
        _isInheritedField = isInheritedField;
        _isPrimaryKeyColumn = false;
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
        return this._columnName;
    }

    private void initField() throws NoSuchFieldException
    {
        if(_isInheritedField)
        {
            _field = _dataMap.getDomainCLass().getSuperclass().getDeclaredField(getFieldName());
        }
        else
        {
            _field = _dataMap.getDomainCLass().getDeclaredField(getFieldName());
        }
        _field.setAccessible(true);

        if(_field.isAnnotationPresent(PrimaryKey.class))
        {
            _isPrimaryKeyColumn = true;
        }
    }

    public void setField(Object domainObject, Object columnValue) throws IllegalAccessException
    {
        _field.set(domainObject, columnValue);
    }

    public boolean getIsInheritedField()
    {
        return _isInheritedField;
    }

    public boolean getIsPrimaryKeyColumn()
    {
        return _isPrimaryKeyColumn;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
