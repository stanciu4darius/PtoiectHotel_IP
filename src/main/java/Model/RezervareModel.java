package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RezervareModel {

    // Verifică dacă o cameră este disponibilă
    public boolean isCameraLibera(int idCamera, LocalDate checkInDate, LocalDate checkOutDate) {
        String query = "SELECT COUNT(*) FROM rezervari WHERE ID_Camera = ? " +
                "AND ((check_in_date <= ? AND check_out_date >= ?) " +
                "OR (check_in_date >= ? AND check_in_date <= ?))";
        try (Connection connection = ConnectionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idCamera);
            preparedStatement.setDate(2, java.sql.Date.valueOf(checkOutDate));
            preparedStatement.setDate(3, java.sql.Date.valueOf(checkInDate));
            preparedStatement.setDate(4, java.sql.Date.valueOf(checkInDate));
            preparedStatement.setDate(5, java.sql.Date.valueOf(checkOutDate));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) == 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Adaugă o rezervare nouă
    public void adaugaRezervare(int idCamera, LocalDate checkInDate, LocalDate checkOutDate, double pret, String tipRezervare) {
        String query = "INSERT INTO rezervari (ID_Camera, check_in_date, check_out_date, tip, PretTotal) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idCamera);
            preparedStatement.setDate(2, java.sql.Date.valueOf(checkInDate));
            preparedStatement.setDate(3, java.sql.Date.valueOf(checkOutDate));
            preparedStatement.setString(4, tipRezervare);
            preparedStatement.setDouble(5, pret);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obține prețul camerei
    public double getPretCamera(int idCamera) {
        String query = "SELECT pret FROM camere WHERE ID_camera = ?";
        try (Connection connection = ConnectionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idCamera);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("pret");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    // Obține următoarea dată liberă pentru cameră
    public LocalDate getUrmatoareaDataLibera(int idCamera, LocalDate checkInDate) {
        String query = "SELECT MAX(check_out_date) FROM rezervari WHERE ID_Camera = ? AND check_out_date >= ?";
        try (Connection connection = ConnectionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idCamera);
            preparedStatement.setDate(2, java.sql.Date.valueOf(checkInDate));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getDate(1) != null) {
                    return resultSet.getDate(1).toLocalDate().plusDays(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
