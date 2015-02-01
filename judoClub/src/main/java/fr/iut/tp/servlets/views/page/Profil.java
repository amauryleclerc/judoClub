package fr.iut.tp.servlets.views.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.judo.bean.Grade;

/**
 * Servlet implementation class Profil
 */
@WebServlet("/Profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Grade> lesGrades = (List<Grade>) request.getAttribute("grades");
		
		ServletOutputStream out = response.getOutputStream();
		out.print("<h2>Mon Profil</h2>");
		out.print("<form action='' method='post' >");
		ajoutInput("nom", "Nom", out);
		ajoutInput("prenom", "Prénom", out);
		ajoutInput("datenaissance","Date de naissance", out);	
		out.print("<label for='grade' > Grade: </label>");
		out.print("<select name='grade' >");
		for(Grade grade : lesGrades){
			out.print("<option value='"+grade.getCode()+"' >"+grade.getLibelle()+"</option>");
		}
		out.print("</select >");
		ajoutInput("poids","Poids", out);
		ajoutInput("login","Login", out);
		ajoutInput("password","Password", out);
		ajoutInput("passwordVerif","Password", out);

		out.print("<input type='submit' />");
		out.print("</form >");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	private void ajoutInput(String nom, String nomAffiche, ServletOutputStream out) throws IOException{
		
		out.print("<p>");
		out.print("<label for='"+nom+"' >"+nomAffiche+": </label>");
		out.print("<input type='text' name='"+nom+"'/>");
		out.print("<p>");
	}

}
