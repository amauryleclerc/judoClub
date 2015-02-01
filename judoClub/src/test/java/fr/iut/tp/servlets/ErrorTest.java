package fr.iut.tp.servlets;

import junit.framework.TestCase;

import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebResponse;

public class ErrorTest extends TestCase {
	private WebConversation webConversation = new WebConversation();
	private static final String URL_ROOT = "http://localhost:8080/judoClub";
	
	public ErrorTest(final String s) {
		super(s);
	}
	
	
	/**
	 * Test avec une page inexistante
	 */
	public void test404() throws Exception {
		WebResponse page;
		page = webConversation.getResponse(URL_ROOT + "/action/qsfsdqfq");
		WebResponse newPage = webConversation.getCurrentPage();
		String title = newPage.getTitle();
		assertEquals("la page doit etre une error 404", "404", title);
	}
	

	
}
