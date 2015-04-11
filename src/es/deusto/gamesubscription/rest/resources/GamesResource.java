package es.deusto.gamesubscription.rest.resources;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import es.deusto.gamesubscription.rest.dao.GameDao;
import es.deusto.gamesubscription.rest.model.Game;

	@Path("/games")
	public class GamesResource {
		@Context
		UriInfo uriInfo;
		@Context
		Request request;
		
		@GET
		@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
		public List<Game> getGames() {
			System.out.println("paso por aqui");
			List<Game> games = new ArrayList<Game>();
			try {
				games.addAll( GameDao.instance().getGames() );
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return games; 
		}
		
		@GET
		@Path("count")
		@Produces(MediaType.TEXT_PLAIN)
		public String getCount() {
			int count;
			try {
				count = GameDao.instance().getGames().size();
				return String.valueOf(count);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Path("{game}")
		public GameResource getTodo(
				@PathParam("game") String id) {
			return new GameResource(id);
		}
		
}
