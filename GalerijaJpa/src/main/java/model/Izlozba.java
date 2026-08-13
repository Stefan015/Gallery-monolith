package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the izlozba database table.
 * 
 */
@Entity
@NamedQuery(name="Izlozba.findAll", query="SELECT i FROM Izlozba i")
public class Izlozba implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idIzlozba;

	@Temporal(TemporalType.DATE)
	private Date datumKraja;

	@Temporal(TemporalType.DATE)
	private Date datumPocetka;

	private String naslov;

	private String opis;

	@OneToMany(mappedBy="izlozba",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<IzlozbaHasUmetnik> umetniks;

	public Izlozba() {
	}

	public int getIdIzlozba() {
		return this.idIzlozba;
	}

	public void setIdIzlozba(int idIzlozba) {
		this.idIzlozba = idIzlozba;
	}

	public Date getDatumKraja() {
		return this.datumKraja;
	}

	public void setDatumKraja(Date datumKraja) {
		this.datumKraja = datumKraja;
	}

	public Date getDatumPocetka() {
		return this.datumPocetka;
	}

	public void setDatumPocetka(Date datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public String getNaslov() {
		return this.naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<IzlozbaHasUmetnik> getIzlozbaHasUmetnik() {
		return this.umetniks;
	}

	public void setIzlozbaHasUmetnik(List<IzlozbaHasUmetnik> umetniks) {
		this.umetniks = umetniks;
	}

}