import Controler.Date_ClientController;
import Model.Date_ClientModel;
import View.Date_Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;

import static org.mockito.Mockito.*;

class Date_ClientControllerTest {

    @Mock
    private Date_ClientModel dateClientModel;
    @Mock
    private Date_Client dateClientView;
    @Mock
    private JButton nextButton;
    @Mock
    private JButton backButton;
    @Mock
    private JTextField cnpField, numeField, prenumeField, emailField, telefonField;
    private Date_ClientController controller;
    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        when(dateClientView.getNextButton()).thenReturn(nextButton);
        when(dateClientView.getBackButton()).thenReturn(backButton);
        when(dateClientView.getCnpField()).thenReturn(cnpField);
        when(dateClientView.getNumeField()).thenReturn(numeField);
        when(dateClientView.getPrenumeField()).thenReturn(prenumeField);
        when(dateClientView.getEmailField()).thenReturn(emailField);
        when(dateClientView.getTelefonField()).thenReturn(telefonField);
        when(dateClientView.getDateClientPanel()).thenReturn(new JPanel());

        controller = new Date_ClientController(dateClientModel, dateClientView);
    }


    @Test
    void testHandleNext_EmptyCnp() {

        when(cnpField.getText()).thenReturn("");
        nextButton.doClick();
        verify(dateClientModel, never()).clientExists(anyString());
        verify(dateClientModel, never()).addClient(anyString(), anyString(), anyString(), anyString(), anyString());
        verifyNoMoreInteractions(dateClientModel);
    }


}
