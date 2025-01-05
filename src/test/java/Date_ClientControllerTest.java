
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import Controler.Date_ClientController;

import Model.Date_ClientModel;
import View.Date_Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;

public class Date_ClientControllerTest {

    private Date_ClientModel dateClientModel;
    private Date_Client dateClientView;
    private Date_ClientController controller;

    @BeforeEach
    void setUp() {
        dateClientModel = mock(Date_ClientModel.class);
        dateClientView = mock(Date_Client.class);
        controller = new Date_ClientController(dateClientModel, dateClientView);
    }

    @Test
    void nextButtonActionPerformed_showsErrorMessageWhenCnpIsEmpty() {
        JButton nextButton = mock(JButton.class);
        JTextField cnpField = mock(JTextField.class);
        when(dateClientView.getNextButton()).thenReturn(nextButton);
        when(dateClientView.getCnpField()).thenReturn(cnpField);
        when(cnpField.getText()).thenReturn("");

        nextButton.getActionListeners()[0].actionPerformed(null);

        verify(dateClientView).getCnpField();
        verify(cnpField).getText();
        verify(dateClientModel, never()).clientExists(anyString());
    }

    @Test
    void nextButtonActionPerformed_showsInfoMessageWhenClientExists() {
        JButton nextButton = mock(JButton.class);
        JTextField cnpField = mock(JTextField.class);
        when(dateClientView.getNextButton()).thenReturn(nextButton);
        when(dateClientView.getCnpField()).thenReturn(cnpField);
        when(cnpField.getText()).thenReturn("1234567890123");
        when(dateClientModel.clientExists("1234567890123")).thenReturn(true);

        nextButton.getActionListeners()[0].actionPerformed(null);

        verify(dateClientModel).clientExists("1234567890123");
    }

    @Test
    void nextButtonActionPerformed_addsNewClientWhenClientDoesNotExist() {
        JButton nextButton = mock(JButton.class);
        JTextField cnpField = mock(JTextField.class);
        JTextField numeField = mock(JTextField.class);
        JTextField prenumeField = mock(JTextField.class);
        JTextField emailField = mock(JTextField.class);
        JTextField telefonField = mock(JTextField.class);
        when(dateClientView.getNextButton()).thenReturn(nextButton);
        when(dateClientView.getCnpField()).thenReturn(cnpField);
        when(dateClientView.getNumeField()).thenReturn(numeField);
        when(dateClientView.getPrenumeField()).thenReturn(prenumeField);
        when(dateClientView.getEmailField()).thenReturn(emailField);
        when(dateClientView.getTelefonField()).thenReturn(telefonField);
        when(cnpField.getText()).thenReturn("1234567890123");
        when(numeField.getText()).thenReturn("John");
        when(prenumeField.getText()).thenReturn("Doe");
        when(emailField.getText()).thenReturn("john.doe@example.com");
        when(telefonField.getText()).thenReturn("1234567890");
        when(dateClientModel.clientExists("1234567890123")).thenReturn(false);

        nextButton.getActionListeners()[0].actionPerformed(null);

        verify(dateClientModel).addClient("John", "Doe", "john.doe@example.com", "1234567890", "1234567890123");
    }

    @Test
    void backButtonActionPerformed_navigatesToAfterLogin() {
        JButton backButton = mock(JButton.class);
        when(dateClientView.getBackButton()).thenReturn(backButton);

        backButton.getActionListeners()[0].actionPerformed(null);

        verify(dateClientView).getBackButton();
    }
}