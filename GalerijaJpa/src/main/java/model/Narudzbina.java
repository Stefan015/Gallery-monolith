package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQuery(name="Narudzbina.findAll", query="SELECT n FROM Narudzbina n")
public class Narudzbina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNarudzbina;

	@Temporal(TemporalType.DATE)
	private Date datum;

	private int ukupnaCena;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="idKorisnikFk")
	private Korisnik korisnik;

	@OneToMany(mappedBy="narudzbina")
	private List<Slika> slikaList;

	public Narudzbina() {
	}

	public int getIdNarudzbina() {
		return this.idNarudzbina;
	}

	public void setIdNarudzbina(int idNarudzbina) {
		this.idNarudzbina = idNarudzbina;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getUkupnaCena() {
		return this.ukupnaCena;
	}

	public void setUkupnaCena(int ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public List<Slika> getSlikaList() {
		return slikaList;
	}

	public void setSlikaList(List<Slika> slikaList) {
		this.slikaList = slikaList;
	}

}