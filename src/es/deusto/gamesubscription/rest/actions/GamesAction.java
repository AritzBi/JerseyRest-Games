package es.deusto.gamesubscription.rest.actions;

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
	// List of games to show over a table
	private List<Game> games = null;
	// Game object used to insert or update
	private Game game = null;
	// String with the searched game
	public String searchedGame;
	// String with the id of the edited game
	public String editedGame;
	// Specify the action to know if update or insert
	public String action;
	// String with the deleted game
	public String deletedGame;

	public String doListing() {
		GamesDAO gamesDAO = new GamesDAO();
		setGames(gamesDAO.findAll());

		return SUCCESS;
	}

	public String goToInsertGame() {
		setAction("game!insertGame.action");
		return "insertGame";
	}

	public String goToEditGame() {
		GamesDAO gamesDAO = new GamesDAO();
		Game result = gamesDAO.getGameById(Integer.valueOf(editedGame));
		setGame(result);
		setAction("editGame.action");
		return "editGame";
	}

	public String doGetGame() {
		GamesDAO gamesDAO = new GamesDAO();
		games = new ArrayList<Game>();
		if (searchedGame != null && !searchedGame.equals("")) {
			games.add(gamesDAO.getGameById(Integer.parseInt(searchedGame)));
			setGames(games);
		} else {
			addActionError("Debes especificar un valor");
		}
		return SUCCESS;
	}

	public String doInsertGame() {
		GamesDAO gamesDAO = new GamesDAO();
		boolean gameInserted = false;

		// if ( game.getAge() )
		// addFieldError("game.age", "Debes rellenar este atributo");
		if (gamesDAO.insertGame(game)) {
			gameInserted = true;
		} else {
			addActionError(getText("errors.invalid.insert.game"));
		}

		// Se quiere pasar al .jsp o ha dado error la insercion
		if (!gameInserted)
			return "insertGame";
		else
			return "successInsertGame";
	}

	public String doEditGame() {
		GamesDAO gamesDAO = new GamesDAO();

		if (gamesDAO.updateGame(game)) {
			return SUCCESS;
		} else {
			addActionError(getText("errors.invalid.update.game"));
			return "editGame";
		}
	}

	public String doDeleteGame() {
		GamesDAO gamesDAO = new GamesDAO();

		if (!gamesDAO.deleteById(Integer.valueOf(deletedGame))) {
			addActionError(getText("errors.invalid.update.game"));
			return "errorDeleteGame";
		} else {
			return SUCCESS;
		}

	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String getSearchedGame() {
		return searchedGame;
	}

	public void setSearchedGame(String searchedGame) {
		this.searchedGame = searchedGame;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public String getEditedGame() {
		return editedGame;
	}

	public void setEditedGame(String editedGame) {
		this.editedGame = editedGame;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDeletedGame() {
		return deletedGame;
	}

	public void setDeletedGame(String deletedGame) {
		this.deletedGame = deletedGame;
	}
}
