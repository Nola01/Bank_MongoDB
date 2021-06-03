package modelo;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
    //Patron Singleton
    private Connection connection;
    private static Conexion conexion;

    /*public static void pruebaConexion () {
        MongoClient mongo = new MongoClient("localhost", 27017);
        *//*MongoCredential mongoCredential = MongoCredential.createCredential(
                "localhost", "MOngoDB", "s3cret".toCharArray());*//*
        MongoDatabase mongoDatabase = mongo.getDatabase("db1");
        MongoCollection mongoCollection = mongoDatabase.getCollection("accounts");
        //connection = DriverManager.getConnection("mongodb://localhost:27017/local?authSource=admin");
        System.out.println(mongoCollection);
    }*/

    private Conexion() throws SQLException, IOException {
        MongoClient mongo = new MongoClient("localhost", 27017);
        /*MongoCredential mongoCredential = MongoCredential.createCredential(
                "localhost", "MOngoDB", "s3cret".toCharArray());*/
        MongoDatabase mongoDatabase = mongo.getDatabase("db1");
        MongoCollection mongoCollection = mongoDatabase.getCollection("accounts");
        //connection = DriverManager.getConnection("mongodb://localhost:27017/local?authSource=admin");
        System.out.println(mongoCollection);


    }


    public Connection getConnection() {
        return connection;
    }

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
            try {
                Conexion conexion1 = new Conexion();
                Connection connection = conexion1.getConnection();
                if (connection != null) {
                    connection.close();
                    System.out.println("Conexión cerrada");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // probamos la conexion
    public static void main(String[] args) throws SQLException, IOException {
        //pruebaConexion();
        Conexion conexion = new Conexion();
        System.out.println(conexion);
        /*try {
            System.out.println("conexión: " + Conexion.getInstance().connection);
            System.out.println("conexión: " + Conexion.getInstance().connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
