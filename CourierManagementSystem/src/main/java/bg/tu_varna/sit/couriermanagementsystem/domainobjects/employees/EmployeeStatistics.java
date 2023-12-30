package bg.tu_varna.sit.couriermanagementsystem.domainobjects.employees;

import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.DomainObject;

/**/
public class EmployeeStatistics implements DomainObject
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private int _employeeID;
    private String _employeeFirstName;
    private String _employeeLastName;
    private String _UCN;
    private int _companyID;
    private String _companyName;
    private int _totalOrders;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------

    //-------------------------
    //Methods:
    //-------------------------

    public int getEmployeeID()
    {
        return _employeeID;
    }

    public void setEmployeeID(int employeeID)
    {
        this._employeeID = employeeID;
    }

    public String getEmployeeFirstName()
    {
        return _employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName)
    {
        this._employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName()
    {
        return _employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName)
    {
        this._employeeLastName = employeeLastName;
    }

    public String getUCN()
    {
        return _UCN;
    }

    public void setUCN(String UCN)
    {
        this._UCN = UCN;
    }

    public int getCompanyID()
    {
        return _companyID;
    }

    public void setCompanyID(int companyID)
    {
        this._companyID = companyID;
    }

    public String getCompanyName()
    {
        return _companyName;
    }

    public void setCompanyName(String companyName)
    {
        this._companyName = companyName;
    }

    public int getTotalOrders()
    {
        return _totalOrders;
    }

    public void setTotalOrders(int totalOrders)
    {
        this._totalOrders = totalOrders;
    }


    //-------------------------
    //Overrides:
    //-------------------------
}
