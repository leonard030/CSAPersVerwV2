package qualifikation;

//import de.persverw.personal.PersonalListe;
//import de.persverw.personal.Personalverwaltung;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


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

//    @SuppressWarnings({ "unchecked", "unused" })
//    public List getStudentById(int studentId) { 
//        Qualifikation particularStuDObj = new Qualifikation();
//             
//        try {
//            transObj = sessionObj.beginTransaction();
//            List<Qualifikation> particularStudentList  = sessionObj.createQuery ("from Qualifikation", Qualifikation.class)	.getResultList();                  
// 
//            // XHTML Response Text
//            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findStudentById",  studentId);
//        } catch(Exception exceptionObj) {
//            exceptionObj.printStackTrace();
//        } finally {
//            transObj.commit();
//        }
//    //    return particularStudentList;
//    }

	public boolean qualifikationHinzufuegen(Qualifikation qualiObj) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Qualifikation> result = entityManager.createQuery("from Qualifikation", Qualifikation.class)
				.getResultList();
		for (Qualifikation qualifikation : result) {
			if (qualifikation.getQualifikationsArt().equals(qualiObj.getQualifikationsArt())
					&& qualifikation.getQualifikationsName().equals(qualiObj.getQualifikationsName())) {
				return true;
			}
		}
		entityManager.getTransaction().begin();
		entityManager.merge(qualiObj);
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		return true;
	}

//	public boolean QualifikationAendern (Qualifikation qualiObj) {
//		EntityManagerFactory entityManagerFactory = Persistence
//				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		List<Qualifikation> result = entityManager.createQuery("from Qualifikation", Qualifikation.class)
//				.getResultList();
//		for (Qualifikation qualifikation : result) {
//			if (qualifikation.getQualifikationsNummer() == qualiObj.getQualifikationsNummer()) {
//				entityManager.getTransaction().begin();
//				qualifikation.setQualifikationsArt(qualiObj.getQualifikationsArt());
//				qualifikation.setQualifikationsName(qualiObj.getQualifikationsName());
//				entityManager.getTransaction().commit();
//				entityManagerFactory.close();
//				return true;
//			}
//		}
//		return false;
//	}

	public boolean QualifikationAendern(Qualifikation qualiObj) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Qualifikation quali = entityManager.find(Qualifikation.class, qualiObj.getQualifikationsNummer());
		if (quali != null) {
			entityManager.getTransaction().begin();
			quali.setQualifikationsArt(qualiObj.getQualifikationsArt());
			quali.setQualifikationsName(qualiObj.getQualifikationsName());
			entityManager.getTransaction().commit();
			return true;
		}
		return false;
	}

//	public boolean QualifikationLoeschen(int qualifikationsNummerFuerLoeschung) {
//		EntityManagerFactory entityManagerFactory = Persistence
//				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		entityManager.getTransaction().begin();
//		List<Qualifikation> result = entityManager.createQuery("from Qualifikation", Qualifikation.class)
//				.getResultList();
//		for (Qualifikation qualifikation : result) {
//			if (qualifikation.getQualifikationsNummer() == qualifikationsNummerFuerLoeschung) {
//				Query q = entityManager.createQuery("delete from Qualifikation where qualifikationsNummer = '"
//						+ qualifikationsNummerFuerLoeschung + "' ");
//				q.executeUpdate();
//				entityManager.getTransaction().commit();
//				entityManagerFactory.close();
//				System.out.println("Qualifikation wurde erfolgreich geloescht!");
//				return true;
//			}
//		}
//		System.out
//				.println("Keinen Qualifikation unter der Nummer: " + qualifikationsNummerFuerLoeschung + " gefunden!");
//		return true;
//	}

	public boolean QualifikationLoeschen(int qualifikationsNummerFuerLoeschung) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Qualifikation quali = entityManager.find(Qualifikation.class, qualifikationsNummerFuerLoeschung);
		if (quali != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(quali);
			entityManager.getTransaction().commit();
			return true;
		}
		return true;
	}

	public List<Qualifikation> suche(String name) {
		List<Qualifikation> QualiList = new ArrayList<Qualifikation>();
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Qualifikation> result = entityManager.createQuery("from Qualifikation", Qualifikation.class)
				.getResultList();
		for (Qualifikation qualifikation : result) {
			if (qualifikation.getQualifikationsName().contains(name)) {
				QualiList.add(qualifikation);
			} else if (qualifikation.getQualifikationsArt().contains(name)) {
				QualiList.add(qualifikation);
			}
		}

		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		return QualiList;
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

	public List<Qualifikation> QualifikationAlle() {
		List<Qualifikation> QualiList = new ArrayList<Qualifikation>();
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.personalverwaltung.jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Qualifikation> result = entityManager.createQuery("from Qualifikation", Qualifikation.class)
				.getResultList();
		for (Qualifikation qualifikation : result) {
			QualiList.add(qualifikation);
		}

		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		return QualiList;
	}

}
