package model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "izlozba_has_umetnik")
public class IzlozbaHasUmetnik {

    @EmbeddedId
    private IzlozbaHasUmetnikPK id = new IzlozbaHasUmetnikPK();

    @ManyToOne
    @MapsId("idIzlozbaGerund")
    @JoinColumn(name = "idIzlozbaGerund")
    private Izlozba izlozba;

    @ManyToOne
    @MapsId("idUmetnikGerund")
    @JoinColumn(name = "idUmetnikGerund")
    private Umetnik umetnik;

	public IzlozbaHasUmetnikPK getId() {
		return id;
	}

	public void setId(IzlozbaHasUmetnikPK id) {
		this.id = id;
	}

	public Izlozba getIzlozba() {
		return izlozba;
	}

	public void setIzlozba(Izlozba izlozba) {
		this.izlozba = izlozba;
	}

	public Umetnik getUmetnik() {
		return umetnik;
	}

	public void setUmetnik(Umetnik umetnik) {
		this.umetnik = umetnik;
	}

}
