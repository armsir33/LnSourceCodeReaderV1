package db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DBOperation {
	private EntityManager em;

	public DBOperation() {
		final String PERSISTENCE_UNIT_NAME = "APIEntity";
		EntityManagerFactory factory;
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
	}

	public EntityManager getEm() {
		return em;
	}

	public void add(String key, String value) {
		APIEntity entry = em.find(APIEntity.class, key);
		if (entry == null) {
			em.getTransaction().begin();
			APIEntity apiEntity = new APIEntity();
			apiEntity.setApi(key);
			apiEntity.setImpl(value.getBytes());
			em.persist(apiEntity);
			em.getTransaction().commit();
		}
	}

	public void remove(String key) {
		APIEntity entry = em.find(APIEntity.class, key);
		if (entry != null) {
			em.getTransaction().begin();
			em.remove(entry);
			em.getTransaction().commit();
		}
	}

	public String get(String key) {
		APIEntity entry = em.find(APIEntity.class, key);
		if(entry != null) 
			return new String(entry.getImpl());
		else
			return null;
	}

	public void update(String key, String value) {
		APIEntity entry = em.find(APIEntity.class, key);
		if (entry != null) {
			em.getTransaction().begin();
			entry.setImpl(value.getBytes());
			em.getTransaction().commit();
		}
	}

	public void clear() {
		em.getTransaction().begin();
		em.createQuery("DELETE FROM APIEntity").executeUpdate();
		em.getTransaction().commit();
	}
	
	public void list() {
		Query q = em.createQuery("select t from APIEntity t");
		List<APIEntity> apiList = q.getResultList();
		for (APIEntity api : apiList) {
			System.out.println(api);
		}
		System.out.println("Size: " + apiList.size());
	}
	
	public void close() {
		em.close();
	}
}
