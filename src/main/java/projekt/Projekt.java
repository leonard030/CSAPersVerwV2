package projekt;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import personal.Personal;
import qualifikation.Qualifikation;

@ManagedBean
@SessionScoped
public class Projekt {

	private int projektNummer;
	private String projektName;
	private String projektFrist;
	private String vonDatum;
	private String bisDatum;
	public Projektverwaltung pv;
	private List<Projekt> projList;
	@ManyToMany(mappedBy = "proj")
	private List<Personal> persoList;
	@ManyToMany
	private List<Qualifikation> qualiList;

	public Projekt(int projektNummer, String projektName, String projektFrist) {
		super();
		this.projektNummer = projektNummer;
		this.projektName = projektName;
		this.projektFrist = projektFrist;
	}

	public Projekt(String projektName, String projektFrist) {
		super();
		this.projektName = projektName;
		this.projektFrist = projektFrist;
	}

	public Projekt(String projektName, String von, String bis) {
		super();
		this.projektName = projektName;
		this.vonDatum = von;
		this.bisDatum = bis;
	}

	public Projekt(int projektNummer, String projektName, String von, String bis) {
		super();
		this.projektName = projektName;
		this.vonDatum = von;
		this.bisDatum = bis;
	}

	public Projekt() {

	}

	public int getProjektNummer() {
		return projektNummer;
	}

	public void setProjektNummer(int projektNummer) {
		this.projektNummer = projektNummer;
	}

	public String getProjektName() {
		return projektName;
	}

	public void setProjektName(String projektName) {
		this.projektName = projektName;
	}

	public String getProjektFrist() {
		return projektFrist;
	}

	public void setProjektFrist(String projektFrist) {
		this.projektFrist = projektFrist;
	}

	public String getVonDatum() {
		return vonDatum;
	}

	public void setVonDatum(String vonDatum) {
		this.vonDatum = vonDatum;
	}

	public String getBisDatum() {
		return bisDatum;
	}

	public void setBisDatum(String bisDatum) {
		this.bisDatum = bisDatum;
	}

	public List<Projekt> getProjList() {
		return projList;
	}

	public void setProjList(List<Projekt> projList) {
		this.projList = projList;
	}

	public List<Personal> getPersoList() {
		return persoList;
	}

	public void setPersoList(List<Personal> persoList) {
		this.persoList = persoList;
	}

	public List<Qualifikation> getQualiList() {
		return qualiList;
	}

	public void setQualiList(List<Qualifikation> qualiList) {
		this.qualiList = qualiList;
	}

	public void saveProjekt() {
		pv = new Projektverwaltung();
		pv.projektHinzufuegen(this);
	}

	public void deleteProjekt(Projekt p) {
		pv = new Projektverwaltung();
		pv.ProjektLoeschen(p.projektNummer);
	}

	public List<Projekt> searchProjekt() {
		pv = new Projektverwaltung();
		projList = pv.suche(projektName);
		for (Projekt proj : projList) {
			projektName = proj.getProjektName();
			projektFrist = proj.getProjektFrist();
		}
		return projList;
	}

	public List<Projekt> zeigeProjekt() {
		pv = new Projektverwaltung();
		projList = pv.ProjektAlle();
		for (Projekt proj : projList) {
			projektName = proj.getProjektName();
			projektFrist = proj.getProjektFrist();
		}
		return projList;
	}

	public void updateProjekt(Projekt p) {
		pv = new Projektverwaltung();
		pv.ProjektAendern(p);
	}
}