package bg.tu_varna.sit.couriermanagementsystem.domainobjects;

/**/
public class Users
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private int _ID;
    private String _username;
    private String _password;

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

    //-------------------------
    //Overrides:
    //-------------------------
}
