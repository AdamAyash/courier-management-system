package bg.tu_varna.sit.couriermanagementsystem.domainobjects.clients;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.PrimaryKey;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.UpdatableDomainObject;

/**/
public class Clients extends UpdatableDomainObject
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    @PrimaryKey
    private int _ID;
    private String _firstName;
    private String _middleName;
    private String _lastName;
    private String _UCN;
    private int _companyID;
    private int _userID;
    private String _address;
    private String _phoneNumber;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------

    public Clients()
    {
    }

    //-------------------------
    //Methods:
    //-------------------------

    public int getID() {return _ID;}
    public String getUCN() {return _UCN;}
    public String getFirstName() {return _firstName;}
    public String getMiddleName() {return _middleName;}
    public String getLastName() {return _lastName;}
    public int getCompanyID() {return _companyID;}
    public int getUserID(){return _userID;}
    public String getPhoneNumber() {return _phoneNumber;}

    public String getAddress()
    {
        return _address;
    }
    public void setAddress(String address)
    {
        _address = address;
    }
    public void setUCN (String UCN) {_UCN = UCN;}
    public void setCompanyID (int CompanyID) {_companyID = CompanyID;}
    public void setUserID(int userID)
    {
        _userID = userID;
    }
    public void setPhoneNumber (String PhoneNumber) {_phoneNumber = PhoneNumber;}
    public void setFirstName(String firstName)
    {
        _firstName = firstName;
    }
    public void setMiddleName(String middleName)
    {
        _middleName = middleName;
    }
    public void setLastName(String lastName)
    {
        _lastName = lastName;
    }
    //-------------------------
    //Overrides:
    //-------------------------

    @Override
    public String toString()
    {
        return _firstName + " - " + _lastName;
    }
}
