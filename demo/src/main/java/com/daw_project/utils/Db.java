package com.daw_project.utils;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    public static Connection connection;

    public static void conectar() {
        System.out.println("Directorio de trabajo: " + System.getProperty("user.dir"));

        try {
           Dotenv dotenv = Dotenv.configure()
    .directory(System.getProperty("user.dir"))
    .filename(".env")
    .load();
            System.out.println("YES .env cargado");

            String host = dotenv.get("DB_HOST");
            String port = dotenv.get("DB_PORT");
            String dbName = dotenv.get("DB_NAME");
            String user = dotenv.get("DB_USER");
            String password = dotenv.get("DB_PASSWORD");

            System.out.println("URL: jdbc:mysql://" + host + ":" + port + "/" + dbName);
            System.out.println("User: " + user);

            String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("YES Conexión establecida");

        } catch (SQLException e) {
            System.err.println("NO Error SQL: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("NO Error general: " + e.getMessage());
            e.printStackTrace();
        }
    }
}