package model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class IzlozbaHasUmetnikPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "idIzlozbaGerund")
    private int idIzlozbaGerund;

    @Column(name = "idUmetnikGerund")
    private int idUmetnikGerund;
    
    public IzlozbaHasUmetnikPK() {}

 
    public int getIdIzlozbaGerund() {
		return idIzlozbaGerund;
	}


	public void setIdIzlozbaGerund(int idIzlozbaGerund) {
		this.idIzlozbaGerund = idIzlozbaGerund;
	}


	public int getIdUmetnikGerund() {
		return idUmetnikGerund;
	}


	public void setIdUmetnikGerund(int idUmetnikGerund) {
		this.idUmetnikGerund = idUmetnikGerund;
	}



	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IzlozbaHasUmetnikPK)) return false;

        IzlozbaHasUmetnikPK that = (IzlozbaHasUmetnikPK) o;

        return idIzlozbaGerund == that.idIzlozbaGerund &&
               idUmetnikGerund == that.idUmetnikGerund;
    }

    @Override
    public int hashCode() {
        int result = idIzlozbaGerund;
        result = 31 * result + idUmetnikGerund;
        return result;
    }
}
