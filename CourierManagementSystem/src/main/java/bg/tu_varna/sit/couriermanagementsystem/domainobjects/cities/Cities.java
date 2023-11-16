package bg.tu_varna.sit.couriermanagementsystem.domainobjects.cities;

import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.DomainObject;

/**/
public class Cities implements DomainObject
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private int _ID;

    private String _name;

    private String _state;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public Cities()
    {

    }

    //-------------------------
    //Methods:
    //-------------------------
    public int getID()
    {
        return _ID;
    }

    public String getName()
    {
        return _name;
    }

    public String getState()
    {
        return _state;
    }

    //-------------------------
    //Overrides:
    //-------------------------
    @Override
    public String toString()
    {
        return _ID + " - " + _name;
    }

}
