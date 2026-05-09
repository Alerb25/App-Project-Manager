package com.daw_project;

import com.daw_project.Panels.ListPanel;
import com.daw_project.Panels.ProjectPanel;
import com.daw_project.utils.Db;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;


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

        Tab tProyecto = new Tab("Crear Proyecto");
        Tab tFicheros = new Tab("Listar Proyectos");

        tProyecto.setClosable(false);
        tFicheros.setClosable(false);
        tPane.getTabs().addAll(tProyecto, tFicheros);

        // Metemos el ProjectPanel en la primera pestaña
        tProyecto.setContent(pMain);

        // añadimos el segundo panel de de Listar Proyectos
        tFicheros.setContent(lPanel);

        //para refrescar automáticamente el listado
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

        //añadir dos eventos a los menu items para exportar e importar archivos
        miAbrir.setOnAction( e -> {
            //este será para importar los proyectos
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar fichero de texto");


            // Filtros de extension
            fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Ficheros de texto", "*.txt"),
            new FileChooser.ExtensionFilter("CSV", "*.csv"),
            new FileChooser.ExtensionFilter("Todos los ficheros", "*.*")
            );
        
            File fichero = fileChooser.showOpenDialog(primaryStage);

            if (fichero != null) {  // null si el usuario cancelo
                try {
                    String contenido = Files.readString(fichero.toPath());
                    textArea.setText(contenido);
                } catch (IOException ex) {
                textArea.setText("Error al leer: " + ex.getMessage());
                }
                }

        } );    


        miGuardar.setOnAction( e -> {
            //este es para exportar a texto plano .txt
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar como");
            fileChooser.setInitialFileName("documento.txt");
            fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Texto", "*.txt")
            );

            File fichero = fileChooser.showSaveDialog(primaryStage);

            if (fichero != null) {
                try {
                    Files.writeString(fichero.toPath(), textArea.getText());
                } catch (IOException ex) {
                    System.err.println("Error al guardar: " + ex.getMessage());
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