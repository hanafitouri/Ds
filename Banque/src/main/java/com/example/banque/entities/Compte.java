package com.example.banque.entities;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.pulsar.PulsarProperties.Transaction;

@Entity
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulaire;
    private double solde;
    
    
    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();
    
  
    public void deposer(double montant) {
        this.solde += montant;
        this.transactions.add(new Transaction());
    }
    
    public boolean retirer(double montant) {
        if (this.solde >= montant) {
            this.solde -= montant;
            this.transactions.add(new Transaction());
            return true;
        }
        return false;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Compte(String titulaire, double solde, List<Transaction> transactions) {
		super();
		this.titulaire = titulaire;
		this.solde = solde;
		this.transactions = transactions;
	}

	public Compte() {
		super();
	}


}