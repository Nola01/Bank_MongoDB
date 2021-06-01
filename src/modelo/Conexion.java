package modelo;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
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

    private Conexion() throws SQLException, IOException {
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoCredential mongoCredential = MongoCredential.createCredential(
                "localhost", "MOngoDB", "s3cret".toCharArray());
        MongoDatabase mongoDatabase = mongo.getDatabase("MongoDB");
    }

    //getter de conexion
    public Connection getConnection() {
        return connection;
    }

    //acceso a la base de datos
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
                Conexion conexion5 = new Conexion();
                Connection connection = conexion5.getConnection();
                if (connection != null) {
                    connection.close();
                    System.out.println("cerrada la conexión");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
