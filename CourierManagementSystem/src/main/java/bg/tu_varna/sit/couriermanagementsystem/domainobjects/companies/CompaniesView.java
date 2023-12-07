package bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies;


import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.DomainObject;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.cities.Cities;

/*Транспортен клас само за table view представяне*/
public class CompaniesView implements DomainObject
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private int _ID;
    private String _name;
    private String _EGFN;
    private String _phoneNumber;
    private String _email;
    private String _cityName;
    private String _state;
    private String _postCode;
    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public CompaniesView()
    {
    }

    //-------------------------
    //Methods:
    //-------------------------
    public int getID()
    {
        return _ID;
    }

    public String getName()
    {
        return _name;
    }

    public String getEGFN()
    {
        return _EGFN;
    }

    public String getEmail()
    {
        return  _email;
    }

    public String getPhoneNumber()
    {
        return  _phoneNumber;
    }

    public void setName(String name)
    {
        _name = name;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        _phoneNumber = phoneNumber;
    }

    public void setEGFN(String EGFN)
    {
        _EGFN = EGFN;
    }

    public void setEmail(String email)
    {
        _email = email;
    }

    public String getCityName()
    {
        return _cityName;
    }

    public String getState()
    {
        return _state;
    }


    //-------------------------
    //Overrides:
    //-------------------------
}
