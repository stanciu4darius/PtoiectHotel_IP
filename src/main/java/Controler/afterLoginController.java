package Controler;

import Model.Date_ClientModel;
import View.afterLogin;
import View.Date_Client;
import View.MainScreenCamere;

import javax.swing.*;

public class afterLoginController {

    private afterLogin afterLoginView;

    public afterLoginController(afterLogin afterLoginView) {
        this.afterLoginView = afterLoginView;
        /**Action pentru butoane pentru fereastra afterLogin*/

        this.afterLoginView.getRezervareButton().addActionListener(e -> goToDateClient());
        this.afterLoginView.getStatusCamereButton().addActionListener(e -> goToMainScreenCamere());
    }

    /** Metoda pentru a deschide view-ul Date_Client*/
    private void goToDateClient() {
        JFrame frame = new JFrame("Date Client");
        Date_Client dateClientView = new Date_Client();
        Date_ClientModel dateClientModel = new Date_ClientModel();
        new Date_ClientController(dateClientModel, dateClientView);

        frame.setContentPane(dateClientView.getDateClientPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fereastra deschisă pe tot ecranul
        frame.setVisible(true);

        // Închide fereastra curentă
        SwingUtilities.getWindowAncestor(afterLoginView.getAfterLoginPanel()).dispose();
    }

    /** Metoda pentru a deschide view-ul MainScreenCamere*/
    private void goToMainScreenCamere() {
        JFrame frame = new JFrame("Main Screen Camere");
        MainScreenCamere mainScreenCamereView = new MainScreenCamere();
        MainScreenCamereController mainScreenCamereController = new MainScreenCamereController(mainScreenCamereView); // Creează controller-ul pentru MainScreenCamere

        frame.setContentPane(mainScreenCamereView.getMainScreenCamerePanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fereastra deschisă pe tot ecranul
        frame.setVisible(true);

// Închide fereastra curentă
        SwingUtilities.getWindowAncestor(afterLoginView.getAfterLoginPanel()).dispose();



    }
}