package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UmetnikDTO;
import com.example.demo.repository.UmetnikRepository;

import model.Umetnik;

@Service
public class UmetnikService {
	
	@Autowired
	UmetnikRepository umetnikRepo;

	public boolean dodajUmetnika(UmetnikDTO umetnikDto) {
		Umetnik umetnik = new Umetnik();
		umetnik.setIme(umetnikDto.getIme());
		umetnik.setMestoRodjenja(umetnikDto.getMestoRodjenja());
		umetnik.setDatumRodjenja(umetnikDto.getDatumRodjenja());
		umetnik.setDatumSmrti(umetnikDto.getDatumSmrti());
		umetnik.setBiografija(umetnikDto.getBiografija());
		umetnikRepo.save(umetnik);
		
		return false;
		
	}

	public List<UmetnikDTO> nadjiUmetnike() {
		List<Umetnik> umetnici = umetnikRepo.findAll();
		List<UmetnikDTO> listaDto = new ArrayList<>();
		
		for(Umetnik u : umetnici) {
			UmetnikDTO dto = new UmetnikDTO();
			dto.setIme(u.getIme());
			dto.setIdUmetnik(u.getIdUmetnik());
			dto.setMestoRodjenja(u.getMestoRodjenja());
			dto.setDatumRodjenja(u.getDatumRodjenja());
			dto.setDatumSmrti(u.getDatumSmrti());
			dto.setBiografija(u.getBiografija());
			
			listaDto.add(dto);
		}
		
		return listaDto;
	}

	public void obrisiUmetnika(Integer idUmetnik) {
		umetnikRepo.deleteById(idUmetnik);
	}
}
