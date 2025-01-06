import Controler.LoginController;
import Model.LoginModel;
import View.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    @Mock
    private LoginModel loginModel;
    @Mock
    private Login loginView;
    @Mock
    private JButton loginButton;
    @Mock
    private JTextField usernameField;
    @Mock
    private JPasswordField passwordField;
    @Mock
    private JPanel loginPanel;
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(loginView.getLoginButton()).thenReturn(loginButton);
        when(loginView.getUsernameField()).thenReturn(usernameField);
        when(loginView.getPasswordField()).thenReturn(passwordField);
        when(loginView.getLoginPanel()).thenReturn(loginPanel);
        loginController = new LoginController(loginModel, loginView);
    }

    @Test
    void testCustomMessageDialogStyling() {
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 16));
        UIManager.put("OptionPane.messageForeground", new Color(60, 60, 120));
        Font messageFont = (Font) UIManager.get("OptionPane.messageFont");
        assertNotNull(messageFont);
        assertEquals("Arial", messageFont.getName());
        assertEquals(Font.PLAIN, messageFont.getStyle());
        Color messageForeground = (Color) UIManager.get("OptionPane.messageForeground");
        assertNotNull(messageForeground);
        assertEquals(new Color(60, 60, 120), messageForeground);
    }
}
