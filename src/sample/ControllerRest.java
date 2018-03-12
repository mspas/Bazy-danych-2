package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerRest {

    @FXML
    private ComboBox RodzajKuchniCombo;
    @FXML
    private ComboBox choice;
    @FXML
    private TextField NazwaDaniaText;
    @FXML
    private TextField priceText;
    private int id_rest;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn offerColumn;
    @FXML
    private TableColumn kitchenColumn;
    @FXML
    private TableColumn priceColumn;

    ObservableList<RestaurantTable> data = FXCollections.observableArrayList();
    ObservableList<OrderTable> data2 = FXCollections.observableArrayList();

    public void initialize() {

        RodzajKuchniCombo.getItems().removeAll(RodzajKuchniCombo.getItems());
        RodzajKuchniCombo.getItems().addAll("Polska", "Włoska", "Meksykańska", "Turecka", "Chińska", "Francuska", "Wietnamska");


        choice.getItems().removeAll(choice.getItems());
        choice.getItems().addAll("Twoje oferty", "Zamówienia");
        choice.getSelectionModel().select("Twoje oferty");



        tableView.setStyle("-fx-control-inner-background:  #607d8b;");
    }

    public void registerPButton(){

    }

    public void getIDRest(int id) {
        id_rest = id;
    }

    public void pobierzDane(ActionEvent event) throws IOException {

        String choose = choice.getValue().toString();
        DBConnect connect = new DBConnect();

        if (choose.equals("Twoje oferty")) {
            data = connect.getAllRestaurant(id_rest);
            offerColumn.setCellValueFactory(
                    //new PropertyValueFactory("deliveryAdress")
                    new PropertyValueFactory<RestaurantTable, String>("offer")
            );
            kitchenColumn.setCellValueFactory(
                    new PropertyValueFactory<RestaurantTable, String>("kitchen")
            );
            priceColumn.setCellValueFactory(
                    //new PropertyValueFactory("deliveryAdress")
                    new PropertyValueFactory<RestaurantTable, String>("price")
            );
            tableView.getItems().removeAll(tableView.getItems());
            tableView.getItems().addAll(data);
        }
        if (choose.equals("Zamówienia")) {

            data2 = connect.getAllOrders(id_rest);
            offerColumn.setCellValueFactory(
                    //new PropertyValueFactory("deliveryAdress")
                    new PropertyValueFactory<OrderTable, String>("nazwastatus")
            );
            kitchenColumn.setCellValueFactory(
                    new PropertyValueFactory<OrderTable, String>("nazwisko")
            );
            priceColumn.setCellValueFactory(
                    //new PropertyValueFactory("deliveryAdress")
                    new PropertyValueFactory<OrderTable, String>("data")
            );
            kitchenColumn.setText("Dostawca");
            priceColumn.setText("Data");
            tableView.getItems().removeAll(tableView.getItems());
            tableView.getItems().addAll(data2);
        }

    }

    public boolean dodajDanie(String danie, String cena, String kuchnia){
        if(danie.isEmpty() || cena.isEmpty() || kuchnia.isEmpty() || (!cena.matches("[0-9]+.[0-9]+") && !cena.matches("[0-9]*")) ){
            return false;
        }
        return  true;
    }

    public void dodajPButton(ActionEvent event) throws IOException {
        String danie = NazwaDaniaText.getText();
        String cena = priceText.getText();
        String kuchnia;
        if(RodzajKuchniCombo.getValue() != null){
            kuchnia = RodzajKuchniCombo.getValue().toString();
        }else{
            kuchnia = null;
        }
        boolean wynik = dodajDanie(danie, cena , kuchnia);
        //if(NazwaDaniaText.getText().isEmpty()|| priceText.getText().isEmpty()|| RodzajKuchniCombo.getValue() == null ){
        if(!wynik) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Komunikat o błędzie");
            alert.setHeaderText(null);
            alert.setContentText("Błąd!");

            alert.showAndWait();

        }else{
            String nazwa = NazwaDaniaText.getText();
            String kuchnia1 = "";
            if(RodzajKuchniCombo.getValue() != null){
                kuchnia1 = RodzajKuchniCombo.getValue().toString();
                System.out.println(kuchnia1);
            }
            double cena1 = Double.parseDouble(priceText.getText());
            System.out.println(id_rest);

            DBConnect connect = new DBConnect();
            connect.addOffer(nazwa, kuchnia1, cena1, id_rest);
        }

    }

    public void registerPButton(ActionEvent event) throws IOException{
        Parent gotoRegisterScene = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene registerScene = new Scene (gotoRegisterScene);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(registerScene);
        app_stage.show();

    }

    public void deleteButton(ActionEvent event) throws IOException{
        if (tableView.getSelectionModel().getSelectedItem() != null){
            RestaurantTable table = (RestaurantTable) tableView.getSelectionModel().getSelectedItem();
            int id = table.getID();

            DBConnect connect = new DBConnect();
            connect.deleteOffer(id);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Komunikat o błędzie");
            alert.setHeaderText(null);
            alert.setContentText("Błąd!");

            alert.showAndWait();
        }
    }
}
