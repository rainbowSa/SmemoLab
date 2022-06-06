package com.smemoLab.webapp.controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smemoLab.webapp.entity.Utente;
import com.smemoLab.webapp.dao.CategoriaDao;
import com.smemoLab.webapp.dao.DaoFactory;
import com.smemoLab.webapp.dao.PostitDao;
import com.smemoLab.webapp.dao.UtenteDao;
import com.smemoLab.webapp.entity.Categoria;
import com.smemoLab.webapp.entity.Postit;



@WebServlet("/AddPost")
public class AddPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "Aggiungi Post IT");
		
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("userSession");
		int id = utente.getId();
		
		DaoFactory factory = DaoFactory.getFactory("mysql");
		UtenteDao uDao = factory.getUtenteDao();
		List<Postit> lista = uDao.findAllbyUtente(id);
		
		CategoriaDao catDao = factory.getCategoriaDao();
		List<Categoria> cat = catDao.getAllCategorie();
		
		request.setAttribute("categorie", cat);
		request.getRequestDispatcher("WEB-INF/jsp/Creazione.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titolo = request.getParameter("titolo");
		String descrizione = request.getParameter("descrizione");
		String utenteS = request.getParameter("utente");
		
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("userSession");
		int id = utente.getId();
	
		LocalDate dataScadenza = LocalDate.now();
		if(request.getParameter("data") != null && !request.getParameter("data").isEmpty()) {
			dataScadenza = LocalDate.parse(request.getParameter("data"));
		}
		else {
			dataScadenza = null;
		}
		
		
		String[] idCatS = request.getParameterValues("categoria");
		DaoFactory factory = DaoFactory.getFactory("mysql");
		UtenteDao uDao = factory.getUtenteDao();
		utente = uDao.findById(id);
		
		if(titolo.isEmpty() || titolo == null) {
			String error = "Errore in fase di inserimento dati, riprova";
			CategoriaDao catDao = factory.getCategoriaDao();
			List<Categoria> cat = catDao.getAllCategorie();
			
			request.setAttribute("categorie", cat);
			request.setAttribute("title", "Crea Post IT - Errore");
			request.setAttribute("error", error );
			request.setAttribute("esito", "");
			request.getRequestDispatcher("WEB-INF/jsp/Creazione.jsp").forward(request, response);
		}
		else {
			Date scadenza;
			
			if(dataScadenza !=null) {
				scadenza = Date.valueOf(dataScadenza);
			}
			else {
				scadenza = null;
			}
			
			
			Postit post = new Postit();
			post.setUtente(utente);
			post.setTitolo(titolo);
			post.setDescrizione(descrizione);
			post.setDataScadenza(scadenza);
			post.setDataCreazione(new Date(System.currentTimeMillis()));
			
			//lista di categorie da inviare a database
			List<Categoria> listaCat = new ArrayList<Categoria>();
			//creo una variabile di tipo Categoria per ogni categoria che mi arriva dal web
			for(int i=0; i<idCatS.length; i++) {
				Categoria cat = new Categoria();
				cat.setId(Integer.parseInt(idCatS[i]));
				listaCat.add(cat);	
			}
			
			post.setCategorie(listaCat);
		
			
			PostitDao postDao = factory.getPostitDao();
			postDao.AddPost(post);
			
			CategoriaDao catDao = factory.getCategoriaDao();
			List<Categoria> cat = catDao.getAllCategorie();
			request.setAttribute("categorie", cat);
			
			String esito = "Inserimento avvenuto con successo";
			request.setAttribute("esito", esito);
			request.setAttribute("error", "" );
			request.setAttribute("title", "Creazione Post it");
			request.getRequestDispatcher("WEB-INF/jsp/Creazione.jsp").forward(request, response);
		}
		
		
		
	}

}
