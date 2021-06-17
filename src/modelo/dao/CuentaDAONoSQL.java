package modelo.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import modelo.Conexion;
import modelo.dto.Cuenta;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.logging.Filter;

public class CuentaDAONoSQL implements CuentaDAO{
    private MongoClient mongoClient = Conexion.getMongoClient();
    private static MongoDatabase mongoDatabase;
    private MongoCollection mongoCollection = Conexion.getMongoCollection();
    private Conexion conexion;

    static {
        try {
            mongoDatabase = Conexion.getInstance().getMongoDatabase();
            System.out.println(mongoDatabase);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cuenta> obtenerListaCuentas() {
        List<Cuenta> listaCuentas = new ArrayList<>();

        mongoCollection.find().forEach((Consumer<Document>) (Document document) ->
        {
            String id = document.getString("_id");
            String iban = document.getString("iban");
            String creditCard = document.getString("creditCard");
            Double balance = document.getDouble("balance");
            String fullName = document.getString("fullName");
            String date = document.getString("date");

            listaCuentas.add(new Cuenta(id, iban, creditCard, balance, fullName, date));
        });

        return listaCuentas;

    }

    @Override
    public boolean borrarCuenta(String idCuenta) {
        Document document = new Document();
        mongoCollection.deleteOne(document.append("_id", new ObjectId(idCuenta)));
        return mongoCollection.deleteOne(document).getDeletedCount() != 0;
    }

    @Override
    public boolean insertarCuenta(Cuenta cuentaSinID) {
        long numInicial = mongoCollection.countDocuments();
        Document document = new Document();
        document.append("iban", cuentaSinID.getIban());
        document.append("creditCard", cuentaSinID.getCreditCard());
        document.append("balance", cuentaSinID.getBalance());
        document.append("fullName", cuentaSinID.getFullName());
        document.append("date", cuentaSinID.getDate());
        mongoCollection.insertOne(document);
        long numFinal = mongoCollection.countDocuments();
        return (numFinal - numInicial) != 0;
    }

    @Override
    public void actualizarCuentaPorID(Cuenta cuentaConID) {
        mongoCollection.updateOne(Filters.eq("_id", cuentaConID.getId()),Updates.combine(
                Updates.set("iban",cuentaConID.getIban()),
                Updates.set("creditCard",cuentaConID.getCreditCard()),
                Updates.set("balance",cuentaConID.getBalance()),
                Updates.set("fullName",cuentaConID.getFullName())));
    }
}
