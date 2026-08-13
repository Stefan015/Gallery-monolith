package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.IzlozbaDTO;
import com.example.demo.exceptions.EmptyFieldException;
import com.example.demo.services.IzlozbeService;


@Controller
@RequestMapping("/izlozba")
public class IzlozbaController {
	
	@Autowired
	IzlozbeService izlozbaService;
	
	@PostMapping("/dodajIzlozbu")
	public String dodajIzlozbu(@ModelAttribute IzlozbaDTO dto ,RedirectAttributes redirectAttributes) {
		if(dto.getNaslov().equals("") || dto.getOpis().equals("") || dto.getUmetnikIds().isEmpty() || dto.getDatumKraja().equals(null)  || dto.getDatumPocetka().equals(null))
			throw new EmptyFieldException("Polja ne smeju ostati prazna!");
		izlozbaService.dodajIzlozbu(dto);
		redirectAttributes.addFlashAttribute("poruka","Izlozba uspesno dodata");
		return "redirect:/adminPanel?adminView=Izlozbe";
	}
	
	@PostMapping("/obrisiIzlozbu")
	public String obrisiIzlozbu(@RequestParam Integer idIzlozbe,RedirectAttributes redirectAttributes) {
		izlozbaService.obrisiIzlozbu(idIzlozbe);
		redirectAttributes.addFlashAttribute("poruka","izlozba Uspesno obrisana");
		return "redirect:/adminPanel?adminView=Izlozbe";
	}
	@GetMapping("/odabirIzlozbeZaPromenu")
	public String odabirIzlozbeZaPromenu(@RequestParam Integer idIzlozbe, RedirectAttributes reddirectAttributes ) {
		reddirectAttributes.addFlashAttribute("izlozbaZaPromenu", izlozbaService.nadjiIzlozbu(idIzlozbe));
		return "redirect:/adminPanel?adminView=IzlozbePromena";
	}
	@PostMapping("/sacuvajPromenuIzlozbe")
	public String sacuvajPromenuIzlozbe(@ModelAttribute IzlozbaDTO dto,RedirectAttributes redirectAttributes) {
		if(dto.getNaslov().equals("") || dto.getOpis().equals("") || dto.getUmetnikIds().isEmpty() || dto.getDatumKraja().equals(null)  || dto.getDatumPocetka().equals(null))
			throw new EmptyFieldException("Polja ne smeju ostati prazna!");
		izlozbaService.sacuvajPromenuIzlozbe(dto);
		redirectAttributes.addFlashAttribute("poruka","Izlozba uspesno promenjena");
		return "redirect:/adminPanel?adminView=Izlozbe";
	}

	@ExceptionHandler(EmptyFieldException.class)
	public String handleEmpty(RuntimeException ex, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("poruka", ex.getMessage());
		return "redirect:/adminPanel?adminView=Izlozbe";
	}
	
}
