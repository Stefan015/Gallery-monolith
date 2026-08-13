package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.IzlozbaDTO;
import com.example.demo.repository.IzlozbaRepository;
import com.example.demo.repository.UmetnikRepository;

import model.Izlozba;
import model.IzlozbaHasUmetnik;
import model.Umetnik;

@Service
public class IzlozbeService {
	
	@Autowired
	IzlozbaRepository izlozbaRepo;
	
	@Autowired
	UmetnikRepository umetnikRepo;
	
	public List<IzlozbaDTO> nadjiIzlozbe() {
	    List<Izlozba> lista = izlozbaRepo.findAll();
	    List<IzlozbaDTO> dtoLista = new ArrayList<>();
	    for(Izlozba i : lista) {
	    	IzlozbaDTO dto = new IzlozbaDTO();
	    	dto.setIdIzlozbe(i.getIdIzlozba());
	        dto.setNaslov(i.getNaslov());
	        dto.setOpis(i.getOpis());
	        dto.setDatumPocetka(i.getDatumPocetka());
	        dto.setDatumKraja(i.getDatumKraja());
	        List<Umetnik> umetnici = new ArrayList<>();

	        for (IzlozbaHasUmetnik veza : i.getIzlozbaHasUmetnik()) {
	            umetnici.add(veza.getUmetnik());
	        }
	        dto.setUmetnici(umetnici);

	        dtoLista.add(dto);
	    }

	    return dtoLista;
	}

	public void dodajIzlozbu(IzlozbaDTO dto) {
		Izlozba izlozba = new Izlozba();
		izlozba.setNaslov(dto.getNaslov());
		izlozba.setOpis(dto.getOpis());
		izlozba.setDatumKraja(dto.getDatumKraja());
		izlozba.setDatumPocetka(dto.getDatumPocetka());
		
		List<IzlozbaHasUmetnik> veze = new ArrayList<>();
		
		for(Integer id : dto.getUmetnikIds()) {
			Umetnik umetnik = umetnikRepo.findById(id)
		            .orElseThrow(() -> new RuntimeException("Umetnik not found: " + id));

		        IzlozbaHasUmetnik veza = new IzlozbaHasUmetnik();

		        veza.setIzlozba(izlozba);
		        veza.setUmetnik(umetnik);

		        veze.add(veza);
		    }
		
	    izlozba.setIzlozbaHasUmetnik(veze);

	    izlozbaRepo.save(izlozba);
		
	}

	public void obrisiIzlozbu(Integer idIzlozbe) {
		izlozbaRepo.deleteById(idIzlozbe);
		
	}

	public IzlozbaDTO nadjiIzlozbu(Integer idIzlozbe) {
		IzlozbaDTO dto = new IzlozbaDTO();
		Izlozba izlozba = izlozbaRepo.findById(idIzlozbe).get();
		dto.setNaslov(izlozba.getNaslov());
		dto.setDatumKraja(izlozba.getDatumKraja());
		dto.setDatumPocetka(izlozba.getDatumPocetka());
		dto.setOpis(izlozba.getOpis());
		dto.setIdIzlozbe(idIzlozbe);
        List<Umetnik> umetnici = new ArrayList<>();

        for (IzlozbaHasUmetnik veza : izlozba.getIzlozbaHasUmetnik()) {
            umetnici.add(veza.getUmetnik());
        }
        dto.setUmetnici(umetnici);
		return dto;
	}

	public void sacuvajPromenuIzlozbe(IzlozbaDTO dto) {
		Izlozba izlozba = izlozbaRepo.findById(dto.getIdIzlozbe()).orElseThrow(() -> new RuntimeException("izlozba ne postoji"));
		izlozba.setNaslov(dto.getNaslov());
		izlozba.setOpis(dto.getOpis());
		izlozba.setDatumKraja(dto.getDatumKraja());
		izlozba.setDatumPocetka(dto.getDatumPocetka());
		
		List<IzlozbaHasUmetnik> postojeciUmetnici = izlozba.getIzlozbaHasUmetnik();
		postojeciUmetnici.clear();

		for(Integer id : dto.getUmetnikIds()) {
			Umetnik umetnik = umetnikRepo.findById(id)
		            .orElseThrow(() -> new RuntimeException("Umetnik not found: " + id));

		        IzlozbaHasUmetnik veza = new IzlozbaHasUmetnik();

		        veza.setIzlozba(izlozba);
		        veza.setUmetnik(umetnik);

		        postojeciUmetnici.add(veza);
		}

	    izlozbaRepo.save(izlozba);
		
	}

}
