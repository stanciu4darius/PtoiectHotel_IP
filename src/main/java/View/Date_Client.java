package View;

import javax.swing.*;

public class Date_Client {
    private JPanel dateClientPanel;
    private JTextField numeField;
    private JTextField prenumeField;
    private JTextField emailField;
    private JTextField telefonField;
    private JTextField cnpField;
    private JButton nextButton;
    private JButton backButton;

    public JPanel getDateClientPanel() {
        if (dateClientPanel == null) {
            System.err.println("dateClientPanel este null!");
        }
        return dateClientPanel;
    }

    public JTextField getNumeField() {
        return numeField;
    }

    public JTextField getPrenumeField() {
        return prenumeField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getTelefonField() {
        return telefonField;
    }

    public JTextField getCnpField() {
        return cnpField;
    }

    public JButton getNextButton() {
        if (nextButton == null) {
            System.err.println("nextButton este null!");
        }
        return nextButton;
    }

    public JButton getBackButton() {
        if (backButton == null) {
            System.err.println("backButton este null!");
        }
        return backButton;
    }
}
