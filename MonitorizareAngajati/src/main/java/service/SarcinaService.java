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
        return sarcinaRepository.filterByEmployee(employee_id);
    }

}
