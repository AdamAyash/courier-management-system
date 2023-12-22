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
    exports bg.tu_varna.sit.couriermanagementsystem;
    opens bg.tu_varna.sit.couriermanagementsystem.domainobjects.companies to javafx.base;
    opens bg.tu_varna.sit.couriermanagementsystem.domainobjects.users to javafx.base;
    opens bg.tu_varna.sit.couriermanagementsystem.controllers.base to javafx.fxml;
    exports bg.tu_varna.sit.couriermanagementsystem.controllers.companies;
    exports bg.tu_varna.sit.couriermanagementsystem.controllers.employees;
    opens bg.tu_varna.sit.couriermanagementsystem.controllers.companies to javafx.fxml;
    exports bg.tu_varna.sit.couriermanagementsystem.controllers.loginform;
    opens bg.tu_varna.sit.couriermanagementsystem.controllers.loginform to javafx.fxml;
    opens bg.tu_varna.sit.couriermanagementsystem.domainobjects.offices to javafx.base;
    opens bg.tu_varna.sit.couriermanagementsystem.controllers.companies.offices to javafx.fxml;
    opens bg.tu_varna.sit.couriermanagementsystem.controllers.employees to  javafx.fxml;
    opens bg.tu_varna.sit.couriermanagementsystem.domainobjects.employees to javafx.base;
    exports bg.tu_varna.sit.couriermanagementsystem.controllers.admin;
    opens bg.tu_varna.sit.couriermanagementsystem.controllers.admin to javafx.fxml;
    opens bg.tu_varna.sit.couriermanagementsystem.domainobjects.clients to javafx.base;
    opens bg.tu_varna.sit.couriermanagementsystem.controllers.clients to  javafx.fxml;
    opens bg.tu_varna.sit.couriermanagementsystem.controllers.orders to  javafx.fxml;
    opens bg.tu_varna.sit.couriermanagementsystem.domainobjects.orders to javafx.base;


}