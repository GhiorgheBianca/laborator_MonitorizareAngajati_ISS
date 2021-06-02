package controller;

import domain.Angajat;
import domain.Sarcina;
import domain.Sef;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.AllServices;
import utils.IObserver;

import java.io.IOException;

public class SefController implements IObserver {
    private AllServices service;
    private Sef sef;
    public Stage dialogStage;

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

    @FXML
    private TextField textFieldDescription;

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldUsername;
    @FXML
    private TextField textFieldPassword;

    public void setPage(AllServices service, Sef sef, Stage dialogStage) {
        this.service = service;
        this.sef = sef;
        this.dialogStage = dialogStage;
        initModel();
    }

    @Override
    public void update() {
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
        Angajat selected = tableViewEmployees.getSelectionModel().getSelectedItem();
        if (selected != null) {
            model_tableSarcini.setAll(service.getTasksForEmployee(selected.getId()));
        }
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

    public void handleSendRequest() {
        String description = textFieldDescription.getText();
        Angajat employee = tableViewEmployees.getSelectionModel().getSelectedItem();
        if (employee != null && description != null && !description.equals("")) {
            Sarcina result = service.sendRequest(new Sarcina(description, "neinceputa", employee.getId()));
            if (result == null) {
                MessageAlert.showErrorMessage(null, "Couldn't send request! Exception occurred.");
            }
            else {
                model_tableSarcini.setAll(service.getTasksForEmployee(employee.getId()));
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Information", "Request sent successfully!");
            }
        }
        else {
            MessageAlert.showErrorMessage(null, "Select an employee and complete the description field!");
        }
    }

    public void handleLogout() {
        try {
            service.logoutSef(sef);
            this.goToLogin();
        }
        catch (Exception ex) {
            MessageAlert.showErrorMessage(null, "Couldn't logout: " + ex);
        }
    }

    public void goToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/loginView.fxml"));

            AnchorPane root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("Login Page");
            loginStage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(root);
            loginStage.setScene(scene);

            LoginController loginController = loader.getController();
            loginController.setPage(service, loginStage);

            dialogStage.close();
            loginStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAddAccount() {
        String name = textFieldName.getText();
        String username = textFieldUsername.getText();
        String password = textFieldPassword.getText();
        if (!name.equals("") && !username.equals("") && !password.equals("")) {
            Angajat result = service.addNewEmployee(new Angajat(name, username, password, "00:00", "00:00"));
            if (result == null) {
                MessageAlert.showErrorMessage(null, "Couldn't add employee! Exception occurred.");
            }
            else {
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Information", "Employee added successfully!");
            }
        }
        else {
            MessageAlert.showErrorMessage(null, "Complete the fields for employee (name, username, password)!");
        }
    }

}
