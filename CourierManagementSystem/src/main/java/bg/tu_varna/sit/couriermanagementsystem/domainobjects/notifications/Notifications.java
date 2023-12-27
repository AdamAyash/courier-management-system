package bg.tu_varna.sit.couriermanagementsystem.domainobjects.notifications;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.PrimaryKey;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.DomainObject;

import java.sql.Date;

/**/
public class Notifications implements DomainObject
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    @PrimaryKey
    private int _ID;
    private int _userID;
    private int _orderID;
    private int _employeeID;
    private String _message;
    private Date _registerDate;
    private boolean _seenByUser;

    public int getID()
    {
        return _ID;
    }

    public void setID(int ID)
    {
        this._ID = ID;
    }

    public int getUserID()
    {
        return _userID;
    }

    public void setUserID(int userID)
    {
        this._userID = userID;
    }

    public int getEmployeeID()
    {
        return _employeeID;
    }

    public void set_employeeID(int employeeID)
    {
        this._employeeID = employeeID;
    }

    public String getMessage()
    {
        return _message;
    }

    public void setMessage(String message)
    {
        this._message = message;
    }

    public Date getRegisterDate()
    {
        return _registerDate;
    }

    public void setRegisterDate(Date registerDate)
    {
        this._registerDate = registerDate;
    }

    public boolean isSeenByUser()
    {
        return _seenByUser;
    }

    public void setSeenByUser(boolean seenByUser)
    {
        this._seenByUser = seenByUser;
    }
    public int getOrderID()
    {
        return _orderID;
    }

    public void setOrderID(int orderID)
    {
        _orderID = orderID;
    }

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public Notifications()
    {
    }

    //-------------------------
    //Methods:
    //-------------------------

    //-------------------------
    //Overrides:
    //-------------------------
}
