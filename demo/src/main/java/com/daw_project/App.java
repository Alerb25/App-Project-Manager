package com.daw_project;

import com.daw_project.Model.ProjectDAO;
import com.daw_project.Model.ProjectDO;
import com.daw_project.Panels.FilePanel;
import com.daw_project.Panels.ListPanel;
import com.daw_project.Panels.ProjectPanel;
import com.daw_project.Panels.botPanel;
import com.daw_project.utils.Db;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        Db.conectar();

        ProjectPanel pMain = new ProjectPanel();
        BorderPane pPrincipal = new BorderPane();
        TabPane tPane = new TabPane();
        ListPanel lPanel = new ListPanel();
        FilePanel fPanel = new FilePanel();
        botPanel bPanel = new botPanel();

        Tab tProyecto = new Tab("Crear Proyecto");
        Tab tFicheros = new Tab("Listar Proyectos");
        Tab tFiles = new Tab("Importar archivo");
        Tab tChat = new Tab("ChatBot");

        tProyecto.setClosable(false);
        tFicheros.setClosable(false);
        tChat.setClosable(false);
        tFiles.setClosable(false);
        tPane.getTabs().addAll(tProyecto, tFicheros, tFiles, tChat);

        // Metemos el ProjectPanel en la primera pestaña
        tProyecto.setContent(pMain);

        // añadimos el segundo panel de de Listar Proyectos
        tFicheros.setContent(lPanel);

        //añadimos el panel de importacion
        tFiles.setContent(fPanel);

        // añadimos el tercer panel con el chatbot
        tChat.setContent(bPanel);

        // para refrescar automáticamente el listado
        tPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab == tFicheros) {
                lPanel.cargarLista();
            }
        });

        // MENUS
        MenuBar mbPrincipal = new MenuBar();
        // Menus
        Menu mArchivo = new Menu("Archivo");
        Menu mBD = new Menu("Ver");
        Menu mOpciones = new Menu("Opciones");
        Menu mAyuda = new Menu("Ayuda");
        Menu mOperaciones = new Menu("Operaciones");
        // MenuItems
        MenuItem miAbrir = new MenuItem("Abrir..");
        MenuItem miGuardar = new MenuItem("Guardar como..");
        MenuItem miSalir = new MenuItem("Cerrar..");
        SeparatorMenuItem separador = new SeparatorMenuItem();

        mArchivo.getItems().addAll(miAbrir, miGuardar, separador, miSalir);
        MenuItem miCrearProyecto = new MenuItem("Crear Proyecto");
        MenuItem miBorrarProyecto = new MenuItem("Borrar Proyecto");
        mBD.getItems().add(mOperaciones);
        mOperaciones.getItems().addAll(miCrearProyecto, miBorrarProyecto);

        // EVENTOS

        // IMPORTAR Y EXPORTAR
        miAbrir.setOnAction(e -> {
            // este será para importar los proyectos
            //llama a FilePanel
            tPane.getSelectionModel().select(tFiles);

        });

        miGuardar.setOnAction(e -> {
            // este es para exportar a texto plano .txt
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar como");
            fileChooser.setInitialFileName("documento.txt");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Texto", "*.txt"));

            File fichero = fileChooser.showSaveDialog(stage.getScene().getWindow());

            if (fichero != null) {
                try {
                    ProjectDAO dao = new ProjectDAO();
                    List<ProjectDO> lista = dao.getAll(); 
                    StringBuilder sb = new StringBuilder();

                    for (ProjectDO p : lista) {
                        sb.append(p.getTitle()).append(";")
                                .append(p.getDescr()).append(";")
                                .append(p.getUrl()).append(";")
                                .append(p.getDif()).append(";")
                                .append(p.getTheme()).append(";")
                                .append(p.getUpdated())
                                .append("\n");
                    }

                    Files.writeString(fichero.toPath(), sb.toString());

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Exportación completada");
                    alert.setContentText("Proyectos exportados correctamente.");
                    alert.showAndWait();

                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error al guardar");
                    alert.setContentText("Error: " + ex.getMessage());
                    alert.showAndWait();
                }
            }
        });

        // Cargamos en la barra de menus lso menus
        mbPrincipal.getMenus().addAll(mArchivo, mBD, mOpciones, mAyuda);

        miSalir.setOnAction(e -> {
            stage.close();
        });

        miCrearProyecto.setOnAction(e -> {
            // Seleccionamos la pestaña primer del panel
            // Que es la de insertar proyectos
            tPane.getSelectionModel().select(tProyecto);
        });

        // Ponemos en la posicion central del borderpane
        // Nuestro Tabpane
        pPrincipal.setCenter(tPane);
        // Ponemos en la parte superior del borderpane el menu
        pPrincipal.setTop(mbPrincipal);

        var scene = new Scene(pPrincipal, 420, 380);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}