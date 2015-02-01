package fr.iut.tp.servlets.controlers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.judo.bean.Competition;
import org.judo.bean.Grade;
import org.judo.services.Service;

/**
 * Servlet implementation class Action
 */
public class Action extends HttpServlet {
	private static final String ERROR404_HTML = "/WEB-INF/404.html";
	//private static final String LOGIN_HTML = "/WEB-INF/login.html";
	private static final String LOGIN_SESSION_OBJECT_ID = "login";
	private static final String NAME_PARAMETER_ID = "name";
	private static final String OK = "ok";
	private static final String PWD = "pwd";
	private static final long serialVersionUID = 1L;
	private static HashMap<String, String> nomRessources;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Action() {
		super();
		nomRessources = new HashMap<String , String>();
		nomRessources.put("/accueil", "/WEB-INF/accueil.html");
		nomRessources.put("/", "/WEB-INF/accueil.html");
		nomRessources.put("/*", "/WEB-INF/accueil.html");
		nomRessources.put("/competitions", "/Competitions");
		nomRessources.put("/coordonnees", "/WEB-INF/coordonnees.html");
		nomRessources.put("/profil", "/Profil");
	}
	
	private void display(HttpServletRequest request, HttpServletResponse response, String nomDeLaRessoure, String idSession) {
		include(request, response, "/header");
		request.setAttribute("idSession", idSession);
		include(request, response, "/utilisateur");
		include(request, response, "/menu");
		request.setAttribute("log", nomDeLaRessoure);
		include(request, response, "/body");
		include(request, response, "/footer");

	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomDeLaRessoure = null;
		String idSession = "";
		if (isExistSession(request)) {
			idSession = getIdSession(request);
		}
		nomDeLaRessoure = getNomRessource(request);
		if(nomDeLaRessoure ==null){
			send404(request, response);
		}else{
			addRessource(request);
			display(request, response, nomDeLaRessoure, idSession);
		}

		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getPathInfo();
		if (islogout(path)) {
			request.getSession().invalidate();
		} else if (verificationPWD(request, response)) {
	
			request.getSession().setAttribute(LOGIN_SESSION_OBJECT_ID, request.getParameter(NAME_PARAMETER_ID));

		}

		response.sendRedirect("/judoClub/action/accueil");
	}
	
	private void include(HttpServletRequest request, HttpServletResponse response, String nomDeLaRessoure) {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(nomDeLaRessoure);
		try {
			rd.include(request, response);
		} catch (ServletException e) {
			log(e.getLocalizedMessage());
		} catch (IOException e) {
			log(e.getLocalizedMessage());
		}
		
	}
	
	private boolean isExistSession(HttpServletRequest request) {
		return request.getSession().getAttribute(LOGIN_SESSION_OBJECT_ID) != null;
	}
	
	private String getIdSession(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(LOGIN_SESSION_OBJECT_ID);
	}
	
	private boolean islogout(String path) {
		return "/logout".contentEquals(path);
	}
	
	private boolean verificationPWD(HttpServletRequest request, HttpServletResponse response) {
		boolean resultat = false;
		String pwd = request.getParameter(PWD);
		String name = request.getParameter(NAME_PARAMETER_ID);
		if (request.getPathInfo() != null && pwd != null && name != null && OK.contentEquals(pwd)) {
			
			resultat = true;
		}
		
		return resultat;
	}
	private String  getNomRessource(HttpServletRequest request){

		for(Entry<String, String> entry : nomRessources.entrySet()) {
		  if( entry.getKey().equals(request.getPathInfo())){
			    return  entry.getValue();
		  }
		   
		}
		return null;
		
	}
	private void send404(HttpServletRequest request, HttpServletResponse response){
		
			RequestDispatcher rd = getServletContext().getRequestDispatcher(ERROR404_HTML);
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	private void addRessource(HttpServletRequest request ){
		if(request.getPathInfo().equals("/competitions")){
			List<Competition> competitions = Service.getCompetitions();
			request.setAttribute("competitions", competitions);
		}else if(request.getPathInfo().equals("/profil")){
			List<Grade> grades = Service.getGrades();
			request.setAttribute("grades", grades);
		}
	}
}
