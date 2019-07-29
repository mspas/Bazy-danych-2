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

public class ControllerDeliverer {
    @FXML
    private TableView<DelivererTable> tableView;
    @FXML
    private TableColumn AdresColumn;
    @FXML
    private TableColumn RestaurantColumn;
    private int id_deliv;


    ObservableList<DelivererTable> data = FXCollections.observableArrayList();
    public void initialize() {

        tableView.setStyle("-fx-control-inner-background:  #607d8b;");
    //tableView.getColumns().addAll(AdresColumn,RestaurantColumn);
    }
    public void getIDRest(int id) {
        id_deliv = id;
        System.out.println(id);
    }
    //doręczono
    public void DoreczonoPButton(){
        if (tableView.getSelectionModel().getSelectedItem() != null){
            DelivererTable table = tableView.getSelectionModel().getSelectedItem();
            System.out.println(table.getDeliveryAdress());
            System.out.println(table.getRestaurant());
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Komunikat o błędzie");
            alert.setHeaderText(null);
            alert.setContentText("Błąd!");

            alert.showAndWait();
        }
    }
    public void pobierzDane(ActionEvent event) throws IOException {
        DBConnect connect = new DBConnect();
        data = connect.getAllDeliverer(id_deliv);
        AdresColumn.setCellValueFactory(
                //new PropertyValueFactory("deliveryAdress")
                new PropertyValueFactory<DelivererTable,String>("deliveryAdress")
        );
        RestaurantColumn.setCellValueFactory(
                new PropertyValueFactory<DelivererTable,String>("restaurant")
        );
        tableView.getItems().removeAll(tableView.getItems());
        tableView.getItems().setAll(this.data);
    }
    public void registerPButton(ActionEvent event) throws IOException{
        Parent gotoDelivererScene = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene loginScene = new Scene (gotoDelivererScene);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(loginScene);
        app_stage.show();
    }

    public void DoreczonoPButton(ActionEvent event) throws IOException {
        DelivererTable table = tableView.getSelectionModel().getSelectedItem();

        DBConnect connect = new DBConnect();
        connect.updateStatus(table.getID());
    }
}
