package com.smemoLab.webapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.smemoLab.webapp.entity.Categoria;

public class CategoriaDaoJPA implements CategoriaDao{
	private static CategoriaDaoJPA instance;
	
	private CategoriaDaoJPA() {
		
	}
	public static CategoriaDaoJPA getInstance() {
			if (instance == null) {
				instance = new CategoriaDaoJPA();
			}
			return instance;
		}
	@Override
	public List<Categoria> getAllCategorie() {
		EntityManager manager = FactoryMysqlJPA.getEntityManager();
		Query query = manager.createQuery("SELECT c FROM Categoria c");
		List<Categoria> categorie = query.getResultList();
		return categorie;
	}
	
	
}
