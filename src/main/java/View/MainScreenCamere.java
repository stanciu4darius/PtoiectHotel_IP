package View;

import Model.ConnectionBD;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MainScreenCamere extends Component {
    private JPanel mainScreenCamerePanel;
    private JButton camera1Button;
    private JButton camera2Button;
    private JButton camera3Button;
    private JButton camera4Button;
    private JButton camera5Button;
    private JButton camera6Button;
    private JButton camera7Button;
    private JButton camera8Button;
    private JButton camera9Button;
    private JButton backButton;

    private Map<JButton, Integer> buttonCameraMap = new HashMap<>(); // Map între buton și ID-ul camerei

    public MainScreenCamere() {
        initializeButtonMapping();
        initializeButtonColorsFromDatabase();

        // Adaugă acțiuni pentru butoane
        addActionListeners();

        // Funcționalitate pentru butonul de back
        backButton.addActionListener(e -> openAfterLogin());
    }

    private void initializeButtonMapping() {
        // Mapare între fiecare buton și ID-ul camerei din baza de date
        buttonCameraMap.put(camera1Button, 1);
        buttonCameraMap.put(camera2Button, 2);
        buttonCameraMap.put(camera3Button, 3);
        buttonCameraMap.put(camera4Button, 4);
        buttonCameraMap.put(camera5Button, 5);
        buttonCameraMap.put(camera6Button, 6);
        buttonCameraMap.put(camera7Button, 7);
        buttonCameraMap.put(camera8Button, 8);
        buttonCameraMap.put(camera9Button, 9);
    }

    private void initializeButtonColorsFromDatabase() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionBD.getConnection();
            String query = "SELECT ID_camera, disponibilitate FROM camere";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idCamera = resultSet.getInt("ID_camera");
                String disponibilitate = resultSet.getString("disponibilitate");

                // Găsește butonul asociat acestui ID_camera
                for (Map.Entry<JButton, Integer> entry : buttonCameraMap.entrySet()) {
                    if (entry.getValue() == idCamera) {
                        setButtonColor(entry.getKey(), disponibilitate);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionBD.close(resultSet);
            ConnectionBD.close(statement);
            ConnectionBD.close(connection);
        }
    }

    private void setButtonColor(JButton button, String disponibilitate) {
        switch (disponibilitate.toUpperCase()) {
            case "LIBER":
                button.setBackground(Color.GREEN);
                break;
            case "OCUPATA":
                button.setBackground(Color.RED);
                break;
            case "IGIENIZARE":
                button.setBackground(Color.YELLOW);
                break;
            default:
                button.setBackground(Color.GRAY); // Default pentru stări necunoscute
                break;
        }
        button.setOpaque(true);
        button.setBorderPainted(false);
    }

    private void addActionListeners() {
        for (JButton button : buttonCameraMap.keySet()) {
            button.addActionListener(e -> handleButtonClick(button));
        }
    }

    private void handleButtonClick(JButton button) {
        int idCamera = buttonCameraMap.get(button);

        // Obține starea curentă din culoarea butonului
        String currentDisponibilitate = getDisponibilitateFromColor(button.getBackground());

        // Determină următoarea stare
        String nextDisponibilitate = getNextDisponibilitate(currentDisponibilitate);

        // Actualizează culoarea butonului
        setButtonColor(button, nextDisponibilitate);

        // Actualizează starea în baza de date
        updateDisponibilitateInDatabase(idCamera, nextDisponibilitate);
    }

    private String getDisponibilitateFromColor(Color color) {
        if (Color.GREEN.equals(color)) return "LIBER";
        if (Color.RED.equals(color)) return "OCUPATA";
        if (Color.YELLOW.equals(color)) return "IGIENIZARE";
        return "NECUNOSCUT";
    }

    private String getNextDisponibilitate(String currentDisponibilitate) {
        switch (currentDisponibilitate) {
            case "LIBER":
                return "OCUPATA";
            case "OCUPATA":
                return "IGIENIZARE";
            case "IGIENIZARE":
                return "LIBER";
            default:
                return "LIBER"; // Starea implicită
        }
    }

    private void updateDisponibilitateInDatabase(int idCamera, String disponibilitate) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionBD.getConnection();
            String updateQuery = "UPDATE camere SET disponibilitate = ? WHERE ID_camera = ?";
            statement = connection.prepareStatement(updateQuery);
            statement.setString(1, disponibilitate);
            statement.setInt(2, idCamera);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionBD.close(statement);
            ConnectionBD.close(connection);
        }
    }

    private void openAfterLogin() {
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(mainScreenCamerePanel);
        if (currentFrame != null) {
            currentFrame.dispose();
        }

        JFrame afterLoginFrame = new JFrame("After Login");
        afterLogin afterLoginView = new afterLogin(); // Instanță a clasei afterLogin
        afterLoginFrame.setContentPane(afterLoginView.getAfterLoginPanel());
        afterLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        afterLoginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
        afterLoginFrame.setVisible(true);
    }

    public JPanel getMainScreenCamerePanel() {
        return mainScreenCamerePanel;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
