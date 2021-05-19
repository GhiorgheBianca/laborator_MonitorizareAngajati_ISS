package domain;

import java.util.Objects;

public class Sarcina {
    private Long id;
    private String descriere;
    private String status;
    private Long id_angajat;

    public Sarcina() {}

    public Sarcina(Long id, String descriere, String status, Long id_angajat) {
        this.id = id;
        this.descriere = descriere;
        this.status = status;
        this.id_angajat = id_angajat;
    }

    public Sarcina(String descriere, String status, Long id_angajat) {
        this.descriere = descriere;
        this.status = status;
        this.id_angajat = id_angajat;
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

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId_angajat() {
        return id_angajat;
    }

    public void setId_angajat(Long id_angajat) {
        this.id_angajat = id_angajat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sarcina sarcina = (Sarcina) o;
        return Objects.equals(id, sarcina.id) && Objects.equals(descriere, sarcina.descriere) && status == sarcina.status && Objects.equals(id_angajat, sarcina.id_angajat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descriere, status, id_angajat);
    }

    @Override
    public String toString() {
        return "Sarcina{" +
                "id=" + id +
                ", descriere='" + descriere + '\'' +
                ", status=" + status +
                ", id_angajat=" + id_angajat +
                '}';
    }

}
