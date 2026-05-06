package com.daw_project.Panels;


import java.util.ArrayList;
import com.daw_project.Model.ProjectDO;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class ProjectPanel extends GridPane {
    // Inicializamos los labels para que no sean null
    private Label lblTitle = new Label("Título:");
    private Label lblTheme = new Label("Tema:");
    private Label lblDif = new Label("Dificultad:");
    private Label lblDesc = new Label("Descripción:");
    private Label lblUrl = new Label("URL:");
    private Label lblUpdated = new Label("Actualizado:");

    public TextField txtTitle = new TextField();
    public ComboBox<String> cmbTheme = new ComboBox<>();
    public Slider sldDif = new Slider(1, 100, 50);
    public TextArea txtDesc = new TextArea();
    public TextField txtUrl = new TextField();
    public CheckBox cb = new CheckBox("¿Está actualizado?"); 

    public ArrayList<ProjectDO> proyectos = new ArrayList();
    public Button btnReset = new Button("Reset");
    public Button btnGuardar = new Button("Guardar");

    public ProjectPanel() {
        // Configuración del GridPane
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        // Configurar ComboBox
        cmbTheme.getItems().addAll("Organización", "Juegos", "Lectura", "Escritura", "Otros");

        // para que se vea la dificultad
        sldDif.setOnMouseDragged(e -> {
            lblDif.setText("Dificultad: " + (int) sldDif.getValue());
        });

        // Configurar TextArea
        txtDesc.setPrefWidth(200);
        txtDesc.setPrefHeight(100);

        // Añadir los elementos
        this.add(lblTitle, 0, 0);
        this.add(txtTitle, 1, 0);
        this.add(lblDesc, 0, 1);
        this.add(txtDesc, 1, 1);
        this.add(lblDif, 0, 2);
        this.add(sldDif, 1, 2);
        this.add(lblTheme, 0, 3);
        this.add(cmbTheme, 1, 3);
        this.add(lblUrl, 0, 4);
        this.add(txtUrl, 1, 4);
        this.add(lblUpdated, 0, 5);
        this.add(cb, 1, 5);

        this.add(btnGuardar, 0, 6);
        this.add(btnReset, 1, 6);

        // Eventos
        btnReset.setOnAction(e -> reset());
        btnGuardar.setOnAction(e -> guardar());
    }

    private void reset() {
        this.txtTitle.clear();
        this.txtDesc.clear();
        this.cmbTheme.getSelectionModel().select(0);
        this.sldDif.setValue(50);
        this.txtUrl.clear();
        this.cb.setSelected(false);

    }

    private void guardar() {

        ProjectDO p = new ProjectDO(
                0,
                txtTitle.getText(),
                txtDesc.getText(),
                txtUrl.getText(),
                (int) sldDif.getValue(),
                cmbTheme.getValue(),
                cb.isSelected());
        proyectos.add(p);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Guardado!");
        alert.setContentText("El proyecto \"" + p.getTitle() + "\" se ha guardado correctamente.");
        alert.showAndWait();

        reset();

    }
}