package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

    /** Verifică dacă utilizatorul există în baza de date*/
    public boolean validateLogin(String username, String password) {
        String query = "SELECT * FROM angajati WHERE nume_angajat = ? AND password_angajat = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Obține conexiunea la baza de date
            connection = ConnectionBD.getConnection();

            // Pregătește interogarea
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            // Execută interogarea
            resultSet = preparedStatement.executeQuery();

            // Dacă există un rezultat, înseamnă că utilizatorul și parola sunt valide
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Închide resursele
            ConnectionBD.close(resultSet);
            ConnectionBD.close(preparedStatement);
            ConnectionBD.close(connection);
        }
    }
}
