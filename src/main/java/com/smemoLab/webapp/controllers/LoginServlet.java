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
import com.smemoLab.webapp.utilities.Utility;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "Login || Registrazione" );
		request.getRequestDispatcher("WEB-INF/jsp/Login.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		DaoFactory factory = DaoFactory.getFactory("jpa");
		UtenteDao utenteDao = factory.getUtenteDao();
		String messaggio = "";
		
		if(!Utility.controlloUsername(username)) {
			messaggio = "L'username non è valido";
			request.setAttribute("errore", messaggio);
			request.setAttribute("title", "Login || Registrazione" );
			request.getRequestDispatcher("WEB-INF/jsp/Login.jsp").forward(request, response);
		}
		else {
			try {
				Utente utenteDalDB = utenteDao.findByUsernameAndPassword(username, password);
				session.setAttribute("userSession", utenteDalDB);
				
				int idRuolo = utenteDalDB.getRuolo().getId();
				switch(idRuolo) {
				case 1:
					response.sendRedirect("HomepageAdmin");
					break;
				case 2:
					response.sendRedirect("HomepageUser");
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				messaggio = "L'utente non risulta registrato";
				request.setAttribute("errore", messaggio);
				request.setAttribute("title", "Login || Registrazione" );
				request.getRequestDispatcher("WEB-INF/jsp/Login.jsp").forward(request, response);
			}
			
		}
	}

}
