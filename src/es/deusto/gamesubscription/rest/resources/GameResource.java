package es.deusto.gamesubscription.rest.resources;


import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


import es.deusto.gamesubscription.rest.dao.GameDao;
import es.deusto.gamesubscription.rest.model.Game;



public class GameResource {
	
	private String id;
	
	public GameResource(String id) {
		this.id = id;
	}
			
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Game getGame() {
		Game todo;
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
		if (!id.equals(game.getId())){
			res = Response.status(409).entity("Put: Game with " + game.getId() +  " does not match with current game").build();
		}else{
			res = Response.noContent().build(); // Code: 204
			try {
				GameDao.instance().insertGame(game);
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
		
	
}