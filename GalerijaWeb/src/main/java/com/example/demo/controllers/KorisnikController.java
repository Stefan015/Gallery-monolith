package com.example.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.exceptions.AdminOperationException;
import com.example.demo.exceptions.EmptyFieldException;
import com.example.demo.exceptions.InvalidLoginCredentialsException;
import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.services.KorisnikService;

import jakarta.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/korisnik")
public class KorisnikController {
	
	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/registracija")
	public String register(@ModelAttribute KorisnikDTO korisnik, RedirectAttributes redirectAttributes) {
		if(korisnik.getKorisnickoIme().equals("") || korisnik.getSifra().equals("") || korisnik.getEmail().equals(""))
			throw new EmptyFieldException("Polja ne smeju biti prazna");
		korisnik.setUloga("korisnik");
		korisnik.setSifra(passwordEncoder.encode(korisnik.getSifra()));
		korisnikService.registrujKorisnika(korisnik);
		redirectAttributes.addFlashAttribute("poruka", "Registracija Uspesna!");
			return "redirect:/login";
	}

	@PostMapping("/dodajKorisnika")
	public String dodajKorisnika(@ModelAttribute KorisnikDTO korisnikDto,RedirectAttributes redirectAttributes) {
		if(korisnikDto.getKorisnickoIme().equals("") || korisnikDto.getSifra().equals("") || korisnikDto.getEmail().equals(""))
			throw new AdminOperationException("Polja ne smeju biti prazna");
		korisnikDto.setSifra(passwordEncoder.encode(korisnikDto.getSifra()));
		korisnikService.registrujKorisnika(korisnikDto);
				redirectAttributes.addFlashAttribute("poruka", "Registracija Uspesna!");
				return "redirect:/adminPanel?adminView=Korisnici";
	}
	
	@PostMapping("/obrisiKorisnika")
	public String obrisiKorisnika(@RequestParam Integer idKorisnik) {
		korisnikService.obrisiKorisnika(idKorisnik);
		return "redirect:/adminPanel?adminView=Korisnici";
	}
	@GetMapping("odabirKorisnikaZaIzmenu")
	public String odabirKorisnikaZaIzmenu(@RequestParam Integer idKorisnik, RedirectAttributes reddirectAttributes ) {
		reddirectAttributes.addFlashAttribute("korisnikZaPromenu", korisnikService.nadjiKorisnika(idKorisnik));
		return "redirect:/adminPanel?adminView=KorisniciPromena";
	}
	@PostMapping("/promeniUloguKorisniku")
	public String promeniUloguKorisniku(@RequestParam Integer idKorisnik, @RequestParam String uloga) {
		korisnikService.promeniUloguKorisniku(idKorisnik,uloga);
		return "redirect:/adminPanel?adminView=Korisnici";
	}
	
	@ExceptionHandler({EmptyFieldException.class, InvalidLoginCredentialsException.class})
	public String handleLogin(RuntimeException ex, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("poruka", "Greska!: "+ ex.getMessage());
	    return "redirect:/login";
	}
	@ExceptionHandler(AdminOperationException.class)
	public String handleAdminErrors(RuntimeException ex, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("poruka", ex.getMessage());
	    return "redirect:/adminPanel?adminView=Korisnici";
	}
	@ExceptionHandler(UserAlreadyExistsException.class)
	public String handleUserExists(RuntimeException ex, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		redirectAttributes.addFlashAttribute("poruka", ex.getMessage());
	    String url = request.getRequestURI();

	    if (url.contains("/register")) {
	        return "redirect:/register";
	    } else if (url.contains("/dodajKorisnika")) {
	        return "redirect:/adminPanel?adminView=Korisnici";
	    }

	    return "redirect:/pocetnaStrana";
	}

}

