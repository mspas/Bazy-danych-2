package sample;

public class RestaurantTable {
    int id;
    String offer;
    String kitchen;
    double price;


    public RestaurantTable(int id, String offer, String kitchen, double price) {
        this.id = id;
        this.offer = offer;
        this.kitchen = kitchen;
        this.price = price;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getKitchen() {
        return kitchen;
    }

    public int getID() {
        return id;
    }

    public void setKitchen(String kitchen) {
        this.kitchen = kitchen;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
