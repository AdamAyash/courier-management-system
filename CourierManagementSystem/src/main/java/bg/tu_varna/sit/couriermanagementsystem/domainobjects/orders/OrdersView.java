package bg.tu_varna.sit.couriermanagementsystem.domainobjects.orders;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.PrimaryKey;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.DomainObject;
import java.sql.Date;

/**/
public class OrdersView implements DomainObject
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private int _orderID;
    private String _orderStatus;
    private String _orderType;
    private double _orderPrice;
    private String _officeName;
    private String _companyName;
    private String _companyEGFN;
    private int _employeeID;
    private String _employeeFullName;
    private int _clientID;
    private String _clientFullName;
    private String _clientUCN;
    private Date _dateRegistered;
    private Date _deliveryDate;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public OrdersView()
    {
    }

    //-------------------------
    //Methods:
    //-------------------------

    public String getOrderStatus()
    {
        return _orderStatus;
    }

    public void setOrderStatus(String orderStatus)
    {
        _orderStatus = orderStatus;
    }

    public int getOrderID()
    {
        return _orderID;
    }

    public void setOrderID(int orderID)
    {
        this._orderID = orderID;
    }

    public String getOrderType()
    {
        return _orderType;
    }

    public void setOrderType(String orderType)
    {
        this._orderType = orderType;
    }

    public double getOrderPrice()
    {
        return _orderPrice;
    }

    public void setOrderPrice(double orderPrice)
    {
        this._orderPrice = orderPrice;
    }

    public String getOfficeName()
    {
        return _officeName;
    }

    public void setOfficeName(String officeName)
    {
        this._officeName = officeName;
    }

    public String getCompanyName()
    {
        return _companyName;
    }

    public void setCompanyName(String companyName)
    {
        this._companyName = companyName;
    }

    public String getCompanyEGFN()
    {
        return _companyEGFN;
    }

    public void setCompanyEGFN(String companyEGFN)
    {
        this._companyEGFN = companyEGFN;
    }

    public String getEmployeeFullName()
    {
        return _employeeFullName;
    }

    public void setEmployeeFullName(String employeeFullName)
    {
        this._employeeFullName = employeeFullName;
    }

    public String getClientFullName()
    {
        return _clientFullName;
    }

    public void setClientFullName(String clientFullName)
    {
        this._clientFullName = clientFullName;
    }

    public String getClientUCN()
    {
        return _clientUCN;
    }

    public void setClientUCN(String clientUCN)
    {
        this._clientUCN = clientUCN;
    }

    public Date getDateRegistered()
    {
        return _dateRegistered;
    }

    public void setDateRegistered(Date _dateRegistered)
    {
        this._dateRegistered = _dateRegistered;
    }

    public Date getDeliveryDate()
    {
        return _deliveryDate;
    }

    public void setDeliveryDate(Date _deliveryDate)
    {
        this._deliveryDate = _deliveryDate;
    }

    public void setClientID(int clientID)
    {
        _clientID = clientID;
    }
    public int getClientID() {return _clientID;}

    public int getEmployeeID()
    {
        return _employeeID;
    }
    public void setEmployeeID(int employeeID)
    {
        _employeeID = employeeID;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
