package com.smemoLab.webapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.smemoLab.webapp.dao.RuoloDaoJPA;
import com.smemoLab.webapp.entity.Ruolo;

public class RuoloDaoJPA implements RuoloDao{

	private static RuoloDaoJPA instance;
	
	private RuoloDaoJPA() {
		
	}
	public static RuoloDaoJPA getInstance() {
			if (instance == null) {
				instance = new RuoloDaoJPA();
			}
			return instance;
		}
	@Override
	public List<Ruolo> getAllRuoli() {
		EntityManager manager = FactoryMysqlJPA.getEntityManager();
		Query query = manager.createQuery("SELECT r FROM Ruolo r", Ruolo.class);
		List<Ruolo> ruoli = query.getResultList();
		
		return ruoli;
	}
	}
			
	

