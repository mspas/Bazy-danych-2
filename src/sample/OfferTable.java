package sample;

public class OfferTable {
    String offer;
    String kitchen;
    double price;
    String restaurantName;
    int id_oferty;
    int id_firmy;

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getKitchen() {
        return kitchen;
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

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getIdFirmy() {return id_firmy;}
    public int getIdOferty() {return id_oferty;}


    public OfferTable(int id, String offer, String kitchen, double price, String restaurantName, int idf) {
        this.id_oferty = id;
        this.offer = offer;
        this.kitchen = kitchen;
        this.price = price;
        this.restaurantName = restaurantName;
        this.id_firmy = idf;
    }
}
