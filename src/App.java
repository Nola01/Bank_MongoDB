import controlador.Controlador;
import controlador.ModeloTabla;
import vista.CuentaVista;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //invocamos al modelo, vista y controlador
                ModeloTabla modeloTabla = new ModeloTabla();
                CuentaVista vista = new CuentaVista();
                new Controlador(modeloTabla,vista);
            }
        });
    }

}
