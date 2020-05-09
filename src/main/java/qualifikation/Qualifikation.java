package qualifikation;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.ManyToMany;

import personal.Personal;
import projekt.Projekt;

@ManagedBean
@SessionScoped
public class Qualifikation {

	private int qualifikationsNummer;
	private String qualifikationsArt;
	private String qualifikationsName;
	public Qualifikationsverwaltung qv;
	private List<Qualifikation> qualiList;
	@ManyToMany (mappedBy = "quali")
	private List<Personal> persoList;
	@ManyToMany (mappedBy = "qualiList")
	private List<Projekt> proj;

	public Qualifikation(String qualifikationsArt, String qualifikationsName) {
		super();
		this.qualifikationsArt = qualifikationsArt;
		this.qualifikationsName = qualifikationsName;
	}

	public Qualifikation(int qualifikationsNummer, String qualifikationsArt, String qualifikationsName) {
		super();
		this.qualifikationsNummer = qualifikationsNummer;
		this.qualifikationsArt = qualifikationsArt;
		this.qualifikationsName = qualifikationsName;
	}

	public Qualifikation() {

	}

	/**
	 * Die Qualifikationsnummer wird zurückgegben
	 * 
	 * @return
	 */
	public int getQualifikationsNummer() {
		return qualifikationsNummer;
	}

	public void setQualifikationsNummer(int qualifikationsnummer) {
		this.qualifikationsNummer = qualifikationsnummer;
	}

	/**
	 * Die Qualifikationsart wird zurückgegben
	 * 
	 * @return
	 */
	public String getQualifikationsArt() {
		return qualifikationsArt;
	}

	/**
	 * Die Qualifikationsart wird festgelegt
	 * 
	 * @param qualifikationsArt
	 */
	public void setQualifikationsArt(String qualifikationsArt) {
		this.qualifikationsArt = qualifikationsArt;
	}

	/**
	 * Der Name der Qualifikation wird zurückgegben
	 * 
	 * @return
	 */
	public String getQualifikationsName() {
		return qualifikationsName;
	}

	/**
	 * Der Name der Qualifikation wird festgelegt
	 * 
	 * @param qualifikationsName
	 */
	public void setQualifikationsName(String qualifikationsName) {
		this.qualifikationsName = qualifikationsName;
	}

	public List<Qualifikation> getQualiList() {
		return qualiList;
	}

	public void setQualiList(List<Qualifikation> qualiList) {
		this.qualiList = qualiList;
	}

	public List<Personal> getPersoList() {
		return persoList;
	}

	public void setPersoList(List<Personal> persoList) {
		this.persoList = persoList;
	}

	public List<Projekt> getProj() {
		return proj;
	}

	public void setProj(List<Projekt> proj) {
		this.proj = proj;
	}
	
	public String toString() {
		return this.qualifikationsName;
	}

	public void saveQuali() {
		qv = new Qualifikationsverwaltung();
		qv.qualifikationHinzufuegen(this);
	}

	public void deleteQuali(Qualifikation quali) {
		qv = new Qualifikationsverwaltung();
		qv.QualifikationLoeschen(quali.qualifikationsNummer);
	}

	public List<Qualifikation> searchQuali() {
		qv = new Qualifikationsverwaltung();
		qualiList = qv.suche(qualifikationsName);
		for (Qualifikation quali : qualiList) {
			qualifikationsArt = quali.getQualifikationsArt();
			qualifikationsName = quali.getQualifikationsName();
		}
		return qualiList;
	}

	public List<Qualifikation> zeigeQuali() {
		qv = new Qualifikationsverwaltung();
		qualiList = qv.QualifikationAlle();
		for (Qualifikation quali : qualiList) {
			qualifikationsArt = quali.getQualifikationsArt();
			qualifikationsName = quali.getQualifikationsName();
		}
		return qualiList;
	}

	public void updateQuali(Qualifikation q) {
		qv = new Qualifikationsverwaltung();
		qv.QualifikationAendern(q);
	}
}