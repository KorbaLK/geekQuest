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
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class ServeCharacterServlet extends HttpServlet {
	
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		resp.setContentType("text/html");
		
		UserService userService = UserServiceFactory.getUserService();
		User currentUser = userService.getCurrentUser();
		
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

		BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
		
		ImagesService imagesService = ImagesServiceFactory.getImagesService();
		String imageUrl = imagesService.getServingUrl(blobKey);
		
		
		resp.getWriter().println("<br><br>Here is your Image: <br> "
				+ "<img src=\""+imageUrl+"\">"); //depricated but no other Solution founded 
				//+ "<img src=\"http://localhost:8888/servecharacter?blob-key="+blobKey.getKeyString()+"\">"); funktioniert nicht
		
		//blobstoreService.serve(blobKey, resp);
		//blobstoreService.serve(blobKey, resp);
		
		
		


	}

}
