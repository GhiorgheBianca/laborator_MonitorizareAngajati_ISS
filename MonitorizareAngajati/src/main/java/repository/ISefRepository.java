package repository;

import domain.Sef;

public interface ISefRepository {

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
    Sef login(String username, String password);

}
