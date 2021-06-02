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
import service.ServiceException;

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

        if (username.equals("") || password.equals("") || job == null) {
            MessageAlert.showErrorMessage(null, "Enter username and password!");
            return;
        }

        try {
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
                        service.setObserverSef(sef.getId(), sefController);
                        sefController.setPage(service, sef, sefStage);

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
                        service.setObserverAngajat(angajat.getId(), angajatController);
                        angajatController.setPage(service, angajat, angajatStage);

                        angajatStage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    dialogStage.hide();
                } else {
                    MessageAlert.showErrorMessage(null, "Authentication error!");
                }
            }
        }
        catch (ServiceException ex) {
            MessageAlert.showErrorMessage(null, ex.getMessage());
        }
    }

}
