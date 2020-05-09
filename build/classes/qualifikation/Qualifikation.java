package qualifikation;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*; 


@Entity
@Table(name = "qualifikation")
@ManagedBean(value="qualifikation")
@SessionScoped
public class Qualifikation {

	@Id
	@Column(name = "qualifikationsNummer")
	@GeneratedValue(generator = "incrementor")
	@GenericGenerator(name = "incrementor", strategy = "increment")
	private int qualifikationsNummer;

	@Column(name = "qualifikationsArt")
	private String qualifikationsArt;

	@Column(name = "qualifikationsName")
	private String qualifikationsName;

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

	/**
	 *
	 * @return
	 */
	public String toString() {
		return "Art: " + this.qualifikationsArt + ", " + "Name: " + this.qualifikationsName + "; ";
	}

}