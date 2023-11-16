package bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.PrimaryKey;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.Updatable;

/*Бизнес клас за фирма*/
public class Companies extends Updatable
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    @PrimaryKey
    private int _ID;
    private String _name;
    private String _EGFN;
    private int _cityID;
    private String _phoneNumber;
    private String _email;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public Companies()
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

    public int getCityID()
    {
        return _cityID;
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

    public void setCityID(int cityID)
    {
        _cityID = cityID;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
