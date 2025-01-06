import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import Model.Date_ClientModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.*;

public class Date_ClientModelTest {

    private Date_ClientModel dateClientModel;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @BeforeEach
    void setUp() throws SQLException {

        dateClientModel = mock(Date_ClientModel.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
    }

    @Test
    void clientExists_returnsTrueWhenClientExists() throws SQLException {
        when(dateClientModel.clientExists("1234567890123")).thenReturn(true);

        boolean result = dateClientModel.clientExists("1234567890123");
        assertTrue(result);
        verify(dateClientModel).clientExists("1234567890123");
    }

    @Test
    void clientExists_returnsFalseWhenClientDoesNotExist() throws SQLException {
        when(dateClientModel.clientExists("1234567890123")).thenReturn(false);
        boolean result = dateClientModel.clientExists("1234567890123");

        assertFalse(result);
        verify(dateClientModel).clientExists("1234567890123");
    }

    @Test
    void addClient_executesInsertWhenClientIsAdded() throws SQLException {
        doNothing().when(dateClientModel).addClient(
                "John", "Doe", "john.doe@example.com", "1234567890", "1234567890123"
        );
        dateClientModel.addClient("John", "Doe", "john.doe@example.com", "1234567890", "1234567890123");
        verify(dateClientModel).addClient(
                "John", "Doe", "john.doe@example.com", "1234567890", "1234567890123"
        );
    }

}
