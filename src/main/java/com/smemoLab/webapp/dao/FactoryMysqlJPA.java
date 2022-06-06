package com.smemoLab.webapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class FactoryMysqlJPA extends DaoFactory {
	public static EntityManager getEntityManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();		
		}
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("SmemoLab");
		factory.getCache().evictAll();
		
		EntityManager manager = factory.createEntityManager();
		return manager;
		
		
		
	}

	@Override
	public UtenteDao getUtenteDao() {
		return UtenteDaoJPA.getInstance();
		
	}

	@Override
	public PostitDao getPostitDao() {
		return PostitDaoJPA.getInstance();
	}

	@Override
	public RuoloDao getRuoloDao() {
		return RuoloDaoJPA.getInstance();
	}

	@Override
	public CategoriaDao getCategoriaDao() {
		return CategoriaDaoJPA.getInstance();
	}
	

}

