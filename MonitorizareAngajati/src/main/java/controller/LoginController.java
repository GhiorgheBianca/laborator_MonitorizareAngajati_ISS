package controller;

import domain.Angajat;
import domain.Sef;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.AllServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginController {
    @FXML
    private TextField textFieldUsername;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox<String> comboBoxPosition = new ComboBox<String>();

    private AllServices service;
    private Stage dialogStage;

    public void setPage(AllServices service, Stage dialogStage) {
        this.service = service;
        this.dialogStage = dialogStage;
    }

    @FXML
    private void initialize() {
        List<String> to_options = new ArrayList<>();
        to_options.add("angajat");
        to_options.add("sef");
        ObservableList<String> observableStatusList = FXCollections.observableArrayList(to_options);
        comboBoxPosition.setItems(observableStatusList);
    }

    @FXML
    public void handleLogin(ActionEvent actionEvent) {
        String username, password, job;
        username = textFieldUsername.getText();
        password = passwordField.getText();
        job = comboBoxPosition.getSelectionModel().getSelectedItem();

        if (username.equals("") || password.equals("")) {
            MessageAlert.showErrorMessage(null, "Enter username and password!");
            return;
        }

        if (job.equals("sef")) {
            Sef sef = service.loginSef(username, password);
            if (sef != null) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/views/managerView.fxml"));

                    AnchorPane root = loader.load();

                    Stage sefStage = new Stage();
                    sefStage.setTitle("Manager:" + sef.getNume());
                    sefStage.initModality(Modality.WINDOW_MODAL);

                    Scene scene = new Scene(root);
                    sefStage.setScene(scene);

                    SefController sefController = loader.getController();
                    sefController.setPage(service, sef);

                    sefStage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                dialogStage.hide();
            }
        } else {
            Angajat angajat = service.loginAngajat(username, password);
            if (angajat != null) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/views/employeeView.fxml"));

                    AnchorPane root = loader.load();

                    Stage angajatStage = new Stage();
                    angajatStage.setTitle("Angajat:" + angajat.getNume());
                    angajatStage.initModality(Modality.WINDOW_MODAL);

                    Scene scene = new Scene(root);
                    angajatStage.setScene(scene);

                    AngajatController angajatController = loader.getController();
                    angajatController.setPage(service, angajat);

                    angajatStage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                dialogStage.hide();
            } else {
                MessageAlert.showErrorMessage(null, "Authentication error!");
            }
        }

        /*
        try {
            if (username != null && password != null) {
                if (job.equals("angajat")) {
                    Angajat angajat = service.loginAngajat(new Angajat(username, username, password, "00:00", "00:00"));

                    Stage stage = new Stage();
                    stage.setTitle(angajat.getNume_de_utilizator() + "'s Workspace");

                    stage.show();
                    profileCtrl.setEmployee(angajat);
                    profileCtrl.setPage(stage);
                    dialogStage.close();
                } else if (job.equals("sef")) {
                    Sef sef = service.loginSef(new Sef(username, username, password));

                    Stage stage = new Stage();
                    stage.setTitle(sef.getNume_de_utilizator() + "'s Workspace");

                    stage.show();
                    profileCtrl.setEmployee(sef);
                    profileCtrl.setPage(stage);
                    dialogStage.close();
                }
            }
        }
        catch (Exception ex) {
            MessageAlert.showErrorMessage(null, "Alert Message: " + ex.getMessage());
        }
        */
    }



}
