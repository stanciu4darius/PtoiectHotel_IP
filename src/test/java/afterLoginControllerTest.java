import Controler.afterLoginController;
import View.afterLogin;
import View.Date_Client;
import View.MainScreenCamere;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.awt.event.ActionListener;

import static org.mockito.Mockito.*;

public class afterLoginControllerTest {

    @Mock
    private afterLogin afterLoginView;
    @Mock
    private JButton rezervareButton;
    @Mock
    private JButton statusCamereButton;
    @Mock
    private Date_Client dateClientView;
    @Mock
    private MainScreenCamere mainScreenCamereView;
    private afterLoginController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(afterLoginView.getRezervareButton()).thenReturn(rezervareButton);
        when(afterLoginView.getStatusCamereButton()).thenReturn(statusCamereButton);
        controller = new afterLoginController(afterLoginView);
    }

    @Test
    void testGoToDateClient() {
        ActionListener actionListener = mock(ActionListener.class);
        rezervareButton.addActionListener(actionListener);
        actionListener.actionPerformed(null);
        verify(afterLoginView).getRezervareButton();
    }

    @Test
    void testGoToMainScreenCamere() {
        ActionListener actionListener = mock(ActionListener.class);
        statusCamereButton.addActionListener(actionListener);
        actionListener.actionPerformed(null);
        verify(afterLoginView).getStatusCamereButton();
    }

    @Test
    void testButtonActionTriggersCorrectMethod() {
        rezervareButton.doClick();
        statusCamereButton.doClick();
        verify(afterLoginView).getRezervareButton();
        verify(afterLoginView).getStatusCamereButton();
    }
}
