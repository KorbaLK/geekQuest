package geekquest;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
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
		if (currentUser != null) {
			resp.getWriter().println(
					"<p>Hello, " + currentUser.getNickname()
							+ "!<br><br>  You can <a href=\""
							+ userService.createLogoutURL(thisURL)
							+ "\">sign out</a>.</p>"
							+ "<br><br><form action=\"/character.html\"> <button type=\"submit\">Please, create a Character!</button></form>");

			//resp.sendRedirect("\\CharacterServlet");

		} else {
			resp.getWriter().println(
					"<p>Please <a href=\""
							+ userService.createLoginURL(thisURL)
							+ "\">sign in</a>.</p>");

		}



	}
}
