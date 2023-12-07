package bg.tu_varna.sit.couriermanagementsystem.userauthentication;

import bg.tu_varna.sit.couriermanagementsystem.database.tables.userstable.UsersTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.Users;
import com.password4j.Hash;
import com.password4j.Password;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*Клас за автентикация на потребители*/
public class UserAuthentication
{
    //-------------------------
    //Constants:
    //-------------------------
    private static UserAuthentication _userAuthenticationInstance = null;

    //-------------------------
    //Members:
    //-------------------------
    private static Map<String, Users> _usersIdentityMap;

    private static Users _currentlyLoggedUser = null;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    private  UserAuthentication()
    {
        _usersIdentityMap = new HashMap<>();
    }

    //-------------------------
    //Methods:
    //-------------------------

    public static UserAuthentication getInstance()
    {
        if(_userAuthenticationInstance == null)
        {
            _userAuthenticationInstance = new UserAuthentication();
            loadAllUsers();
        }

        return  _userAuthenticationInstance;
    }
    public String hashPassword(String password)
    {
        Hash hashFunction = Password.hash(password).addRandomSalt().withArgon2();
        return hashFunction.getResult();
    }

    private  boolean verifyPassword(String password, String hash)
    {
        return Password.check(password, hash).withArgon2();
    }


    private static void loadUsersIdentityMap(List<Users> _usersList)
    {
        for(Users user : _usersList)
        {
            _usersIdentityMap.put(user.getUsername(), user);
        }
    }

    public  boolean authenticateUser(String username, String password)
    {
       Users searchedUser = _usersIdentityMap.get(username);

       if(searchedUser == null)
           return  false;

       if(!verifyPassword(password, searchedUser.getPassword()))
           return false;

        _currentlyLoggedUser = searchedUser;

        return  true;
    }

    private static void loadAllUsers()
    {
        List<Users> usersList = new ArrayList<>();
        UsersTable usersTable = new UsersTable();

        if(!usersTable.selectAllRecords(usersList))
            return;

        loadUsersIdentityMap(usersList);
    }

    public Users getCurrentlyLoggedUser()
    {
        return _currentlyLoggedUser;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
