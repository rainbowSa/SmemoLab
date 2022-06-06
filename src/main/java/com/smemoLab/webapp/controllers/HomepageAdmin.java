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
import com.smemoLab.webapp.dao.UtenteDao;
import com.smemoLab.webapp.entity.Postit;
import com.smemoLab.webapp.entity.Utente;


@WebServlet("/HomepageAdmin")
public class HomepageAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setAttribute("title", "Homepage");
	
	HttpSession session = request.getSession();
	Utente utente = (Utente) session.getAttribute("userSession");
	int id = utente.getId();
	DaoFactory  factory = DaoFactory.getFactory("mysql");
	PostitDao postItDao = factory.getPostitDao();
	UtenteDao user = factory.getUtenteDao();
	List<Utente> userList = user.getAllUtenti();
	Date oggi = Date.valueOf(LocalDate.now().minusDays(1));
	Date dataScadenza = Date.valueOf(oggi.toLocalDate().plusDays(4));
	List<Postit> lista = postItDao.findByScadenza(oggi, dataScadenza, id);
	
	request.setAttribute("userList", userList);
	request.setAttribute("lista", lista);
	request.getRequestDispatcher("WEB-INF/jsp/HomeAdmin.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
