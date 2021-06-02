package service;

import domain.Sarcina;
import repository.ISarcinaRepository;

import java.util.List;

public class SarcinaService {

    private final ISarcinaRepository sarcinaRepository;

    public SarcinaService(ISarcinaRepository sarcinaRepository) {
        this.sarcinaRepository = sarcinaRepository;
    }

    public List<Sarcina> filterByAngajat(Long employee_id){
        return this.sarcinaRepository.filterByEmployee(employee_id);
    }

    public Sarcina saveRequest(Sarcina sarcina) {
        return this.sarcinaRepository.save(sarcina);
    }

    public Sarcina updateRequest(Sarcina sarcina) {
        return this.sarcinaRepository.update(sarcina);
    }
}
