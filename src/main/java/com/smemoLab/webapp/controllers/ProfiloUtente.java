package com.smemoLab.webapp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smemoLab.webapp.dao.DaoFactory;
import com.smemoLab.webapp.dao.UtenteDao;
import com.smemoLab.webapp.entity.Utente;


@WebServlet("/ProfiloUtente")
public class ProfiloUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "Il mio profilo");
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("userSession");
		request.setAttribute("utente", utente);
		request.getRequestDispatcher("WEB-INF/jsp/Profilo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoFactory factory = DaoFactory.getFactory("jpa");
		UtenteDao utenteDao = factory.getUtenteDao();
		String u = request.getParameter("username");
		
		Utente utente = utenteDao.findByUsername(u);
		request.setAttribute("utente", utente);
		System.out.println(utente);
		doGet(request, response);
	}

}
