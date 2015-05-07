package es.deusto.gamesubscription.rest.main;

import java.util.List;

import es.deusto.gamesubscription.rest.dao.ClientDAO;
import es.deusto.gamesubscription.rest.dao.GamesDAO;
import es.deusto.gamesubscription.rest.dao.SubscriptionDAO;
import es.deusto.gamesubscription.rest.dao.SubscriptionUserDAO;
import es.deusto.gamesubscription.rest.model.Client;
import es.deusto.gamesubscription.rest.model.Game;
import es.deusto.gamesubscription.rest.model.Subscription;
import es.deusto.gamesubscription.rest.model.SubscriptionUser;

public class MainTest {

	// Example of id game
	private static int ID_JUEGO_PRUEBA = 15;
	// Example of id subscription
	private static int ID_SUBSCRIPTION = 2;
	// Example of id client
	private static int ID_CLIENT = 1;

	public static void main(String[] args) {

		checkClientMethods();
		checkGameMethods();
		checkSubscriptionMethods();
		checkSubscriptionUserMethods();
	}

	private static void checkSubscriptionUserMethods() {
		System.out.println("*** USER SUBSCRIPTIONS **");
		SubscriptionUserDAO subscriptionUserDAO = new SubscriptionUserDAO();

		// [INSERCION: nuevo subscritor]
		if (subscriptionUserDAO.insertSubscritor(ID_CLIENT, ID_SUBSCRIPTION)) {
			System.out.println("Inserción correcta de un nuevo subscriptor con id " + ID_CLIENT  + " para la subscripcion " + ID_SUBSCRIPTION);
		}
		
		//[SELECT: subscritores de una subscripcion]
		List<SubscriptionUser> subscriptors = subscriptionUserDAO.findSubscriptrorsBySubscriptionId(ID_SUBSCRIPTION);
		System.out.println("Clientes de subscripcion " + ID_SUBSCRIPTION + subscriptors);
		
		//[DELETE: nuevo subsritor]
		if (subscriptionUserDAO.deleteSubscritor(ID_CLIENT, ID_SUBSCRIPTION) ) {
			System.out.println("Borrado correctamente subscritor con id "+ ID_CLIENT + " para la subscripcion " + ID_SUBSCRIPTION);
		}
		System.out.println("*******************");
	}

	private static void checkSubscriptionMethods() {

		System.out.println("*** SUBSCRIPTIONS ***");
		SubscriptionDAO subscriptionDAO = new SubscriptionDAO();

		// [INSERCION: nueva subscripcion]
		Subscription subscription = new Subscription();
		subscription.setDescription("For Standard users");
		subscription.setName("Standard");
		subscription.setPrice(20);
		subscription.setIdGame(ID_JUEGO_PRUEBA);

		if (subscriptionDAO.insertSubscription(subscription)) {
			System.out.println("Inserción correcta de la subscripcion con id "
					+ subscription.getId());
		}

		// [SELECT: subscripciones del juego]
		List<Subscription> subscriptions = subscriptionDAO
				.findSubscriptionsByIdGame(ID_JUEGO_PRUEBA);
		System.out.println("Subscripciones del juego: " + subscriptions);

		// [UPDATE: subscripcion actualizado]
		subscription.setDescription("For Standard Users Rate");
		if (subscriptionDAO.updateSubscription(subscription)) {
			System.out.println("Actualizado correctamente subscripcion con id "
					+ subscription.getId());
		}

		// [DELETE: juego insertado]
		long subscriptionId = subscription.getId();
		if (subscriptionDAO.deleteById(subscriptionId)) {
			System.out.println("Borrado correctamente subscripcion con id "
					+ subscriptionId);
		}
		System.out.println("*******************");
	}

	private static void checkGameMethods() {
		System.out.println("*** GAMES *** ");
		GamesDAO gamesDAO = new GamesDAO();

		// [INSERCION: nuevo juego]
		Game game = new Game();
		game.setAge(19);
		game.setDescription("Action game");
		game.setName("GTA VI");
		game.setType("Action");

		if (gamesDAO.insertGame(game)) {
			System.out.println("Inserción correcta del juego con id "
					+ game.getId());
		}

		// [SELECT: juego insertado]
		Game juegoInsertado = gamesDAO.getGameById(game.getId());
		System.out.println(juegoInsertado);

		// [UPDATE: juego insertado]
		juegoInsertado.setDescription("Action game of 2010");
		if (gamesDAO.updateGame(juegoInsertado)) {
			System.out.println("Actualizado correctamente juego con id "
					+ juegoInsertado.getId());

			juegoInsertado = gamesDAO.getGameById(game.getId());
			System.out.println("*** JUEGO ACTUALIZADO ***");
			System.out.println(juegoInsertado);
		}

		// [DELETE: juego insertado]
		int juegoId = juegoInsertado.getId();
		if (gamesDAO.deleteById(juegoId)) {
			System.out.println("Borrado correctamente juego con id " + juegoId);
		}

		// [SELECT: todos los juegos]
		List<Game> games = gamesDAO.findAll();
		System.out.println("Juegos recuperados: " + games);
		System.out.println("*******************");
	}

	private static void checkClientMethods() {
		System.out.println("*** CLIENTS *** ");
		ClientDAO clientDAO = new ClientDAO();

		// [INSERCION: nuevo cliente]
		Client cliente = new Client();
		cliente.setDni("458939695");
		cliente.setAddress("Calle La Pantomima");
		cliente.setName("Hugo");
		cliente.setSurname("Perez");
		cliente.setTel_number("657890231");

		if (clientDAO.insertClient(cliente)) {
			System.out.println("Inserción correcta del cliente con id "
					+ cliente.getId());
		}

		// [SELECT: cliente insertado por id]
		Client clienteInsertado = clientDAO.getClientById(cliente.getId());
		System.out.println("*** SELECT BY ID *** ");
		System.out.println(clienteInsertado);

		// [SELECT: cliente insertado por DNI]
		clienteInsertado = clientDAO.getClientByDNI(cliente.getDni());
		System.out.println("*** SELECT BY DNI *** ");
		System.out.println(clienteInsertado);

		// [UPDATE: cliente insertado]
		clienteInsertado.setSurname("Perez Galdos");
		if (clientDAO.updateClient(clienteInsertado)) {
			System.out.println("Actualizado correctamente cliente con id "
					+ clienteInsertado.getId());

			clienteInsertado = clientDAO.getClientByDNI(clienteInsertado
					.getDni());
			System.out.println("** CLIENTE ACTUALIZADO **");
			System.out.println(clienteInsertado);
		}

		// [DELETE: cliente insertado]
		int clientId = clienteInsertado.getId();
		if (clientDAO.deleteById(clienteInsertado.getId())) {
			System.out.println("Borrado correctamente cliente con id "
					+ clientId);
		}

		// [SELECT: todos los clientes]
		List<Client> clientes = clientDAO.findAll();
		System.out.println("Clientes recuperados: " + clientes);
		
		System.out.println("*******************");
	}
}
