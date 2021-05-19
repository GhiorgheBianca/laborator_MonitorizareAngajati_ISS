package domain;

import java.util.Objects;

public class Sef {
    private Long id;
    private String nume;
    private String nume_de_utilizator;
    private String parola;

    public Sef() {}

    public Sef(Long id, String nume, String nume_de_utilizator, String parola) {
        this.id = id;
        this.nume = nume;
        this.nume_de_utilizator = nume_de_utilizator;
        this.parola = parola;
    }

    public Sef(String nume, String nume_de_utilizator, String parola) {
        this.id = this.generateRandomId();
        this.nume = nume;
        this.nume_de_utilizator = nume_de_utilizator;
        this.parola = parola;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sef sef = (Sef) o;
        return Objects.equals(id, sef.id) && Objects.equals(nume, sef.nume) && Objects.equals(nume_de_utilizator, sef.nume_de_utilizator) && Objects.equals(parola, sef.parola);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, nume_de_utilizator, parola);
    }

    @Override
    public String toString() {
        return "Sef{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", nume_de_utilizator='" + nume_de_utilizator + '\'' +
                ", parola='" + parola + '\'' +
                '}';
    }

}
