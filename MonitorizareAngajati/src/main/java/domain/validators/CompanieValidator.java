package domain.validators;

import domain.Companie;

public class CompanieValidator implements Validator<Companie> {
    @Override
    public void validate(Companie entity) throws ValidationException {
        String error_message = "";
        if (entity.getNume() == null || "".equals(entity.getNume())) {
            error_message += "Eroare de validare la nume!\n";
        }

        if (!error_message.equals("")) {
            throw new ValidationException(error_message);
        }
    }
}
