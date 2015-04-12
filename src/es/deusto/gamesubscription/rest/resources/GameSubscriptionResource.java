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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import es.deusto.gamesubscription.rest.dao.GameDao;
import es.deusto.gamesubscription.rest.model.Subscription;
import es.deusto.gamesubscription.rest.model.SubscriptionUser;

public class GameSubscriptionResource {
	private long id;

	public GameSubscriptionResource(long id){
		System.out.println(id);
		this.id=id;
	}
	
	@DELETE
	@Path("clients/{client}")
	public void deleteSubscription(@PathParam("client") String id2) {
		try {
			GameDao.instance().deleteSubscriptionClient(id,Long.parseLong(id2));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@POST
	@Path("clients/{client}")
	public Response createSubscription( @Context UriInfo uriInfo, @PathParam("client") String idCliente ) {
		Response res = null;
		if ( (Long)id == null || idCliente == null )
		{
			res = Response.status(409).entity("Post: Client and subscription need some value").build();
		}
		else
		{
			int id_sub;
			try {
				id_sub = GameDao.instance().createSubscription(Long.valueOf(idCliente), id);
				URI uri = uriInfo.getAbsolutePathBuilder().path(id_sub+"").build();
				res = Response.created(uri).entity(idCliente).build(); // Code: 201
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	@DELETE
	public void deleteSubscription() {
		try {
			GameDao.instance().deleteSubscription(id);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@GET
	@Path("clients/{client}")
	public List<Subscription> getSubscriptions(@PathParam("client") String id2) {
		System.out.println("pit");
		System.out.println(id2);
		return null;
	}
	@GET
	@Path("clients")
	public List<SubscriptionUser> getSubscriptions() {
		List<SubscriptionUser> subcriptions = new ArrayList<SubscriptionUser>();
		try {
			subcriptions.addAll( GameDao.instance().getSubscriptionUsers(id) );
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return subcriptions; 
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_XML})
	public Response putSubscription( Subscription subscription) {
		Response res;
		if ( !String.valueOf(id).equals( String.valueOf( subscription.getId() ) ) ){
			res = Response.status(409).entity("Put: Subscription with " + subscription.getId() +  " does not match with current subscription").build();
		}else{
			res = Response.noContent().build(); // Code: 204
			try {
				GameDao.instance().modifySubscription(subscription);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
}
