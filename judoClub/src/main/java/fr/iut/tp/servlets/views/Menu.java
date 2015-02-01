package fr.iut.tp.servlets.views;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Menu
 */

public class Menu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LOGOUT_HTML = "/WEB-INF/logout.html";
	private static final String LOGIN_HTML = "/WEB-INF/login.html";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Menu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idSession = (String) request.getAttribute("idSession");
		String ressource = LOGIN_HTML;
		ServletOutputStream out = response.getOutputStream();
		out.print("<div class='menu'>");
		out.print("<h3>Menu</h3>");
		out.print("<a href='/judoClub/action/accueil' >Accueil</a><br/>");
		out.print("<a href='/judoClub/action/competitions' >Competitions</a><br/>");
		out.print("<a href='/judoClub/action/coordonnees' >Coordonnees</a><br/>");
		if(!idSession.equals("")){
			out.print("<a href='/judoClub/action/profil' >profil</a><br/>");
			ressource = LOGOUT_HTML;
		}
		include(request,response, ressource);
		out.print("</div>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	private void include(HttpServletRequest request, HttpServletResponse response, String nomDeLaRessoure) {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(nomDeLaRessoure);
		try {
			rd.include(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
