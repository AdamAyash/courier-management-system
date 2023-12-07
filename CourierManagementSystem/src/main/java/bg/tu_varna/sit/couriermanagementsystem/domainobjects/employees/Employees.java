package bg.tu_varna.sit.couriermanagementsystem.domainobjects.employees;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.PrimaryKey;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.UpdatableDomainObject;

import java.sql.Date;

/**/
public class Employees extends UpdatableDomainObject
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
    private String  _lastName;
    private String  _UCN;
    private Date _dateOfBirth;
    private short _gender;
    private String _phoneNumber;
    private int  _userID;
    private int _cityID;
    private int _companyID;
    private String _email;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public Employees()
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

    public String getFirstName()
    {
        return _firstName;
    }

    public void setFirstName(String firstName)
    {
        this._firstName = firstName;
    }

    public String getLastName()
    {
        return _lastName;
    }

    public void setLastName(String _lastName)
    {
        this._lastName = _lastName;
    }

    public String getUCN()
    {
        return _UCN;
    }

    public void setUCN(String UCN)
    {
        this._UCN = UCN;
    }

    public Date getDateOfBirth()
    {
        return _dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth)
    {
        this._dateOfBirth = dateOfBirth;
    }

    public short getGender()
    {
        return _gender;
    }

    public void setGender(short gender)
    {
        this._gender = gender;
    }

    public String getPhoneNumber()
    {
        return _phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this._phoneNumber = phoneNumber;
    }

    public int getUserID()
    {
        return _userID;
    }

    public void setUserID(int userID)
    {
        this._userID = userID;
    }

    public int getCityID()
    {
        return _cityID;
    }

    public void setCityID(int cityID)
    {
        this._cityID = cityID;
    }

    public int getCompanyID()
    {
        return _companyID;
    }

    public void setCompanyID(int companyID)
    {
        this._companyID = companyID;
    }

    public String getEmail(){return _email;}

    public void setEmail(String email){_email = email;}

    public String getMiddleName()
    {
        return _middleName;
    }
    public void setMiddleName(String middleName)
    {
        _middleName = middleName;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
