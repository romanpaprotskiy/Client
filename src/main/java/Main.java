import Database.*;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Layouts/MainLayout.fxml"));
        primaryStage.setTitle("Main");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        try(Socket socket = new Socket("127.0.0.1",4000)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //writer.write("selectAll");
            ArrayList<Custom> list = new ArrayList<>();
            list = (ArrayList<Custom>) objectInputStream.readObject();
            System.out.println(list);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
