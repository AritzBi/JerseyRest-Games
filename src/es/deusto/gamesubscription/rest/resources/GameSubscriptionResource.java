package es.deusto.gamesubscription.rest.resources;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
	public int deleteSubscription(@PathParam("client") String id2) {
		try {
			return GameDao.instance().deleteSubscriptionClient(id,Long.parseLong(id2));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	@DELETE
	public int deleteSubscription() {
		try {
			return GameDao.instance().deleteSubscription(id);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
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
}
