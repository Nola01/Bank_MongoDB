package modelo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;


public class Conexion {

    //private Connection connection;
    private static Conexion conexion;
    private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;
    private static MongoCollection mongoCollection;

    //Patron Singleton
    private Conexion() throws SQLException, IOException {
        mongoClient = new MongoClient("localhost", 27017);
        /*MongoCredential mongoCredential = MongoCredential.createCredential(
                "localhost", "MOngoDB", "s3cret".toCharArray());*/
        mongoDatabase = mongoClient.getDatabase("db1");
        mongoCollection = mongoDatabase.getCollection("accounts");
        //System.out.println(mongoDatabase);
        //System.out.println(mongoCollection);
        /*FindIterable<Document> iterDoc = mongoCollection.find();
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }*/

    }

    public static MongoCollection getMongoCollection() {
        return mongoCollection;
    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    public static Conexion getConexion() {
        return conexion;
    }

    //arrancar conexion
    public static Conexion getInstance() throws SQLException, IOException {
        if (conexion == null) {
            Runtime.getRuntime().addShutdownHook(new HookCierreConexion());
            conexion = new Conexion();
        }
        return conexion;
    }

    //cierre de la conexion
    static class HookCierreConexion  extends Thread  {
        @Override
        public void run() {
            Conexion conexion = null;
            try {
                conexion = new Conexion();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (conexion != null) {
                //connection.close();
                System.out.println("Conexión cerrada");
            }

        }
    }

    // probamos la conexion
    public static void main(String[] args) throws SQLException, IOException {
        /*Conexion conexion = new Conexion();
        System.out.println(conexion);*/

        try {
            System.out.println("Conexión: " + Conexion.getInstance().getConexion());
            System.out.println("Conexión: " + Conexion.getInstance().getConexion());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

