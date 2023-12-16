package bg.tu_varna.sit.couriermanagementsystem.domainobjects.orders.ordertypes;

import bg.tu_varna.sit.couriermanagementsystem.domainobjects.base.DomainObject;

/**/
public class OrderTypes implements DomainObject
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private int _ID;
    private String _name;
    private float _price;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public OrderTypes()
    {

    }

    //-------------------------
    //Methods:
    //-------------------------

    public int getID()
    {
        return _ID;
    }

    public void setID(int ID)
    {
        this._ID = ID;
    }

    public String getName()
    {
        return _name;
    }

    public void setName(String name)
    {
        this._name = name;
    }

    public float getPrice()
    {
        return _price;
    }

    public void setPrice(float price)
    {
        this._price = price;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
