package es.deusto.gamesubscription.rest.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

import es.deusto.gamesubscription.rest.dao.ClientDAO;
import es.deusto.gamesubscription.rest.model.Client;

public class ClientsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Variable utilizada para obtener todos los clientes
	public List<Client> clients;
	// Variable utilizada para buscar un cliente
	public String dni;
	// Variable utilizada para editar un cliente
	public String editedClient;
	// Variable utilizada para almacenar el cliente insertado o editado
	public Client client;
	// Variable utilizada para borrar un cliente
	public String deletedClient;
	// Specify the action to know if update or insert
	public String action;

	public ClientDAO clientDAO;

	public ClientsAction() {
		clientDAO = new ClientDAO();
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEditedClient() {
		return editedClient;
	}

	public void setEditedClient(String editedClient) {
		this.editedClient = editedClient;
	}

	public String getDeletedClient() {
		return deletedClient;
	}

	public void setDeletedClient(String deletedClient) {
		this.deletedClient = deletedClient;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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

	public String doListing() {
		setClients(clientDAO.findAll());
		return SUCCESS;
	}

	public String doGetClient() {
		if (dni == null) {
			addFieldError("dni", "errors.required.dni");
		} else {
			Client cliente = clientDAO.getClientByDNI(dni);
			if (cliente == null)
				addActionError("¡No existe cliente con ese DNI!");
			else {
				List<Client> clientesAux = new ArrayList<Client>();
				clientesAux.add(cliente);
				setClients(clientesAux);
			}
		}
		return SUCCESS;
	}

	public String doGoToEdit() {
		setClient(clientDAO.getClientById(Integer.valueOf(editedClient)));
		setAction("editClient.action");
		return "editClient";
	}

	public String doEdit() {

		if (validateClientInfo()) {
			if (clientDAO.updateClient(client))
				return SUCCESS;
			else {
				addActionError(getText("errors.invalid.edit.client"));
			}
		}
		return "editClient";

	}

	public String doDelete() {
		if ( !clientDAO.deleteById(Integer.valueOf(deletedClient))) {
			addActionError(getText("errors.invalid.delete.client"));
		}
		return SUCCESS;
	}

	private boolean validateClientInfo() {
		boolean allCorrect = true;
		if (StringUtils.isBlank(client.getName())) {
			addFieldError("client.name", getText("errors.required.client.name"));
			allCorrect = false;
		}
		if (StringUtils.isBlank(client.getSurname())) {
			addFieldError("client.surname", getText("errors.required.client.surname"));
			allCorrect = false;
		}
		if (StringUtils.isBlank(client.getAddress())) {
			addFieldError("client.address", getText("errors.required.client.address"));
			allCorrect = false;
		}
		if (StringUtils.isBlank(client.getTel_number())) {
			addFieldError("client.tel_number",
					getText("errors.required.client.telephone"));
			allCorrect = false;
		}
		return allCorrect;
	}
}
