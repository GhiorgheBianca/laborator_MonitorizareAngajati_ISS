package controller;

import domain.Angajat;
import service.AllServices;

public class AngajatController {
    public AllServices service;
    public Angajat angajat;

    public void setPage(AllServices service, Angajat angajat) {
        this.service = service;
        this.angajat = angajat;
        //init();
    }

    public void handleUpdateRequest() {}

    public void handleSetHour() {}

    public void handleLogout() {}

}
