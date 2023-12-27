package bg.tu_varna.sit.couriermanagementsystem.Tasks;

import bg.tu_varna.sit.couriermanagementsystem.database.queries.ComparisonTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.LockTypes;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLCriteria;
import bg.tu_varna.sit.couriermanagementsystem.database.queries.SQLQuery;
import bg.tu_varna.sit.couriermanagementsystem.database.tables.notificationstable.NotificationsTable;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.notifications.Notifications;
import bg.tu_varna.sit.couriermanagementsystem.domainobjects.users.Users;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class ClientNotificationsTask
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private final long THREAD_TIMEOUT = 600000;
    private static final Logger _logger = LogManager.getLogger();
    private Users _currentlyLoggedUser;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public ClientNotificationsTask(Users currentlyLoggedUser)
    {
        _currentlyLoggedUser = currentlyLoggedUser;
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.minutes(1), e -> run())
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    //-------------------------
    //Methods:
    //-------------------------

    //-------------------------
    //Overrides:
    //-------------------------

    public void run()
    {

        final List<Notifications> notificationsList = new ArrayList<>();
        final NotificationsTable notificationsTable = new NotificationsTable();

        SQLQuery sqlQuery = new SQLQuery(notificationsTable.getTableName(), LockTypes.READ_ONLY);
        sqlQuery.addCriteria(new SQLCriteria(NotificationsTable.NotificationsTableColumns.USER_ID.getColumnName(),
                ComparisonTypes.EQUALS, _currentlyLoggedUser.getID()));
        sqlQuery.addCriteria(new SQLCriteria(NotificationsTable.NotificationsTableColumns.SEEN_BY_USER.getColumnName(),
                ComparisonTypes.EQUALS, 0));

        if(!notificationsTable.selectAllRecordsWhere(notificationsList, sqlQuery))
            return;

        for(Notifications notification : notificationsList)
        {
            org.controlsfx.control.Notifications notifications = org.controlsfx.control.Notifications.create()
                    .text(notification.getMessage())
                    .position(Pos.BOTTOM_LEFT);
            notifications.show();

            notification.setSeenByUser(true);
            if(!notificationsTable.updateRecord(notification))
                continue;
        }
    }
}
