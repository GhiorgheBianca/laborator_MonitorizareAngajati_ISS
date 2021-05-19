package domain;

import java.util.Objects;

public class Angajat {
    private Long id;
    private String nume;
    private String nume_de_utilizator;
    private String parola;
    private String ora_conectare;
    private String ora_deconectare;

    public Angajat() {}

    public Angajat(Long id, String nume, String nume_de_utilizator, String parola, String ora_conectare, String ora_deconectare) {
        this.id = id;
        this.nume = nume;
        this.nume_de_utilizator = nume_de_utilizator;
        this.parola = parola;
        this.ora_conectare = ora_conectare;
        this.ora_deconectare = ora_deconectare;
    }

    public Angajat(String nume, String nume_de_utilizator, String parola, String ora_conectare, String ora_deconectare) {
        this.id = generateRandomId();
        this.nume = nume;
        this.nume_de_utilizator = nume_de_utilizator;
        this.parola = parola;
        this.ora_conectare = ora_conectare;
        this.ora_deconectare = ora_deconectare;
    }

    private Long generateRandomId() {
        long leftLimit = 1L;
        long rightLimit = 9999999999L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNume_de_utilizator() {
        return nume_de_utilizator;
    }

    public void setNume_de_utilizator(String nume_de_utilizator) {
        this.nume_de_utilizator = nume_de_utilizator;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getOra_conectare() {
        return ora_conectare;
    }

    public void setOra_conectare(String ora_conectare) {
        this.ora_conectare = ora_conectare;
    }

    public String getOra_deconectare() {
        return ora_deconectare;
    }

    public void setOra_deconectare(String ora_deconectare) {
        this.ora_deconectare = ora_deconectare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Angajat angajat = (Angajat) o;
        return Objects.equals(id, angajat.id) && Objects.equals(nume, angajat.nume) && Objects.equals(nume_de_utilizator, angajat.nume_de_utilizator) && Objects.equals(parola, angajat.parola) && Objects.equals(ora_conectare, angajat.ora_conectare) && Objects.equals(ora_deconectare, angajat.ora_deconectare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, nume_de_utilizator, parola, ora_conectare, ora_deconectare);
    }

    @Override
    public String toString() {
        return "Angajat{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", nume_de_utilizator='" + nume_de_utilizator + '\'' +
                ", parola='" + parola + '\'' +
                ", ora_conectare=" + ora_conectare +
                ", ora_deconectare=" + ora_deconectare +
                '}';
    }

}
