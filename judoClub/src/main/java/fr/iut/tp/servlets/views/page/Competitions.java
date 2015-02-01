package fr.iut.tp.servlets.views.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.judo.bean.Competition;

/**
 * Servlet implementation class Competitions
 */
@WebServlet("/Competitions")
public class Competitions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Competitions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream out = response.getOutputStream();
		out.print("<h2>Competitions</h2>");
		List<Competition> lesCompetitions = (List<Competition>) request.getAttribute("competitions");
		out.print("<table border='1'  >");
		out.print("<tr>");
		out.print("<th>nom</th>");
		out.print("<th>date</th>");
		out.print("<th>departement</th>");
		out.print("<th>ville</th>");
		out.print("<th>adresse</th>");
		out.print("</tr>");
		for(Competition competition : lesCompetitions){
			out.print("<tr>");
			out.print("<td>"+competition.getNom()+"</td>");
			out.print("<td>"+competition.getDate().toLocaleString()+"</td>");
			out.print("<td>"+competition.getDepartement()+"</td>");
			out.print("<td>"+competition.getVille()+"</td>");
			out.print("<td>"+competition.getAdresse()+"</td>");
			out.print("</tr>");
		}
		out.print("</table>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
