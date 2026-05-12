// FilePanel.java
package com.daw_project.Panels;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import java.io.File;
import java.nio.file.Files;

public class FilePanel extends BorderPane {

    private TextArea txtContenido = new TextArea();
    private Label lblRuta = new Label("Ningún fichero seleccionado");
    private Button btnSeleccionar = new Button("📂 Seleccionar fichero...");
    private Button btnLimpiar = new Button("Limpiar");
    private File ficheroActual = null;

    public FilePanel() {
        this.setPadding(new Insets(15));

        // --- TextArea (zona central, solo lectura) ---
        txtContenido.setEditable(false);
        txtContenido.setWrapText(true);
        txtContenido.setPromptText("El contenido del fichero aparecerá aquí...");
        txtContenido.setStyle("-fx-font-family: monospace; -fx-font-size: 12;");

        // --- Parte superior: ruta + botones ---
        HBox topBar = new HBox(10);
        topBar.setPadding(new Insets(0, 0, 10, 0));
        topBar.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        lblRuta.setStyle("-fx-text-fill: gray; -fx-font-style: italic;");
        HBox.setHgrow(lblRuta, Priority.ALWAYS);
        lblRuta.setMaxWidth(Double.MAX_VALUE);

        topBar.getChildren().addAll(btnSeleccionar, btnLimpiar, lblRuta);

        this.setTop(topBar);
        this.setCenter(txtContenido);

        // --- Eventos ---
        btnSeleccionar.setOnAction(e -> seleccionarFichero());
        btnLimpiar.setOnAction(e -> limpiar());
    }

    private void seleccionarFichero() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar fichero a importar");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Ficheros de texto", "*.txt"),
            new FileChooser.ExtensionFilter("CSV", "*.csv"),
            new FileChooser.ExtensionFilter("Todos los ficheros", "*.*")
        );

        File fichero = fileChooser.showOpenDialog(this.getScene().getWindow());

        if (fichero != null) {
            try {
                String contenido = Files.readString(fichero.toPath());
                txtContenido.setText(contenido);
                lblRuta.setText(fichero.getAbsolutePath());
                ficheroActual = fichero;
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error al leer fichero");
                alert.setContentText("No se pudo leer el fichero: " + ex.getMessage());
                alert.showAndWait();
            }
        }
    }

    private void limpiar() {
        txtContenido.clear();
        lblRuta.setText("Ningún fichero seleccionado");
        ficheroActual = null;
    }

    // Devuelve el File actualmente cargado, o null si no hay ninguno 
    public File getFicheroActual() {
        return ficheroActual;
    }

    // Devuelve el contenido en bruto del TextArea 
    public String getContenido() {
        return txtContenido.getText();
    }
}