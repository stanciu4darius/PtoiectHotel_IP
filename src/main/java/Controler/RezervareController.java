package Controler;

import Model.RezervareModel;
import View.Rezervare;

import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RezervareController {

    private final RezervareModel rezervareModel;
    private final Rezervare rezervareView;

    public RezervareController(RezervareModel rezervareModel, Rezervare rezervareView) {
        this.rezervareModel = rezervareModel;
        this.rezervareView = rezervareView;

        System.out.println("RezervareController inițializat.");

        // Conectează acțiunea butonului Calculare Preț
        rezervareView.getCalcularePretButton().addActionListener(e -> {
            System.out.println("Apăsat pe Calculare Preț.");
            handleCalcularePret();
        });

        // Conectează acțiunea butonului Finalizare Rezervare
        rezervareView.getFinalizareRezervareButton().addActionListener(e -> {
            System.out.println("Apăsat pe Finalizare Rezervare.");
            handleFinalizareRezervare();
        });
    }

    private void handleCalcularePret() {
        try {
            int idCamera = Integer.parseInt(rezervareView.getIdCameraField().getText().trim());
            LocalDate checkInDate = LocalDate.parse(rezervareView.getCheckInDateField().getText().trim());
            LocalDate checkOutDate = LocalDate.parse(rezervareView.getCheckOutDateField().getText().trim());

            if (!checkOutDate.isAfter(checkInDate)) {
                JOptionPane.showMessageDialog(null, "Data de check-out trebuie să fie după data de check-in!", "Eroare", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double pret = calculatePret(idCamera, checkInDate, checkOutDate);
            JOptionPane.showMessageDialog(null, "Prețul total este: " + pret + " RON", "Preț", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Datele introduse sunt invalide!", "Eroare", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void handleFinalizareRezervare() {
        try {
            int idCamera = Integer.parseInt(rezervareView.getIdCameraField().getText().trim());
            LocalDate checkInDate = LocalDate.parse(rezervareView.getCheckInDateField().getText().trim());
            LocalDate checkOutDate = LocalDate.parse(rezervareView.getCheckOutDateField().getText().trim());

            if (rezervareModel.isCameraLibera(idCamera, checkInDate, checkOutDate)) {
                double pret = calculatePret(idCamera, checkInDate, checkOutDate);
                rezervareModel.adaugaRezervare(idCamera, checkInDate, checkOutDate, pret, "online");
                JOptionPane.showMessageDialog(null, "Rezervare efectuată cu succes!", "Succes", JOptionPane.INFORMATION_MESSAGE);
            } else {
                LocalDate urmatoareaData = rezervareModel.getUrmatoareaDataLibera(idCamera, checkInDate);
                JOptionPane.showMessageDialog(null, "Camera nu este disponibilă. Următoarea dată liberă: " + urmatoareaData,
                        "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Datele introduse sunt invalide!", "Eroare", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private double calculatePret(int idCamera, LocalDate checkInDate, LocalDate checkOutDate) {
        long zile = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        double pretPeNoapte = rezervareModel.getPretCamera(idCamera);
        return zile * pretPeNoapte;
    }
}
