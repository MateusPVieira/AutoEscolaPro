module br.edu.ifsp {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.edu.ifsp.application.view to javafx.fxml;
    opens br.edu.ifsp.application.controller to javafx.fxml;
    exports br.edu.ifsp.application.view;
    exports br.edu.ifsp.application.controller;
}
