package service;

import domain.Angajat;
import repository.IAngajatRepository;

import java.util.List;

public class AngajatService {
    private final IAngajatRepository repositoryAngajat;

    public AngajatService(IAngajatRepository repositoryAngajat) {
        this.repositoryAngajat = repositoryAngajat;
    }

    public Angajat login(String username, String password) {
        return repositoryAngajat.login(username, password);
    }

    public List<Angajat> filterByPresence(){
        return repositoryAngajat.filterByPresence();
    }

}
