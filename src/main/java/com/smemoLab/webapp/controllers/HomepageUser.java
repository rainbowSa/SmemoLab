package com.smemoLab.webapp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.smemoLab.webapp.dao.DaoFactory;
import com.smemoLab.webapp.dao.PostitDao;
import com.smemoLab.webapp.entity.Postit;
import com.smemoLab.webapp.entity.Utente;


@WebServlet("/HomepageUser")
public class HomepageUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "Homepage");
		
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("userSession");
		int id = utente.getId();
		request.setAttribute("utente", utente);
		DaoFactory  factory = DaoFactory.getFactory("mysql");
		PostitDao postItDao = factory.getPostitDao();
		Date oggi = Date.valueOf(LocalDate.now().minusDays(1));
		Date dataScadenza = Date.valueOf(oggi.toLocalDate().plusDays(4));
		List<Postit> lista = postItDao.findByScadenza(oggi, dataScadenza, id);
		
		request.setAttribute("lista", lista);
		request.getRequestDispatcher("WEB-INF/jsp/HomeUser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
