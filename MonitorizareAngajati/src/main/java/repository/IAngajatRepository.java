package repository;

import domain.Angajat;
import domain.validators.ValidationException;

import java.util.List;

public interface IAngajatRepository {

    /**
     *
     * @param id -the id of the entity to be returned
     *           id must not be null
     * @return the entity with the specified id
     *          or null - if there is no entity with the given id
     * @throws IllegalArgumentException
     *                  if id is null.
     */
    Angajat findOne(Long id);

    /**
     *
     * @return active employees (disconnection date < connection date)
     */
    List<Angajat> filterByPresence();

    /**
     *
     * @param username - employee's username
     *                 username must not be null
     * @param password - employee's password
     *                 password must not be null
     * @return the entity with the specified username and password
     *          or null - if there is no entity with the given username and password
     * @throws IllegalArgumentException
     *             if username or password are null.
     */
    Angajat login(String username, String password);

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
    Angajat save(Angajat entity);

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
    Angajat update(Angajat entity);

}
