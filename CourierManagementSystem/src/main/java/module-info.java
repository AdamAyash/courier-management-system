module bg.tu_varna.sit.couriermanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens bg.tu_varna.sit.couriermanagementsystem to javafx.fxml;
    exports bg.tu_varna.sit.couriermanagementsystem;
}