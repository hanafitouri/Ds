package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entites.Compte;

@SpringBootApplication
public class FirstApplication {
	public static List<Compte> compts =new ArrayList<>();
	public static void main(String[] args) {
		SpringApplication.run(FirstApplication.class, args);
		
		    Compte c1=new Compte("foulen",1650);
	       
	        
	        compts.add(c1);
	        
	}

	
}
