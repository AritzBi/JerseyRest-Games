package es.deusto.gamesubscription.actions;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import es.deusto.gamesubscription.rest.dao.GamesDAO;
import es.deusto.gamesubscription.rest.model.Game;

public class GamesAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Game> games = null;
	public String searchedGame;

	public String getSearchedGame() {
		return searchedGame;
	}

	public void setSearchedGame(String searchedGame) {
		this.searchedGame = searchedGame;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public String doListing() {
		GamesDAO gamesDAO = new GamesDAO();
		setGames( gamesDAO.findAll() );
		
		return SUCCESS;
	}
	
	public String doGetGame() {
		GamesDAO gamesDAO = new GamesDAO();
		games = new ArrayList<Game>();
		if ( searchedGame != null && !searchedGame.equals("") )
		{
			games.add ( gamesDAO.getGameById( Integer.parseInt(searchedGame) ) );
			setGames( games );
		}
		else
		{
			addActionError("Debes especificar un valor");
		}
		
		return SUCCESS;
	}
	
	public String doInsertGame() {
		
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

}
