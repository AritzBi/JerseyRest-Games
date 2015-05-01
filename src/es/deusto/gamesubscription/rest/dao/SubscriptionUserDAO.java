package es.deusto.gamesubscription.rest.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;

import es.deusto.gamesubscription.rest.model.Client;
import es.deusto.gamesubscription.rest.model.SubscriptionUser;

public class SubscriptionUserDAO {

	private SqlSessionFactory sqlSessionFactory;

	public SubscriptionUserDAO() {
		sqlSessionFactory = SqlSessionFactoryHelper.getSqlSessionFactory();
	}

	public List<SubscriptionUser> findSubscriptrorsBySubscriptionId(long id) {
		List<HashMap<String, Object>> results = null;
		List<SubscriptionUser> subscriptors = new ArrayList<SubscriptionUser>();
		SqlSession session = sqlSessionFactory.openSession();
		try {
			results = session.selectList("findSubscriptionsByIdSubscription", id );
		} catch (SqlSessionException e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		if (results != null) {
			SubscriptionUser subscriptor;
			for (HashMap<String, Object> map : results) {
				subscriptor = new SubscriptionUser();
				Client cliente = new Client();
				cliente.setId((int)map.get("id_client"));
				cliente.setName((String)map.get("name"));
				cliente.setSurname((String)map.get("surname"));
				cliente.setDni((String)map.get("dni"));
				cliente.setAddress((String) map.get("address"));
				cliente.setTel_number((String)map.get("tel_number"));
				subscriptor.setCliente(cliente);
				Timestamp fechaSus = (Timestamp)map.get("date");
				subscriptor.setFechaSuscripcion(fechaSus.toString());
				subscriptors.add(subscriptor);
			}
		}
		return subscriptors;
	}

	public boolean insertSubscritor(int idCliente, int idSubscripcion) {
		boolean resultado = false;
		SqlSession session = sqlSessionFactory.openSession();
		HashMap<String, Integer> mapaDatos = new HashMap<String, Integer>();
		mapaDatos.put("idCliente", idCliente);
		mapaDatos.put("idSubscripcion", idSubscripcion);
		try {
			if (session.insert("insertSubscritor", mapaDatos) > 0) {
				resultado = true;
			}
			session.commit();
		} catch (SqlSessionException e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		return resultado;
	}
	
	public boolean deleteSubscritor ( int idCliente, int idSubscripcion ) {
		boolean resultado = false;
		SqlSession session = sqlSessionFactory.openSession();
		HashMap<String, Integer> mapaDatos = new HashMap<String, Integer>();
		mapaDatos.put("idCliente", idCliente);
		mapaDatos.put("idSubscripcion", idSubscripcion);
		try {
			if (session.delete("deleteSubscritor", mapaDatos) > 0) {
				resultado = true;
			}
			session.commit();
		} catch (SqlSessionException e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		return resultado;
	}
}
