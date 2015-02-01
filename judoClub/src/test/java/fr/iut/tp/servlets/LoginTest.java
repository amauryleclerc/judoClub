package fr.iut.tp.servlets;

import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebResponse;
import junit.framework.TestCase;

public class LoginTest extends TestCase {
	
	private WebConversation webConversation = new WebConversation();
	private static final String URL_ROOT = "http://localhost:8080/judoClub";
	
	public LoginTest(final String s) {
		super(s);
	}
	


	/**
	 * Test si la servlet est déployé
	 */
	public void testWebCheckServlets() {
		
		try {
			webConversation.getResponse(URL_ROOT + "/action");
		} catch (Exception e) {
			fail("The login servlet hasn't been found");
		}
	}
	
	/**
	 * Test avec un mot de passe invalide 
	 */
	public void testBadLogin() throws Exception {
		WebResponse page;
		page = webConversation.getResponse(URL_ROOT + "/action/accueil");
		WebForm form = page.getFormWithName("loginform");
		form.setParameter("name", "test");
		form.setParameter("pwd", "test");
		form.submit();
		WebResponse newPage = webConversation.getCurrentPage();
		String utilisateur = newPage.getElementWithID("utilisateur").getText();
		assertEquals("Ce message non authentifié doit apparaitre si le mot de passe est incorrect", "Non authentifié", utilisateur);
	}
	
	/**
	 * Test avec un mot de passe valide
	 */
	public void testValidLogin() throws Exception {
		WebResponse page;
		page = webConversation.getResponse(URL_ROOT + "/action/accueil");
		WebForm form = page.getFormWithName("loginform");
		form.setParameter("name", "paul");
		form.setParameter("pwd", "ok");
		form.submit();
		WebResponse newPage = webConversation.getCurrentPage();
		String utilisateur = newPage.getElementWithID("utilisateur").getText();
		assertEquals("Ce message le nom de l'utilisateur doit apparaitre si le mot de passe est correct", "Utilisateur : paul", utilisateur);
	}
	

	
}
