module bg.tu_varna.sit.couriermanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires java.naming;

    opens bg.tu_varna.sit.couriermanagementsystem to javafx.fxml;
    opens bg.tu_varna.sit.couriermanagementsystem.controllers to javafx.fxml;
    exports bg.tu_varna.sit.couriermanagementsystem;
}