package bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies;

import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.DomainObject;

/**/
public class CompanyStatistics implements DomainObject
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private int _companyID;
    private String _companyName;
    private int _totalClients;
    private int _totalEmployees;
    private double _totalAmount;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------

    //-------------------------
    //Methods:
    //-------------------------

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

    public int getTotalClients()
    {
        return _totalClients;
    }

    public void setTotalClients(int totalClients)
    {
        this._totalClients = totalClients;
    }

    public int getTotalEmployees()
    {
        return _totalEmployees;
    }

    public void setTotalEmployees(int totalEmployees)
    {
        this._totalEmployees = totalEmployees;
    }

    public double getTotalAmount()
    {
        return _totalAmount;
    }

    public void setTotalAMount(double totalAmount)
    {
        this._totalAmount = totalAmount;
    }


    //-------------------------
    //Overrides:
    //-------------------------
}
