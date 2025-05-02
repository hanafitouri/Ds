package com.example.banque.controller;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.banque.entities.Compte;
import com.example.banque.repositories.CompteRepository;

@Controller
@RequestMapping("/comptes")
public class BanqueController {
    
    private final CompteRepository compteRepository;
    
    public BanqueController(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }
    
   
    @GetMapping
    public String listeComptes(Model model) {
        model.addAttribute("comptes", compteRepository.findAll());
        return "listeComptes";
    }
    
    @GetMapping("/ajouter")
    public String formulaireAjout(Model model) {
        model.addAttribute("compte", new Compte());
        return "ajouterCompte";
    }
    
    @PostMapping("/ajouter")
    public String ajouterCompte(@ModelAttribute Compte compte, RedirectAttributes redirectAttributes) {
        compteRepository.save(compte);
        redirectAttributes.addFlashAttribute("success", "Compte créé avec succès");
        return "redirect:/comptes";
    }
    
    
    @GetMapping("/details/{id}")
    public String detailsCompte(@PathVariable Long id, Model model) {
        Compte compte = compteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Compte invalide"));
        model.addAttribute("compte", compte);
        return "detailsCompte";
    }
    
  
    @GetMapping("/depot/{id}")
    public String formulaireDepot(@PathVariable Long id, Model model) {
        Compte compte = compteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Compte invalide"));
        model.addAttribute("compte", compte);
        return "formDepot";
    }
    
   
    @PostMapping("/depot/{id}")
    public String effectuerDepot(
            @PathVariable Long id,
            @RequestParam double montant,
            RedirectAttributes redirectAttributes) {
        
        Compte compte = compteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Compte invalide"));
        
        compte.deposer(montant);
        compteRepository.save(compte);
        
        redirectAttributes.addFlashAttribute("success", "Dépôt de " + montant + " € effectué");
        return "redirect:/comptes/details/" + id;
    }
    
    
    @GetMapping("/retrait/{id}")
    public String formulaireRetrait(@PathVariable Long id, Model model) {
        Compte compte = compteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Compte invalide"));
        model.addAttribute("compte", compte);
        return "formRetrait";
    }
    
  
    @PostMapping("/retrait/{id}")
    public String effectuerRetrait(
            @PathVariable Long id,
            @RequestParam double montant,
            RedirectAttributes redirectAttributes) {
        
        Compte compte = compteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Compte invalide"));
        
        if (compte.retirer(montant)) {
            compteRepository.save(compte);
            redirectAttributes.addFlashAttribute("success", "Retrait de " + montant + " € effectué");
        } else {
            redirectAttributes.addFlashAttribute("error", "Solde insuffisant pour ce retrait");
        }
        
        return "redirect:/comptes/details/" + id;
    }
    
   
    @PostMapping("/supprimer/{id}")
    public String supprimerCompte(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        compteRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Compte supprimé avec succès");
        return "redirect:/comptes";
    }
}