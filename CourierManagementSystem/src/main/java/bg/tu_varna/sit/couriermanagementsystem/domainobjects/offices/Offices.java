package bg.tu_varna.sit.couriermanagementsystem.domainobjects.offices;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.PrimaryKey;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.UpdatableDomainObject;
import java.sql.Date;
import java.util.Objects;

/*Дискова структура за  Офици*/
public class Offices extends UpdatableDomainObject
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------

    @PrimaryKey
    private int _ID;
    private int _companyID;
    private String _officeName;
    private int _cityID;
    private String _address;
    private Date _dateEstablished;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public Offices()
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

    public int getCityID()
    {
        return _cityID;
    }

    public void setCityID(int cityID)
    {
        this._cityID = cityID;
    }

    public String getAddress()
    {
        return _address;
    }

    public void setAddress(String address)
    {
        this._address = address;
    }

    public int getCompanyID()
    {
        return _companyID;
    }
    public void setCompanyID(int companyID)
    {
        _companyID = companyID;
    }

    public Date getDateEstablished()
    {
        return _dateEstablished;
    }
    public void setDateEstablished(Date dateEstablished)
    {
        _dateEstablished = dateEstablished;
    }

    public String getOfficeName()
    {
        return _officeName;
    }

    public void setOfficeName(String officeName)
    {
        _officeName = officeName;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(!(o instanceof Offices offices)) return false;
        return _ID == offices._ID;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(_ID, _companyID, _cityID, _address);
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
