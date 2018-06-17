import Connection.*;
import Controllers.*;
import Database.*;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public Stage primaryStage;
    private Scene MainScene,LoginScene,Option,Scene;



    public void start(Stage primaryStage) throws Exception {
        Controller.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Layouts/LoginLayout.fxml"));
        primaryStage.setTitle("Authorization");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
