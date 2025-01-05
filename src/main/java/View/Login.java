package View;

import javax.swing.*;
import java.awt.*;

public class Login {
    private JPanel loginPanel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;

    public Login() {
        // Crearea panelului principal
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(new Color(240, 240, 255)); // Fundal deschis

        // Inițializarea componentelor
        JLabel titleLabel = new JLabel("Bine ai venit!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28)); // Titlu mai mare
        titleLabel.setForeground(new Color(60, 60, 120));

        JLabel usernameLabel = new JLabel("Nume utilizator:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Font mai mare pentru label-uri

        textField1 = new JTextField(15);
        textField1.setFont(new Font("Arial", Font.PLAIN, 16)); // Text mai mare în câmp

        JLabel passwordLabel = new JLabel("Parolă:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        passwordField1 = new JPasswordField(15);
        passwordField1.setFont(new Font("Arial", Font.PLAIN, 16));

        loginButton = new JButton("Autentificare");
        loginButton.setBackground(new Color(60, 120, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16)); // Font mai mare pentru buton

        // Layout-ul componentelor
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Spațiu între componente
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        loginPanel.add(textField1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        loginPanel.add(passwordField1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, gbc);
    }

    // Getter pentru JPanel-ul principal
    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public JTextField getUsernameField() {
        return textField1;
    }

    public JPasswordField getPasswordField() {
        return passwordField1;
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}
