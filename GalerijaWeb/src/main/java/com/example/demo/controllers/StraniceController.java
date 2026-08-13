package com.example.demo.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.SlikaDTO;
import com.example.demo.services.IzlozbeService;
import com.example.demo.services.KorisnikService;
import com.example.demo.services.NarudzbinaService;
import com.example.demo.services.SlikaService;
import com.example.demo.services.UmetnikService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StraniceController {
	
	@Autowired
	SlikaService slikaService;
	
	@Autowired
	IzlozbeService izlozbeService;
	
	@Autowired
	UmetnikService umetnikService;
	
	@Autowired 
	KorisnikService korisnikService;
	
	@Autowired 
	NarudzbinaService narudzbinaService;

	@GetMapping("/pocetnaStrana")
	    public String pocetna() {
	        return "index";
	    }

	    @GetMapping("/izlozbe")
	    public String izlozbe(Model model) {
	    	model.addAttribute("izlozbe", izlozbeService.nadjiIzlozbe());
	        return "izlozbe";
	    }
	    
	    @SuppressWarnings("unchecked")
		@GetMapping("/narudzbina")
	    public String narudzbina(Model model,HttpSession session) {
	    	List<SlikaDTO> slike = slikaService.nadjiSlikePoIdu((Set<Integer>) session.getAttribute("korpa"));
	    	int ukupnaCena = slikaService.ukupnaCena(slike);
	    	model.addAttribute("slikeUKorpi", slike );
	    	model.addAttribute("ukupnaCena", ukupnaCena );
	        return "narudzbina";
	    }

	    @GetMapping("/slike")
	    public String slike(Model model) {
	    	model.addAttribute("listaSlika", slikaService.nadjiSlike());
	        return "slike";
	    }
	    @GetMapping("/umetnici")
	    public String umetnici(Model model) {
	    	model.addAttribute("umetnici", umetnikService.nadjiUmetnike());
	        return "umetnici";
	    }
	    @GetMapping("/login")
	    public String login() {
	        return "login";
	    }
	    
	    @GetMapping("/registracija")
	    public String registracija() {
	        return "registracija";
	    }
	    @GetMapping("/adminPanel")
	    public String adminPanel(@RequestParam(required = false)String adminView, Model model) {
	    	
	    	if(adminView != null) {
	    		switch(adminView) {
	    		case "Korisnici","KorisniciPromena":
	    			model.addAttribute("korisnici", korisnikService.nadjiKorisnike());
	    			break;
	    		
	    		case "Slike":
	    			model.addAttribute("umetnici", umetnikService.nadjiUmetnike());
	    			model.addAttribute("slike", slikaService.nadjiSlike());
	    			break;
	    			
	    		case "Umetnici":
	    			model.addAttribute("umetnici", umetnikService.nadjiUmetnike());
	    			break;
	    		case "Izlozbe":
	    			model.addAttribute("umetnici", umetnikService.nadjiUmetnike());
	    			model.addAttribute("izlozbe", izlozbeService.nadjiIzlozbe());
	    			break;
	    		case "IzlozbePromena":
	    			model.addAttribute("umetnici", umetnikService.nadjiUmetnike());
	    			model.addAttribute("izlozbe", izlozbeService.nadjiIzlozbe());
	    			break;
	    		default:
	    			break;
	    		}
	    	}
	    	model.addAttribute("adminView", adminView);
	    	
	        return "adminPanel";
	    }

}
