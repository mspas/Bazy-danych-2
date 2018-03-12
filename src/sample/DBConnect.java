package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marcin
 */
public class DBConnect {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private int activeID;

    public DBConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?autoReconnect=true&useSSL=false", "root", "admin");
            st = con.createStatement();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

    }

    public ObservableList<OfferTable> getAllOffers() {
        ObservableList<OfferTable> lista = FXCollections.observableArrayList();
        try {
            String query = ""
                    + "SELECT oferty.firmaid_firma, oferty.id_oferta, oferty.nazwa_dania, oferty.cena, oferty.rodzaj_kuchni, firma.nazwa"
                    + " FROM oferty"
                    + " JOIN firma ON oferty.FirmaID_firma = firma.id_firma;";
            rs = st.executeQuery(query);
            while (rs.next()) {
                int idf = Integer.parseInt(rs.getString("oferty.firmaid_firma"));
                int id = Integer.parseInt(rs.getString("oferty.id_oferta"));
                String nazwad = rs.getString("oferty.nazwa_dania");
                double cena = Double.parseDouble(rs.getString("oferty.cena"));
                String rk = rs.getString("oferty.rodzaj_kuchni");
                String nazwaf = rs.getString("firma.nazwa");
                OfferTable oferta = new OfferTable(id, nazwad, rk, cena, nazwaf, idf);
                lista.add(oferta);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return lista;
    }

    public void updateStatus(int idz) {
        try {
            String query = "UPDATE zamówienie SET status = 'zakończone' WHERE id_zamowienie = " + idz + ";";
            st.executeUpdate(query);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public ObservableList<DelivererTable> getAllDeliverer(int id_dostawcy) {
        ObservableList<DelivererTable> lista = FXCollections.observableArrayList();
        try {
            String query = ""
                    + "SELECT zamówienie.id_zamowienie, zamówienie.data, adres.ulica, adres.nr_domu, adres.nr_mieszkania, adres.miasto, firma.nazwa, zamówienie.status\n" +
                    "FROM zamówienie\n" +
                    "JOIN adres ON zamówienie.AdresID_adres = adres.id_adres\n" +
                    "JOIN oferty ON zamówienie.OfertyID_oferta = oferty.id_oferta\n" +
                    "JOIN firma ON oferty.FirmaID_firma = firma.id_firma\n" +
                    "JOIN dostawca ON zamówienie.DostawcaID_dostawca = dostawca.id_dostawca\n" +
                    "WHERE zamówienie.DostawcaID_dostawca = " + id_dostawcy + ";";
            rs = st.executeQuery(query);
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("zamówienie.id_zamowienie"));
                String adres = rs.getString("zamówienie.data") + " " + rs.getString("adres.ulica") + " " + rs.getString("adres.nr_domu") + " " +
                        rs.getString("adres.nr_mieszkania") + " " + rs.getString("adres.miasto");
                String restauracja = rs.getString("firma.nazwa") + " " + rs.getString("zamówienie.status");
                DelivererTable deliver = new DelivererTable(id, adres, restauracja);
                lista.add(deliver);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return lista;
    }

    public ObservableList<RestaurantTable> getAllRestaurant(int id_rest) {
        ObservableList<RestaurantTable> lista = FXCollections.observableArrayList();
        try {
            String query = ""
                    + "SELECT oferty.id_oferta, oferty.nazwa_dania, oferty.cena, oferty.rodzaj_kuchni, firma.id_firma"
                    + " FROM oferty"
                    + " JOIN firma ON oferty.FirmaID_firma = firma.id_firma"
                    + " WHERE firma.id_firma = " + id_rest + ";";
            rs = st.executeQuery(query);
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("oferty.id_oferta"));
                String nazwa = rs.getString("oferty.nazwa_dania");
                double cena = Double.parseDouble(rs.getString("oferty.cena"));
                String rk = rs.getString("oferty.rodzaj_kuchni");
                RestaurantTable rest = new RestaurantTable(id, nazwa, rk, cena);
                lista.add(rest);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return lista;
    }
    public ObservableList<OrderTable> getMyOrders(int id_client) {
        ObservableList<OrderTable> lista = FXCollections.observableArrayList();
        try {
            String query = ""
                    + "SELECT zamówienie.id_zamowienie, oferty.nazwa_dania, zamówienie.ofertyid_oferta, zamówienie.DostawcaID_dostawca, zamówienie.KlientID_klient, zamówienie.data, zamówienie.status, dostawca.imie, dostawca.nazwisko, firma.nazwa, oferty.FirmaID_firma\n" +
                    "FROM zamówienie\n" +
                    "JOIN oferty ON oferty.id_oferta = zamówienie.ofertyid_oferta\n" +
                    "JOIN firma ON firma.id_firma = oferty.FirmaID_firma\n" +
                    "JOIN dostawca ON dostawca.id_dostawca = zamówienie.DostawcaID_dostawca\n" +
                    "WHERE zamówienie.klientid_klient = " + id_client +";";
            rs = st.executeQuery(query);
            while (rs.next()) {
                int idof = Integer.parseInt(rs.getString("zamówienie.ofertyid_oferta"));
                int idz = Integer.parseInt(rs.getString("zamówienie.id_zamowienie"));
                int iddost = Integer.parseInt(rs.getString("zamówienie.DostawcaID_dostawca"));
                int id_rest = Integer.parseInt(rs.getString("oferty.FirmaID_firma"));
                String nazwa = rs.getString("oferty.nazwa_dania");
                String nazwisko = rs.getString("dostawca.imie") + " " + rs.getString("dostawca.nazwisko");
                String data = rs.getString("zamówienie.data");
                String status = rs.getString("zamówienie.status");
                String nazwaf = rs.getString("firma.nazwa");
                OrderTable order = new OrderTable(idz, idof, iddost, id_client, id_rest, nazwa, data, status, nazwisko, nazwaf);
                lista.add(order);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return lista;
    }

    public ObservableList<OrderTable> getAllOrders(int id_rest) {
        ObservableList<OrderTable> lista = FXCollections.observableArrayList();
        try {
            String query = ""
                    + "SELECT zamówienie.id_zamowienie, oferty.nazwa_dania, zamówienie.ofertyid_oferta, zamówienie.DostawcaID_dostawca, zamówienie.KlientID_klient, zamówienie.data, zamówienie.status, dostawca.imie, dostawca.nazwisko\n" +
                    "FROM zamówienie\n" +
                    "JOIN oferty ON oferty.id_oferta = zamówienie.ofertyid_oferta\n" +
                    "JOIN firma ON firma.id_firma = oferty.FirmaID_firma\n" +
                    "JOIN dostawca ON dostawca.id_dostawca = zamówienie.DostawcaID_dostawca\n" +
                    "WHERE firma.id_firma = " + id_rest +";";
            rs = st.executeQuery(query);
            while (rs.next()) {
                int idof = Integer.parseInt(rs.getString("zamówienie.ofertyid_oferta"));
                int idz = Integer.parseInt(rs.getString("zamówienie.id_zamowienie"));
                int iddost = Integer.parseInt(rs.getString("zamówienie.DostawcaID_dostawca"));
                int idk = Integer.parseInt(rs.getString("zamówienie.KlientID_klient"));
                String nazwa = rs.getString("oferty.nazwa_dania");
                String nazwisko = rs.getString("dostawca.imie") + " " + rs.getString("dostawca.nazwisko");
                String data = rs.getString("zamówienie.data");
                String status = rs.getString("zamówienie.status");
                OrderTable order = new OrderTable(idz, idof, iddost, idk, id_rest, nazwa, data, status, nazwisko, "a");
                lista.add(order);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return lista;
    }

    public void getData() {
        try {
            String query = ""
                    + "SELECT dostawca.imie, dostawca.nazwisko, adres.rejon, dostawca.status"
                    + " FROM dostawca"
                    + " JOIN adres ON dostawca.AdresID_adres = adres.id_adres"
                    + " WHERE adres.rejon IN "
                    + " (SELECT adres.rejon FROM adres"
                    + " JOIN firma ON firma.AdresID_adres = adres.id_adres"
                    + " WHERE dostawca.status = 'dostepny')"
                    + " ORDER BY adres.rejon;";
            rs = st.executeQuery(query);
            System.out.println("Recordy z bazy");
            while (rs.next()) {
                String di = rs.getString("dostawca.imie");
                String dn = rs.getString("dostawca.nazwisko");
                String ar = rs.getString("adres.rejon");
                String ds = rs.getString("dostawca.status");
                System.out.println("Imie: " + di + " " + dn + " Rejon: " + ar + " Status: " + ds);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public int login(String login, String haslo) {
        int typI = 0;
        try {
            String query = ""
                    + "SELECT typ, login, haslo, id_konta"
                    + " FROM danelogowania"
                    + " WHERE login = '"+ login + "' AND haslo = '" + haslo + "';";
            rs = st.executeQuery(query);
            while (rs.next()) {
                String typDB = rs.getString("typ");
                int id_konta = Integer.parseInt(rs.getString("id_konta"));
                activeID = id_konta;
                //String loginDB = rs.getString("danelogowania.login");
                //String hasloDB = rs.getString("danelogowania.haslo");
                if (typDB.equals("klient")) typI = 1;
                if (typDB.equals("dostawca")) typI = 2;
                if (typDB.equals("firma")) typI = 3;
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return typI;
    }

    public void addOffer (String nazwa, String kuchnia, double cena, int id_firma) {
        try {
            String query = "INSERT INTO oferty (firmaID_firma, nazwa_dania, cena, rodzaj_kuchni) VALUES('" + id_firma + "', '"+ nazwa + "', '"+ cena + "', '"+ kuchnia +"');";
            st.executeUpdate(query);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public int getActiveID (){
        return activeID;
    }

    public void order(int id_client, int id_offer, int id_rest) {
        List<Integer> lista = new ArrayList<Integer>();
        int licznik = 0, id_dost = 0;
        try {
            String query = "SELECT dostawca.id_dostawca"
                    + " FROM dostawca"
                    + " JOIN firma ON firma.rejon = dostawca.rejon"
                    + " WHERE firma.id_firma = " + id_rest + " AND dostawca.status = 'dostępny';";
            rs = st.executeQuery(query);
            System.out.println(query);
            while (rs.next()) {
                int id_dostawca = Integer.parseInt(rs.getString("dostawca.id_dostawca"));
               // System.out.println(id_dostawca);
                lista.add(id_dostawca);
                //System.out.println(lista.get(0));
                licznik += 1;
            }
            Random generator = new Random();
            id_dost = lista.get(generator.nextInt(licznik));
           // System.out.println(lista.get(1));
           // System.out.println(id_dost);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        int id_adres = 0;
        try {
            String query = "SELECT adres.id_adres"
                    + " FROM klient"
                    + " JOIN adres ON klient.adresid_adres = adres.id_adres"
                    + " WHERE klient.id_klient = " + id_client + ";";
            rs = st.executeQuery(query);
            while (rs.next()) {
                id_adres = Integer.parseInt(rs.getString("adres.id_adres"));
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //Date data = new Date(dateFormat.format(calendar.getTime()));
            String dateString = dateFormat.format(calendar.getTime()).toString();
            String query = "INSERT INTO zamówienie (ofertyid_oferta, dostawcaid_dostawca, klientid_klient, data, status, adresid_adres)" +
                    " VALUES('" + id_offer + "', '"+ id_dost + "', '"+ id_client + "', '"+ dateString +"', 'aktywne', '" + id_adres + "');";
            st.executeUpdate(query);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public ObservableList<OfferTable> search (String nazwa, String kuchnia, double min, double max){

        ObservableList<OfferTable> lista = FXCollections.observableArrayList();

        String condition = " WHERE";
        boolean check = false;
        if (!nazwa.equals("")) {
            condition = condition + " oferty.nazwa_dania = '" + nazwa + "'";
            check = true;
        }
        if (!kuchnia.equals("Dowolna")) {
            if (check)  { condition += " AND"; }
            condition = condition + " oferty.rodzaj_kuchni = '" + kuchnia + "'";
            check = true;
        }
        if (max > 0) {
            if (check) { condition += " AND"; }
            condition = condition + " oferty.cena > " + min + " AND oferty.cena < " + max;
        }

        if (condition == " WHERE") condition = "";

        try {
            String query = ""
                    + "SELECT oferty.nazwa_dania, oferty.rodzaj_kuchni, oferty.cena, firma.nazwa, oferty.firmaid_firma, oferty.id_oferta"
                    + " FROM oferty"
                    + " JOIN firma ON oferty.firmaid_firma = firma.id_firma"
                    + condition + ";";
            System.out.println(query);
            rs = st.executeQuery(query);
            while (rs.next()) {
               // String nazwa_dania = rs.getString("nazwa_dania");
               // String rodzaj_kuchni = rs.getString("rodzaj_kuchni");
               // String cena = rs.getString("cena");
                //System.out.println(nazwa_dania + " " + rodzaj_kuchni + " " + cena);

                int idf = Integer.parseInt(rs.getString("oferty.firmaid_firma"));
                int id = Integer.parseInt(rs.getString("oferty.id_oferta"));
                String nazwad = rs.getString("oferty.nazwa_dania");
                double cena = Double.parseDouble(rs.getString("oferty.cena"));
                String rk = rs.getString("oferty.rodzaj_kuchni");
                String nazwaf = rs.getString("firma.nazwa");
                OfferTable oferta = new OfferTable(id, nazwad, rk, cena, nazwaf, idf);
                lista.add(oferta);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return lista;
    }

    public void register(int typ, String login, String haslo, String miasto, String ulica, String nrdomu, String nrmieszk, String nazwaFirmyOrDzielnicy, String imie, String nazwisko, String telefon) {
        try {
            int adresCount = 0, accCount = 0;
            if (typ == 1) {
                String adresy = "SELECT * FROM adres";
                rs = st.executeQuery(adresy);
                while (rs.next()) {
                    adresCount = Integer.parseInt(rs.getString("id_adres"));
                }
                adresCount += 1;
                String id = "SELECT * FROM klient";
                rs = st.executeQuery(id);
                while (rs.next()) {
                    accCount = Integer.parseInt(rs.getString("id_klient"));
                }
                accCount += 1;
                System.out.println(accCount);

                String query = "INSERT INTO adres (ulica, miasto, nr_domu, nr_mieszkania) VALUES('" + ulica + "', '"+ miasto + "', '"+ nrdomu + "', '"+ nrmieszk +"');";
                st.executeUpdate(query);
                System.out.println(query);
                String query2 = "INSERT INTO klient (imie, nazwisko, nr_telefonu, AdresID_adres) VALUES('" + imie + "', '"+ nazwisko + "', '"+ telefon + "', '"+ adresCount +"');";
                st.executeUpdate(query2);
                System.out.println(query2);
                String query3 = "INSERT INTO danelogowania (typ, login, haslo, id_konta) VALUES('klient', '" + login + "', '" + haslo + "', '" + accCount + "');";
                st.executeUpdate(query3);
                System.out.println(query3);
            }
            if (typ == 2) {
                String id = "SELECT * FROM dostawca";
                rs = st.executeQuery(id);
                while (rs.next()) {
                    accCount = Integer.parseInt(rs.getString("id_dostawca"));
                }
                accCount += 1;

                String query2 = "INSERT INTO dostawca (imie, nazwisko, nr_telefonu, status, rejon) VALUES('" + imie + "', '"+ nazwisko + "', '"+ telefon + "', 'dostepny', '" + nazwaFirmyOrDzielnicy + "');";
                st.executeUpdate(query2);
                System.out.println(query2);
                String query3 = "INSERT INTO danelogowania (typ, login, haslo, id_konta) VALUES('dostawca', '" + login + "', '" + haslo + "', '" + accCount + "');";
                st.executeUpdate(query3);
            }
            if (typ == 3) {
                String adresy = "SELECT * FROM adres";
                rs = st.executeQuery(adresy);
                while (rs.next()) {
                    adresCount = Integer.parseInt(rs.getString("id_adres"));
                }
                adresCount += 1;
                String id = "SELECT * FROM firma";
                rs = st.executeQuery(id);
                while (rs.next()) {
                    accCount = Integer.parseInt(rs.getString("id_firma"));
                }
                accCount += 1;

                String query = "INSERT INTO adres (ulica, miasto, nr_domu, nr_mieszkania) VALUES('" + ulica + "', '" + miasto + "', '" + nrdomu + "', '" + nrmieszk + "');";
                st.executeUpdate(query);
                System.out.println(nazwisko);
                String query2 = "INSERT INTO firma (nazwa, nr_telefonu, AdresID_adres, rejon) VALUES('" + nazwaFirmyOrDzielnicy + "', '" + telefon + "', '" + adresCount + "', '" + nazwisko + "');";
                st.executeUpdate(query2);
                String query3 = "INSERT INTO danelogowania (typ, login, haslo, id_konta) VALUES('firma', '" + login + "', '" + haslo + "', '" + accCount + "');";
                st.executeUpdate(query3);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public void deleteOffer(int id) {
        try {
            String query = "DELETE FROM oferty WHERE id_oferta = " + id + ";";
            st.executeUpdate(query);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}