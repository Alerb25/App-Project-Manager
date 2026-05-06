package com.daw_project.Panels;

import com.daw_project.Model.ProjectDO;
import com.daw_project.Model.ProjectDAO;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListPanel extends VBox {

    private ListView<String> listView = new ListView<>();
    private Button btnRefresh = new Button("Refrescar");
    private Label lblInfo = new Label("Total: 0 proyectos");

    private Label lblTitleVal = new Label("-");
    private Label lblDescVal = new Label("-");
    private Label lblUrlVal = new Label("-");
    private Label lblDifVal = new Label("-");
    private Label lblThemeVal = new Label("-");
    private Label lblUpdatedVal = new Label("-");

    private List<ProjectDO> proyectos = new ArrayList<>(); 
    private ProjectDAO projectDAO = new ProjectDAO();      

    public ListPanel() {
        this.setPadding(new Insets(15));
        this.setSpacing(10);

        // Panel de detalle
        GridPane detalle = new GridPane();
        detalle.setHgap(10);
        detalle.setVgap(6);
        detalle.setPadding(new Insets(10));
        detalle.add(new Label("Título:"), 0, 0);      detalle.add(lblTitleVal, 1, 0);
        detalle.add(new Label("Descripción:"), 0, 1); detalle.add(lblDescVal, 1, 1);
        detalle.add(new Label("URL:"), 0, 2);         detalle.add(lblUrlVal, 1, 2);
        detalle.add(new Label("Dificultad:"), 0, 3);  detalle.add(lblDifVal, 1, 3);
        detalle.add(new Label("Tema:"), 0, 4);        detalle.add(lblThemeVal, 1, 4);
        detalle.add(new Label("Actualizado:"), 0, 5); detalle.add(lblUpdatedVal, 1, 5);

        // Al seleccionar un item muestra su detalle
        listView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            int idx = newVal.intValue();
            if (idx >= 0 && idx < this.proyectos.size()) {
                ProjectDO p = this.proyectos.get(idx);
                lblTitleVal.setText(p.getTitle());
                lblDescVal.setText(p.getDescr());
                lblUrlVal.setText(p.getUrl());
                lblDifVal.setText(String.valueOf(p.getDif()));
                lblThemeVal.setText(p.getTheme());
                lblUpdatedVal.setText(p.getUpdated() ? "Sí" : "No");
            }
        });

        btnRefresh.setOnAction(e -> cargarLista());

        this.getChildren().addAll(lblInfo, listView, btnRefresh, new Separator(), detalle);
        cargarLista();
    }

    public void cargarLista() {
        try {
            proyectos = projectDAO.listar(); // SELECT * FROM Project

            listView.getItems().clear();
            for (ProjectDO p : proyectos) {
                listView.getItems().add(p.getTitle());
            }

            lblInfo.setText("Total: " + proyectos.size() + " proyectos");

        } catch (SQLException e) {
            lblInfo.setText("Error al cargar proyectos");
            e.printStackTrace();
        }
    }
}