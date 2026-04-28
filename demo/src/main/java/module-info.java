module com.daw_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java; // Nombre del módulo de Dotenv

    opens com.daw_project to javafx.fxml;
    opens com.daw_project.Model to javafx.base;
    
    exports com.daw_project;
}