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

import com.smemoLab.webapp.dao.CategoriaDao;
import com.smemoLab.webapp.dao.DaoFactory;
import com.smemoLab.webapp.dao.PostitDao;
import com.smemoLab.webapp.entity.Categoria;
import com.smemoLab.webapp.entity.Postit;
import com.smemoLab.webapp.entity.Utente;

@WebServlet("/Modifica")
public class Modifica extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "Modifica");
		DaoFactory factory = DaoFactory.getFactory("mysql");
		CategoriaDao catDao = factory.getCategoriaDao();
		
		Postit postIt = factory.getPostitDao().findByTitolo(request.getParameter("titolo"));
		request.setAttribute("postit", postIt);
		
		List<Categoria> cat = catDao.getAllCategorie();
		
		request.setAttribute("categorie", cat);
		request.getRequestDispatcher("WEB-INF/jsp/Modifica.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			DaoFactory factory = DaoFactory.getFactory("mysql");
			CategoriaDao catDao = factory.getCategoriaDao();
			PostitDao postItDao = factory.getPostitDao();
			
			HttpSession session = request.getSession();
			Utente utente = (Utente) session.getAttribute("userSession");
			int id = utente.getId();
			
			String vecchioTitolo = request.getParameter("titoloVecchio");
			String titolo = request.getParameter("titolo");
			String vecchiaDescrizione = request.getParameter("descrizioneVecchia");
			String descrizione = request.getParameter("descrizione");
			Postit postit = postItDao.findByTitolo(vecchioTitolo);
			
			LocalDate dataScadenza = LocalDate.now();
			if(request.getParameter("data") != null && !request.getParameter("data").isEmpty()) {
				dataScadenza = LocalDate.parse(request.getParameter("data"));
			}
			else {
				dataScadenza = null;
			}
			
			String[] idCatS = request.getParameterValues("categoria");
			
			if(titolo.isEmpty() || titolo == null) {
				String error = "Errore in fase di inserimento dati, riprova";
				List<Categoria> cat = catDao.getAllCategorie();
				
				request.setAttribute("categorie", cat);
				request.setAttribute("title", "Crea Post IT - Errore");
				request.setAttribute("error", error );
				request.setAttribute("esito", "");
				request.getRequestDispatcher("WEB-INF/jsp/Modifica.jsp").forward(request, response);
			}
			else {
				Date scadenza;
				
				if(dataScadenza !=null) {
					scadenza = Date.valueOf(dataScadenza);
				}
				else {
					scadenza = null;
				}
				
				postit.setUtente(utente);
				postit.setTitolo(titolo);
				postit.setDescrizione(descrizione);
				postit.setDataScadenza(scadenza);
				postit.setDataCreazione(new Date(System.currentTimeMillis()));
				
				List<Categoria> listaCat = new ArrayList<Categoria>();
				for(int i=0; i<idCatS.length; i++) {
					Categoria cat = new Categoria();
					cat.setId(Integer.parseInt(idCatS[i]));
					listaCat.add(cat);	
				}
				
				postit.setCategorie(listaCat);
				
				
				postItDao.aggiornaPostIt(postit);
		
			List<Categoria> cat = catDao.getAllCategorie();
		
			request.setAttribute("categorie", cat);
		
			request.setAttribute("title", "Modifica Post it");
			request.getRequestDispatcher("WEB-INF/jsp/Modifica.jsp").forward(request, response);
		}
	}
}
		
		
		
	


