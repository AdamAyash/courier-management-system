package bg.tu_varna.sit.couriermanagementsystem.domainobjects.orders;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.PrimaryKey;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.UpdatableDomainObject;
import java.sql.Date;

/**/
public class Orders extends UpdatableDomainObject
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------

    @PrimaryKey
    private int _ID;
    private int _officeID;
    private int _companyID;
    private int _orderTypeID;
    private int _employeeID;
    private int _clientID;
    private Date _dateRegistered;
    private short _status;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public Orders()
    {
    }

    //-------------------------
    //Methods:
    //-------------------------
    public int getID()
    {
        return _ID;
    }

    public void setID(int ID)
    {
        this._ID = ID;
    }

    public int getOfficeID()
    {
        return _officeID;
    }

    public int getCompanyID()
    {
        return _companyID;
    }

    public void setCompanyID(int companyID)
    {
        _companyID = companyID;
    }

    public void setOfficeID(int officeID)
    {
        this._officeID = officeID;
    }

    public int getOrderTypeID()
    {
        return _orderTypeID;
    }

    public void setOrderTypeID(int orderTypeID)
    {
        this._orderTypeID = orderTypeID;
    }

    public int getEmployeeID()
    {
        return _employeeID;
    }

    public void setEmployeeID(int employeeID)
    {
        this._employeeID = employeeID;
    }

    public int getClientID()
    {
        return _clientID;
    }

    public void setClientID(int clientID)
    {
        this._clientID = clientID;
    }

    public Date getDateRegistered()
    {
        return _dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered)
    {
        this._dateRegistered = dateRegistered;
    }

    public short getStatus()
    {
        return _status;
    }

    public void setStatus(short status)
    {
        this._status = status;
    }
    //-------------------------
    //Overrides:
    //-------------------------
}
