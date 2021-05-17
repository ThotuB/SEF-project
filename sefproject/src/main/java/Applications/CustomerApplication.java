package Applications;

import Controllers.CustomerController;
import Databases.CustomerDTB;
import Databases.UserDTB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerApplication extends Application {

    CustomerDTB customerDTB;

    @Override
    public void init() throws Exception {
        super.init();
        customerDTB = new CustomerDTB("src/main/resources/Databases/CustomersDTB.json", "Alin");

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Stages/customer_main.fxml"));

        Parent root = loader.load();

        CustomerController controller = loader.getController();
        controller.setup("Alin");

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Customer");

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
