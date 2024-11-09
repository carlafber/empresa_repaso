package com.example.empresa_repaso.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.example.empresa_repaso.classes.Cliente;
import com.example.empresa_repaso.classes.Transacciones;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Conectar {
    private static Connection connection;

    public static Connection conectarMySQL() throws ClassNotFoundException, SQLException, IOException {
        if (connection == null || connection.isClosed()) {
            Properties configuration = new Properties();
            configuration.load(new FileInputStream(new File("src/main/resources/configuration/databasesql.properties")));

            String host = configuration.getProperty("host");
            String port = configuration.getProperty("port");
            String name = configuration.getProperty("name");
            String username = configuration.getProperty("username");
            String password = configuration.getProperty("password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC", username, password);
        }
        return connection;
    }

    public static MongoClient conectarMongo(){
        try{
            Properties configuration = new Properties();
            configuration.load(new FileInputStream(new File("src/main/resources/configuration/databasemongo.properties")));

            String username = configuration.getProperty("username");
            String password = configuration.getProperty("password");
            String host = configuration.getProperty("host");
            String port = configuration.getProperty("port");
            String author = configuration.getProperty("author");

            final MongoClient conexion = new MongoClient(new MongoClientURI("mongodb://" + username + ":" + password + "@" + host + ":" + port + "/?authSource=" + author));
            return conexion;
        } catch (Exception e) {
            Alerta.mensajeError("Conexión Fallida\n" + e.getMessage());
            return null;
        }
    }
}
