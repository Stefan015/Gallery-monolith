package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.SlikaDTO;
import com.example.demo.repository.SlikaRepository;
import com.example.demo.repository.UmetnikRepository;

import model.Slika;
import model.Umetnik;

@Service 
public class SlikaService {
	
	@Autowired
	SlikaRepository slikaRepo;
	
	@Autowired
	UmetnikRepository umetnikRepo;

	public void dodajSliku(SlikaDTO slikaDto, MultipartFile file) {
		
		Umetnik umetnik = umetnikRepo.findById(slikaDto.getUmetnikId())
			    .orElseThrow(() -> new RuntimeException("Umetnik not found"));
		
		String imeFajla= file.getOriginalFilename();
		Slika slika = new Slika();
		slika.setIme(slikaDto.getIme());
		slika.setCena(slikaDto.getCena());
		slika.setStatus(slikaDto.getStatus());
		slika.setTehnika(slikaDto.getTehnika());
		slika.setUrlSlike("paintings/" + imeFajla);
		slika.setUmetnik(umetnik);
		slikaRepo.save(slika);
		
	}

	public List<SlikaDTO> nadjiSlike() {

	    List<Slika> slike = slikaRepo.findAll();
	    List<SlikaDTO> listaDto = new ArrayList<>();

	    for (Slika s : slike) {
	        SlikaDTO dto = new SlikaDTO();
	        dto.setIdSlika(s.getIdSlika());
	        dto.setUrlSlike(s.getUrlSlike());
	        dto.setIme(s.getIme());
	        dto.setCena(s.getCena());
	        dto.setTehnika(s.getTehnika());
	        dto.setStatus(s.getStatus());
	        dto.setUmetnik(s.getUmetnik());
	        listaDto.add(dto);
	    }

	    return listaDto;
	}
	
	public List<SlikaDTO> nadjiSlikePoIdu(Set<Integer> korpa) {
		List<SlikaDTO> slikeDto = new ArrayList<>();
		for(Integer i : korpa) {
			SlikaDTO dto =nadjiSlikuPoId(i);
			slikeDto.add(dto);
		}
		return slikeDto;
	}
	
	public SlikaDTO nadjiSlikuPoId(Integer idSlika) {

	    Slika s = slikaRepo.findById(idSlika).get();

	    SlikaDTO dto = new SlikaDTO();
	    dto.setIdSlika(s.getIdSlika());
	    dto.setUrlSlike(s.getUrlSlike());
	    dto.setIme(s.getIme());
	    dto.setCena(s.getCena());
	    dto.setTehnika(s.getTehnika());
	    dto.setStatus(s.getStatus());
	    dto.setUmetnik(s.getUmetnik());

	    return dto;
	}
	
	public int ukupnaCena(List<SlikaDTO> slike) {
	    int suma = 0;

	    for (SlikaDTO s : slike) {
	        suma += s.getCena();
	    }

	    return suma;
	}

	public void obrisiSliku(Integer idSlika) {
		slikaRepo.deleteById(idSlika);
	}
}
