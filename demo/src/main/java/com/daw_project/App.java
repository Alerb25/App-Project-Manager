package com.daw_project;

import Panels.ProjectPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.com.daw_project.Panels.ListPanel;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
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

        //añadimos el segundo panel de de Listar Proyectos
        tFicheros.setContent(lPanel);

        //MENUS
        MenuBar mbPrincipal = new MenuBar();
        // Menus
        Menu mArchivo = new Menu("Archivo");
        Menu mBD = new Menu("Ver");
        Menu mOpciones = new Menu("Opciones");
        Menu mAyuda = new Menu("Ayuda");
        Menu mOperaciones = new Menu("Operaciones");
        // MenuItems
        MenuItem miAbrir = new MenuItem("Abrir..");
        MenuItem miGuardar = new MenuItem("Guardar..");
        MenuItem miSalir = new MenuItem("Cerrar..");
        SeparatorMenuItem separador = new SeparatorMenuItem();

        mArchivo.getItems().addAll(miAbrir, miGuardar, separador, miSalir);
        MenuItem miCrearProyecto = new MenuItem("Crear Proyecto");
        MenuItem miBorrarProyecto = new MenuItem("Borrar Proyecto");
        mBD.getItems().add(mOperaciones);
        mOperaciones.getItems().addAll(miCrearProyecto, miBorrarProyecto);

        // Cargamos en la barra de menus lso menus
        mbPrincipal.getMenus().addAll(mArchivo, mBD, mOpciones, mAyuda);

        //EVENTOS
                miSalir.setOnAction(e -> {
            stage.close();
        });

        miCrearProyecto.setOnAction(e -> {
            // Seleccionamos la pestaña primer del panel
            // Que es la de insertar pelicula
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