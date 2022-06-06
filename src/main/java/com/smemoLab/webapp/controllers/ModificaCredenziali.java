package com.smemoLab.webapp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smemoLab.webapp.dao.DaoFactory;
import com.smemoLab.webapp.dao.UtenteDao;
import com.smemoLab.webapp.entity.Utente;
import com.smemoLab.webapp.utilities.Utility;

@WebServlet("/ModificaCredenziali")
public class ModificaCredenziali extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "Modifica Informazioni");
		Utente u = DaoFactory.getFactory("mysql").getUtenteDao().findByUsername("username");
		request.getRequestDispatcher("WEB-INF/jsp/ModificaCredenziali.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoFactory factory = DaoFactory.getFactory("jpa");
		UtenteDao utenteDao = factory.getUtenteDao();
		String vecchioNome = request.getParameter("nomeVecchio");
		String nuovoNome = request.getParameter("nome");
		String vecchioCognome = request.getParameter("cognomeVecchio");
		String nuovoCognome = request.getParameter("cognome");
		String vecchiaEmail = request.getParameter("emailVecchia");
		String nuovaEmail = request.getParameter("email");
		String vecchiaUsername = request.getParameter("usernameVecchia");
		String nuovaUsername = request.getParameter("username");
		
		Utente utente = utenteDao.findByUsername(vecchiaUsername);
		String messaggio = "";
		
		Boolean control;
		
		control= Utility.controlloNomeCognome(nuovoNome) && Utility.controlloNomeCognome(nuovoCognome) && Utility.controlloEmail(nuovaEmail);
		
		if(control) {
			utente.setNome(nuovoNome);
			utente.setCognome(nuovoCognome);
			utente.setEmail(nuovaEmail);
			utente.setUsername(nuovaUsername);
			utenteDao.update(utente);
			request.setAttribute("utente", utente);
			request.getRequestDispatcher("WEB-INF/jsp/HomeUser.jsp").forward(request, response);
		}else {
			messaggio = "I dati inseriti non sono corretti";
			request.setAttribute("esito", messaggio);
			request.getRequestDispatcher("WEB-INF/jsp/ModificaCredenziali.jsp").forward(request, response);
		}
	}

}