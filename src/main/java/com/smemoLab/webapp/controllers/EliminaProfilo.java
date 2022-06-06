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

/**
 * Servlet implementation class EliminaProfilo
 */
@WebServlet("/EliminaProfilo")
public class EliminaProfilo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chiaveElimina = "elimina";
		request.setAttribute("chiaveElimina", chiaveElimina);
		
		request.setAttribute("title", "Homepage");
	
		System.out.println("chiave elimina che arriva in homepageUser: " + chiaveElimina);
		
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("userSession");
		request.setAttribute("utente", utente);
		int id = utente.getId();
		DaoFactory factory = DaoFactory.getFactory("jpa");
		PostitDao postItDao = factory.getPostitDao();
		Date oggi = Date.valueOf(LocalDate.now());
		Date dataScadenza = Date.valueOf(oggi.toLocalDate().plusDays(4));
		List<Postit> lista = postItDao.findByScadenza(oggi, dataScadenza, id);
		request.setAttribute("postit", lista);
		request.getRequestDispatcher("WEB-INF/jsp/HomeUser.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoFactory factory = DaoFactory.getFactory("jpa");
		String keyElimina = request.getParameter("keyElimina");
		
		boolean utenteControllo = false;
		System.out.println("KEY ELIMINA nel post: " + keyElimina);
		if(keyElimina != null && keyElimina.equals("si")) {
			Integer prova = Integer.parseInt(request.getParameter("userId"));
			System.out.println("User id che piazziamo nella query: " + prova);
			utenteControllo = factory.getUtenteDao().remove(prova);
			response.sendRedirect("Homepage");
		}else {
	    	response.sendRedirect("HomepageUser");
	    }
	}

}
