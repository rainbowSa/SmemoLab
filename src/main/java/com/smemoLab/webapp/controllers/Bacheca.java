package com.smemoLab.webapp.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smemoLab.webapp.dao.DaoFactory;
import com.smemoLab.webapp.dao.UtenteDao;
import com.smemoLab.webapp.entity.Postit;
import com.smemoLab.webapp.entity.Utente;


@WebServlet("/Bacheca")
public class Bacheca extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "BACHECA");
	
		
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("userSession");
		int id = utente.getId();
		
		DaoFactory factory = DaoFactory.getFactory("jpa");
		UtenteDao uDao = factory.getUtenteDao();
		List<Postit> lista = uDao.findAllbyUtente(id);
		
		//chiamo e rimando la chiave dalla jsp per fare la distinzione tra ricerca per titolo e data
		String chiave = request.getParameter("key");
		System.out.println("Chiave che arriva nella servlet: " + chiave);
		request.setAttribute("chiavereq", chiave);
		
		request.setAttribute("postit", lista);
		request.getRequestDispatcher("WEB-INF/jsp/Bacheca.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
