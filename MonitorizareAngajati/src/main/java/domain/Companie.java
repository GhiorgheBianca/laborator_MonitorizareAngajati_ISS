package domain;

import java.util.Objects;

public class Companie {
    private String nume;

    public Companie(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Companie companie = (Companie) o;
        return Objects.equals(nume, companie.nume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume);
    }

    @Override
    public String toString() {
        return "Companie{" +
                "nume='" + nume + '\'' +
                '}';
    }

}
