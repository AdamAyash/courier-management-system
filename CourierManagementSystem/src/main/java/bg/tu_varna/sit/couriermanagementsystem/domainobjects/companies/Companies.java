package bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies;

import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.DomainObject;

import java.util.PrimitiveIterator;

/*Бизнес клас за фирма*/
public class Companies implements DomainObject
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private int _ID;
    private int _updateCounter;
    private String _name;
    private String _EGFN;
    private int _cityID;
    private String _phoneNumber;


    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public Companies()
    {
    }

    //-------------------------
    //Methods:
    //-------------------------

    //-------------------------
    //Overrides:
    //-------------------------
}
