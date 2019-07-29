package sample;

public class OrderTable {

    String nazwad;
    String nazwisko;
    String data;
    String status;
    String nazwastatus;
    String nazwaf;
    int id_oferty;
    int id_firmy;
    int id_zam;
    int id_klient;
    int id_dost;


    public String getNazwaf() {
        return nazwaf;
    }

    public void setNazwaf(String nazwaf) {
        this.nazwaf = nazwaf;
    }

    public String getNazwastatus() {        return nazwastatus;    }

    public void setNazwastatus(String nazwastatus) {        this.nazwastatus = nazwastatus;    }

    public String getNazwisko() {        return nazwisko;    }

    public void setNazwisko(String nazwisko) {        this.nazwisko = nazwisko;    }
    public String getNazwad() {      return nazwad;   }

    public void setNazwad(String nazwad) {        this.nazwad = nazwad;    }

    public String getData() {        return data;    }

    public void setData(String data) {        this.data = data;    }

    public String getStatus() {        return status;    }

    public void setStatus(String status) {        this.status = status;    }

    public int getId_oferty() {        return id_oferty;    }

    public void setId_oferty(int id_oferty) {        this.id_oferty = id_oferty;    }

    public int getId_firmy() {        return id_firmy;    }

    public void setId_firmy(int id_firmy) {        this.id_firmy = id_firmy;    }

    public int getId_zam() {        return id_zam;    }

    public void setId_zam(int id_zam) {        this.id_zam = id_zam;    }

    public int getId_klient() {        return id_klient;    }

    public void setId_klient(int id_klient) {        this.id_klient = id_klient;    }

    public int getId_dost() {        return id_dost;    }

    public void setId_dost(int id_dost) {        this.id_dost = id_dost;    }

    public OrderTable(int idz, int idof, int iddost, int idk, int id_rest, String nazwa, String data, String status, String nazwisko, String nazwaf) {
        this.id_oferty = idof;
        this.status = status;
        this.id_dost = iddost;
        this.id_zam = idz;
        this.id_klient = idk;
        this.id_firmy = id_rest;
        this.data = data;
        this.nazwad = nazwa;
        this.nazwisko = nazwisko;
        this.nazwaf = nazwaf;
        this.nazwastatus = status + " - " + nazwa;
    }
}
