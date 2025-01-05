package View;

import javax.swing.*;

public class Rezervare {
    private JPanel rezervarePanel;
    private JTextField idCameraField;
    private JTextField checkInDateField;
    private JTextField checkOutDateField;
    private JButton calcularePretButton;
    private JButton finalizareRezervareButton;

    public Rezervare() {
        System.out.println("Rezervare View inițializat.");
    }

    public JPanel getRezervarePanel() {
        if (rezervarePanel == null) {
            System.err.println("rezervarePanel este null!");
        }
        return rezervarePanel;
    }

    public JTextField getIdCameraField() {
        if (idCameraField == null) {
            System.err.println("idCameraField este null!");
        }
        return idCameraField;
    }

    public JTextField getCheckInDateField() {
        if (checkInDateField == null) {
            System.err.println("checkInDateField este null!");
        }
        return checkInDateField;
    }

    public JTextField getCheckOutDateField() {
        if (checkOutDateField == null) {
            System.err.println("checkOutDateField este null!");
        }
        return checkOutDateField;
    }

    public JButton getCalcularePretButton() {
        if (calcularePretButton == null) {
            System.err.println("calcularePretButton este null!");
        }
        return calcularePretButton;
    }

    public JButton getFinalizareRezervareButton() {
        if (finalizareRezervareButton == null) {
            System.err.println("finalizareRezervareButton este null!");
        }
        return finalizareRezervareButton;
    }
}
