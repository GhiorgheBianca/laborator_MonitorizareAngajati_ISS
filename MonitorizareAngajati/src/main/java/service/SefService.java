package service;

import domain.Angajat;
import domain.Sef;
import repository.ISefRepository;

public class SefService {
    private final ISefRepository repositorySef;

    public SefService(ISefRepository repositorySef) {
        this.repositorySef = repositorySef;
    }

    public Sef login(String username, String password) {
        return repositorySef.login(username, password);
    }

}
