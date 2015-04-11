package es.deusto.gamesubscription.rest.resources;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import es.deusto.gamesubscription.rest.dao.GameDao;
import es.deusto.gamesubscription.rest.model.Client;

@Path("/clients")
public class ClientsResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Client> getClients() {
		System.out.println("paso por aqui");
		List<Client> clients = new ArrayList<Client>();
		try {
			clients.addAll( GameDao.instance().getClients() );
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clients; 
	}
	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count;
		try {
			count = GameDao.instance().getClients().size();
			return String.valueOf(count);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Path("{client}")
	public ClientResource getTodo(
			@PathParam("client") String id) {
		return new ClientResource(id);
	}
}
