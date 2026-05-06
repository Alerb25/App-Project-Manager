package com.daw_project.utils;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    
    public static Connection connection;

    // Usamos un bloque static para que la conexión se cree en cuanto se use la clase

    public static void conectar() {
        try {
            Dotenv dotenv = Dotenv.load();

            String host = dotenv.get("DB_HOST");
            String port = dotenv.get("DB_PORT");
            String dbName = dotenv.get("DB_NAME");
            String user = dotenv.get("DB_USER");
            String password = dotenv.get("DB_PASSWORD");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

            // Intentamos establecer la conexión
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida con éxito.");


        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error cargando el fichero .env: " + e.getMessage());
        }
        
    }
}
