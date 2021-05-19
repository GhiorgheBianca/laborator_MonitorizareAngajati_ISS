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
        primaryStage.setTitle("Login");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/loginView.fxml"));
        AnchorPane myPane = loader.load();
        primaryStage.setScene(new Scene(myPane));

        LoginController ctrl = loader.getController();
        ctrl.setPage(getAllServices(), primaryStage);

        primaryStage.show();
    }

    static AllServices getAllServices() {
        ApplicationContext context = new ClassPathXmlApplicationContext("MonitorizareAngajatiApp.xml");
        return context.getBean(AllServices.class);
    }
}
