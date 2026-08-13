package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.exceptions.AdminOperationException;
import com.example.demo.exceptions.InvalidLoginCredentialsException;
import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.repository.KorisnikRepository;

import jakarta.transaction.Transactional;
import model.Korisnik;

@Service
public class KorisnikService {

	@Autowired
	KorisnikRepository korisnikRepo; 
	
	public void registrujKorisnika(KorisnikDTO dto) {
		Optional<Korisnik> korisnik = korisnikRepo.findByKorisnickoImeOrMail(dto.getKorisnickoIme(),dto.getEmail());
		
		if(korisnik.isPresent() && korisnik.get().getKorisnickoIme().equals(dto.getKorisnickoIme())) 
			throw new UserAlreadyExistsException("Korisnicko ime vec postoji!");
		else if(korisnik.isPresent())
			throw new UserAlreadyExistsException("Email se vec koristi!");
		
		dodajKorisnika(dto);
	}
	public Optional<Korisnik> nadjiKorisnikaPoEmailIliKorisnickomImenu(String identifer) {
		return korisnikRepo.findByKorisnickoImeOrMail(identifer, identifer);
	}
	
	public KorisnikDTO loginKorisnika(KorisnikDTO korisnikDto) {
		Optional<Korisnik> korisnik = nadjiKorisnikaPoEmailIliKorisnickomImenu(korisnikDto.getIdentifier());
		if(!(korisnik.isPresent() && korisnik.get().getSifra().equals(korisnikDto.getSifra())))
			throw new InvalidLoginCredentialsException("Pogresni podaci!");
			
			Korisnik k = korisnik.get();
	        KorisnikDTO dto = new KorisnikDTO();
	        dto.setIdKorisnik(k.getIdKorisnik());
	        dto.setKorisnickoIme(k.getKorisnickoIme());
	        dto.setEmail(k.getMail());
	        dto.setUloga(k.getUloga());

        return dto;

    }


	public void dodajKorisnika(KorisnikDTO dto) {
		Korisnik k = new Korisnik();
		k.setKorisnickoIme(dto.getKorisnickoIme());
		k.setSifra(dto.getSifra());
		k.setMail(dto.getEmail());
		k.setUloga(dto.getUloga());
		korisnikRepo.save(k);
	}

	public List<KorisnikDTO> nadjiKorisnike() {
			List<Korisnik> korisnikLista = korisnikRepo.findAll();
			List<KorisnikDTO> listaDto = new ArrayList<>();
			for(Korisnik k : korisnikLista) {
				KorisnikDTO dto = new KorisnikDTO();
				dto.setIdKorisnik(k.getIdKorisnik());
				dto.setKorisnickoIme(k.getKorisnickoIme());
				dto.setSifra(k.getSifra());
				dto.setEmail(k.getMail());
				dto.setUloga(k.getUloga());
				listaDto.add(dto);
			}
			
		return listaDto;
	}

	public void obrisiKorisnika(Integer korisnikId) {
		korisnikRepo.deleteById(korisnikId);
	}

	public KorisnikDTO nadjiKorisnika(Integer idKorisnika) {
		Korisnik korisnik = korisnikRepo.findById(idKorisnika).get();
		if(korisnik == null) 
				throw new AdminOperationException("Korisnik sa unetim idom ne postoji!");

		KorisnikDTO dto = new KorisnikDTO();
		dto.setKorisnickoIme(korisnik.getKorisnickoIme());
		dto.setIdKorisnik(idKorisnika);
		dto.setUloga(null);
		return dto;
	}
	
	@Transactional
	public void promeniUloguKorisniku(Integer idKorisnik, String uloga) {
		Korisnik k = korisnikRepo.findById(idKorisnik).get();
		if(k==null) throw new AdminOperationException("Korisnik sa unetim idom ne postoji!");
		korisnikRepo.promeniUloguKorisniku(idKorisnik, uloga);
		
	}

}
