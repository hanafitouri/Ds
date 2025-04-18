package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entites.Compte;

@Controller
public class BanqueController {
    private List<Compte> comptes = new ArrayList<>();

    @GetMapping("/comptes")
    public String listeComptes(Model model) {
        model.addAttribute("comptes", comptes);
        return "listeComptes";
    }

    @GetMapping("/ajouter")
    public String formulaireAjout(Model model) {
        model.addAttribute("compte", new Compte());
        return "ajouterCompte";
    }

    @PostMapping("/ajouter")
    public String ajouterCompte(@ModelAttribute Compte compte) {
        comptes.add(compte);
        return "redirect:/comptes";
    }

    @GetMapping("/details/{id}")
    public String detailsCompte(@PathVariable int id, Model model) {
        Compte compte = trouverCompteParId(id);
        if (compte != null) {
            model.addAttribute("compte", compte);
            return "detailsCompte";
        }
        return "redirect:/comptes";
    }

    @GetMapping("/depot/{id}")
    public String formulaireDepot(@PathVariable int id, Model model) {
        Compte compte = trouverCompteParId(id);
        if (compte != null) {
            model.addAttribute("compte", compte);
            model.addAttribute("operation", "depot");
            return "operationCompte";
        }
        return "redirect:/comptes";
    }

    @GetMapping("/retrait/{id}")
    public String formulaireRetrait(@PathVariable int id, Model model) {
        Compte compte = trouverCompteParId(id);
        if (compte != null) {
            model.addAttribute("compte", compte);
            model.addAttribute("operation", "retrait");
            return "operationCompte";
        }
        return "redirect:/comptes";
    }

    @PostMapping("/operation/{id}")
    public String effectuerOperation(
            @PathVariable int id,
            @RequestParam String type,
            @RequestParam double montant) {
        
        Compte compte = trouverCompteParId(id);
        if (compte != null) {
            if ("depot".equals(type)) {
                compte.deposer(montant);
            } else if ("retrait".equals(type)) {
                compte.retirer(montant);
            }
        }
        return "redirect:/details/" + id;
    }

    private Compte trouverCompteParId(int id) {
        return comptes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }
}