package com.smemoLab.webapp.dao;

import com.smemoLab.webapp.dao.UtenteDao;
import com.smemoLab.webapp.dao.FactoryMysqlJPA;

public abstract class DaoFactory {
	public abstract UtenteDao getUtenteDao();
	public abstract PostitDao getPostitDao();
	public abstract RuoloDao getRuoloDao();
	public abstract CategoriaDao getCategoriaDao();
	
	
	public static DaoFactory getFactory(String tipoDB) {
		switch (tipoDB) {
		case "mysql":
			return new FactoryMysqlJPA();
	
	
		/*case "Oracle":
			return new FactoryOracleJPA();*/
	
		default:
			return new FactoryMysqlJPA();

	}
	}
}
