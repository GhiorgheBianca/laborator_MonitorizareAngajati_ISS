package domain.validators;

import domain.Sarcina;
import repository.IAngajatRepository;

public class SarcinaValidator implements Validator<Sarcina> {
    private final IAngajatRepository repoAngajat;

    public SarcinaValidator(IAngajatRepository repoAngajat) {
        this.repoAngajat = repoAngajat;
    }

    @Override
    public void validate(Sarcina entity) throws ValidationException {
        String error_message = "";
        if (entity.getId() == null || entity.getId() < 0) {
            error_message += "Eroare de validare la ID!\n";
        }
        if (entity.getDescriere() == null || "".equals(entity.getDescriere())) {
            error_message += "Eroare de validare la descriere!\n";
        }
        if (entity.getId_angajat() == null || entity.getId_angajat() < 0) {
            error_message += "Eroare de validare la ID-ul angajatului!\n";
        }

        if (repoAngajat.findOne(entity.getId_angajat()) == null) {
            error_message += "Nu exista niciun angajat cu ID-ul mentionat!\n";
        }

        if (!error_message.equals("")) {
            throw new ValidationException(error_message);
        }
    }
}
