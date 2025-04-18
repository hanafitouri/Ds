package com.example.demo.entites;

import java.util.ArrayList;
import java.util.List;

public class Entreprise {
	private int id;
	private String nomEntreprise;
	public static List<Compte> employes =new ArrayList<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomEntreprise() {
		return nomEntreprise;
	}
	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}
}
