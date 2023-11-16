package bg.tu_varna.sit.couriermanagementsystem.domainobjects.base;

/**/
public class UpdatableDomainObject implements DomainObject
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private int _updateCounter;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------

    //-------------------------
    //Methods:
    //-------------------------
    public int getUpdateCounter()
    {
       return _updateCounter;
    }

    public boolean CompareUpdateCounter(UpdatableDomainObject otherUpdatableDomainObject)
    {
        return _updateCounter == otherUpdatableDomainObject.getUpdateCounter();
    }

    public void  incrementCounter()
    {
        _updateCounter++;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
