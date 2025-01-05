
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import Controler.LoginController;
import Model.LoginModel;
import View.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;

public class LoginControllerTest {

    private LoginModel loginModel;
    private Login loginView;
    private LoginController controller;

    @BeforeEach
    void setUp() {
        loginModel = mock(LoginModel.class);
        loginView = mock(Login.class);
        controller = new LoginController(loginModel, loginView);
    }

    @Test
    void handleLogin_showsSuccessMessageWhenLoginIsValid() {
        JButton loginButton = mock(JButton.class);
        JTextField usernameField = mock(JTextField.class);
        JPasswordField passwordField = mock(JPasswordField.class);
        when(loginView.getLoginButton()).thenReturn(loginButton);
        when(loginView.getUsernameField()).thenReturn(usernameField);
        when(loginView.getPasswordField()).thenReturn(passwordField);
        when(usernameField.getText()).thenReturn("validUser");
        when(passwordField.getPassword()).thenReturn("validPass".toCharArray());
        when(loginModel.validateLogin("validUser", "validPass")).thenReturn(true);

        loginButton.getActionListeners()[0].actionPerformed(null);

        verify(loginModel).validateLogin("validUser", "validPass");
    }

    @Test
    void handleLogin_showsErrorMessageWhenLoginIsInvalid() {
        JButton loginButton = mock(JButton.class);
        JTextField usernameField = mock(JTextField.class);
        JPasswordField passwordField = mock(JPasswordField.class);
        when(loginView.getLoginButton()).thenReturn(loginButton);
        when(loginView.getUsernameField()).thenReturn(usernameField);
        when(loginView.getPasswordField()).thenReturn(passwordField);
        when(usernameField.getText()).thenReturn("invalidUser");
        when(passwordField.getPassword()).thenReturn("invalidPass".toCharArray());
        when(loginModel.validateLogin("invalidUser", "invalidPass")).thenReturn(false);

        loginButton.getActionListeners()[0].actionPerformed(null);

        verify(loginModel).validateLogin("invalidUser", "invalidPass");
    }

    @Test
    void handleLogin_navigatesToAfterLoginWhenLoginIsValid() {
        JButton loginButton = mock(JButton.class);
        JTextField usernameField = mock(JTextField.class);
        JPasswordField passwordField = mock(JPasswordField.class);
        when(loginView.getLoginButton()).thenReturn(loginButton);
        when(loginView.getUsernameField()).thenReturn(usernameField);
        when(loginView.getPasswordField()).thenReturn(passwordField);
        when(usernameField.getText()).thenReturn("validUser");
        when(passwordField.getPassword()).thenReturn("validPass".toCharArray());
        when(loginModel.validateLogin("validUser", "validPass")).thenReturn(true);

        loginButton.getActionListeners()[0].actionPerformed(null);

        verify(loginModel).validateLogin("validUser", "validPass");
    }
}
