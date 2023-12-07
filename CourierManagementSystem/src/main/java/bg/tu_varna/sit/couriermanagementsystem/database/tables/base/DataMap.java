package bg.tu_varna.sit.couriermanagementsystem.database.tables.base;

import java.util.ArrayList;
import java.util.List;

/**/
public class DataMap
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private Class _domainClass;
    private String _tableName;
    private List<Column> _columns;

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public DataMap(Class domainClass, String tableName)
    {
        _domainClass = domainClass;
        _tableName = tableName;
        _columns = new ArrayList();
    }

    //-------------------------
    //Methods:
    //-------------------------
    public void addNewColumn(Column column)
    {
        _columns.add(column);
    }

    public Class getDomainCLass(){
        return this._domainClass;
    }

    public String getTableName()
    {
        return _tableName;
    }

    public List<Column> getColumns()
    {
        return  _columns;
    }

    public String columnList()
    {
        StringBuilder columnsList = new StringBuilder();
        for (int columnIndex = 0; columnIndex < _columns.size(); columnIndex++)
        {
            Column currentColumn = _columns.get(columnIndex);
            columnsList.append(currentColumn.getColumnName());

            if(!(columnIndex == _columns.size() - 1))
                columnsList.append(",");
        }

        return columnsList.toString();
    }

    public Column getPrimaryKeyColumn()
    {
        for(Column column: _columns)
        {
            if(column.getIsPrimaryKeyColumn())
                return column;
        }

        return null;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
