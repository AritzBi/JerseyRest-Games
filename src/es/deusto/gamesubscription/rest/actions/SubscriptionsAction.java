package es.deusto.gamesubscription.rest.actions;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import es.deusto.gamesubscription.rest.dao.SubscriptionDAO;
import es.deusto.gamesubscription.rest.model.Subscription;

public class SubscriptionsAction extends ActionSupport {

	public String idGame;

	public List<Subscription> subscriptions = null;

	public String editedSubscription;

	public String deletedSubscription;

	public Subscription subscription = null;

	private String action;

	public String getIdGame() {
		return idGame;
	}

	public void setIdGame(String idGame) {
		this.idGame = idGame;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public String getEditedSubscription() {
		return editedSubscription;
	}

	public void setEditedSubscription(String editedSubscription) {
		this.editedSubscription = editedSubscription;
	}

	public String getDeletedSubscription() {
		return deletedSubscription;
	}

	public void setDeletedSubscription(String deletedSubscription) {
		this.deletedSubscription = deletedSubscription;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	private static final long serialVersionUID = 1L;

	public String doListing() {
		SubscriptionDAO subscriptionsDAO = new SubscriptionDAO();
		if ( idGame == null )
		{
			idGame = String.valueOf( ServletActionContext.getRequest().getAttribute("idGame") );
		}
		setSubscriptions(subscriptionsDAO.findSubscriptionsByIdGame(Integer
				.valueOf(idGame)));
		return SUCCESS;
	}

	public String goToInsert() {
		setAction("insertSubscription.action");
		return "insertSubscription";
	}

	public String goToEditSubscription() {
		SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
		setSubscription(subscriptionDAO.getById(Integer
				.parseInt(editedSubscription)));
		setAction("editSubscription.action");
		return "editSubscription";
	}

	public String doEdit() {
		SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
		if(validateSubscriptionInfo()){
			if (subscriptionDAO.updateSubscription(subscription)) {
				return SUCCESS;
			} else {
				addActionError("errors.invalidad.update.subscription");
				setAction("editSubscription.action");
				return "editSubscription";
			}			
		}
		setAction("editSubscription.action");
		return "editSubscription";

	}
	
	public String doInsert() {
		SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
		if(validateSubscriptionInfo()){
			if(subscriptionDAO.insertSubscription(subscription)) {
				return SUCCESS;
			} else {
				addActionError("errors.invalidad.insert.subscription");
				setAction("insertSubscription.action");
				return "insertSubscription";
			}	
		}
		setAction("insertSubscription.action");
		return "insertSubscription";
	}
	
	public String doDelete() {
		SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
		if ( !subscriptionDAO.deleteById(Integer.valueOf( deletedSubscription ) ) ) {
			addActionError("errors.invalidad.delete.subscription");
		}
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	private boolean validateSubscriptionInfo() {
		boolean allCorrect = true;
		if (StringUtils.isBlank(subscription.getName())) {
			addFieldError("subscription.name", getText("errors.required.subscription.name"));
			allCorrect = false;
		}
		if (StringUtils.isBlank(subscription.getDescription())) {
			addFieldError("subscription.description",
					getText("errors.required.subscription.description"));
			allCorrect = false;
		}
		
		
		return allCorrect;
	}
}
