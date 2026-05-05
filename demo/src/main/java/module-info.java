module com.daw_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;
    

    opens com.daw_project to javafx.fxml;
    exports com.daw_project;   
}