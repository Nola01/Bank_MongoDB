package modelo.dao;

import modelo.dto.Cuenta;

import java.util.List;

public interface CuentaDAO {
    List<Cuenta> obtenerListaCuentas();
    boolean borrarCuenta(String idCuenta);
    boolean insertarCuenta(Cuenta cuentaSinID);
    void actualizarCuentaPorID(Cuenta cuentaConID);
}
