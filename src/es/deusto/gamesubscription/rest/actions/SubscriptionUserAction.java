package es.deusto.gamesubscription.rest.actions;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import es.deusto.gamesubscription.rest.dao.ClientDAO;
import es.deusto.gamesubscription.rest.dao.SubscriptionUserDAO;
import es.deusto.gamesubscription.rest.model.Client;
import es.deusto.gamesubscription.rest.model.SubscriptionUser;

public class SubscriptionUserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SubscriptionUserDAO subscriptionUserDAO;
	private List<Client> clientes;
	private String idClienteSeleccionado;

	private String idSubscription;
	private List<SubscriptionUser> subscriptors;
	
	private String action;

	public List<Client> getClientes() {
		return clientes;
	}

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

	public String getIdClienteSeleccionado() {
		return idClienteSeleccionado;
	}

	public void setIdClienteSeleccionado(String idClienteSeleccionado) {
		this.idClienteSeleccionado = idClienteSeleccionado;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	public SubscriptionUserAction() {
		subscriptionUserDAO = new SubscriptionUserDAO();
	}

	/**** METHOD: LISTING ****/

	public String doListing() {
		setSubscriptors(subscriptionUserDAO
				.findSubscriptrorsBySubscriptionId(Long.valueOf(idSubscription)));
		return SUCCESS;
	}

	/**** [END] // METHOD: LISTING ****/

	/*** METHOD: goToInsert ***/

	public void setClientes(List<Client> clientes) {
		this.clientes = clientes;
	}
	public String goToInsert() {
		ClientDAO clientDAO = new ClientDAO();
		SubscriptionUserDAO subscriptionUserDAO = new SubscriptionUserDAO();
		setClientes(clientDAO.findAll());
		List<SubscriptionUser>subscriptors_tmp = subscriptionUserDAO.findSubscriptrorsBySubscriptionId(Long.parseLong(idSubscription));
		String dni=null;
		for (SubscriptionUser subscriptionUser: subscriptors_tmp){
			dni = subscriptionUser.getCliente().getDni();
			for (int i=0;i<clientes.size();i++){
				if (clientes.get(i).getDni().equals(dni)){
					clientes.remove(i);
				}
			}
		}
		setAction("insertSubscriptor.action");
		return "insertSubscriptor";
	}

	public String doInsert() {

		if (subscriptionUserDAO.insertSubscritor(
				Integer.valueOf(idClienteSeleccionado),
				Integer.valueOf(idSubscription))) {
			return SUCCESS;
		} else {
			addActionError(getText("errors.invalidad.insert.subscriptor"));
			setAction("insertSubscriptor.action");
			return "insertSubscriptor";
		}
	}

	public String doDelete() {
		if (!subscriptionUserDAO.deleteSubscritor(
				Integer.valueOf(idClienteSeleccionado),
				Integer.valueOf(idSubscription))) {
			addActionError(getText("errors.invalid.delete.subscriptor"));
		}
		return SUCCESS;
	}
}
