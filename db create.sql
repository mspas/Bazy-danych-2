CREATE TABLE `adres` (
  `id_adres` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `ulica` varchar(255) DEFAULT NULL,
  `miasto` text,
  `nr_domu` mediumint(9) DEFAULT NULL,
  `nr_mieszkania` mediumint(9) DEFAULT NULL,
  `rejon` text,
  PRIMARY KEY (`id_adres`)
) 

CREATE TABLE `danelogowania` (
  `typ` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `haslo` varchar(45) NOT NULL,
  `id_konta` int(11) NOT NULL,
  UNIQUE KEY `login_UNIQUE` (`login`)
) 

CREATE TABLE `dostawca` (
  `id_dostawca` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `imie` varchar(255) DEFAULT NULL,
  `nazwisko` varchar(255) DEFAULT NULL,
  `nr_telefonu` varchar(100) DEFAULT NULL,
  `status` text,
  `rejon` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_dostawca`)
) 

CREATE TABLE `firma` (
  `ID_firma` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(255) NOT NULL,
  `nr_telefonu` varchar(255) NOT NULL,
  `AdresID_adres` int(11) NOT NULL,
  `rejon` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_firma`)
) 

CREATE TABLE `klient` (
  `ID_klient` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(255) NOT NULL,
  `nazwisko` varchar(255) NOT NULL,
  `nr_telefonu` varchar(255) NOT NULL,
  `AdresID_adres` int(11) NOT NULL,
  PRIMARY KEY (`ID_klient`),
  UNIQUE KEY `nr_telefonu` (`nr_telefonu`)
)

CREATE TABLE `oferty` (
  `ID_oferta` int(11) NOT NULL AUTO_INCREMENT,
  `FirmaID_firma` int(11) NOT NULL,
  `nazwa_dania` varchar(255) NOT NULL,
  `cena` double NOT NULL,
  `rodzaj_kuchni` varchar(255) NOT NULL,
  `opis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_oferta`)
) 

CREATE TABLE `zamówienie` (
  `ID_zamowienie` int(11) NOT NULL AUTO_INCREMENT,
  `OfertyID_oferta` int(11) NOT NULL,
  `DostawcaID_dostawca` int(11) NOT NULL,
  `KlientID_klient` int(11) NOT NULL,
  `data` date NOT NULL,
  `status` varchar(255) NOT NULL,
  `AdresID_adres` int(11) NOT NULL,
  PRIMARY KEY (`ID_zamowienie`)
)