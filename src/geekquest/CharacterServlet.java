package geekquest;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class CharacterServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.setContentType("text/html");

		UserService userService = UserServiceFactory.getUserService();
		User currentUser = userService.getCurrentUser();
		String emailCu = currentUser.getEmail();

		resp.getWriter().println("<p>Hallo "+emailCu+" Hier ist ihr neuer Character.");

		resp.getWriter().println(req.getParameter("name")+ " " + req.getParameter("charclass") + " health = 10 ");




		/*

		Player player1 = new Player(emailCu, "Frodo", "Mage", 100);

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Entity entity1 = new Entity(player1.getKey());
		entity1.setProperty("name", player1.getName());
		entity1.setProperty("charclass", player1.getCharclass());
		entity1.setProperty("health", player1.getHealth());

		 datastore.put(entity1);

		 */

	}

}
