package bg.tu_varna.sit.couriermanagementsystem.domainobjects.users;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.base.PrimaryKey;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.DomainObject;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.UpdatableDomainObject;

import java.nio.file.AccessMode;

/*Бизнес клас за потребител*/
public class Users extends UpdatableDomainObject
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    @PrimaryKey
    private int _ID;
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

    public void setPassword(String password)
    {
        this._password = password;
    }

    public void setAccess(AccessRights accessRight){this._accessID = accessRight.ordinal();}

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

    public int getAccessID()
    {
        return _accessID;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
