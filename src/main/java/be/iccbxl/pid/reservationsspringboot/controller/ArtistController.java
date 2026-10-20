package be.iccbxl.pid.reservationsspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

import be.iccbxl.pid.reservationsspringboot.model.Artist;
import be.iccbxl.pid.reservationsspringboot.service.Artistservice;

@Controller
public class ArtistController {
    @Autowired
    Artistservice service;

    // 1. Afficher la liste des artistes (Évite l'erreur GET 405 sur /artists)
    @GetMapping("/artists")
    public String index(Model model) {
        List<Artist> artists = service.getAllArtists();
        model.addAttribute("artists", artists);
        model.addAttribute("title", "Liste des artistes");
        
        return "artist/index";
    }

    // 2. Afficher un artiste par son ID (Nécessaire pour la redirection après la création)
    @GetMapping("/artists/{id}")
    public String show(Model model, @PathVariable("id") long id) {
        Artist artist = service.getArtist(id);
        model.addAttribute("artist", artist);
        model.addAttribute("title", "Fiche d'un artiste");
        
        return "artist/show";
    }

    // 3. Afficher le formulaire de création
    @GetMapping("/artists/create")
    public String create(Model model) {
        Artist artist = new Artist();
        model.addAttribute("artist", artist);
        
        return "artist/create";
    }
    
    // 4. Enregistrer le nouvel artiste
    @PostMapping("/artists")
    public String store(@Valid @ModelAttribute Artist artist, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "artist/create";
        }
            
        service.addArtist(artist);
        
        return "redirect:/artists/" + artist.getId();
    }
}