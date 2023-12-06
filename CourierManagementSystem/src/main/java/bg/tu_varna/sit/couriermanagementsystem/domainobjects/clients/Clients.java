package bg.tu_varna.sit.couriermanagementsystem.domainobjects.clients;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.PrimaryKey;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.UpdatableDomainObject;

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
    private String _name;
    private String _UCN;
    private int _companyID;
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

    public String getName() {return _name;}

    public String getUCN() {return _UCN;}

    public int getCompanyID() {return _companyID;}

    public String getPhoneNumber() {return _phoneNumber;}

    public void setName (String name) {_name = name;}

    public void setUCN (String UCN) {_UCN = UCN;}

    public void setCompanyID (int CompanyID) {_companyID = CompanyID;}

    public void setPhoneNumber (String PhoneNumber) {_phoneNumber = PhoneNumber;}

    //-------------------------
    //Overrides:
    //-------------------------
}
