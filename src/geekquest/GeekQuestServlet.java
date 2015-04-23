package geekquest;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class GeekQuestServlet extends HttpServlet {

	private BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		UserService userService = UserServiceFactory.getUserService();

		User currentUser = userService.getCurrentUser();

		// String thisURL = req.getRequestURI();

		String loginUrl = userService.createLoginURL("/");
		String logoutUrl = userService.createLogoutURL("/");

		resp.setContentType("text/html");
		req.setAttribute("user", currentUser);
		req.setAttribute("loginUrl", loginUrl);
		req.setAttribute("logoutUrl", logoutUrl);

		// If no user exists
		if (currentUser == null) {
			resp.getWriter().println(
					"<p>Please <a href=\""
							+ userService.createLoginURL(loginUrl)
							+ "\">sign in</a>.</p>");
		}

		// if a user exists
		if (currentUser != null) {
			resp.getWriter().println(
					"<p>Hello, " + currentUser.getNickname()
							+ "!<br><br>  You can <a href=\""
							+ userService.createLogoutURL(logoutUrl)
							+ "\">sign out</a>.</p>");

			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();

			Entity userCharacter = null;
			Key keyCurrentuser = KeyFactory.createKey("character",
					currentUser.getEmail());

			try {
				userCharacter = datastore.get(keyCurrentuser);
			} catch (EntityNotFoundException e) {
				// No Character there
			}

			// If there is no character xet
			if (userCharacter == null) {
				resp.getWriter()
						.println(
								"<br><br>You don´t have a Character yet...<br><br>"
										+ "<br><br><form action=\"/character.jsp\"> <button type=\"submit\">Please, create a Character!</button></form>");
			} else {
				resp.getWriter().println(
						"Here is you character: <br><br>" + "Name: "
								+ userCharacter.getProperty("name")
								+ "<br>Charclass: "
								+ userCharacter.getProperty("charclass")
								+ "<br>Health: "
								+ userCharacter.getProperty("health")
								+ "<br><br>Mission1: "
								+ userCharacter.getProperty("mission1")
								+ " Accomplished: "
								+ userCharacter.getProperty("accmiss1")
								+ "<br><br>Mission2: "
								+ userCharacter.getProperty("mission2")
								+ " Accomplished: "
								+ userCharacter.getProperty("accmiss2"));

				resp.getWriter().println("<br><br>No Image is there, upload a new Image for your Character:"
				+ "<form action=\"/image.jsp\"><button type=\"submit\">Upload a new Image</button></form>");
			}
			
		}

	}
}
