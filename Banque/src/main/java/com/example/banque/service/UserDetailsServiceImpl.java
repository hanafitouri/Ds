package com.example.banque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.banque.entities.User;
import com.example.banque.repositories.UserRepository;

@Service
	public class UserDetailsServiceImpl implements UserDetailsService {
	    
	    @Autowired
	    private UserRepository userRepository;
	    
	    @Override
	    public UserDetails loadUserByUsername(String username) {
	        User user = userRepository.findByUsername(username)
	            .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
	        // ...
			return user;
	    }
	}

