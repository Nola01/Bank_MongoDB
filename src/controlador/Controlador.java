package controlador;

import vista.CuentaVista;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Controlador {
    private ModeloTabla modeloTabla;
    private CuentaVista vista;

    public Controlador(ModeloTabla modeloTabla, CuentaVista vista) {
        this.modeloTabla = modeloTabla;
        this.vista = vista;
        vista.getTable().setModel(modeloTabla);
        registrarEventos();
    }

    private void registrarEventos() {

    }


}

