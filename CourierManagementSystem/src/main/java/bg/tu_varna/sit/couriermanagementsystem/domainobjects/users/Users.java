package bg.tu_varna.sit.couriermanagementsystem.domainobjects.users;

import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.DomainObject;

/*Бизнес клас за потребител*/
public class Users implements DomainObject
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private int _ID;
    private  int _updateCounter;
    private String _username;
    private String _password;
    private int _accessID;

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public Users()
    {

    }

    //-------------------------
    //Methods:
    //-------------------------
    public void setUsername(String username)
    {
        this._username = username;
    }

    public int getID()
    {
        return _ID;
    }

    public String getUsername()
    {
        return _username;
    }

    public String getPassword()
    {
        return _password;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
