package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        //Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = (Parent) loader.load();
        ControllerLogin loginController = loader.getController();
        loginController.setErrorLabel(false, true);
        primaryStage.setTitle("Projekt bazy danych");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {

        DBConnect connect = new DBConnect();
        launch(args);

    }
}
