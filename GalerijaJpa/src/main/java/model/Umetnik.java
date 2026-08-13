package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the umetnik database table.
 * 
 */
@Entity
@NamedQuery(name="Umetnik.findAll", query="SELECT u FROM Umetnik u")
public class Umetnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUmetnik;

	@Lob
	private String biografija;

	@Temporal(TemporalType.DATE)
	private Date datumRodjenja;

	@Temporal(TemporalType.DATE)
	private Date datumSmrti;

	private String ime;

	private String mestoRodjenja;

	//bi-directional many-to-one association to Slika
	@OneToMany(mappedBy="umetnik", cascade = CascadeType.REMOVE)
	private List<Slika> slikas;


	@OneToMany(mappedBy = "umetnik")
	private List<IzlozbaHasUmetnik> veze;

	public Umetnik() {
	}

	public int getIdUmetnik() {
		return this.idUmetnik;
	}

	public void setIdUmetnik(int idUmetnik) {
		this.idUmetnik = idUmetnik;
	}

	public String getBiografija() {
		return this.biografija;
	}

	public void setBiografija(String biografija) {
		this.biografija = biografija;
	}

	public Date getDatumRodjenja() {
		return this.datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public Date getDatumSmrti() {
		return this.datumSmrti;
	}

	public void setDatumSmrti(Date datumSmrti) {
		this.datumSmrti = datumSmrti;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getMestoRodjenja() {
		return this.mestoRodjenja;
	}

	public void setMestoRodjenja(String mestoRodjenja) {
		this.mestoRodjenja = mestoRodjenja;
	}

	public List<Slika> getSlikas() {
		return this.slikas;
	}

	public void setSlikas(List<Slika> slikas) {
		this.slikas = slikas;
	}

	public Slika addSlika(Slika slika) {
		getSlikas().add(slika);
		slika.setUmetnik(this);

		return slika;
	}

	public Slika removeSlika(Slika slika) {
		getSlikas().remove(slika);
		slika.setUmetnik(null);

		return slika;
	}

}