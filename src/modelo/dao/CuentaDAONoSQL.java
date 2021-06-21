package modelo.dao;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import modelo.Conexion;
import modelo.dto.Cuenta;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
        //creamos la lista de cuentas que devuelve el metodo

        List<Cuenta> listaCuentas = new ArrayList<>();

        //Mongo funciona con documentos, es decir, cada objeto cuenta que hay en nuestra BD es un documento. Por
        //eso usamos el metodo find en nuestra coleccion para buscar, y con un forEach recorremos todos los documentos
        //de la colección. De cada documento obtenemos los valores de sus campos
        mongoCollection.find().forEach((Consumer<Document>) (Document document) ->
        {
            ObjectId id = document.getObjectId("_id");
            String iban = document.getString("iban");
            String creditCard = document.getString("creditCard");
            Double balance = document.getDouble("balance");
            String fullName = document.getString("fullName");
            String date = document.getString("date");

            //por cada documento, usamos los valores de sus campos para crear un objeto cuenta y añadirlo a la lista
            listaCuentas.add(new Cuenta(id, iban, creditCard, balance, fullName, date));
        });

        return listaCuentas;

    }

    @Override
    public boolean borrarCuenta(String idCuenta) {
        //como Mongo trabaja con documentos, creamos uno nuevo
        Document document = new Document();
        //con el metodo deleteOne podemos eliminar un elemento de nuestra coleccion. El documento que hemos
        //creado esta vacio, asi que con el metodo append le añadimos el id de la cuenta que pasamos por
        //parametro en el metodo, que es la que queremos eliminar. Así, el metodo deleteOne eliminará el
        //documento o cuenta con ese id.
        mongoCollection.deleteOne(document.append("_id", new ObjectId(idCuenta)));

        //devuelve TRUE si se elimina algún documento (count de documentos eliminados distinto de 0)
        //devuelve FALSE si no se elimina ningún documento (count de documentos eliminados = 0)
        return mongoCollection.deleteOne(document).getDeletedCount() != 0;
    }

    @Override
    public boolean insertarCuenta(Cuenta cuentaSinID) {
        //creamos una variable long que guarda el numero de documentos de la coleccion
        long numInicial = mongoCollection.countDocuments();

        //creamos un nuevo documento y le añadimos los valores de los campos del objeto cuenta que pasamos como parametro
        Document document = new Document();
        document.append("iban", cuentaSinID.getIban());
        document.append("creditCard", cuentaSinID.getCreditCard());
        document.append("balance", cuentaSinID.getBalance());
        document.append("fullName", cuentaSinID.getFullName());
        document.append("date", cuentaSinID.getDate());

        //con el metodo insertOne añadimos el documento anterior
        mongoCollection.insertOne(document);

        //creamos otra variable que guarda el numero de documentos de la coleccion, que debe variar si se ha añadido
        //correctamente
        long numFinal = mongoCollection.countDocuments();

        //devuelve TRUE si la resta de las dos variables long creadas es distinto de 0, es decir, ha aumentado el numero
        //de documentos de la colección. Devuelve FALSE si la resta es igual a 0, es decir, no hay cambios
        return (numFinal - numInicial) != 0;
    }

    @Override
    public void actualizarCuentaPorID(Cuenta cuentaConID) {
        //con el metodo updateOne podemos actualizar un documento de la coleccion, seteando los valores de los campos
        mongoCollection.updateOne(Filters.eq("_id", cuentaConID.getId()),Updates.combine(
                Updates.set("iban",cuentaConID.getIban()),
                Updates.set("creditCard",cuentaConID.getCreditCard()),
                Updates.set("balance",cuentaConID.getBalance()),
                Updates.set("fullName",cuentaConID.getFullName())));
    }
}
