module com.daw_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;
    requires javafx.graphics;
    requires google.genai;
    

    opens com.daw_project to javafx.fxml;
    exports com.daw_project;   
}