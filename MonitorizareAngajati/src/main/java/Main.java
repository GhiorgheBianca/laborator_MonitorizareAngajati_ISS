import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AllServices;

public class Main extends Application {
    public void start(Stage primaryStage) throws Exception {
        AllServices service = getAllServices();

        primaryStage.setTitle("Login Page");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/loginView.fxml"));
        AnchorPane myPane = loader.load();
        primaryStage.setScene(new Scene(myPane));

        LoginController ctrl = loader.getController();
        ctrl.setPage(service, primaryStage);

        primaryStage.show();


        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Login Page");

        FXMLLoader second_loader = new FXMLLoader();
        second_loader.setLocation(getClass().getResource("/views/loginView.fxml"));
        AnchorPane second_myPane = second_loader.load();
        secondaryStage.setScene(new Scene(second_myPane));

        LoginController second_ctrl = second_loader.getController();
        second_ctrl.setPage(service, secondaryStage);

        secondaryStage.show();
    }

    static AllServices getAllServices() {
        ApplicationContext context = new ClassPathXmlApplicationContext("MonitorizareAngajatiApp.xml");
        return context.getBean(AllServices.class);
    }
}
