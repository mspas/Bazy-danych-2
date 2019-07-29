package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class ControllerMain{
    //ObservableList<OfferTable> data = FXCollections.observableArrayList();

    @FXML
    private TableView tableView;
    @FXML
    private TableColumn offerColumn;
    @FXML
    private TableColumn kitchenColumn;
    @FXML
    private TableColumn priceColumn;
    @FXML
    private TableColumn restaurantNameColumn;
    private int id_client;
    @FXML
    private ComboBox RodzajKuchniCombo;
    @FXML
    private ComboBox choice;
    @FXML
    private TextField NazwaDaniaText;
    @FXML
    private TextField MinText;
    @FXML
    private TextField MaxText;

    @FXML
    public void initialize() {
        RodzajKuchniCombo.getItems().removeAll(RodzajKuchniCombo.getItems());
        RodzajKuchniCombo.getItems().addAll("Dowolna", "Polska", "Włoska", "Meksykańska", "Turecka", "Chińska", "Francuska", "Wietnamska");
        RodzajKuchniCombo.getSelectionModel().select("Dowolna");

        choice.getItems().removeAll(choice.getItems());
        choice.getItems().addAll("Przeglądaj oferty", "Twoje zamówienia");
        choice.getSelectionModel().select("Przeglądaj oferty");



        tableView.setStyle("-fx-control-inner-background:  #607d8b;");
        //ListView
    }
    public void getIDRest(int id) {
        id_client = id;
    }
    // zaloguj się
    public void GoToLoginButton(ActionEvent event) throws IOException{
        Parent gotoLoginScene = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene loginScene = new Scene (gotoLoginScene);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(loginScene);
        app_stage.show();
    }
    public void showData(ActionEvent event) throws IOException {
        if (!choice.getValue().toString().equals("Przeglądaj oferty")) {
            ObservableList<OrderTable> data = FXCollections.observableArrayList();
            DBConnect connect = new DBConnect();
            data = connect.getMyOrders(id_client);
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
            restaurantNameColumn.setCellValueFactory(
                    new PropertyValueFactory<OfferTable,String>("nazwaf")
            );
            kitchenColumn.setText("Dostawca");
            priceColumn.setText("Data");
            tableView.getItems().removeAll(tableView.getItems());
            tableView.getItems().setAll(data);
        }
    }
    public boolean filtrowanie(String min, String max){
        if (min.matches("[0-9]*")&& max.matches("[0-9]*")){
            return true;
        }
        return false;
    }
    // Filtruj przycisk
    public void FilterPButton(ActionEvent event) throws IOException {
        ObservableList<OfferTable> data = FXCollections.observableArrayList();
        tableView.getItems().removeAll(tableView.getItems());
        String text = MinText.getText();
        //text.matches("[0-9]*");
        String text2 = MaxText.getText();
        boolean wynik = filtrowanie(text, text2);
        //text2.matches("[0-9]*");
        //if (text.matches("[0-9]*")&& text2.matches("[0-9]*")){
        if(wynik){
            String nazwa = NazwaDaniaText.getText();
            double min, max;
            String kuchnia = RodzajKuchniCombo.getValue().toString();
            String smin = MinText.getText();
            if (smin.equals("")) min = 0;
            else min = Double.parseDouble(smin.replaceAll(" ","."));
            String smax = MaxText.getText();
            if (smax.equals("")) max = 0;
            else max = Double.parseDouble(smax.replaceAll(" ","."));

            DBConnect connect = new DBConnect();
            data = connect.search(nazwa, kuchnia, min, max);

            //data = connect.getAllOffers();

            offerColumn.setCellValueFactory(
                    //new PropertyValueFactory("deliveryAdress")
                    new PropertyValueFactory<OfferTable,String>("offer")
            );
            kitchenColumn.setCellValueFactory(
                    new PropertyValueFactory<OfferTable,String>("kitchen")
            );
            priceColumn.setCellValueFactory(
                    //new PropertyValueFactory("deliveryAdress")
                    new PropertyValueFactory<OfferTable,String>("price")
            );
            restaurantNameColumn.setCellValueFactory(
                    new PropertyValueFactory<OfferTable,String>("restaurantName")
            );
            kitchenColumn.setText("Rodzaj kuchni");
            priceColumn.setText("Cena");
            tableView.setStyle("-fx-control-inner-background:  #607d8b;");
            //ListView
            tableView.getItems().addAll(data);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Komunikat o błędzie");
            alert.setHeaderText(null);
            alert.setContentText("Błędne dane do wyszukania");

            alert.showAndWait();
        }



    }
    // Zamówienie przycisk
    public void BuyPButton(ActionEvent event) throws IOException {
        //zmówienie
        if (tableView.getSelectionModel().getSelectedItem() != null){
            OfferTable table = (OfferTable)tableView.getSelectionModel().getSelectedItem();
            System.out.println(table.getOffer());
            System.out.println(table.getRestaurantName());

            DBConnect connect = new DBConnect();
            connect.order(id_client, table.getIdOferty(), table.getIdFirmy());
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Komunikat o błędzie");
            alert.setHeaderText(null);
            alert.setContentText("Błąd!");

            alert.showAndWait();
        }
    }

}
