package modelo;

import modelo.dao.CuentaDAO;
import modelo.dao.CuentaDAONoSQL;

public class Test {
    public static void main(String[] args) {
        CuentaDAO cuentaDAO = new CuentaDAONoSQL();
        System.out.println("Listar los registros de la tabla: ");
        System.out.println(cuentaDAO.obtenerListaCuentas());
        /*ObjectId id = 60b0c98893cb56e5cbb36160;
        System.out.println(cuentaDAO.borrarCuenta("60b0c98893cb56e5cbb36160"));*/
    }
}
