package com.example.demo.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import model.Umetnik;

public class IzlozbaDTO {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datumKraja;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datumPocetka;

	private String naslov;

	private String opis;
	
	private Integer idIzlozbe;
	
	private List<Integer> umetnikIds;
	
	private List<Umetnik> umetnici;

	public List<Umetnik> getUmetnici() {
		return umetnici;
	}

	public void setUmetnici(List<Umetnik> umetnici) {
		this.umetnici = umetnici;
	}

	public Integer getIdIzlozbe() {
		return idIzlozbe;
	}

	public void setIdIzlozbe(Integer idIzlozbe) {
		this.idIzlozbe = idIzlozbe;
	}

	public Date getDatumKraja() {
		return datumKraja;
	}

	public void setDatumKraja(Date datumKraja) {
		this.datumKraja = datumKraja;
	}

	public Date getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(Date datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<Integer> getUmetnikIds() {
		return umetnikIds;
	}

	public void setUmetnikIds(List<Integer> umetnikIds) {
		this.umetnikIds = umetnikIds;
	}

}
