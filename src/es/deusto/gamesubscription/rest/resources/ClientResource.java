package es.deusto.gamesubscription.rest.resources;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import es.deusto.gamesubscription.rest.dao.GameDao;
import es.deusto.gamesubscription.rest.model.Client;

public class ClientResource {
private String id;
	
	public ClientResource(String id) {
		this.id = id;
	}
			
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Client getClient() {
		Client client;
		try {
			client = GameDao.instance().getClient(Long.parseLong(id));
			return client;
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response putClient(@Context UriInfo uriInfo, Client client) {
		Response res;
		if (!id.equals(client.getId())){
			res = Response.status(409).entity("Put: Client with " + client.getId() +  " does not match with current client").build();
		}else{
			res = Response.noContent().build(); // Code: 204
			try {
				GameDao.instance().modifyClient(client);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	@DELETE
	public void deleteClient() {
		try {
			GameDao.instance().deleteClient(Long.parseLong(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
