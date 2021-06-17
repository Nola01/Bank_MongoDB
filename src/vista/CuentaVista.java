package vista;

import javax.swing.*;
import java.awt.*;

public class CuentaVista {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JLabel titleLabel;
    private JPanel bottomPanel;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton addButton;
    private JScrollPane centrePanel;
    private JTable table;
    private JPanel rightPanel;
    private JTextField textFieldIban;
    private JLabel ibanLabel;
    private JLabel creditcardLabel;
    private JTextField textFieldCreditcard;
    private JTextField textFieldBalance;
    private JLabel balanceLabel;
    private JLabel fullnameLabel;
    private JTextField textFieldFullname;
    private JButton saveNewButton;
    private JButton cancelButton;

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getSaveButton() {
        return updateButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JTable getTable() {
        return table;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }

    public JTextField getTextFieldIban() {
        return textFieldIban;
    }

    public JTextField getTextFieldCreditcard() {
        return textFieldCreditcard;
    }

    public JTextField getTextFieldBalance() {
        return textFieldBalance;
    }

    public JTextField getTextFieldFullname() {
        return textFieldFullname;
    }

    public JButton getSaveNewButton() {
        return saveNewButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public CuentaVista() {
        JFrame frame = new JFrame("Banco");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(dimension.width / 2, dimension.height / 2);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
