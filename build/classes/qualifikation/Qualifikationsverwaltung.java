package qualifikation;
//import de.persverw.personal.PersonalListe;
//import de.persverw.personal.Personalverwaltung;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Qualifikationsverwaltung implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8584552726329896529L;
	private static Qualifikationsverwaltung instance;
//	public QualifikationsListe qualifikationsListe = new QualifikationsListe();
//	public PersonalListe personalListe = new PersonalListe();
//	public Personalverwaltung persVerw = new Personalverwaltung();

	public Qualifikationsverwaltung() {
	};

	public static Qualifikationsverwaltung getInstance() {
		if (Qualifikationsverwaltung.instance == null) {
			Qualifikationsverwaltung.instance = new Qualifikationsverwaltung();
		}
		return Qualifikationsverwaltung.instance;
	}

	public boolean qualifikationNeu(Qualifikation qualineu) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		List<Qualifikation> result = entityManager.createQuery("from Qualifikation", Qualifikation.class)
				.getResultList();
		for (Qualifikation qualifikation : result) {
			if (qualifikation.equals(qualineu)) {
				return true;
			}
		}

		entityManager.persist(qualineu);
		entityManager.getTransaction().commit();
		entityManagerFactory.close();

		return true;
	}

	public boolean qualifikationNeuDurchPersonalBearbeiten(String qualifikationsArtNeu, String qualifikationsNameNeu) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Qualifikation quali = new Qualifikation();
		List<Qualifikation> result = entityManager.createQuery("from Qualifikation", Qualifikation.class)
				.getResultList();
		for (Qualifikation qualifikation : result) {
			if (qualifikation.getQualifikationsArt().equals(qualifikationsArtNeu)
					&& qualifikation.getQualifikationsName().equals(qualifikationsNameNeu)) {
				return true;
			}
		}

		quali.setQualifikationsArt(qualifikationsArtNeu);
		quali.setQualifikationsName(qualifikationsNameNeu);

		entityManager.persist(quali);
		entityManager.getTransaction().commit();

		entityManagerFactory.close();
		return true;
	}

	public boolean QualifikationAendernArt(int qualifikationsNummer, String qualifikationsArtNeu) {
		int treffer = 0;
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Qualifikation> result = entityManager.createQuery("from Qualifikation", Qualifikation.class)
				.getResultList();
		for (Qualifikation qualifikation : result) {
			if (qualifikation.getQualifikationsNummer() == qualifikationsNummer) {
				qualifikation.setQualifikationsArt(qualifikationsArtNeu);
				treffer++;
				entityManager.getTransaction().commit();
				entityManagerFactory.close();
				System.out.println("Art wurde erfolgreich auf " + qualifikationsArtNeu + " geaendert!");
				return true;
			}
		}
		if (treffer <= 0) {
			System.out.println("Keinen Qualifikation unter der Nummer: " + qualifikationsNummer + " gefunden!");
		}
		return false;
	}

	public boolean QualifikationAendernName(int qualifikationsNummer, String qualifikationsNameNeu) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Qualifikation> result = entityManager.createQuery("from Qualifikation", Qualifikation.class)
				.getResultList();
		for (Qualifikation qualifikation : result) {
			if (qualifikation.getQualifikationsNummer() == qualifikationsNummer) {
				qualifikation.setQualifikationsName(qualifikationsNameNeu);
				entityManager.getTransaction().commit();
				entityManagerFactory.close();
				System.out.println("Name wurde erfolgreich auf " + qualifikationsNameNeu + " geaendert!");
				return true;
			}
		}
		System.out.println("Keinen Qualifikation unter der Nummer: " + qualifikationsNummer + " gefunden!");
		return true;
	}

	public boolean QualifikationLoeschen(int qualifikationsNummerFuerLoeschung) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Qualifikation> result = entityManager.createQuery("from Qualifikation", Qualifikation.class)
				.getResultList();
		for (Qualifikation qualifikation : result) {
			if (qualifikation.getQualifikationsNummer() == qualifikationsNummerFuerLoeschung) {
				Query q = entityManager.createQuery("delete from Qualifikation where qualifikationsNummer = '"
						+ qualifikationsNummerFuerLoeschung + "' ");
				q.executeUpdate();
				entityManager.getTransaction().commit();
				entityManagerFactory.close();
				System.out.println("Qualifikation wurde erfolgreich geloescht!");
				return true;
			}
		}
		System.out
				.println("Keinen Qualifikation unter der Nummer: " + qualifikationsNummerFuerLoeschung + " gefunden!");
		return true;
	}

	public void suche(String name) {
		int treffer = 0;
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Qualifikation> result = entityManager.createQuery("from Qualifikation", Qualifikation.class)
				.getResultList();
		for (Qualifikation qualifikation : result) {
			if (qualifikation.getQualifikationsName().contains(name)) {

				System.out.println("Nummer: " + qualifikation.getQualifikationsNummer() + "\n" + "Art: "
						+ qualifikation.getQualifikationsArt() + "\n" + "Name: " + qualifikation.getQualifikationsName()
						+ "\n");
				treffer++;
			} else if (qualifikation.getQualifikationsArt().contains(name)) {
				System.out.println("Nummer: " + qualifikation.getQualifikationsNummer() + "\n" + "Art: "
						+ qualifikation.getQualifikationsArt() + "\n" + "Name: " + qualifikation.getQualifikationsName()
						+ "\n");
				treffer++;
			}
		}
		if (treffer <= 0) {
			System.out
					.println("Es wurde keine Qualifikation mit dem Namen " + name + " oder einem aehnlichen gefunden");
		}

		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}

	public void sortierenNachNameASC() {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Qualifikation> result = entityManager
				.createQuery("from Qualifikation order by qualifikationsName asc", Qualifikation.class).getResultList();
		for (Qualifikation qualifikation : result) {
			System.out.println();
			System.out.println("Nummer:   " + qualifikation.getQualifikationsNummer());
			System.out.println("Art:      " + qualifikation.getQualifikationsArt());
			System.out.println("Name:     " + qualifikation.getQualifikationsName());
			System.out.println("-------------------------------------");
		}
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}

	public void sortierenNachNameDESC() {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Qualifikation> result = entityManager
				.createQuery("from Qualifikation order by qualifikationsName desc", Qualifikation.class)
				.getResultList();
		for (Qualifikation qualifikation : result) {
			System.out.println();
			System.out.println("Nummer:   " + qualifikation.getQualifikationsNummer());
			System.out.println("Art:      " + qualifikation.getQualifikationsArt());
			System.out.println("Name:     " + qualifikation.getQualifikationsName());
			System.out.println("-------------------------------------");
		}
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}

	public void sortierenNachArtASC() {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Qualifikation> result = entityManager
				.createQuery("from Qualifikation order by qualifikationsArt asc", Qualifikation.class).getResultList();
		for (Qualifikation qualifikation : result) {
			System.out.println();
			System.out.println("Nummer:   " + qualifikation.getQualifikationsNummer());
			System.out.println("Art:      " + qualifikation.getQualifikationsArt());
			System.out.println("Name:     " + qualifikation.getQualifikationsName());
			System.out.println("-------------------------------------");
		}
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}

	public void sortierenNachArtDESC() {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Qualifikation> result = entityManager
				.createQuery("from Qualifikation order by qualifikationsArt desc", Qualifikation.class).getResultList();
		for (Qualifikation qualifikation : result) {
			System.out.println();
			System.out.println("Nummer:   " + qualifikation.getQualifikationsNummer());
			System.out.println("Art:      " + qualifikation.getQualifikationsArt());
			System.out.println("Name:     " + qualifikation.getQualifikationsName());
			System.out.println("-------------------------------------");
		}
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}

	public void print() {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Qualifikation> result = entityManager.createQuery("from Qualifikation", Qualifikation.class)
				.getResultList();
		for (Qualifikation qualifikation : result) {
			System.out.println();
			System.out.println("Nummer:   " + qualifikation.getQualifikationsNummer());
			System.out.println("Art:      " + qualifikation.getQualifikationsArt());
			System.out.println("Name:     " + qualifikation.getQualifikationsName());
			System.out.println("-------------------------------------");
		}
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}

}
