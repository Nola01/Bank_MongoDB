package modelo.dao;

import modelo.dto.Cuenta;
import java.util.List;

public interface CuentaDAO {
    List<Cuenta> obtenerListaCuentas();
    boolean borrarCuenta(int idCuenta);
    boolean insertarCuenta(Cuenta cuentaSinID);
    boolean actualizarCuentaPorID(Cuenta cuentaConID);
}
