package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.UmetnikDTO;
import com.example.demo.exceptions.EmptyFieldException;
import com.example.demo.services.UmetnikService;

@Controller
@RequestMapping("/umetnik")
public class UmetnikController {
	@Autowired
	UmetnikService umetnikService;
	
	@PostMapping("/dodajUmetnika")
	public String dodajUmetnika(@ModelAttribute UmetnikDTO umetnik,RedirectAttributes redirectAttributes) {
		if(umetnik.getIme().equals("") || umetnik.getBiografija().equals("") || umetnik.getDatumRodjenja().equals(null) || umetnik.getDatumSmrti().equals(null))
			throw new EmptyFieldException("Polja ne smeju ostati prazna!");
		umetnikService.dodajUmetnika(umetnik);
		redirectAttributes.addFlashAttribute("poruka","Umetnik uspesno dodat!");
		return "redirect:/adminPanel?adminView=Umetnici";
	}
	@PostMapping("/obrisiUmetnika")
	public String obrisiUmetnika(@RequestParam Integer idUmetnik) {
		umetnikService.obrisiUmetnika(idUmetnik);
		return "redirect:/adminPanel?adminView=Umetnici";
	}
	
	@ExceptionHandler(EmptyFieldException.class)
	public String handle(RuntimeException ex, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("poruka", "Greska!: "+ ex.getMessage());
		return "redirect:/adminPanel?adminView=Umetnici";
	}

}
