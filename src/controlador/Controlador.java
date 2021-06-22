package controlador;

import modelo.dto.Cuenta;
import org.bson.types.ObjectId;
import vista.CuentaVista;

import java.time.LocalDate;

public class Controlador {
    private ModeloTabla modeloTabla;
    private CuentaVista vista;

    public Controlador(ModeloTabla modeloTabla, CuentaVista vista) {
        //generamos la tabla y creamos un metodo para registrar los eventos de los botones
        this.modeloTabla = modeloTabla;
        this.vista = vista;
        vista.getTable().setModel(modeloTabla);
        registrarEventos();
    }

    private void registrarEventos() {
        //añadimos los listener
        vista.getAddButton().addActionListener(e -> mostrarPanelDerecho());
        vista.getSaveNewButton().addActionListener(e -> guardarCuenta());
        vista.getCancelButton().addActionListener(e -> ocultarPanelDerecho());
        vista.getDeleteButton().addActionListener(e -> borrarCuenta());

    }

    private void borrarCuenta() {
        //creamos la variable row y guardamos en ella el numero de fila seleccionada
        int row = vista.getTable().getSelectedRow();
        int r = vista.getTable().convertRowIndexToModel(row);

        //eliminamos esa fila desde la clase modeloTabla
        modeloTabla.removeRow(r);
    }

    private void ocultarPanelDerecho() {
        //ocultamos el panel derecho en la vista
        vista.getRightPanel().setVisible(false);

        //seteamos los valores de los campos para dejarlos en blanco la proxima vez que mostremos el panel derecho
        vista.getTextFieldIban().setText("");
        vista.getTextFieldCreditcard().setText("");
        vista.getTextFieldBalance().setText("");
        vista.getTextFieldFullname().setText("");
    }

    private void guardarCuenta() {
        //creamos un nuevo objeto cuenta con los valores introducidos en los campos del panel derecho de la vista
        ObjectId id = new ObjectId();
        String iban = vista.getTextFieldIban().getText();
        String creditCard = vista.getTextFieldCreditcard().getText();
        Double balance = Double.valueOf(vista.getTextFieldBalance().getText());
        String fullName = vista.getTextFieldFullname().getText();
        String date = LocalDate.now().toString();

        Cuenta cuenta = new Cuenta(id, iban, creditCard, balance, fullName, date);

        //añadimos la cuenta a la lista
        modeloTabla.addRow(cuenta);

        //para ocultar el panel derecho despues de añadir
        ocultarPanelDerecho();

    }

    private void mostrarPanelDerecho() {
        //mostramos el panel derecho en la vista
        vista.getRightPanel().setVisible(true);
    }




}

