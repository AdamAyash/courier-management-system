module bg.tu_varna.sit.couriermanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires java.naming;
    requires org.apache.logging.log4j;
    requires password4j;

    opens bg.tu_varna.sit.couriermanagementsystem to javafx.fxml;
    opens bg.tu_varna.sit.couriermanagementsystem.controllers to javafx.fxml;
    exports bg.tu_varna.sit.couriermanagementsystem;
    exports bg.tu_varna.sit.couriermanagementsystem.controllers;
    opens bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies to javafx.base;
    opens bg.tu_varna.sit.couriermanagementsystem.domainobjects.users to javafx.base;
    opens bg.tu_varna.sit.couriermanagementsystem.controllers.base to javafx.fxml;


}