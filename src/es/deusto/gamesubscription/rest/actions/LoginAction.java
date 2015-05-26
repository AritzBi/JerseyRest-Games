package es.deusto.gamesubscription.rest.actions;

import com.opensymphony.xwork2.ActionSupport;

import es.deusto.gamesubscription.rest.dao.UserDAO;

/**
 * <p>
 * Validate a user login.
 * </p>
 */
public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		if (getUsername() == null || getPassword() == null) {
			addActionError(getText("errors.invalid.login.details"));
			return INPUT;
		} else {
			UserDAO userDAO = new UserDAO();
			if (userDAO.findByUserAndPassword(getUsername(), getPassword()))
				return SUCCESS;
			else {
				addActionError(getText("errors.invalid.login.details"));
				return INPUT;
			}
		}
	}

	// ---- Username property ----

	private String username = null;

	public String getUsername() {
		return username;
	}

	public void setUsername(String value) {
		username = value;
	}

	private String password = null;

	public String getPassword() {
		return password;
	}

	public void setPassword(String value) {
		password = value;
	}

}
