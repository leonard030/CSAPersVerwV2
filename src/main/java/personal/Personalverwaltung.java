package personal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import abteilung.Abteilung;
import projekt.Projekt;
import qualifikation.Qualifikation;

public class Personalverwaltung implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6928955922388486062L;
	private static Personalverwaltung instance;

	public Personalverwaltung() {

	};

	public static Personalverwaltung getInstance() {
		if (Personalverwaltung.instance == null) {
			Personalverwaltung.instance = new Personalverwaltung();
		}
		return Personalverwaltung.instance;
	}

	public boolean personalHinzufuegen(Personal persoObj) {
		Abteilung abtObj = new Abteilung();
		Qualifikation qualiObj = new Qualifikation();
		Projekt projObj = new Projekt();
		abtObj.setAbteilungsName(persoObj.getAbteilung());
		qualiObj.setQualifikationsName(persoObj.getQualifikationen());
		projObj.setProjektName(persoObj.getProjekte());
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Personal> resultperso = entityManager.createQuery("from Personal", Personal.class).getResultList();
		List<Abteilung> resultabt = entityManager.createQuery("from Abteilung", Abteilung.class).getResultList();
		for (Personal perso : resultperso) {
			if (perso.getPersonalName().equals(persoObj.getPersonalName())
					&& perso.getPersonalVorname().equals(persoObj.getPersonalVorname())) {
				return true;
			}
		}
		for (Abteilung abt : resultabt) {
			if (abt.getAbteilungsName().equals(abtObj.getAbteilungsName())) {
				entityManager.getTransaction().begin();
				entityManager.merge(persoObj);
				entityManager.getTransaction().commit();
				entityManagerFactory.close();
				return true;
			}
		}
		entityManager.getTransaction().begin();
		entityManager.merge(persoObj);
		entityManager.merge(abtObj);
		entityManager.merge(projObj);
		entityManager.merge(qualiObj);
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		return true;
	}

	public boolean qualifikationHinzufuegen(int persoNummer, String qualiName) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Personal perso = entityManager.find(Personal.class, persoNummer);
		List<Qualifikation> resultQuali = entityManager.createQuery("from Qualifikation", Qualifikation.class)
				.getResultList();
		for (Qualifikation qualiList : resultQuali) {
			if (qualiList.getQualifikationsName().equals(qualiName)) {
				entityManager.getTransaction().begin();
				perso.getQualiList().add(perso.getQualifikationen());
				perso.getQualiList().add(qualiName);
				perso.setQualifikationen(perso.getQualiList().toString());
				entityManager.getTransaction().commit();
				return true;
			}
		}
		Qualifikation qualiObj = new Qualifikation();
		qualiObj.setQualifikationsName(qualiName);

		entityManager.getTransaction().begin();
		perso.getQualiList().add(perso.getQualifikationen());
		perso.getQualiList().add(qualiName);
		perso.setQualifikationen(perso.getQualiList().toString());
		entityManager.merge(qualiObj);
		entityManager.getTransaction().commit();
		return true;
	}

	public boolean projektHinzufuegen(int persoNummer, String projName) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Personal perso = entityManager.find(Personal.class, persoNummer);
		List<Projekt> resultProj = entityManager.createQuery("from Projekt", Projekt.class).getResultList();
		for (Projekt projList : resultProj) {
			if (projList.getProjektName().equals(projName)) {
				entityManager.getTransaction().begin();
				perso.getProjList().add(perso.getProjekte());
				perso.getProjList().add(projName);
				perso.setProjekte(perso.getProjList().toString());
				entityManager.getTransaction().commit();
				return true;
			}

		}
		Projekt projObj = new Projekt();
		projObj.setProjektName(projName);

		entityManager.getTransaction().begin();
		perso.getProjList().add(perso.getProjekte());
		perso.getProjList().add(projName);
		perso.setProjekte(perso.getProjList().toString());
		entityManager.merge(projObj);
		entityManager.getTransaction().commit();
		return true;
	}

	public boolean PersonalAendern(Personal persoObj) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Personal perso = entityManager.find(Personal.class, persoObj.getPersonalNummer());
		if (perso != null) {
			entityManager.getTransaction().begin();
			perso.setPersonalName(persoObj.getPersonalName());
			perso.setPersonalVorname(persoObj.getPersonalVorname());
			perso.setPersonalGeburtsdatum(persoObj.getPersonalGeburtsdatum());
			perso.setPersonalGeschlecht(persoObj.getPersonalGeschlecht());
			perso.setRang(persoObj.getRang());
			perso.setAbteilung(persoObj.getAbteilung());
			perso.setProjekte(persoObj.getProjekte());
			perso.setQualifikationen(persoObj.getQualifikationen());
			entityManager.getTransaction().commit();
			return true;
		}
		return false;
	}

	public boolean PersonalLoeschen(int personalNummerFuerLoeschung) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Personal perso = entityManager.find(Personal.class, personalNummerFuerLoeschung);
		if (perso != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(perso);
			entityManager.getTransaction().commit();
			return true;
		}
		return true;
	}

	public List<Personal> suche(String name) {
		List<Personal> PersoList = new ArrayList<Personal>();
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Personal> result = entityManager.createQuery("from Personal", Personal.class).getResultList();
		for (Personal perso : result) {
			if (perso.getPersonalName().contains(name)) {
				PersoList.add(perso);
			} else if (perso.getPersonalVorname().contains(name)) {
				PersoList.add(perso);
			}
		}
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		return PersoList;
	}
	
	public List<Personal> sucheDurchAbteilung(String name) {
		List<Personal> PersoList = new ArrayList<Personal>();
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Personal> result = entityManager.createQuery("from Personal", Personal.class).getResultList();
		for (Personal perso : result) {
		if (perso.getAbteilung().equals(name)) {
				PersoList.add(perso);
			} 
		}
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		return PersoList;
	}
	
	public List<Personal> sucheDurchProjekt(String name) {
		List<Personal> PersoList = new ArrayList<Personal>();
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Personal> result = entityManager.createQuery("from Personal", Personal.class).getResultList();
		for (Personal perso : result) {
		if (perso.getProjekte().contains(name)) {
				PersoList.add(perso);
			} 
		}
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		return PersoList;
	}
	
	public List<Personal> sucheDurchQualifikation(String name) {
		List<Personal> PersoList = new ArrayList<Personal>();
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Personal> result = entityManager.createQuery("from Personal", Personal.class).getResultList();
		for (Personal perso : result) {
		if (perso.getQualifikationen().contains(name)) {
				PersoList.add(perso);
			} 
		}
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		return PersoList;
	}

	public List<Personal> PersonalAlle() {
		List<Personal> PersoList = new ArrayList<Personal>();
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Personal> result = entityManager.createQuery("from Personal", Personal.class).getResultList();
		for (Personal perso : result) {
			PersoList.add(perso);
		}
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		return PersoList;
	}

}
