package service;

import domain.Angajat;
import domain.Sarcina;
import domain.Sef;

import java.util.List;

public class AllServices {
    private final AngajatService angajatService;
    private final SarcinaService sarcinaService;
    private final SefService sefService;

    public AllServices(AngajatService angajatService, SarcinaService sarcinaService, SefService sefService) {
        this.angajatService = angajatService;
        this.sarcinaService = sarcinaService;
        this.sefService = sefService;
    }

    public Angajat loginAngajat(String username, String password){
        return angajatService.login(username, password);
    }

    public Sef loginSef(String username, String password){
        return sefService.login(username, password);
    }

    public List<Angajat> getEmployeesOnDuty(){
        return angajatService.filterByPresence();
    }

    public List<Sarcina> getTasksForEmployee(Long employee_id){
        return sarcinaService.filterByAngajat(employee_id);
    }

}
