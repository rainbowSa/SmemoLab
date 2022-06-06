package com.smemoLab.webapp.controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smemoLab.webapp.dao.DaoFactory;
import com.smemoLab.webapp.dao.PostitDao;
import com.smemoLab.webapp.entity.Postit;
import com.smemoLab.webapp.entity.Utente;

@WebServlet("/CercaPostItServlet")
public class CercaPostItServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DaoFactory factory = DaoFactory.getFactory("jpa");
		PostitDao postItDAO = factory.getPostitDao();
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("userSession");
		int id = utente.getId();
		String chiavePerCerca = null;
		request.setAttribute("chiavePerCerca", chiavePerCerca);
		String chiave = request.getParameter("key");
		String ricerca = request.getParameter("ricerca");
		System.out.println("Ricerca ottenuta: " + ricerca);
		//Date ricerca2 = request.getParameter("ricerca");
		String messaggio;
		List<Postit> postIt = null;
		
		System.out.println("id utente nella ricerca post: " + id);
		String idUtente =String.valueOf(id);
		request.setAttribute("idUtente", idUtente);
		
		System.out.println("user id: " +id + " " + "Ricerca: " + ricerca);
		if(chiave.equals("titolo")) {
			
			postIt = postItDAO.findByTitolo(ricerca, id);
			if(postIt.isEmpty()) {
				messaggio="Non ci sono post-it che corrispondono al criterio di ricerca";
				request.setAttribute("messaggio", messaggio);
			}else {
				request.setAttribute("postit", postIt);
			}
		
			
				
			}
		else {
			System.out.println("Sono nell'else creazione servlet!");
			LocalDate data = LocalDate.now();
			if(!request.getParameter("ricerca").isBlank()) {
			data = LocalDate.parse(request.getParameter("ricerca"));
			}
			Date dataUscita = Date.valueOf(data); 
			System.out.println("Data uscita: " + dataUscita);
			postIt = postItDAO.findByCreazione(dataUscita, id);
			
			if(postIt.isEmpty()) {
				messaggio="Non ci sono post-it che corrispondono al criterio di ricerca";
				request.setAttribute("messaggio", messaggio);
			}else {
				request.setAttribute("postit", postIt);
			}
		
		}
		request.getRequestDispatcher("WEB-INF/jsp/Bacheca.jsp").forward(request, response);

		
	}
}
