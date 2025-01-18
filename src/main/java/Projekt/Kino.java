package Projekt;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Kino extends JFrame {

    private JFrame frame;
    private JPanel jppanel;
    private JLabel lbl_ueberschrift;
    private JLabel lbl_gesamtpreis;
    private JLabel lbl_getränke;
    private JLabel lbl_snacks;
    private JLabel lbl_kino;
    private JComboBox<String> cb_ausgabekino;
    private JComboBox<String> cb_ausgabesnacks;
    private JComboBox<String> cb_ausgabeGetränke;
    private JTextArea ta_ausgabespeichern;
    private JTextArea ta_gesamtpreis;
    private JButton bt_speicher;
    private JButton bt_bestellen;
    private JButton bt_loeschen;

    private List<KinoBestellung> bestellungen;

    //Array List Erstellung
    public Kino() {
        bestellungen = new ArrayList<>(); // Initialisiere die Liste der Bestellungen

        // 3 init Objekte hinzufügen
        bestellungen.add(new KinoBestellung("Moana 2 (9.50 €)", "Popcorn süß (4.50 €)", "Cola (4.50 €)", berechnePreis("Popcorn süß (4.50 €)", "Cola (4.50 €)", "Moana 2 (9.50 €)")));
        bestellungen.add(new KinoBestellung("Wicked (9.50 €)", "Nachos mit Käsedip (5.00 €)", "Fanta (4.50 €)", berechnePreis("Nachos mit Käsedip (5.00 €)", "Fanta (4.50 €)", "Wicked (9.50 €)")));
        bestellungen.add(new KinoBestellung("Terrifier 3 (9.50 €)", "Popcorn salzig (3.00 €)", "Wasser (3.50 €)", berechnePreis("Popcorn salzig (3.00 €)", "Wasser (3.50 €)", "Terrifier 3 (9.50 €)")));

        // Zur manuellen GUI Veränderung
        initializeGUI();


        // Initiale Bestellungen anzeigen
        anzeigenBestellungen();

        addEventListeners();
    }

    private void initializeGUI() {
        // Hier wurde zur Inspiration ChatGPT genutzt (
        setTitle("CineRadar");
        setSize(1024, 768); // Setze eine feste Fenstergröße (Breite x Höhe)
        setLocationRelativeTo(null); // Positioniert das Fenster in der Bildschirmmitte
        setResizable(false); // Verhindert das Verändern der Fenstergröße
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(173, 216, 230));
        getContentPane().setLayout(null);

        getContentPane().setBackground(new Color(173, 216, 230));

        JLabel lbl_titel = new JLabel("CineRadar");
        lbl_titel.setHorizontalAlignment(SwingConstants.CENTER); // Zentriert die Überschrift
        lbl_titel.setFont(new Font("Arial", Font.BOLD, 36)); // Große Schriftart und fett
        lbl_titel.setBounds(0, 10, 1024, 50); // Die gesamte Breite nutzen
        getContentPane().add(lbl_titel);
        //bis hier wurde zur Inspiration ChatGPT genutzt

        // GUI-Komponenten (manuell erstellt)
        JLabel lbl_kino = new JLabel("Film:");
        lbl_kino.setBounds(50, 100, 80, 25);
        getContentPane().add(lbl_kino);
        //ComboBox zur Filmwahl
        cb_ausgabekino = new JComboBox<>(new String[]{
                "Bitte Film auswählen",
                "Moana 2 (9.50 €)",
                "Wicked (9.50 €)",
                "Terrifier 3 (9.50 €)",
                "Joker 2 (9.50 €)"
        });
        cb_ausgabekino.setBounds(150, 100, 200, 25);
        getContentPane().add(cb_ausgabekino);

        JLabel lbl_snacks = new JLabel("Snacks:");
        lbl_snacks.setBounds(50, 150, 80, 25);
        getContentPane().add(lbl_snacks);
        //ComboBox zur Snackwahl
        cb_ausgabesnacks = new JComboBox<>(new String[]{
                "Bitte Snacks auswählen",
                "Popcorn süß (4.50 €)",
                "Nachos mit Käsedip (5.00 €)",
                "Popcorn salzig (3.00 €)"
        });
        cb_ausgabesnacks.setBounds(150, 150, 200, 25);
        getContentPane().add(cb_ausgabesnacks);

        JLabel lbl_getränke = new JLabel("Getränke:");
        lbl_getränke.setBounds(50, 200, 80, 25);
        getContentPane().add(lbl_getränke);
        //ComboBox zur Getränkewahl
        cb_ausgabeGetränke = new JComboBox<>(new String[]{
                "Bitte Getränk auswählen",
                "Cola (4.50 €)",
                "Sprite (4.50 €)",
                "Fanta (4.50 €)",
                "Wasser (3.50 €)"
        });
        cb_ausgabeGetränke.setBounds(150, 200, 200, 25);
        getContentPane().add(cb_ausgabeGetränke);
        // Button deklaration
        bt_speicher = new JButton("Speichern");
        bt_speicher.setBounds(50, 250, 100, 30);
        getContentPane().add(bt_speicher);

        bt_bestellen = new JButton("Bestellen");
        bt_bestellen.setBounds(170, 250, 100, 30);
        getContentPane().add(bt_bestellen);

        bt_loeschen = new JButton("Löschen");
        bt_loeschen.setBounds(290, 250, 100, 30);
        getContentPane().add(bt_loeschen);

        ta_ausgabespeichern = new JTextArea();
        ta_ausgabespeichern.setBounds(400, 80, 600, 400);
        ta_ausgabespeichern.setEditable(false);
        getContentPane().add(ta_ausgabespeichern);

        JLabel lbl_gesamtpreis = new JLabel("Gesamtpreis:");
        lbl_gesamtpreis.setBounds(400, 500, 100, 25);
        getContentPane().add(lbl_gesamtpreis);

        ta_gesamtpreis = new JTextArea();
        ta_gesamtpreis.setBounds(520, 500, 150, 25);
        ta_gesamtpreis.setEditable(false);
        getContentPane().add(ta_gesamtpreis);
    }
    //Button Listener
    private void addEventListeners() {
        bt_speicher.addActionListener(e -> speichern());
        bt_bestellen.addActionListener(e -> anzeigenBestellungen());
        bt_loeschen.addActionListener(e -> loeschen());
    }

    //Fehlermeldung
    private void speichern() {
        try {
            String film = (String) cb_ausgabekino.getSelectedItem();
            String snack = (String) cb_ausgabesnacks.getSelectedItem();
            String getränk = (String) cb_ausgabeGetränke.getSelectedItem();

            if ("Bitte Film auswählen".equals(film) ||
                    "Bitte Snacks auswählen".equals(snack) ||
                    "Bitte Getränk auswählen".equals(getränk)) {
                JOptionPane.showMessageDialog(this, "Bitte alle Felder korrekt ausfüllen!", "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double preis = berechnePreis(snack, getränk, film);
            KinoBestellung bestellung = new KinoBestellung(film, snack, getränk, preis);
            bestellungen.add(bestellung);

            JOptionPane.showMessageDialog(this, "Bestellung gespeichert!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void anzeigenBestellungen() {
        if (bestellungen.isEmpty()) {
            ta_ausgabespeichern.setText("Keine Bestellungen vorhanden.");
            ta_gesamtpreis.setText("0,00 €");
            return;
        }

        StringBuilder ausgabe = new StringBuilder();
        double gesamtpreis = 0.0;

        for (KinoBestellung bestellung : bestellungen) {
            ausgabe.append(bestellung.toString()).append("\n");
            gesamtpreis += bestellung.getPreis();
        }

        ta_ausgabespeichern.setText(ausgabe.toString());
        ta_gesamtpreis.setText(String.format("%.2f €", gesamtpreis));
    }
    //Lösch Button erstellen
    private void loeschen() {
        bestellungen.clear();
        ta_ausgabespeichern.setText("");
        ta_gesamtpreis.setText("0,00 €");
        JOptionPane.showMessageDialog(this, "Alle Bestellungen wurden gelöscht!");
    }
    //Preisberechnung deklaration
    public double berechnePreis(String snack, String getränk, String film) {
        double preis = 0.0;

        if (snack.contains("4.50")) preis += 4.50;
        if (snack.contains("5.00")) preis += 5.00;
        if (snack.contains("3.00")) preis += 3.00;

        if (getränk.contains("4.50")) preis += 4.50;
        if (getränk.contains("3.50")) preis += 3.50;

        if (film.contains("9.50")) preis += 9.50;

        return preis;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Kino kino = new Kino();
            kino.setVisible(true);
        });
    }
}


