module bg.tu_varma.sit.couriermanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens bg.tu_varma.sit.couriermanagementsystem to javafx.fxml;
    exports bg.tu_varma.sit.couriermanagementsystem;
}