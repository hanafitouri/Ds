package com.example.demo.entites;



public class Compte {
    
    
    private int id;
    private String titulaire;
    private double solde;

   

    public Compte(String titulaire, double solde) {
        this();
        this.titulaire = titulaire;
        this.solde = solde;
    }

   
    public int getId() {
        return id;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public void setTitulaire(String titulaire) {
        this.titulaire = titulaire;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void deposer(double montant) {
        if (montant > 0) {
            this.solde += montant;
        }
    }

    public boolean retirer(double montant) {
        if (montant > 0 && this.solde >= montant) {
            this.solde -= montant;
            return true;
        }
        return false;
    }
    public Compte() {}
}