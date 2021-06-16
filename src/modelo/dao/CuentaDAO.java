package modelo.dao;

import modelo.dto.Cuenta;
import org.bson.types.ObjectId;

import java.util.List;

public interface CuentaDAO {
    List<Cuenta> obtenerListaCuentas();
    boolean borrarCuenta(ObjectId idCuenta);
    boolean insertarCuenta(Cuenta cuentaSinID);
    boolean actualizarCuentaPorID(Cuenta cuentaConID);
}
