package controlador;

import modelo.dao.CuentaDAO;
import modelo.dao.CuentaDAONoSQL;
import modelo.dto.Cuenta;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ModeloTabla extends AbstractTableModel {
    private String[] columnas = {"ID", "IBAN", "CREDIT CARD", "BALANCE", "FULL NAME", "DATE"};
    private CuentaDAO cuentaDAO = new CuentaDAONoSQL();
    List<Cuenta> listaCuentas;

    public ModeloTabla() {
        //obtenemos la lista de cuentas
        listaCuentas = cuentaDAO.obtenerListaCuentas();
    }

    @Override
    public int getRowCount() {
        //obtenemos el numero de filas que tendra nuestra tabla
        return listaCuentas.size();
    }

    @Override
    public int getColumnCount() {
        //obtenemos el numero de columnas que tendra nuestra tabla
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //obtenemos el valor de cada celda de la tabla
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
        return rowIndex;
    }

    @Override
    public String getColumnName(int column) {
        //obtenemos los nombres de las columnas de la tabla
        return columnas[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //establecemos las columnas que se pueden editar
        return columnIndex == 2 || columnIndex == 3 || columnIndex == 4;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //obtenemos los datos de las celdas de la fila seleccionada y los guardamos como un objeto cuenta que
        //es el que vamos a modificar
        Cuenta cuentaAModificar = listaCuentas.get(rowIndex);

        //en el switch SOLO ponemos los casos en los que la columna es editable (2,3,4), y seteamos los valores de
        //esas celdas cambiandolos por lo que escribe el usuario
        switch (columnIndex){
            case 2:
                cuentaAModificar.setCreditCard((String) aValue);
                break;
            case 3:
                cuentaAModificar.setBalance(Double.parseDouble((String) aValue));
                break;
            case 4:
                cuentaAModificar.setFullName((String) aValue);
                break;
        }

        //usamos el metodo creado en CuentaDAONoSQL para actualizar la cuenta
        cuentaDAO.actualizarCuentaPorID(cuentaAModificar);
    }

    public void addRow(Cuenta cuenta) {
        //añadimos la cuenta a la BD
        cuentaDAO.insertarCuenta(cuenta);

        //añadimos la cuenta a la lista
        listaCuentas.add(cuenta);

        //actualizamos la tabla
        fireTableDataChanged();
    }

    public void removeRow(int row) {
        //obtenemos la lista de todas las cuentas antes de borrar alguna
        cuentaDAO.obtenerListaCuentas();

        //si las filas de la lista anterior es menor que 0 significa que no hay filas, por lo
        //tanto no hay datos que borrar
        if (row < 0)
            return;

        //eliminar cuenta de la BD
        cuentaDAO.borrarCuenta(listaCuentas.get(row).getId().toString());

        //eliminar cuenta de la lista
        listaCuentas.remove(row);
        fireTableDataChanged();
    }

}
