package domain.validators;

import domain.Sef;

public class SefValidator implements Validator<Sef> {
    @Override
    public void validate(Sef entity) throws ValidationException {
        String error_message = "";
        if (entity.getId() == null || entity.getId() < 0) {
            error_message += "Eroare de validare la ID!\n";
        }
        if (entity.getNume() == null || "".equals(entity.getNume())) {
            error_message += "Eroare de validare la nume!\n";
        }
        if (entity.getNume_de_utilizator() == null || "".equals(entity.getNume_de_utilizator())) {
            error_message += "Eroare de validare la numele de utilizator!\n";
        }
        if (entity.getParola() == null || "".equals(entity.getParola())) {
            error_message += "Eroare de validare la parola!\n";
        }

        if (!error_message.equals("")) {
            throw new ValidationException(error_message);
        }
    }
}
