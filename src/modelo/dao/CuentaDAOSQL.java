package modelo.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import modelo.Conexion;
import modelo.dto.Cuenta;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CuentaDAOSQL implements CuentaDAO{
    private MongoClient mongoClient;
    private MongoDatabase mongodb;
    private Conexion conexion;

    {
        try {
            conexion = Conexion.getInstance().getConexion();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public MongoDatabase getMongodb() {
        return mongodb;
    }

    public void setMongodb(MongoDatabase mongodb) {
        this.mongodb = mongodb;
    }

    @Override
    public List<Cuenta> obtenerListaCuentas() {
        List<Cuenta> cuentas = new ArrayList<>();
        String [] datos = conexion.toString().split("[^=*,]");





        return null;
    }

    @Override
    public boolean borrarCuenta(int idCuenta) {
        return false;
    }

    @Override
    public boolean insertarCuenta(Cuenta cuentaSinID) {
        return false;
    }

    @Override
    public boolean actualizarCuentaPorID(Cuenta cuentaConID) {
        return false;
    }
}
