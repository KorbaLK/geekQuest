package geekquest;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		UserService userService = UserServiceFactory.getUserService();

		User currentUser = userService.getCurrentUser();

		String thisURL = req.getRequestURI();

		resp.setContentType("text/html");

		if (currentUser == null) {
			resp.getWriter().println(
					"<p>Please <a href=\""
							+ userService.createLoginURL(thisURL)
							+ "\">sign in</a>.</p>");
		}

		if (currentUser != null) {
			resp.getWriter().println(
					"<p>Hello, " + currentUser.getNickname()
							+ "!<br><br>  You can <a href=\""
							+ userService.createLogoutURL(thisURL)
							+ "\">sign out</a>.</p>");

			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();

			Entity userCharakter = null;
			Key keyCurrentuser = KeyFactory.createKey("character",
					currentUser.getEmail());

			try {
				userCharakter = datastore.get(keyCurrentuser);
			} catch (EntityNotFoundException e) {
				// No Charakter there
			}

			if (userCharakter == null) {
				resp.getWriter()
						.println(
								"<br><br>You don´t have a Character yet...<br><br>"
										+ "<br><br><form action=\"/character.html\"> <button type=\"submit\">Please, create a Character!</button></form>");
			} else {
				resp.getWriter().println(
						"Here is you character: <br><br>" + "Name: "
								+ userCharakter.getProperty("name")
								+ "<br>Charclass: "
								+ userCharakter.getProperty("charclass")
								+ "<br>Health: "
								+ userCharakter.getProperty("health"));
			}
		}

	}
}
