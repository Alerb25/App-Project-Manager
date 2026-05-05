package main.java.com.daw_project.Panels;


import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ListPanel extends VBox {

    private ListView<String> listView = new ListView<>();
    private Button btnRefresh = new Button("🔄 Refrescar");
    private Label lblInfo = new Label("Selecciona un proyecto para ver detalles");

    // Área de detalle
    private Label lblTitleVal = new Label("-");
    private Label lblDescVal = new Label("-");
    private Label lblUrlVal = new Label("-");

    private ArrayList<Proyecto> proyectos;

    public ListPanel(ArrayList<Proyecto> proyectos) {
        this.proyectos = proyectos;

        this.setPadding(new Insets(15));
        this.setSpacing(10);

        // Panel de detalle
        GridPane detalle = new GridPane();
        detalle.setHgap(10);
        detalle.setVgap(6);
        detalle.setPadding(new Insets(10));
        detalle.add(new Label("Título:"), 0, 0);
        detalle.add(lblTitleVal, 1, 0);
        detalle.add(new Label("Descripción:"), 0, 1);
        detalle.add(lblDescVal, 1, 1);
        detalle.add(new Label("URL:"), 0, 2);
        detalle.add(lblUrlVal, 1, 2);

        // Evento: al seleccionar un proyecto de la lista, muestra el detalle
        listView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            int idx = newVal.intValue();
            if (idx >= 0 && idx < this.proyectos.size()) {
                Proyecto p = this.proyectos.get(idx);
                lblTitleVal.setText(p.getTitulo());
                lblDescVal.setText(p.getDescripcion());
                lblUrlVal.setText(p.getUrl());
            }
        });

        // Evento: refrescar la lista
        btnRefresh.setOnAction(e -> cargarLista());

        this.getChildren().addAll(lblInfo, listView, btnRefresh, new Separator(), detalle);

        cargarLista();
    }

    public void cargarLista() {
        listView.getItems().clear();
        for (Proyecto p : proyectos) {
            listView.getItems().add(p.getTitulo());
        }
        lblInfo.setText("Total: " + proyectos.size() + " proyectos");
    }
}
