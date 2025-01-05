package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Date_ClientModel {


    public boolean clientExists(String cnp) {
        String query = "SELECT COUNT(*) AS count FROM clienti WHERE CNP = ?";
        try (Connection connection = ConnectionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, cnp);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    System.out.println("CNP găsit în baza de date: " + count); // Debug
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Eroare la verificarea CNP-ului: " + e.getMessage());
        }
        return false;
    }

    public void addClient(String nume, String prenume, String email, String telefon, String cnp) {
        String query = "INSERT INTO clienti (nume, prenume, email, nr_telefon, CNP) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nume);
            preparedStatement.setString(2, prenume);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, telefon);
            preparedStatement.setString(5, cnp);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Client nou adăugat. Rânduri afectate: " + rowsAffected); // Debug
        } catch (SQLException e) {
            System.err.println("Eroare la adăugarea clientului: " + e.getMessage());
        }
    }
}
