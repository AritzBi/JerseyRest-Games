package es.deusto.gamesubscription.rest.resources;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilderException;
import javax.ws.rs.core.UriInfo;

import es.deusto.gamesubscription.rest.dao.GameDao;
import es.deusto.gamesubscription.rest.model.Game;
import es.deusto.gamesubscription.rest.model.Subscription;

public class GameResource {
	
	private String id;
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	public GameResource(String id) {
		this.id = id;
	}
			
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Game getGame() {
		Game todo = null;
		try {
			todo = GameDao.instance().getGame(Long.parseLong(id));
			return todo;
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response putGame(@Context UriInfo uriInfo, Game game) {
		Response res;
		if ( !id.equals( String.valueOf( game.getId() ) ) ){
			res = Response.status(409).entity("Put: Game with " + game.getId() +  " does not match with current game").build();
		}else{
			res = Response.noContent().build(); // Code: 204
			try {
				GameDao.instance().modifyGame(game);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	@DELETE
	public void deleteGame() {
		try {
			GameDao.instance().deleteGame(Long.parseLong(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Path("subscriptions")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Subscription> getSubscriptions() {
		List<Subscription> subcriptions = new ArrayList<Subscription>();
		try {
			subcriptions.addAll( GameDao.instance().getSubscriptionsByGame(Long.parseLong(id)) );
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return subcriptions; 
	}
	
	@POST
	@Path("subscriptions")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response newSubscription(@Context UriInfo uriInfo, Subscription subscription) throws ClassNotFoundException, IllegalArgumentException, UriBuilderException, SQLException {
		Response res;
		if ( (Long)subscription.getId() != null && subscription.getId() != 0L )
		{
			res = Response.status(409).entity("Post: Subscription with " + subscription.getId() +  " already exists").build();
		}
		else
		{
			int id_sub=GameDao.instance().insertSubscription(subscription, Long.parseLong(id));
			URI uri = uriInfo.getAbsolutePathBuilder().path(id_sub+"").build();
			res = Response.created(uri).entity(subscription).build(); // Code: 201
		}
		return res;
	}
	

}
		
	
