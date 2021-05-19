package controller;

import domain.Angajat;
import domain.Sarcina;
import domain.Sef;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.AllServices;

public class SefController {
    private AllServices service;
    private Sef sef;

    private final ObservableList<Angajat> model_tableAngajati = FXCollections.observableArrayList();
    private final ObservableList<Sarcina> model_tableSarcini = FXCollections.observableArrayList();

    @FXML
    private TableView<Angajat> tableViewEmployees;
    @FXML
    private TableColumn<Angajat, String> tableColumnName;
    @FXML
    private TableColumn<Angajat, String> tableColumnUsername;
    @FXML
    private TableColumn<Angajat, String> tableColumnPassword;
    @FXML
    private TableColumn<Angajat, String> tableColumnHour;

    @FXML
    private TableView<Sarcina> tableViewRequests;
    @FXML
    private TableColumn<Sarcina, String> tableColumnDescription;
    @FXML
    private TableColumn<Sarcina, String> tableColumnStatus;

    public void setPage(AllServices service, Sef sef) {
        this.service = service;
        this.sef = sef;
        initModel();
    }

    @FXML
    public void initialize() {
        tableColumnName.setCellValueFactory(new PropertyValueFactory<Angajat, String>("Nume"));
        tableColumnUsername.setCellValueFactory(new PropertyValueFactory<Angajat, String>("Nume_de_utilizator"));
        tableColumnPassword.setCellValueFactory(new PropertyValueFactory<Angajat, String>("Parola"));
        tableColumnHour.setCellValueFactory(new PropertyValueFactory<Angajat, String>("Ora_conectare"));
        tableViewEmployees.setItems(model_tableAngajati);

        tableColumnDescription.setCellValueFactory(new PropertyValueFactory<Sarcina, String>("Descriere"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<Sarcina, String>("Status"));
        tableViewRequests.setItems(model_tableSarcini);
    }

    public void initModel() {
        model_tableAngajati.setAll(service.getEmployeesOnDuty());
    }

    public void handleClickAngajat() {
        Angajat selected = tableViewEmployees.getSelectionModel().getSelectedItem();
        if (selected != null) {
            model_tableSarcini.setAll(service.getTasksForEmployee(selected.getId()));
        }
        else {
            MessageAlert.showErrorMessage(null, "Select an employee!");
        }
    }

    public void handleSendRequest(){}

    public void handleAddAccount(){}

    public void handleLogout(){}

}
