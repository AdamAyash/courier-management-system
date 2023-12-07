package bg.tu_varna.sit.couriermanagementsystem.domainobjects.clients;

import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.Users;

/**/
public class ClientsDetails
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private Users _clientAccount;
    private Clients _clientRecord;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public ClientsDetails()
    {
        _clientAccount = new Users();
        _clientRecord = new Clients();
    }

    public ClientsDetails(Users clientAccount, Clients clientRecord)
    {
        _clientAccount = clientAccount;
        _clientRecord = clientRecord;
    }

    //-------------------------
    //Methods:
    //-------------------------
    public Users getClientAccount()
    {
        return _clientAccount;
    }

    public Clients getClientRecord()
    {
        return _clientRecord;
    }

    public void setClientAccount(Users clientAccount)
    {
        _clientAccount = clientAccount;
    }

    public void setClientRecord(Clients clientRecord)
    {
        _clientRecord = clientRecord;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
