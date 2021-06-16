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
            ObjectId id = document.getObjectId("_id");
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
    public boolean borrarCuenta(ObjectId idCuenta) {
        mongoCollection.deleteOne(Filters.eq(idCuenta));
        return false;
    }

    @Override
    public boolean insertarCuenta(Cuenta cuentaSinID) {
        mongoCollection.insertOne(new Document().append("iban", cuentaSinID.getIban()).
                append("creditCard", cuentaSinID.getCreditCard()).append("balance", cuentaSinID.getBalance()).
                append("fullName", cuentaSinID.getFullName()).append("date", cuentaSinID.getDate()));
        return false;
    }

    @Override
    public boolean actualizarCuentaPorID(Cuenta cuentaConID) {
        mongoCollection.updateMany(Filters.eq("id", cuentaConID.getId()), Updates.set("iban", cuentaConID.getIban()),);
        return false;
    }
}
