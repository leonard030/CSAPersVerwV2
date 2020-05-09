package projekt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Projektverwaltung implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7319769488498178480L;
	private static Projektverwaltung instance;

	public Projektverwaltung() {
	};

	public static Projektverwaltung getInstance() {
		if (Projektverwaltung.instance == null) {
			Projektverwaltung.instance = new Projektverwaltung();
		}
		return Projektverwaltung.instance;
	}

	public boolean projektHinzufuegen(Projekt projObj) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Projekt> result = entityManager.createQuery("from Projekt", Projekt.class).getResultList();
		for (Projekt proj : result) {
			if (proj.getProjektName().equals(projObj.getProjektName())) {
				return true;
			}
		}
		entityManager.getTransaction().begin();
		entityManager.merge(projObj);
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		return true;
	}

	public boolean ProjektAendern(Projekt projObj) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Projekt proj = entityManager.find(Projekt.class, projObj.getProjektNummer());
		if (proj != null) {
			entityManager.getTransaction().begin();
			proj.setProjektName(projObj.getProjektName());
			proj.setProjektFrist(projObj.getProjektFrist());
			entityManager.getTransaction().commit();
			return true;
		}
		return false;
	}

	public boolean ProjektLoeschen(int projektNummerFuerLoeschung) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Projekt proj = entityManager.find(Projekt.class, projektNummerFuerLoeschung);
		if (proj != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(proj);
			entityManager.getTransaction().commit();
			return true;
		}
		return true;
	}

	public List<Projekt> suche(String name) {
		List<Projekt> ProjList = new ArrayList<Projekt>();
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Projekt> result = entityManager.createQuery("from Projekt", Projekt.class).getResultList();
		for (Projekt proj : result) {
			if (proj.getProjektName().contains(name)) {
				ProjList.add(proj);
			}
		}
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		return ProjList;
	}

	public List<Projekt> ProjektAlle() {
		List<Projekt> ProjList = new ArrayList<Projekt>();
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Projekt> result = entityManager.createQuery("from Projekt", Projekt.class).getResultList();
		for (Projekt proj : result) {
			ProjList.add(proj);
		}
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		return ProjList;
	}

}
