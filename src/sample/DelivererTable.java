package sample;

public class DelivererTable {
    int id;
    String deliveryAdress;
    String restaurant;

    public DelivererTable(int id, String deliveryAdress, String restaurant) {
        this.id = id;
        this.deliveryAdress = deliveryAdress;
        this.restaurant = restaurant;
    }

    public String getDeliveryAdress() {
        return deliveryAdress;
    }

    public void setDeliveryAdress(String deliveryAdress) {
        this.deliveryAdress = deliveryAdress;
    }

    public String getRestaurant() {
        return restaurant;
    }
    public int getID() {
        return id;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }
}
