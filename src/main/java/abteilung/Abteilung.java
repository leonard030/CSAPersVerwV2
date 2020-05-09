package abteilung;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import personal.Personal;

@ManagedBean
@SessionScoped
public class Abteilung implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8188251355489755481L;
	public int abteilungsNummer;
	public String abteilungsName;
	public String fuehrungskraftVorname;
	public String fuehrungskraftName;
	public Abteilungsverwaltung av;
	private List<Abteilung> abteilungList;
	@OneToMany(mappedBy = "abt")
	private List<Personal> persoList;
	private Personal perso;
	
	public Abteilung(int abteilungsNummer, String abteilungsName, String fuehrungskraftVorname,
			String fuehrungskraftName) {
		super();
		this.abteilungsNummer = abteilungsNummer;
		this.abteilungsName = abteilungsName;
		this.fuehrungskraftVorname = fuehrungskraftVorname;
		this.fuehrungskraftName = fuehrungskraftName;
	}
	
	public Abteilung() {
	}

	public int getAbteilungsNummer() {
		return abteilungsNummer;
	}

	public void setAbteilungsNummer(int abteilungsNummer) {
		this.abteilungsNummer = abteilungsNummer;
	}

	public Abteilung(String abteilungsNameNeu) {
		this.abteilungsName = abteilungsNameNeu;
	}

	public String getAbteilungsName() {
		return abteilungsName;
	}

	public void setAbteilungsName(String abteilungsName) {
		this.abteilungsName = abteilungsName;
	}

	public String getFuehrungskraftVorname() {
		return fuehrungskraftVorname;
	}

	public void setFuehrungskraftVorname(String fuehrungskraftVorname) {
		this.fuehrungskraftVorname = fuehrungskraftVorname;
	}

	public String getFuehrungskraftName() {
		return fuehrungskraftName;
	}

	public void setFuehrungskraftName(String fuehrungskraftName) {
		this.fuehrungskraftName = fuehrungskraftName;
	}

	public List<Abteilung> getAbteilungList() {
		return abteilungList;
	}

	public void setAbteilungList(List<Abteilung> abteilungList) {
		this.abteilungList = abteilungList;
	}

	public List<Personal> getPersoList() {
		return persoList;
	}

	public void setPersoList(List<Personal> persoList) {
		this.persoList = persoList;
	}

	public void saveAbteilung() {
		av = new Abteilungsverwaltung();
		av.abteilungHinzufuegen(this);
	}

	public void deleteAbteilung(Abteilung a) {
		av = new Abteilungsverwaltung();
		av.abteilungLoeschen(a.abteilungsNummer);
	}
	

	public Personal getPerso() {
		return perso;
	}

	public void setPerso(Personal perso) {
		this.perso = perso;
	}

	public List<Abteilung> searchAbteilung() {
		av = new Abteilungsverwaltung();
		abteilungList = av.suche(abteilungsName);
		for (Abteilung abt : abteilungList) {
			abteilungsName = abt.getAbteilungsName();
			fuehrungskraftName = abt.getFuehrungskraftName();
			fuehrungskraftVorname = abt.getFuehrungskraftVorname();
		}
		return abteilungList;
	}

	public List<Abteilung> zeigeAbteilung() {
		av = new Abteilungsverwaltung();
		abteilungList = av.AbteilungAlle();
		for (Abteilung abt : abteilungList) {
			abteilungsName = abt.getAbteilungsName();
			fuehrungskraftName = abt.getFuehrungskraftName();
			fuehrungskraftVorname = abt.getFuehrungskraftVorname();
		}
		return abteilungList;
	}

	public void updateAbteilung(Abteilung a) {
		av = new Abteilungsverwaltung();
		av.abteilungAendern(a);
	}

}
