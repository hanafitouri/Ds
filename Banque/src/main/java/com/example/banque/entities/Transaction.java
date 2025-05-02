package com.example.banque.entities;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String type;
    private double montant;
    private LocalDateTime date = LocalDateTime.now();
    
    @ManyToOne
    @JoinColumn(name = "compte_id")
    private Compte compte;
    
    // Constructeurs, getters et setters
}
