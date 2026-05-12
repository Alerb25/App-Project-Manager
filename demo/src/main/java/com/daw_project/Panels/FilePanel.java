// FilePanel.java
package com.daw_project.Panels;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import java.io.File;
import java.lang.classfile.Label;
import java.nio.file.Files;

import com.daw_project.Model.ProjectDAO;
import com.daw_project.Model.ProjectDO;

public class FilePanel extends BorderPane {

    private TextArea txtContenido = new TextArea();
    private Label lblRuta = new Label("Ningún fichero seleccionado");
    private Button btnSeleccionar = new Button("📂 Seleccionar fichero...");
    private Button btnLimpiar = new Button("Limpiar");
    private File ficheroActual = null;

    public FilePanel() {
        this.setPadding(new Insets(15));

        // TextArea (zona central, solo lectura)
        txtContenido.setEditable(false);
        txtContenido.setWrapText(true);
        txtContenido.setPromptText("El contenido del fichero aparecerá aquí...");
        txtContenido.setStyle("-fx-font-family: monospace; -fx-font-size: 12;");

        // Parte superior: ruta + botones
        HBox topBar = new HBox(10);
        topBar.setPadding(new Insets(0, 0, 10, 0));
        topBar.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        lblRuta.setStyle("-fx-text-fill: gray; -fx-font-style: italic;");
        HBox.setHgrow(lblRuta, Priority.ALWAYS);
        lblRuta.setMaxWidth(Double.MAX_VALUE);

        topBar.getChildren().addAll(btnSeleccionar, btnLimpiar, lblRuta);
        Button btnImportar = new Button("⬇ Importar proyectos");
        btnImportar.setDisable(true); // deshabilitado hasta que haya fichero cargado

        // Habilitarlo cuando haya contenido
        btnSeleccionar.setOnAction(e -> {
            seleccionarFichero();
            btnImportar.setDisable(ficheroActual == null);
        });

        btnImportar.setOnAction(e -> importar());

        HBox bottomBar = new HBox(btnImportar);
        bottomBar.setPadding(new Insets(10, 0, 0, 0));
        this.setBottom(bottomBar);

        this.setTop(topBar);
        this.setCenter(txtContenido);

        // Eventos
        btnSeleccionar.setOnAction(e -> seleccionarFichero());
        btnLimpiar.setOnAction(e -> limpiar());
    }

    private void importar() {
        String contenido = txtContenido.getText();
        String[] lineas = contenido.split("\n");
        ProjectDAO dao = new ProjectDAO();
        int importados = 0;

        for (String linea : lineas) {
            linea = linea.trim();
            if (linea.isEmpty())
                continue;

            String[] partes = linea.split(";");
            if (partes.length < 6)
                continue;

            try {
                ProjectDO p = new ProjectDO(
                        0,
                        partes[0].trim(),
                        partes[1].trim(),
                        partes[2].trim(),
                        Integer.parseInt(partes[3].trim()),
                        partes[4].trim(),
                        Boolean.parseBoolean(partes[5].trim()));
                if (dao.insert(p))
                    importados++;
            } catch (Exception ex) {
                System.err.println("Línea inválida: " + linea);
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Importación completada");
        alert.setContentText("Se importaron " + importados + " proyecto(s).");
        alert.showAndWait();
    }

    private void seleccionarFichero() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar fichero a importar");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Ficheros de texto", "*.txt"),
                new FileChooser.ExtensionFilter("CSV", "*.csv"),
                new FileChooser.ExtensionFilter("Todos los ficheros", "*.*"));

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