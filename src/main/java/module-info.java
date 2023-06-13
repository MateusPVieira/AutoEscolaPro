module br.edu.ifsp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.apache.logging.log4j;

    opens br.edu.ifsp.application.view to javafx.fxml;
    opens br.edu.ifsp.application.controller to javafx.fxml;
    exports br.edu.ifsp.application.view;
    exports br.edu.ifsp.application.controller;
    exports br.edu.ifsp.application.controller.qualification;
    opens br.edu.ifsp.application.controller.qualification to javafx.fxml;
}
