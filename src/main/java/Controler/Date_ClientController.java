package Controler;

import Model.Date_ClientModel;
import Model.RezervareModel;
import View.Date_Client;
import View.Rezervare;
import View.afterLogin;

import javax.swing.*;

public class Date_ClientController {

    private final Date_ClientModel dateClientModel;
    private final Date_Client dateClientView;

    public Date_ClientController(Date_ClientModel dateClientModel, Date_Client dateClientView) {
        this.dateClientModel = dateClientModel;
        this.dateClientView = dateClientView;

        System.out.println("Date_ClientController inițializat.");

        /** Conectăm acțiunile butoanelor*/
        if (dateClientView.getNextButton() != null) {
            dateClientView.getNextButton().addActionListener(e -> {
                System.out.println("Apăsat pe NEXT.");
                handleNext();
            });
        } else {
            System.err.println("Butonul NEXT este null.");
        }

        if (dateClientView.getBackButton() != null) {
            dateClientView.getBackButton().addActionListener(e -> {
                System.out.println("Apăsat pe BACK din date_client.");
                handleBack();
            });
        } else {
            System.err.println("Butonul BACK este null.");
        }
    }

    private void handleNext() {
        System.out.println("Execut handleNext.");

        String cnp = dateClientView.getCnpField().getText().trim();
        if (cnp.isEmpty()) {
            JOptionPane.showMessageDialog(null, "CNP-ul este gol!", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (dateClientModel.clientExists(cnp)) {
            JOptionPane.showMessageDialog(null, "Clientul există deja!", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Clientul nu există. Adăugăm client nou.", "Info", JOptionPane.INFORMATION_MESSAGE);
            dateClientModel.addClient(
                    dateClientView.getNumeField().getText().trim(),
                    dateClientView.getPrenumeField().getText().trim(),
                    dateClientView.getEmailField().getText().trim(),
                    dateClientView.getTelefonField().getText().trim(),
                    cnp
            );
        }

        navigateToRezervare();
    }

    private void navigateToRezervare() {
        System.out.println("Navigăm la Rezervare.");
        JFrame frame = new JFrame("Rezervare");
        Rezervare rezervareView = new Rezervare();
        RezervareModel rezervareModel = new RezervareModel();

        // Inițializează Controller-ul pentru Rezervare
        new RezervareController(rezervareModel, rezervareView);

        frame.setContentPane(rezervareView.getRezervarePanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        // Închide fereastra curentă
        SwingUtilities.getWindowAncestor(dateClientView.getDateClientPanel()).dispose();
    }

    private void handleBack() {
        System.out.println("Navigăm la After Login din date_client.");
        JFrame frame = new JFrame("After Login");
        afterLogin afterLoginView = new afterLogin();
        new afterLoginController(afterLoginView);

        frame.setContentPane(afterLoginView.getAfterLoginPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fereastra deschisă pe tot ecranul
        frame.setVisible(true);

        // Închide fereastra curentă
        SwingUtilities.getWindowAncestor(dateClientView.getDateClientPanel()).dispose();
    }
}
