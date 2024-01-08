package bg.tu_varna.sit.couriermanagementsystem.Tasks;

import bg.tu_varna.sit.couriermanagementsystem.database.storedprocedures.StoredProcedure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**/
public class AutoUpdateOrdersTask extends Thread
{
    //-------------------------
    //Constants:
    //-------------------------
    private final String SP_UPDATE_ORDER_STATUSES = "SP_UPDATE_ORDER_STATUSES";
    private final  long THREAD_TIMEOUT = 60000;  //Една минута
    private static final Logger _logger = LogManager.getLogger();

    //-------------------------
    //Members:
    //-------------------------

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------

    //-------------------------
    //Methods:
    //-------------------------
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                StoredProcedure storedProcedure = new StoredProcedure(SP_UPDATE_ORDER_STATUSES);
                storedProcedure.executeStoredProcedure();
                Thread.sleep(THREAD_TIMEOUT);
            }
            catch (InterruptedException exception)
            {
                _logger.error(exception.getMessage());
            }
        }
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
