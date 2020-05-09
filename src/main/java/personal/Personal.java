package personal;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import abteilung.Abteilung;
import projekt.Projekt;
import qualifikation.Qualifikation;

@ManagedBean
@SessionScoped
public class Personal {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3893263370007180269L;
	private int personalNummer;
	private String personalName;
	private String personalVorname;
	private Date personalGeburtsdatum;
	private char personalGeschlecht;
	private int rang;
	private Personalverwaltung pv;
	private List<Personal> persoList;
	private String abteilung;
	String qualifikationen;
	String projekte;
	private List<String> qualiList = new ArrayList<String>();
	private List<String> projList = new ArrayList<String>();
	
	public Personal(int personalNummer, String personalName, String personalVorname, Date personalGeburtsdatum,
			char personalGeschlecht, String abteilung, String projekte, String qualifikationen) {
		super();
		this.personalNummer = personalNummer;
		this.personalName = personalName;
		this.personalVorname = personalVorname;
		this.personalGeburtsdatum = personalGeburtsdatum;
		this.personalGeschlecht = personalGeschlecht;
		this.abteilung = abteilung;
		this.projekte = projekte;
		this.qualifikationen = qualifikationen;
	}

	public Personal(String personalName, String personalVorname, Date personalGeburtsdatum, char personalGeschlecht, String abteilung, String projekte, String qualifikationen) {
	}

	public Personal() {
	}

	public int getPersonalNummer() {
		return personalNummer;
	}

	public void setPersonalNummer(int personalNummer) {
		this.personalNummer = personalNummer;
	}

	public String getPersonalName() {
		return personalName;
	}

	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}

	public String getPersonalVorname() {
		return personalVorname;
	}

	public void setPersonalVorname(String personalVorname) {
		this.personalVorname = personalVorname;
	}

	public Date getPersonalGeburtsdatum() {
		return personalGeburtsdatum;
	}

	public void setPersonalGeburtsdatum(Date personalGeburtsdatum) {
		this.personalGeburtsdatum = personalGeburtsdatum;
	}

	public char getPersonalGeschlecht() {
		return personalGeschlecht;
	}

	public void setPersonalGeschlecht(char personalGeschlecht) {
		this.personalGeschlecht = personalGeschlecht;
	}

	public int getRang() {
		return rang;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	public List<Personal> getPersoList() {
		return persoList;
	}

	public void setPersoList(List<Personal> persoList) {
		this.persoList = persoList;
	}

	public String getAbteilung() {
		return abteilung;
	}

	public void setAbteilung(String abteilung) {
		this.abteilung = abteilung;
	}

	public List<String> getQualiList() {
		return qualiList;
	}

	public void setQualiList(List<String> qualiList) {
		this.qualiList = qualiList;
	}

	public List<String> getProjList() {
		return projList;
	}

	public void setProjList(List<String> projList) {
		this.projList = projList;
	}

	public String getQualifikationen() {
		return qualifikationen;
	}

	public void setQualifikationen(String qualifikationen) {
		this.qualifikationen = qualifikationen;
	}

	public String getProjekte() {
		return projekte;
	}

	public void setProjekte(String projekte) {
		this.projekte = projekte;
	}

	public void savePerso() {
		pv = new Personalverwaltung();
		pv.personalHinzufuegen(this);
	}

	public void deletePerso(Personal p) {
		pv = new Personalverwaltung();
		pv.PersonalLoeschen(p.personalNummer);
	}

	public List<Personal> searchPerso() {
		pv = new Personalverwaltung();
		persoList = pv.suche(personalName);
		for (Personal perso : persoList) {
			personalName = perso.getPersonalName();
			personalVorname = perso.getPersonalVorname();
			personalGeburtsdatum = perso.getPersonalGeburtsdatum();
			personalGeschlecht = perso.getPersonalGeschlecht();
			rang = perso.getRang();
			abteilung = perso.getAbteilung();
			projekte = perso.getProjekte();
			qualifikationen = perso.getQualiList().toString();
		}
		return persoList;
	}
	
	public List<Personal> searchAbteilung(Abteilung p) {
		pv = new Personalverwaltung();
		persoList = pv.sucheDurchAbteilung(p.getAbteilungsName());
		for (Personal perso : persoList) {
			personalName = perso.getPersonalName();
			personalVorname = perso.getPersonalVorname();
			personalGeburtsdatum = perso.getPersonalGeburtsdatum();
			personalGeschlecht = perso.getPersonalGeschlecht();
			rang = perso.getRang();
			abteilung = perso.getAbteilung();
			projekte = perso.getProjList().toString();
			qualifikationen = perso.getQualiList().toString();
		}
		return persoList;
	}
	
	public List<Personal> searchProjekt(Projekt p) {
		pv = new Personalverwaltung();
		persoList = pv.sucheDurchAbteilung(p.getProjektName());
		for (Personal perso : persoList) {
			personalName = perso.getPersonalName();
			personalVorname = perso.getPersonalVorname();
			personalGeburtsdatum = perso.getPersonalGeburtsdatum();
			personalGeschlecht = perso.getPersonalGeschlecht();
			rang = perso.getRang();
			abteilung = perso.getAbteilung();
			projekte = perso.getProjList().toString();
			qualifikationen = perso.getQualiList().toString();
		}
		return persoList;
	}
	
	public List<Personal> searchQualifikation(Qualifikation p) {
		pv = new Personalverwaltung();
		persoList = pv.sucheDurchAbteilung(p.getQualifikationsName());
		for (Personal perso : persoList) {
			personalName = perso.getPersonalName();
			personalVorname = perso.getPersonalVorname();
			personalGeburtsdatum = perso.getPersonalGeburtsdatum();
			personalGeschlecht = perso.getPersonalGeschlecht();
			rang = perso.getRang();
			abteilung = perso.getAbteilung();
			projekte = perso.getProjList().toString();
			qualifikationen = perso.getQualiList().toString();
		}
		return persoList;
	}

	public List<Personal> zeigePerso() {
		pv = new Personalverwaltung();
		persoList = pv.PersonalAlle();
		for (Personal perso : persoList) {
			personalName = perso.getPersonalName();
			personalVorname = perso.getPersonalVorname();
			personalGeburtsdatum = perso.getPersonalGeburtsdatum();
			personalGeschlecht = perso.getPersonalGeschlecht();
			rang = perso.getRang();
			abteilung = perso.getAbteilung();
			projekte = perso.getProjList().toString().replace("[", "").replace("]", "");
			qualifikationen = perso.getQualiList().toString().replace("[", "").replace("]", "");
		}
		return persoList;
	}
	
	public void updatePerso(Personal p) {
		pv = new Personalverwaltung();
		pv.PersonalAendern(p);
	}
	
	public void addQualifikation(Personal p) {
		pv = new Personalverwaltung();
		pv.qualifikationHinzufuegen(p.personalNummer,p.qualifikationen);
	}
	
	public void addProjekt(Personal p) {
		pv = new Personalverwaltung();
		pv.projektHinzufuegen(p.personalNummer,p.projekte);
	}
}