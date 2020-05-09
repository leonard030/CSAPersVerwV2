package abteilung;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Abteilungsverwaltung implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -252253825431564134L;
	private static Abteilungsverwaltung instance;

	public Abteilungsverwaltung() {
	};

	public static Abteilungsverwaltung getInstance() {
		if (Abteilungsverwaltung.instance == null) {
			Abteilungsverwaltung.instance = new Abteilungsverwaltung();
		}
		return Abteilungsverwaltung.instance;
	}

	public boolean abteilungHinzufuegen(Abteilung abtObj) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Abteilung> result = entityManager.createQuery("from Abteilung", Abteilung.class).getResultList();
		for (Abteilung abteilung : result) {
			if (abteilung.getAbteilungsName().equals(abtObj.getAbteilungsName())) {
				return true;
			}
		}
		entityManager.getTransaction().begin();
		entityManager.merge(abtObj);
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		return true;
	}

	public boolean abteilungAendern(Abteilung abtObj) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Abteilung abt = entityManager.find(Abteilung.class, abtObj.getAbteilungsNummer());
		if (abt != null) {
			entityManager.getTransaction().begin();
			abt.setAbteilungsName(abtObj.getAbteilungsName());
			abt.setFuehrungskraftName(abtObj.getFuehrungskraftName());
			abt.setFuehrungskraftVorname(abtObj.getFuehrungskraftVorname());
			entityManager.getTransaction().commit();
			return true;
		}
		return false;
	}

	public boolean abteilungLoeschen(int abteilungsNummerFuerLoeschung) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Abteilung abt = entityManager.find(Abteilung.class, abteilungsNummerFuerLoeschung);
		if (abt != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(abt);
			entityManager.getTransaction().commit();
			return true;
		}
		return true;
	}

	public List<Abteilung> suche(String name) {
		List<Abteilung> AbtList = new ArrayList<Abteilung>();
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Abteilung> result = entityManager.createQuery("from Abteilung", Abteilung.class).getResultList();
		for (Abteilung abt : result) {
			if (abt.getAbteilungsName().contains(name)) {
				AbtList.add(abt);
			}
		}
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		return AbtList;
	}

	public List<Abteilung> AbteilungAlle() {
		List<Abteilung> AbtList = new ArrayList<Abteilung>();
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Abteilung> result = entityManager.createQuery("from Abteilung", Abteilung.class)
				.getResultList();
		for (Abteilung abt : result) {
			AbtList.add(abt);
		}
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		return AbtList;
	}

}