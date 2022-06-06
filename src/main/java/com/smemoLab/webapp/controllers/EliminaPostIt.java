package com.smemoLab.webapp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smemoLab.webapp.dao.DaoFactory;
import com.smemoLab.webapp.dao.PostitDao;

@WebServlet("/EliminaPostIt")
public class EliminaPostIt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoFactory factory = DaoFactory.getFactory("jpa");
		PostitDao postItDAO = factory.getPostitDao();
		String titolo = request.getParameter("titolo");
		String messaggio = "";
		
		
		if(postItDAO.removeByTitolo(titolo)) {
			messaggio = "Post-It eliminato con successo";
			request.setAttribute("messaggio", messaggio);
		}
		else{
		messaggio="Impossibile eliminare il Post-It";
		request.setAttribute("messaggio", messaggio);
		}
		
		request.getRequestDispatcher("/Bacheca").forward(request, response);
	}

}
