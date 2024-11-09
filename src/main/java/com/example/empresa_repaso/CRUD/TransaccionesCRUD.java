package com.example.empresa_repaso.CRUD;


import com.example.empresa_repaso.classes.Cliente;
import com.example.empresa_repaso.classes.Transacciones;
import com.example.empresa_repaso.util.Alerta;
import com.example.empresa_repaso.util.Conectar;
import com.example.empresa_repaso.util.HibernateUtil;
import com.example.empresa_repaso.util.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class TransaccionesCRUD implements CRUDTransacciones {
    SessionFactory factory = HibernateUtil.getSessionFactory();
    private MongoClient con;
    private MongoCollection<Document> collection = null;
    private String json;
    private Document doc;

    /*public TransaccionesCRUD() {
        try {
            con = Conectar.conectarMongo();

            MongoDatabase database = con.getDatabase("Empresa");

            database.createCollection("transacciones");

            collection = database.getCollection("transacciones");


            /*if (collection.countDocuments() == 0) {
                for (Cliente cliente : obtenerTransacciones()) {
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
    }*/

    @Override
    public List<Transacciones> obtenerTransaccionesCliente(Cliente cliente) {
        Transaction transaction = null;
        List<Transacciones> transacciones = null;

        try(Session session = factory.openSession()) {
            System.out.println("jdvejsahbknlmñKSDNF");
            transaction = session.beginTransaction();
            System.out.println("begin");
            transacciones = session.createQuery("from Transacciones WHERE cliente.id = :id", Transacciones.class)
                    .setParameter("id", cliente.getId())
                    .list();
            transaction.commit();
            System.out.println(transacciones);

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Si ocurre un error, hacemos rollback.
            }
        }

        return transacciones;

    }
}
