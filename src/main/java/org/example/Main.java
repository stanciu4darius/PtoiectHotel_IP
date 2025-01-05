package org.example;

import Controler.LoginController;
import Model.LoginModel;
import View.Login;

import javax.swing.*;

public class Main {

    /**
     * Punctul de intrare al aplicației.
     * Inițializează interfața de login și o afișează pe tot ecranul.
     *
     * @param args Argumentele din linia de comandă (nerelevante în această aplicație).
     */
    public static void main(String[] args) {
        System.out.println("Aplicația a început.");

        // Inițializează View și Model pentru Login
        System.out.println("Inițializare Login View și Login Model...");
        Login loginView = new Login();
        LoginModel loginModel = new LoginModel();

        // Inițializează Controller-ul pentru Login
        System.out.println("Inițializare Login Controller...");
        new LoginController(loginModel, loginView);

        // Setează fereastra principală pentru Login
        System.out.println("Configurare fereastră principală...");
        createAndShowFrame(loginView.getLoginPanel(), "Login");

        System.out.println("Fereastra principală este afișată.");
    }

    /**
     * Creează și afișează o fereastră complet configurată.
     *
     * @param panel JPanel-ul care va fi afișat în fereastră.
     * @param title Titlul ferestrei.
     */
    private static void createAndShowFrame(JPanel panel, String title) {
        JFrame frame = new JFrame(title);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fereastra pe tot ecranul
        frame.setVisible(true);
    }
}
