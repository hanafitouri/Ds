package com.example.banque.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banque.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	    List<Transaction> findByCompteId(Long compteId);
}
