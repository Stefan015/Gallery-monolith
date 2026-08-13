package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;

/**
 * The persistent class for the slika database table.
 * 
 */
@Entity
@NamedQuery(name="Slika.findAll", query="SELECT s FROM Slika s")
public class Slika implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSlika;

	private int cena;

	@Temporal(TemporalType.DATE)
	private Date datumNastanka;

	private String ime;

	private String status;

	private String tehnika;

	private String urlSlike;

	@ManyToOne
	@JoinColumn(name="idNarudzbinaFk")
	private Narudzbina narudzbina;


	//bi-directional many-to-one association to Umetnik
	@ManyToOne
	@JoinColumn(name="idUmetnikFk")
	private Umetnik umetnik;

	public Slika() {
	}

	public int getIdSlika() {
		return this.idSlika;
	}

	public void setIdSlika(int idSlika) {
		this.idSlika = idSlika;
	}

	public int getCena() {
		return this.cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public Date getDatumNastanka() {
		return this.datumNastanka;
	}

	public void setDatumNastanka(Date datumNastanka) {
		this.datumNastanka = datumNastanka;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTehnika() {
		return this.tehnika;
	}

	public void setTehnika(String tehnika) {
		this.tehnika = tehnika;
	}

	public String getUrlSlike() {
		return this.urlSlike;
	}

	public void setUrlSlike(String urlSlike) {
		this.urlSlike = urlSlike;
	}

	public Umetnik getUmetnik() {
		return this.umetnik;
	}

	public void setUmetnik(Umetnik umetnik) {
		this.umetnik = umetnik;
	}

	public Narudzbina getNarudzbina() {
		return narudzbina;
	}

	public void setNarudzbina(Narudzbina narudzbina) {
		this.narudzbina = narudzbina;
	}

}