package com.example.empresa_repaso.CRUD;

import com.example.empresa_repaso.classes.Cliente;
import com.example.empresa_repaso.util.Alerta;
import com.example.empresa_repaso.util.Conectar;
import com.example.empresa_repaso.util.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteCRUD implements CRUDCliente {
    private MongoClient con;
    private MongoCollection<Document> collection = null;
    private String json;
    private Document doc;

    public ClienteCRUD() {
        try {
            con = Conectar.conectarMongo();

            MongoDatabase database = con.getDatabase("Empresa");

            database.createCollection("cliente");

            collection = database.getCollection("cliente");


            if (collection.countDocuments() == 0) {
                for (Cliente cliente : obtenerClientes()) {
                    Gson gson = new GsonBuilder()
                            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                            .create();
                    json = gson.toJson(cliente);
                    doc = Document.parse(json);
                    collection.insertOne(doc);
                }
            }

        } catch (Exception exception) {
            Alerta.mensajeError(exception.getClass().getName() + ": " + exception.getMessage());
        }
    }

    @Override
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        Cliente cliente;

        try{
            Connection con = Conectar.conectarMySQL();
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                cliente = new Cliente(nombre, apellidos, email, telefono);
                cliente.setId(id);
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            Alerta.mensajeError(e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            Alerta.mensajeError(e.getMessage());
            throw new RuntimeException(e);
        }
        return clientes;
    }

    @Override
    public boolean actualizarCliente(Cliente cliente) {
        int cambios;
        String sql = "UPDATE Cliente SET nombre = ?, apellidos = ?, email = ?, telefono = ? WHERE id = ?";

        try {
            Connection con = Conectar.conectarMySQL();
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellidos());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getTelefono());
            statement.setInt(5, cliente.getId());

            cambios = statement.executeUpdate();

            doc = new Document("nombre", cliente.getNombre())
                    .append("apellidos", cliente.getApellidos())
                    .append("email", cliente.getEmail())
                    .append("telefono", cliente.getTelefono());

            collection.updateOne(Filters.eq("id", cliente.getId()), new Document("$set", doc));

            return cambios == 1;

        } catch (SQLException e) {
            Alerta.mensajeError(e.getMessage());
            return false;
        } catch (IOException | ClassNotFoundException e) {
            Alerta.mensajeError(e.getMessage());
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        int cambios;
        String sql = "DELETE FROM Cliente WHERE id = ?";

        try{
            Connection con = Conectar.conectarMySQL();
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setInt(1, cliente.getId());

            cambios = statement.executeUpdate();

            collection.deleteOne(new Document("id", cliente.getId()));

            return cambios == 1;

        } catch (Exception e) {
            Alerta.mensajeError(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean insertarCliente(Cliente cliente) {
        int cambios;
        String sql = "INSERT INTO Cliente (nombre, apellidos, email, telefono) VALUES (?, ?, ?, ?)";

        try {
            Connection con = Conectar.conectarMySQL();
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellidos());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getTelefono());
            cambios = statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                int id_generado = rs.getInt(1);
                cliente.setId(id_generado);
            }

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();
            json = gson.toJson(cliente);
            doc = Document.parse(json);
            collection.insertOne(doc);

            return cambios == 1;

        } catch (SQLException e) {
            Alerta.mensajeError(e.getMessage());
            return false;
        } catch (IOException | ClassNotFoundException e) {
            Alerta.mensajeError(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
