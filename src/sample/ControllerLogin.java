package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerLogin {
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Label errorMessage;
    //@FXML
    //private PasswordField errorText;
    private boolean iffirstTacz = false;
    private boolean setError = false;

    @FXML
    public void initialize() {
        //  errorMessage.setVisible(false);
        /*if (iffirstTacz) {
            errorMessage.setVisible(false);
        }
        else if (!setError) {
            errorMessage.setVisible(false);
        }
        else {
            errorMessage.setVisible(true);
        }*/
    }

    public void setErrorLabel(boolean check, boolean firstTacz) {
        //errorMessage.setVisible(check);
        //setError = check;
        //iffirstTacz = firstTacz;
    }
    //logowanie
    public void pressButton2(ActionEvent event) throws IOException {

        if(usernameText.getText().isEmpty() || passwordText.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Komunikat o błędzie");
            alert.setHeaderText(null);
            alert.setContentText("Błąd!");

            alert.showAndWait();
        }else{

        String login = usernameText.getText();
        String haslo = passwordText.getText();
        DBConnect connect = new DBConnect();
        int nextWindow = 0;
        int id_konta;
        nextWindow = connect.login(login, haslo);
        // Parent gotoRegisterScene = FXMLLoader.load(getClass().getResource("Login.fxml"));
        if (nextWindow == 1) {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Parent gotoRegisterScene = (Parent) loader.load();
            ControllerMain mainController = loader.getController();
            System.out.println(connect.getActiveID());
            mainController.getIDRest(connect.getActiveID());
            Scene registerScene = new Scene(gotoRegisterScene);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(registerScene);
            app_stage.show();

            /*Parent gotoRegisterScene = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/sample/second_view.fxml"));
            Scene registerScene = new Scene(gotoRegisterScene);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(registerScene);
            app_stage.show();*/
        }
        if (nextWindow == 2) {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("MainWindowDeliverer.fxml"));
            Parent gotoRegisterScene = (Parent) loader.load();
            ControllerDeliverer delController = loader.getController();
            System.out.println(connect.getActiveID());
            delController.getIDRest(connect.getActiveID());
            Scene registerScene = new Scene(gotoRegisterScene);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(registerScene);
            app_stage.show();

            /*Parent gotoRegisterScene = FXMLLoader.load(getClass().getResource("MainWindowDeliverer.fxml"));
            Scene registerScene = new Scene(gotoRegisterScene);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(registerScene);
            app_stage.show();*/
        }
        if (nextWindow == 3) {
            //Parent gotoRegisterScene = FXMLLoader.load(getClass().getResource("MainWindowRest.fxml"));
            FXMLLoader loader=new FXMLLoader(getClass().getResource("MainWindowRest.fxml"));
            Parent gotoRegisterScene = (Parent) loader.load();
            ControllerRest restController = loader.getController();
            restController.getIDRest(connect.getActiveID());
            Scene registerScene = new Scene(gotoRegisterScene);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(registerScene);
            app_stage.show();
        }
        if (nextWindow == 0) {

            if (nextWindow == 0) {
                usernameText.setText("");
                passwordText.setText("");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Komunikat o błędzie");
                alert.setHeaderText(null);
                alert.setContentText("Błędne dane do logowania!");
                alert.showAndWait();

            }
        }
    }
    }
    //zarejestruj się
    public void registerPButton(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Register.fxml"));
        Parent gotoRegisterScene = (Parent) loader.load();
        ControllerRegister regController = loader.getController();
        Scene registerScene = new Scene(gotoRegisterScene);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(registerScene);
        app_stage.show();
    }
    //Wróć

    //zaloguj
}
