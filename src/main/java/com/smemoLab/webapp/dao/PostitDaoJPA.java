package com.smemoLab.webapp.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import com.smemoLab.webapp.entity.Postit;

public class PostitDaoJPA implements PostitDao {

	private static PostitDaoJPA instance;
	
	private PostitDaoJPA() {
		
	}
	public static PostitDaoJPA getInstance() {
			if (instance == null) {
				instance = new PostitDaoJPA();
			}
			return instance;
		}
	@Override
	public List<Postit> getAllPostIt() {
		EntityManager manager = FactoryMysqlJPA.getEntityManager();
		Query query = manager.createQuery("SELECT p FROM Postit p");
		List<Postit> postit = query.getResultList();
		return postit;
	}
	@Override
	public void AddPost(Postit post) {
		EntityManager manager = FactoryMysqlJPA.getEntityManager();
		manager.getTransaction().begin();
		manager.persist(post);
		manager.getTransaction().commit();		
	}
	
	@Override
	public boolean aggiornaPostIt(Postit postIt) {
		EntityManager manager = FactoryMysqlJPA.getEntityManager();
		try {
			manager.getTransaction().begin();
			manager.merge(postIt);
			manager.getTransaction().commit();
		} catch(RollbackException e) {
			return false;
		}
		return true;
	}

	@Override
	public void eliminaPostIt(String titolo) {
		EntityManager manager = FactoryMysqlJPA.getEntityManager();
		manager.getTransaction().begin();
		if(!manager.contains(titolo)) {
			titolo = manager.merge(titolo);
		}
		manager.remove(titolo);
		manager.getTransaction().commit();
	}

	@Override
	public Postit findByTitolo(String titolo) {
		Postit postIt;
		try{
			EntityManager manager = FactoryMysqlJPA.getEntityManager();
			Query query = manager.createQuery("SELECT p FROM Postit p WHERE p.titolo =:x");
			query.setParameter("x", titolo);
			postIt = (Postit)query.getSingleResult();
		} catch (NoResultException e ) {
			return null;
		}
		catch (NullPointerException ex ) {
			return null;
		}
		
		return postIt;
	}

	

	@Override
	public List<Postit> findByData(Date dataCreazione) {
		EntityManager manager = FactoryMysqlJPA.getEntityManager();
		Query query = manager.createQuery("SELECT p FROM Postit p WHERE p.dataCreazione =:x");
		query.setParameter("x", dataCreazione);
		List<Postit> postIt = query.getResultList(); 
		return postIt;
	}

	@Override
	public List<Postit> findByScadenza(Date dataScadenza) {
		EntityManager manager = FactoryMysqlJPA.getEntityManager();
		Query query = manager.createQuery("SELECT p FROM Postit p WHERE p.dataScadenza =:x");
		query.setParameter("x", dataScadenza);
		List<Postit> postIt = query.getResultList(); 
		return postIt;
	}
	
	@Override
	public boolean removeByTitolo(String titolo) {
		EntityManager manager = FactoryMysqlJPA.getEntityManager();
		manager.getTransaction().begin();
		int check = manager.createQuery("DELETE FROM Postit p WHERE p.titolo =:x ").setParameter("x", titolo).executeUpdate();
		manager.getTransaction().commit();
		return check > 0;
	}
	@Override
	public List<Postit> getAllPostItTitolo(String titolo) {
		try {
			EntityManager manager = FactoryMysqlJPA.getEntityManager();
			List<Postit> postIt = manager.createQuery("SELECT p FROM Postit p WHERE p.titolo =:x").setParameter("x", titolo).getResultList();
			return postIt; 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Postit>();
	}
	
	@Override
	public List<Postit> getAllPostItScadenza(Date dataScadenza) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Postit> findByCreazione(Date dataCreazione, int id) {
		EntityManager manager = FactoryMysqlJPA.getEntityManager();
		Query query = manager.createQuery("SELECT DISTINCT p From Postit p, Utente u WHERE p.utente.id = :id AND p.dataCreazione =:x ", Postit.class);
		query.setParameter("id", id);
		query.setParameter("x", dataCreazione);
		List<Postit> postIt = (List<Postit>) query.getResultList(); 
		return postIt;
	}
	@Override
	public List<Postit> findByScadenza(Date oggi, Date dataScadenza, int id) {
			EntityManager manager = FactoryMysqlJPA.getEntityManager();
			Query query = manager.createQuery("SELECT DISTINCT p From Postit p, Utente u WHERE p.utente.id = :id AND p.dataScadenza BETWEEN :oggi AND :traTreGiorni", Postit.class);
			query.setParameter("traTreGiorni", dataScadenza);
			System.out.println(dataScadenza);
			query.setParameter("oggi", oggi);
			System.out.println(oggi);
			query.setParameter("id", id);
			List<Postit> postIt = (List<Postit>) query.getResultList(); 
			return postIt;
		}
	
	@Override
	public List<Postit> findByTitolo(String titolo, int id) {
		List<Postit> postIt = null;
		try {
			EntityManager manager = FactoryMysqlJPA.getEntityManager();
			Query query = manager.createQuery("SELECT DISTINCT p From Postit p, Utente u WHERE p.utente.id = :id AND p.titolo =:x ", Postit.class);
			query.setParameter("id", id);
			query.setParameter("x", titolo);
			postIt = (List<Postit>) query.getResultList(); 
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
			return postIt;
		}
	
}
