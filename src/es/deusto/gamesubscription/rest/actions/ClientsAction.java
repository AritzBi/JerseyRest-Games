package es.deusto.gamesubscription.rest.actions;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import es.deusto.gamesubscription.rest.dao.ClientDAO;
import es.deusto.gamesubscription.rest.model.Client;

public class ClientsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<Client> clients;

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	public String doListing() {
		ClientDAO clientDAO = new ClientDAO();
		setClients(clientDAO.findAll());

		return SUCCESS;
	}
}
