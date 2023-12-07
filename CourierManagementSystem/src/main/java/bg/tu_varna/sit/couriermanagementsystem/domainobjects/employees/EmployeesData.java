package bg.tu_varna.sit.couriermanagementsystem.domainobjects.employees;

import bg.tu_varna.sit.couriermanagementsystem.database.connection.DatabaseConnectionPool;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.employees.EmployeesTable;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.userstable.UsersTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.AccessRights;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.Users;
import bg.tu_varna.sit.couriermanagementsystem.userauthentication.UserAuthentication;
import java.sql.Connection;

/*Бизнес клас за служители*/
public class EmployeesData
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public EmployeesData()
    {
    }

    //-------------------------
    //Methods:
    //-------------------------
    public boolean insertEmployee(EmployeeDetails employeeDetails)
    {
        DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        Connection connection = databaseConnectionPool.getConnection();

        //Хеширане на паролата на новия потребител;
        UserAuthentication userAuthentication = UserAuthentication.getInstance();
        final String newEmployeeHashedPassword =  userAuthentication.hashPassword(employeeDetails.getEmployeeAccount().getPassword());

        Users newUserRecord = new Users();
        newUserRecord.setUsername(employeeDetails.getEmployeeAccount().getUsername());
        newUserRecord.setPassword(newEmployeeHashedPassword);
        newUserRecord.setAccess(AccessRights.EMPLOYEE);

        UsersTable usersTable = new UsersTable(connection);
        if(!usersTable.insertRecord(newUserRecord))
            return false;

        employeeDetails.getEmployeeRecord().setUserID(newUserRecord.getID());

        EmployeesTable employeesTable = new  EmployeesTable(connection);
        if(!employeesTable.insertRecord(employeeDetails.getEmployeeRecord()))
            return false;

        if(!employeesTable.CommitTransaction())
            return false;

        if(!databaseConnectionPool.releaseConnection(connection))
            return false;

        return true;
    }

    public boolean updateEmployee(EmployeeDetails employeeDetails)
    {
        DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        Connection connection = databaseConnectionPool.getConnection();

        //Хеширане на паролата
        UserAuthentication userAuthentication = UserAuthentication.getInstance();
        final String newEmployeeHashedPassword =  userAuthentication.hashPassword(employeeDetails.getEmployeeAccount().getPassword());

        Users userRecord = new Users();
       UsersTable usersTable = new UsersTable(connection);
        if(!usersTable.selectRecordByID(userRecord, employeeDetails.getEmployeeRecord().getUserID()))
            return false;

        userRecord.setPassword(newEmployeeHashedPassword);
        userRecord.setUsername(employeeDetails.getEmployeeAccount().getUsername());

        if(!usersTable.updateRecord(userRecord))
            return false;

        EmployeesTable employeesTable = new  EmployeesTable(connection);
        if(!employeesTable.updateRecord(employeeDetails.getEmployeeRecord()))
            return false;

        if(!employeesTable.CommitTransaction())
            return false;

        if(!databaseConnectionPool.releaseConnection(connection))
            return false;

        return true;
    }

    public boolean deleteEmployee(EmployeeDetails employeeDetails)
    {
        DatabaseConnectionPool databaseConnectionPool = DatabaseConnectionPool.getInstance();
        Connection connection = databaseConnectionPool.getConnection();

        EmployeesTable employeesTable = new EmployeesTable(connection);
        if(!employeesTable.deleteRecord(employeeDetails.getEmployeeRecord()))
            return false;

        UsersTable usersTable = new UsersTable(connection);
        if(!usersTable.deleteRecord(employeeDetails.getEmployeeAccount()))
            return false;

        if(!usersTable.CommitTransaction())
            return false;

        if(!databaseConnectionPool.releaseConnection(connection))
            return false;

        return true;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
