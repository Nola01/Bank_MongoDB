package controlador;

import modelo.dao.CuentaDAO;
import modelo.dao.CuentaDAONoSQL;
import modelo.dto.Cuenta;

import javax.swing.table.AbstractTableModel;
import java.util.Collections;
import java.util.List;

public class ModeloTabla extends AbstractTableModel {
    private String[] columnas = {"ID", "IBAN", "CREDIT CARD", "BALANCE", "FULL NAME", "DATE"};
    private CuentaDAO cuentaDAO = new CuentaDAONoSQL();
    List<Cuenta> listaCuentas;

    public ModeloTabla() {
        listaCuentas = cuentaDAO.obtenerListaCuentas();
        Collections.sort(listaCuentas);
    }


    @Override
    public int getRowCount() {
        return listaCuentas.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return listaCuentas.get(rowIndex).getId();
            case 1:
                return listaCuentas.get(rowIndex).getIban();
            case 2:
                return listaCuentas.get(rowIndex).getCreditCard();
            case 3:
                return listaCuentas.get(rowIndex).getBalance();
            case 4:
                return listaCuentas.get(rowIndex).getFullName();
            case 5:
                return listaCuentas.get(rowIndex).getDate();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

}
