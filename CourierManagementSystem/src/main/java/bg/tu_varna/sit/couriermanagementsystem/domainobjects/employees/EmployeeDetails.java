package bg.tu_varna.sit.couriermanagementsystem.domainobjects.employees;


import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.Users;

/*Транспортен клас за Employees */
public class EmployeeDetails
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private Employees _employeeRecord;
    private Users _userAccount;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------

    public EmployeeDetails()
    {
        _employeeRecord = new Employees();
        _userAccount = new Users();
    }

    public EmployeeDetails(Employees employeeRecord, Users user)
    {
        _employeeRecord = employeeRecord;
        _userAccount = user;
    }

    //-------------------------
    //Methods:
    //-------------------------
    public Employees getEmployeeRecord()
    {
        return _employeeRecord;
    }

    public Users getEmployeeAccount()
    {
        return _userAccount;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
