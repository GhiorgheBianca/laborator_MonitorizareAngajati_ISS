package service;

import domain.Angajat;
import domain.Sarcina;
import domain.Sef;
import utils.IObserver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AllServices {
    private final AngajatService angajatService;
    private final SarcinaService sarcinaService;
    private final SefService sefService;

    private final Map<Long, IObserver> loggedAngajati;
    private final Map<Long, IObserver> loggedSefi;

    public AllServices(AngajatService angajatService, SarcinaService sarcinaService, SefService sefService) {
        this.angajatService = angajatService;
        this.sarcinaService = sarcinaService;
        this.sefService = sefService;
        this.loggedAngajati = new ConcurrentHashMap<>();
        this.loggedSefi = new ConcurrentHashMap<>();
    }

    public void setObserverAngajat(Long id, IObserver client) {
        loggedAngajati.put(id, client);
    }

    public void setObserverSef(Long id, IObserver client) {
        loggedSefi.put(id, client);
    }

    public Angajat loginAngajat(String username, String password){
        Angajat res = angajatService.login(username, password);
        if (res != null) {
            if (loggedAngajati.get(res.getId()) != null) {
                throw new ServiceException("Employee already logged in!");
            }
        } else {
            throw new ServiceException("Authentication failed!");
        }
        return res;
    }

    public Sef loginSef(String username, String password){
        Sef res = sefService.login(username, password);
        if (res != null) {
            if (loggedSefi.get(res.getId()) != null) {
                throw new ServiceException("Manager already logged in!");
            }
        } else {
            throw new ServiceException("Authentication failed!");
        }
        return res;
    }

    public List<Angajat> getEmployeesOnDuty(){
        return angajatService.filterByPresence();
    }

    public List<Sarcina> getTasksForEmployee(Long employee_id){
        return sarcinaService.filterByAngajat(employee_id);
    }

    public Sarcina sendRequest(Sarcina sarcina) {
        Sarcina result = sarcinaService.saveRequest(sarcina);
        notifyClients();
        return result;
    }

    public Sarcina updateRequest(Sarcina sarcina) {
        Sarcina result = sarcinaService.updateRequest(sarcina);
        notifyClients();
        return result;
    }

    public Angajat addNewEmployee(Angajat angajat) {
        return angajatService.addNewEmployee(angajat);
    }

    public Angajat changeHoursForEmployee(Angajat angajat) {
        Angajat result = angajatService.changeHoursForEmployee(angajat);
        notifyClients();
        return result;
    }

    public void logoutAngajat(Angajat angajat) {
        IObserver localClients = loggedAngajati.remove(angajat.getId());
        if (localClients == null) {
            throw new ServiceException("User " + angajat.getNume() + " is not logged in!");
        }
    }

    public void logoutSef(Sef sef) {
        IObserver localClients = loggedSefi.remove(sef.getId());
        if (localClients == null) {
            throw new ServiceException("User " + sef.getNume() + " is not logged in!");
        }
    }

    public void notifyClients() {
        for (IObserver angajat : loggedAngajati.values()) {
            angajat.update();
        }
        for (IObserver sef : loggedSefi.values()) {
            sef.update();
        }
    }
}
