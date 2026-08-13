package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.SlikaDTO;
import com.example.demo.exceptions.EmptyFieldException;
import com.example.demo.services.SlikaService;

@Controller
@RequestMapping("/slika")
public class SlikaController {
	
	@Autowired
	SlikaService slikaService;
	
	@PostMapping("/dodajSliku")
	public String dodajSliku(@ModelAttribute SlikaDTO slika, @RequestParam("slikaPath") MultipartFile file,RedirectAttributes redirectAttributes) {
		if(slika.getIme().equals("") || slika.getTehnika().equals("") || slika.getCena()== null || slika.getStatus().equals("") || slika.getUmetnikId()==0 || file ==null)
			throw new EmptyFieldException("Polja ne smeju biti prazna!");
		slikaService.dodajSliku(slika,file);
		redirectAttributes.addFlashAttribute("poruka","Slika uspesno dodata!");
		return "redirect:/adminPanel?adminView=Slike";
	}
	
	@PostMapping("/obrisiSliku")
	public String obrisiSliku(@RequestParam Integer idSlika, RedirectAttributes redirectAttributes) {
		slikaService.obrisiSliku(idSlika);
		redirectAttributes.addFlashAttribute("poruka","Slika uspesno obrisana!");
		return "redirect:/adminPanel?adminView=Slike";
		
	}
	@ExceptionHandler(EmptyFieldException.class)
	public String handleEmpty(RuntimeException ex, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("poruka", ex.getMessage());
		return "redirect:/adminPanel?adminView=Slike";
	}

}
