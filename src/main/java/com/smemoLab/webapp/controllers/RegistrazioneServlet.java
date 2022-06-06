package com.smemoLab.webapp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smemoLab.webapp.dao.DaoFactory;
import com.smemoLab.webapp.dao.RuoloDao;
import com.smemoLab.webapp.dao.UtenteDao;
import com.smemoLab.webapp.entity.Ruolo;
import com.smemoLab.webapp.entity.Utente;
import com.smemoLab.webapp.utilities.Utility;

@WebServlet("/Registrazione")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/Login.jsp").forward(request, response);
		request.setAttribute("title", "Registrazione" );
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String messaggio = "";
		String errore;
		Boolean control;
		
		control= Utility.controlloNomeCognome(nome) && Utility.controlloNomeCognome(cognome) && Utility.controlloEmail(email) && Utility.controlloPassword(password);
		DaoFactory factory = DaoFactory.getFactory("mysql");
		UtenteDao utenteDAO = factory.getUtenteDao();
		
		
		RuoloDao ruoloDAO = factory.getRuoloDao();
		Ruolo ruolo = new Ruolo(2);
		
		if (control) {
			
			Utente utente = new Utente (nome, cognome, email, username, password, ruolo);
			if(utenteDAO.salvaUtente(utente)) {
				messaggio="Registrazione effettuata";
			}else {
				messaggio="L'username inserito è già esistente";
			}
			request.setAttribute("title", "Registrazione" );
			request.setAttribute("control", control);
			request.setAttribute("messaggio", messaggio);
			request.getRequestDispatcher("WEB-INF/jsp/Login.jsp").forward(request, response);	
		}else {
			errore = "I dati inseriti sono errati";
			request.setAttribute("title", "Registrazione" );
			request.setAttribute("control", control);
			request.setAttribute("errore", errore);
			request.getRequestDispatcher("WEB-INF/jsp/Login.jsp").forward(request, response);
		}
	}
		
	}
