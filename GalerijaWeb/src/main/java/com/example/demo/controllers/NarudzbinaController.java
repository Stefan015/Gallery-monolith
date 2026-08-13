package com.example.demo.controllers;

import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.NarudzbinaDTO;
import com.example.demo.services.NarudzbinaService;
import com.example.demo.services.SlikaService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@RequestMapping("/narudzbina")
public class NarudzbinaController {

	@Autowired
	NarudzbinaService narudzbinaService;
	
	@Autowired
	SlikaService slikaService;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/dodajUkorpu")
	public String dodajUKorpu(@RequestParam Integer idSlika, HttpSession session) {
		
		Set<Integer> korpa = (Set<Integer>) session.getAttribute("korpa");
		korpa.add(idSlika);
		session.setAttribute("korpa", korpa);
		return "redirect:/slike";
	}
	@SuppressWarnings("unchecked")
	@PostMapping("/zavrsiNarudzbinu")
	public String zavrsiNarudzbinu(HttpSession session, RedirectAttributes redirectAttributes) {
		Set<Integer> slikeUKorpi = (Set<Integer>) session.getAttribute("korpa");
		Integer idKorisnik = (Integer) session.getAttribute("idKorisnik");
		Integer idNarudzbina = narudzbinaService.zavrsiNarudzbinu(slikaService.nadjiSlikePoIdu(slikeUKorpi),idKorisnik);
		session.setAttribute("korpa", new HashSet<>());
        redirectAttributes.addFlashAttribute("poruka", "Narudžbina uspešna!");
		return "redirect:/narudzbina/izvestaj?id=" + idNarudzbina;
	}
	
	@GetMapping("/izvestaj")
	public void izvestaj(@RequestParam Integer id, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {

	    NarudzbinaDTO n = narudzbinaService.nadjiNarudzbinu(id);
	    JasperPrint report = narudzbinaService.kreirajIzvestaj(n);
	    
	    response.setContentType("text/html");
	    response.setContentType("application/pdf");
	    response.setHeader("Content-Disposition", "attachment; filename=narudzbina"+n.getIdNarudzbina()+".pdf");

	    OutputStream out = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(report, out);
	    out.close();
	}
	
}
