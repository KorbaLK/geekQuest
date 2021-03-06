package geekquest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class CharacterServlet extends HttpServlet {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.setContentType("text/html");

		// Save the Image in the Database
		
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		List<BlobKey> blobKeys = blobs.get("myFile");
		BlobKey blob = blobKeys.get(0);
		
		/*
		if (blobKeys == null || blobKeys.isEmpty()) {
			resp.sendRedirect("/");
		} else {
			resp.sendRedirect("/character?blob-key="
					+ blobKeys.get(0).getKeyString());
		}*/

		// Save the Character in the Database

		UserService userService = UserServiceFactory.getUserService();
		User currentUser = userService.getCurrentUser();

		String emailCu = currentUser.getEmail();
		String name = req.getParameter("name");
		String charclass = req.getParameter("charclass");
		String blobKey = blob.getKeyString();

		Player player1 = new Player(emailCu, name, charclass);

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		String mission1 = player1.getRandomMission();
		String mission2 = player1.getRandomMission();

		while (mission1.equals(mission2)) {
			mission2 = player1.getRandomMission();
		}

		Entity entity1 = new Entity(player1.getKey());
		entity1.setProperty("name", player1.getName());
		entity1.setProperty("charclass", player1.getCharclass());
		entity1.setProperty("health", player1.HEALTH);
		entity1.setProperty("mission1", mission1);
		entity1.setProperty("accmiss1", false);
		entity1.setProperty("mission2", mission2);
		entity1.setProperty("accmiss2", false);
		entity1.setProperty("blob-key", blobKey);

		datastore.put(entity1);

		resp.getWriter().println(
				"<p>Hallo " + emailCu + " Here is your new character:");

		resp.getWriter()
				.println(
						"<br><br>"
								+ "name: "
								+ name
								+ " <br>charclass: "
								+ charclass
								+ " <br>health: "
								+ player1.HEALTH
								+ "<br><br><table border=\"1\"><tr><td>Description</td><td>Accomplished</td></tr>"
								+ "<tr><td>" + mission1 + "</td>" + "<td>"
								+ false + "</td></tr><tr><td>" + mission2
								+ "</td><td>" + false + "</td></tr>+<tr><td>Blobkey:</td><td>"+ blobKey+"</td></tr></table>");

		ImagesService imagesService = ImagesServiceFactory.getImagesService();
		String imageUrl = imagesService.getServingUrl(blob);
		
		resp.getWriter().println("<br><br>Here is your Image: <br> "
				+ "<img src=\""+imageUrl+"\">"); //depricated but no other Solution founded 

	}
	
	

}
