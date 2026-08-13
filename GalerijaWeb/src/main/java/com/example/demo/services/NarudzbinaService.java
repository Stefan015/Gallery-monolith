package com.example.demo.services;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.NarudzbinaDTO;
import com.example.demo.dto.SlikaDTO;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.NarudzbinaRepository;
import com.example.demo.repository.SlikaRepository;

import jakarta.transaction.Transactional;
import model.Korisnik;
import model.Narudzbina;
import model.Slika;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class NarudzbinaService {

	@Autowired
	NarudzbinaRepository narudzbinaRepo;
	
	@Autowired
	KorisnikRepository korisnikRepo;
	
	@Autowired
	SlikaRepository slikaRepo;

	@Transactional
	public Integer zavrsiNarudzbinu(List<SlikaDTO> lista, Integer idKorisnik) {

	    Narudzbina n = new Narudzbina();
	    n.setDatum(new Date());

	    int total = 0;
	    for (SlikaDTO s : lista) {
	        total += s.getCena();
	    }
	    n.setUkupnaCena(total);
	    
	    Korisnik k = korisnikRepo.findById(idKorisnik)
	            .orElseThrow(() -> new RuntimeException("Korisnik ne postoji"));
	    n.setKorisnik(k);

	    List<Slika> slike = new ArrayList<>();

	    for (SlikaDTO dto : lista) {
	        Slika s = slikaRepo.findById(dto.getIdSlika())
	                .orElseThrow(() -> new RuntimeException("Slika ne postoji"));

	        s.setNarudzbina(n);
	        s.setStatus("prodato");
	        slike.add(s);
	    }

	    n.setSlikaList(slike);

	    narudzbinaRepo.save(n);
	    
	    
	    return n.getIdNarudzbina();
	}

	public NarudzbinaDTO nadjiNarudzbinu(Integer id) {
	    Narudzbina n = narudzbinaRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Narudzbina ne postoji"));

	    NarudzbinaDTO dto = new NarudzbinaDTO();

	    dto.setIdNarudzbina(n.getIdNarudzbina());
	    dto.setDatum(n.getDatum());
	    dto.setUkupnaCena(n.getUkupnaCena());

	    dto.setKorisnikId(n.getKorisnik().getIdKorisnik());
	    dto.setKorisnickoIme(n.getKorisnik().getKorisnickoIme());

	    List<SlikaDTO> slike = new ArrayList<>();

	    for (Slika s : n.getSlikaList()) {
	        SlikaDTO slikaDto = new SlikaDTO();
	        slikaDto.setIdSlika(s.getIdSlika());
	        slikaDto.setIme(s.getIme());
	        slikaDto.setCena(s.getCena());
	        slikaDto.setTehnika(s.getTehnika());
	        slikaDto.setStatus(s.getStatus());
	        slikaDto.setUmetnik(s.getUmetnik());
	        slike.add(slikaDto);
	    }

	    dto.setSlike(slike);

	    return dto;
	}

	public JasperPrint kreirajIzvestaj(NarudzbinaDTO narudzbina) throws JRException {
		List<SlikaDTO> slike = narudzbina.getSlike();
		slike.sort(Comparator.comparing(
			    s -> s.getUmetnik() != null ? s.getUmetnik().getIme() : ""
			));
		JRBeanCollectionDataSource dataSource = new
				JRBeanCollectionDataSource(narudzbina.getSlike());
				InputStream inputStream =
				this.getClass().getResourceAsStream("/jasperreports/narudzbinaRacun.jrxml");
				JasperReport jasperReport;
				jasperReport = JasperCompileManager.compileReport(inputStream);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("ukupnaCena",narudzbina.getUkupnaCena());
				params.put("datum", narudzbina.getDatum());
				params.put("korisnik", narudzbina.getKorisnickoIme());
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,
				dataSource);
		return jasperPrint;
	}
	
}
