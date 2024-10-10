module es.aritzherrero.ejerciciob {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.aritzherrero.ejerciciob to javafx.fxml;
    exports es.aritzherrero.ejerciciob;
    exports es.aritzherrero.ejerciciob.Controladores;
    opens es.aritzherrero.ejerciciob.Controladores to javafx.fxml;
    opens es.aritzherrero.ejerciciob.Model to javafx.fxml, javafx.base;
}