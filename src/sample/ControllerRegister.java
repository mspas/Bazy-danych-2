package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerRegister {
    @FXML
    private Label errorMessage;
    @FXML
    private ComboBox typeOfAccCombo;
    @FXML
    private TextField AdditionalText;
    @FXML
    private TextField loginText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private PasswordField password2Text;
    @FXML
    private TextField nameText;
    @FXML
    private TextField surnameText;
    @FXML
    private TextField cityText;
    @FXML
    private TextField streetText;
    @FXML
    private TextField hnoText;
    @FXML
    private TextField anoText;
    @FXML
    private TextField phoneText;
    @FXML
    public void initialize() {
        typeOfAccCombo.getItems().removeAll(typeOfAccCombo.getItems());
        typeOfAccCombo.getItems().addAll("Klient", "Dostawca", "Restauracja");
        typeOfAccCombo.getSelectionModel().select("Klient");
        AdditionalText.setVisible(true);
    }

    private int accountType;
    private boolean errorType;

    public void setErrorLabel(boolean check) {
        errorMessage.setVisible(check);
    }

    public void ComboClicked(ActionEvent event) throws IOException {
        if(typeOfAccCombo.getValue() != null) {
            if(typeOfAccCombo.getValue() == "Klient"){
                AdditionalText.setVisible(true);
                AdditionalText.setPromptText("Nazwa dzielnicy");
                nameText.setVisible(true);
                surnameText.setVisible(true);
                surnameText.setPromptText("Nazwisko");
                streetText.setVisible(true);
                cityText.setVisible(true);
                hnoText.setVisible(true);
                anoText.setVisible(true);
                phoneText.setVisible(true);
                accountType = 1;
            }
            else if (typeOfAccCombo.getValue() == "Dostawca"){
                AdditionalText.setVisible(true);
                AdditionalText.setPromptText("Nazwa dzielnicy");
                nameText.setVisible(true);
                surnameText.setVisible(true);
                surnameText.setPromptText("Nazwisko");
                streetText.setVisible(false);
                cityText.setVisible(false);
                hnoText.setVisible(false);
                anoText.setVisible(false);
                phoneText.setVisible(true);
                accountType = 2;
            }
            else if (typeOfAccCombo.getValue() == "Restauracja"){
                AdditionalText.setVisible(true);
                AdditionalText.setPromptText("Nazwa restauracji");
                nameText.setVisible(false);
                surnameText.setVisible(true);
                surnameText.setPromptText("Nazwa dzielnicy");
                streetText.setVisible(true);
                cityText.setVisible(true);
                hnoText.setVisible(true);
                anoText.setVisible(true);
                phoneText.setVisible(true);
                accountType = 3;
            }

        }
    }

    public void GoToLoginButton(ActionEvent event) throws IOException {
        Parent gotoLoginScene = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene loginScene = new Scene (gotoLoginScene);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(loginScene);
        app_stage.show();
    }

    public boolean rejestracja(String name, String surname, String city, String street, String hno, String ano, String password, String password2, String phone, String additional, String login,String rodzaj){
        if (rodzaj == "Klient"){
            if (name.isEmpty() || surname.isEmpty() || city.isEmpty() || street.isEmpty()
                    || hno.isEmpty() || ano.isEmpty() || password.isEmpty() || password2.isEmpty() || phone.isEmpty()
                    || additional.isEmpty() || login.isEmpty() || !phone.matches("[0-9]*") || !password.equals(password2)|| !hno.matches("[0-9]*")|| !ano.matches("[0-9]*")) {
                return false;
            }
            return true;
        }


        if (rodzaj == "Dostawca"){
            if (name.isEmpty() || surname.isEmpty()  || password.isEmpty() || password2.isEmpty() || phone.isEmpty()
                    || additional.isEmpty() || login.isEmpty() || !phone.matches("[0-9]*") || !password.equals(password2)){
                return false;
            }
            return true;

        }
        if (rodzaj == "Restauracja") {
            if (city.isEmpty() || street.isEmpty() || hno.isEmpty() || ano.isEmpty() || password.isEmpty() || password2.isEmpty() || phone.isEmpty()
                    || additional.isEmpty() || login.isEmpty() || !phone.matches("[0-9]*") || !password.equals(password2)|| !hno.matches("[0-9]*")|| !ano.matches("[0-9]*")) {
                return false;
            }
            return true;

        }

        return false;
    }

    public void register(ActionEvent event) throws IOException {
        String login = loginText.getText();
        String haslo = passwordText.getText();
        String haslo2 = password2Text.getText();
        String imie = nameText.getText();
        String nazwisko = surnameText.getText();
        String telefon = phoneText.getText();
        String miasto = cityText.getText();
        String ulica = streetText.getText();
        String nrdomu = hnoText.getText();
        String nrmieszk = anoText.getText();
        String nazwaFirmyOrDzielnicy = AdditionalText.getText();
        String name = nameText.getText();
        String surname =  surnameText.getText();
        String city = cityText.getText();
        String street =  streetText.getText();
        String hno = hnoText.getText();
        String ano = anoText.getText();
        String password = passwordText.getText();
        String password2 =password2Text.getText();
        String phone = phoneText.getText();
        String additional = AdditionalText.getText();
        String login1 = loginText.getText();
        String rodzaj = typeOfAccCombo.getValue().toString();
        boolean wynik = rejestracja(name, surname, city, street, hno, ano, password, password2,phone,additional,login1,rodzaj);
        if (wynik){
            DBConnect connect = new DBConnect();
            connect.register(accountType, login, haslo, miasto, ulica, nrdomu, nrmieszk, nazwaFirmyOrDzielnicy, imie, nazwisko, telefon);
            Parent gotoLoginScene = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene loginScene = new Scene (gotoLoginScene);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(loginScene);
            app_stage.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Komunikat o błędzie");
            alert.setHeaderText(null);
            alert.setContentText("Błąd!");
            alert.showAndWait();
        }
    }
}
