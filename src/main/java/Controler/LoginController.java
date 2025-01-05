package Controler;

import Model.LoginModel;
import View.Login;
import View.afterLogin;

import javax.swing.*;
import java.awt.*;

public class LoginController {

    private LoginModel loginModel;
    private Login loginView;

    public LoginController(LoginModel loginModel, Login loginView) {
        this.loginModel = loginModel;
        this.loginView = loginView;

        /** Adaugă acțiune pentru butonul de login */
        this.loginView.getLoginButton().addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        String username = loginView.getUsernameField().getText();
        String password = new String(loginView.getPasswordField().getPassword());

        if (loginModel.validateLogin(username, password)) {
            showCustomMessageDialog("Login reușit!", "Succes", JOptionPane.INFORMATION_MESSAGE);

            /** Navigare către interfața afterLogin */
            JFrame frame = new JFrame("After Login");
            afterLogin afterLoginView = new afterLogin();
            new afterLoginController(afterLoginView);

            frame.setContentPane(afterLoginView.getAfterLoginPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fereastra deschisă pe tot ecranul
            frame.setVisible(true);

            // Închide fereastra curentă
            SwingUtilities.getWindowAncestor(loginView.getLoginPanel()).dispose();
        } else {
            showCustomMessageDialog("Nume sau parolă incorectă!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Metodă pentru afișarea popup-urilor personalizate */
    private void showCustomMessageDialog(String message, String title, int messageType) {
        // Setare stil personalizat
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 16)); // Font mai mare
        UIManager.put("OptionPane.messageForeground", new Color(60, 60, 120)); // Culoare text
        UIManager.put("Panel.background", new Color(240, 240, 255)); // Fundal panel

        UIManager.put("OptionPane.background", new Color(240, 240, 255)); // Fundal dialog
        UIManager.put("Button.background", new Color(60, 120, 180)); // Culoare buton
        UIManager.put("Button.foreground", Color.WHITE); // Text buton
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 14)); // Font buton

        // Afișare mesaj
        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}
