package es.deusto.gamesubscription.rest.actions;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import es.deusto.gamesubscription.rest.dao.ClientDAO;
import es.deusto.gamesubscription.rest.dao.SubscriptionUserDAO;
import es.deusto.gamesubscription.rest.model.Client;
import es.deusto.gamesubscription.rest.model.SubscriptionUser;

public class SubscriptionUserAction extends ActionSupport {

	@Override
	public String execute() throws Exception {
		return super.execute();
	}
	
	/**** METHOD: LISTING ****/
	private String idSubscription;
	private List<SubscriptionUser> subscriptors;

	public String getIdSubscription() {
		return idSubscription;
	}
	public void setIdSubscription(String idSubscription) {
		this.idSubscription = idSubscription;
	}
	public List<SubscriptionUser> getSubscriptors() {
		return subscriptors;
	}
	public void setSubscriptors(List<SubscriptionUser> subscriptors) {
		this.subscriptors = subscriptors;
	}
	public String doListing() {
		SubscriptionUserDAO subscriptionUserDAO = new SubscriptionUserDAO();
		setSubscriptors(subscriptionUserDAO
				.findSubscriptrorsBySubscriptionId(Long.valueOf(idSubscription)));
		return SUCCESS;
	}
	/**** [END] // METHOD: LISTING ****/
	
	/*** METHOD: goToInsert  ***/
	//Also use the varibale idSubscription
	private List<Client> clientes;
	
	public List<Client> getClientes() {
		return clientes;
	}
	public void setClientes(List<Client> clientes) {
		this.clientes = clientes;
	}
	
	public String goToInsert() {
		ClientDAO clientDAO = new ClientDAO();
		setClientes(clientDAO.findAll());
		
		return "insertSubscriptor";
	}
}
