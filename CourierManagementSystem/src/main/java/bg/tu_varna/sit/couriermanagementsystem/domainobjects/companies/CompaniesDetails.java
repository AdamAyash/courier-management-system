package bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies;


import bg.tu_varna.sit.couriermanagementsystem.domainobjects.cities.Cities;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.offices.Offices;

import java.util.ArrayList;
import java.util.List;

/*Транспортен клас за компания и нейните офиси*/
public class CompaniesDetails
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private Companies _companiesRecord;
    private List<Offices> _officesList;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public CompaniesDetails(Companies companiesRecord, List<Offices> officesList)
    {
        _companiesRecord = companiesRecord;
        _officesList = officesList;
    }

    public CompaniesDetails()
    {
        _officesList = new ArrayList<>();
        _companiesRecord = new Companies();
    }

    //-------------------------
    //Methods:
    //-------------------------
    public Companies getCompaniesRecord()
    {
        return _companiesRecord;
    }

    public List<Offices> getOfficesList()
    {
        return _officesList;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
