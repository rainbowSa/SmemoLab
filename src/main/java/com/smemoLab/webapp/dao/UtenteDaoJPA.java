package com.smemoLab.webapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;


import com.smemoLab.webapp.entity.Postit;
import com.smemoLab.webapp.entity.Utente;

public class UtenteDaoJPA implements UtenteDao{
	private static UtenteDaoJPA instance;
	
	private UtenteDaoJPA() {
		
	}
	public static UtenteDaoJPA getInstance() {
			if (instance == null) {
				instance = new UtenteDaoJPA();
			}
			return instance;
		}
	
	
	@Override
	public List<Utente> getAllUtenti() {
		EntityManager manager = FactoryMysqlJPA.getEntityManager();
		Query query = manager.createQuery("SELECT u FROM Utente u", Utente.class);
		List<Utente> utenti = query.getResultList();
		return utenti;
	}
	@Override
	public boolean salvaUtente(Utente utente) {
		EntityManager manager = FactoryMysqlJPA.getEntityManager();
		try {
			manager.getTransaction().begin();
			manager.persist(utente);
			manager.getTransaction().commit();
		}catch(RollbackException e) {
			return false;
		}
		return true;
	}
	@Override
	public Utente findByUsernameAndPassword(String username, String password) {
		EntityManager manager = FactoryMysqlJPA.getEntityManager();
        Query query = manager.createQuery("SELECT u FROM Utente u WHERE u.username =:x AND u.password =:y");
        query.setParameter("x", username);
        query.setParameter("y", password);
        Utente utente = (Utente)query.getSingleResult();
        return utente;
	}
	@Override
	public boolean update(Utente utente) {
		EntityManager manager = FactoryMysqlJPA.getEntityManager();
		try {
			manager.getTransaction().begin();
			manager.merge(utente);
			manager.getTransaction().commit();
		} catch(RollbackException e) {
			return false;
		}
		return true;
	}
	@Override
	public boolean remove(int idUtente) {
		EntityManager manager = FactoryMysqlJPA.getEntityManager();
		manager.getTransaction().begin();
		int check = manager.createQuery("DELETE FROM Utente u WHERE u.id =:x").setParameter("x", idUtente).executeUpdate();
		manager.getTransaction().commit();
		
		return check > 0;
	}
	
	@Override
	public List<Postit> findAllbyUtente(int idUtente) {
			List<Postit> lista;
			
			try {
				EntityManager manager = FactoryMysqlJPA.getEntityManager();
				Query query = manager.createQuery("SELECT u.postIt FROM Utente u WHERE u.id=:x");
				query.setParameter("x", idUtente);
				lista = query.getResultList();
				
			} catch (NullPointerException e) {
				return null;
			}
			
			return lista;
	}
	@Override
	public Utente findById(int idDaRicercare) {
		EntityManager manager = FactoryMysqlJPA.getEntityManager();
		Query query = manager.createQuery("SELECT u FROM Utente u WHERE u.id =:x", Utente.class);
		query.setParameter("x", idDaRicercare);
		Utente utente = (Utente) query.getSingleResult();		
		
		return utente;
	}
	@Override
	public Utente findByUsername(String username) {
			Utente utente;
			try{
				EntityManager manager = FactoryMysqlJPA.getEntityManager();
				Query query = manager.createQuery("SELECT u FROM Utente u WHERE u.username =:x");
				query.setParameter("x", username);
				utente = (Utente)query.getSingleResult();
			} catch (NoResultException e ) {
				return null;
			}
			catch (NullPointerException ex ) {
				return null;
			}
			
			return utente;
		}
	}


