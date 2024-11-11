package com.OR.OR;

public class Klub {


    private String naziv;

    private String grad;
    private int godinaOsnutka;
    private int brojTrofejaLigi;
    private int brojIgraca;
    private double prosjekGodina;
    private int brojBodovaProsleGodine;
    private int brojLigiPrvaka;
    private int brojKupova;
    private double tmVrijednost;
    private String stadion;


    private int trener;


    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public int getGodinaOsnutka() {
        return godinaOsnutka;
    }

    public void setGodinaOsnutka(int godinaOsnutka) {
        this.godinaOsnutka = godinaOsnutka;
    }

    public int getBrojTrofejaLigi() {
        return brojTrofejaLigi;
    }

    public void setBrojTrofejaLigi(int brojTrofejaLigi) {
        this.brojTrofejaLigi = brojTrofejaLigi;
    }

    public int getBrojIgraca() {
        return brojIgraca;
    }

    public void setBrojIgraca(int brojIgraca) {
        this.brojIgraca = brojIgraca;
    }

    public double getProsjekGodina() {
        return prosjekGodina;
    }

    public void setProsjekGodina(double prosjekGodina) {
        this.prosjekGodina = prosjekGodina;
    }

    public int getBrojBodovaProsleGodine() {
        return brojBodovaProsleGodine;
    }

    public void setBrojBodovaProsleGodine(int brojBodovaProsleGodine) {
        this.brojBodovaProsleGodine = brojBodovaProsleGodine;
    }

    public int getBrojLigiPrvaka() {
        return brojLigiPrvaka;
    }

    public void setBrojLigiPrvaka(int brojLigiPrvaka) {
        this.brojLigiPrvaka = brojLigiPrvaka;
    }

    public int getBrojKupova() {
        return brojKupova;
    }

    public void setBrojKupova(int brojKupova) {
        this.brojKupova = brojKupova;
    }

    public double getTmVrijednost() {
        return tmVrijednost;
    }

    public void setTmVrijednost(double tmVrijednost) {
        this.tmVrijednost = tmVrijednost;
    }
    public void setStadion(String stadion) {
        this.stadion = stadion;
    }
    public void setTrener(int trener) {
        this.trener = trener;
    }
}
