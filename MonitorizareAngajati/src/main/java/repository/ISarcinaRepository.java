package repository;

import domain.Sarcina;
import domain.validators.ValidationException;

import java.util.List;

public interface ISarcinaRepository {

    /**
     *
     * @param employee_id - long, the ID on an employee
     *                   employee_id must not be null
     * @return tasks filtered by employee's ID
     * @throws IllegalArgumentException
     *            if day is null or
     *            day is not an integer number between 1 and 31
     */
    List<Sarcina> filterByEmployee(Long employee_id);

    /**
     *
     * @param entity
     *         entity must be not null
     * @return null - if the given entity is saved
     *         otherwise returns the entity (id already exists)
     * @throws ValidationException
     *            if the entity is not valid
     * @throws RepositoryException
     *            if there is already an entity with the same id
     * @throws IllegalArgumentException
     *            if the given entity is null.
     */
    Sarcina save(Sarcina entity);

    /**
     *
     * @param entity
     *          entity must not be null
     * @return null - if the entity is updated,
     *                otherwise  returns the entity  - (e.g id does not exist).
     * @throws IllegalArgumentException
     *             if the given entity is null.
     * @throws ValidationException
     *             if the entity is not valid.
     */
    Sarcina update(Sarcina entity);

}
