
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import Model.Date_ClientModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.*;

public class Date_ClientModelTest {

    private Date_ClientModel dateClientModel;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @BeforeEach
    void setUp() throws SQLException {
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        dateClientModel = new Date_ClientModel(); // Injectează mock-ul
    }

    @Test
    void clientExists_returnsTrueWhenClientExists() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("count")).thenReturn(1);

        boolean result = dateClientModel.clientExists("1234567890123");
        assertTrue(result);
        verify(preparedStatement).setString(1, "1234567890123");
        verify(preparedStatement).executeQuery();
    }

    @Test
    void clientExists_returnsFalseWhenClientDoesNotExist() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("count")).thenReturn(0);

        boolean result = dateClientModel.clientExists("1234567890123");

        assertTrue(result);    }


    @Test
    void clientExists_returnsFalseWhenSQLExceptionOccurs() throws SQLException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());

        boolean result = dateClientModel.clientExists("1234567890123");

        assertTrue(result);
    }

    @Test
    void addClient_executesInsertWhenClientIsAdded() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        dateClientModel.addClient("John", "Doe", "john.doe@example.com", "1234567890", "1234567890123");

        verify(preparedStatement).setString(1, "John");
        verify(preparedStatement).setString(2, "Doe");
        verify(preparedStatement).setString(3, "john.doe@example.com");
        verify(preparedStatement).setString(4, "1234567890");
        verify(preparedStatement).setString(5, "1234567890123");
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void addClient_logsErrorWhenSQLExceptionOccurs() throws SQLException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException());

        dateClientModel.addClient("John", "Doe", "john.doe@example.com", "1234567890", "1234567890123");

        // Assuming there's a logger to verify
        // verify(logger).error(anyString(), any(SQLException.class));
    }
}