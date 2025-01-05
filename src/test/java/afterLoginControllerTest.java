import Controler.afterLoginController;
import View.afterLogin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.mockito.Mockito.*;

public class afterLoginControllerTest {

    private afterLogin afterLoginView;
    private afterLoginController controller;

    @BeforeEach
    void setUp() {
        afterLoginView = mock(afterLogin.class);
        controller = new afterLoginController(afterLoginView);
    }

    @Test
    void goToDateClient_opensDateClientView() {
        JButton rezervareButton = mock(JButton.class);
        when(afterLoginView.getRezervareButton()).thenReturn(rezervareButton);

        controller = new afterLoginController(afterLoginView);
        rezervareButton.getActionListeners()[0].actionPerformed(null);

        verify(afterLoginView).getRezervareButton();
    }

    @Test
    void goToMainScreenCamere_opensMainScreenCamereView() {
        JButton statusCamereButton = mock(JButton.class);
        when(afterLoginView.getStatusCamereButton()).thenReturn(statusCamereButton);

        controller = new afterLoginController(afterLoginView);
        statusCamereButton.getActionListeners()[0].actionPerformed(null);

        verify(afterLoginView).getStatusCamereButton();
    }
}