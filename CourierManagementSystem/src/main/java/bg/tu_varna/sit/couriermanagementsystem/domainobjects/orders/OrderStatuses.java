package bg.tu_varna.sit.couriermanagementsystem.domainobjects.orders;

public enum OrderStatuses
{

    DELIVERED(0, "Delivered"),
    NOT_DELIVERED(1, "Not Delivered"),
    REJECTED(2, "Rejected");

    private OrderStatuses(int statusID, String statusName)
    {
        _statusID = statusID;
        _statusName = statusName;
    }

    private int _statusID;
    private String _statusName;

    public int getID()
    {
        return _statusID;
    }

    public String getStatusName()
    {
        return _statusName;
    }

    @Override
    public String toString() {
        return getID() + " - " + getStatusName();
    }

}
