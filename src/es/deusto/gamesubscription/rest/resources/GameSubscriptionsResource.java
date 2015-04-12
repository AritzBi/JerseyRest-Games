package es.deusto.gamesubscription.rest.resources;

import java.sql.SQLException;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
@Path("/subscriptions")
public class GameSubscriptionsResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@Path("{subscription}")
	public GameSubscriptionResource deleteSubscription(
			@PathParam("subscription") String id) throws NumberFormatException, ClassNotFoundException, SQLException {
		return new GameSubscriptionResource(Long.parseLong(id));
	}
	


}
