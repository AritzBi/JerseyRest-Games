package es.deusto.gamesubscription.actions;
import com.opensymphony.xwork2.ActionSupport;

/**
 * <p> Validate a user login. </p>
 */
public  class LoginAction  extends ActionSupport {

	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
        System.out.println("Validating login");
		if (getUsername() == null || getPassword() == null) {
			addActionError("Compulsory to specify both username and password!");
			return INPUT;
		}
		if(!getUsername().equals("Admin") || !getPassword().equals("Admin")){
            addActionError(getText("invalid_login_details"));
            return ERROR;
		}else{
			return SUCCESS;
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
