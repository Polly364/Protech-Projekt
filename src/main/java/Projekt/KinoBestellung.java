package Projekt;

public class KinoBestellung  {
    private String film;
    private String snack;
    private String getränk;
    private double preis;

    public KinoBestellung(String film, String snack, String getränk, double preis) {
        this.film = film;
        this.snack = snack;
        this.getränk = getränk;
        this.preis = preis;
    }

    public double getPreis() {
        return preis;
    }

    @Override
    public String toString() {
        return "Film: " + film + "\n" +
                "Snack: " + snack + "\n" +
                "Getränk: " + getränk + "\n";
    }
}


