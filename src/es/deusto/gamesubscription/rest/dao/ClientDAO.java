package es.deusto.gamesubscription.rest.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;

import es.deusto.gamesubscription.rest.model.Client;

public class ClientDAO {

	private SqlSessionFactory sqlSessionFactory;

	public ClientDAO() {
		sqlSessionFactory = SqlSessionFactoryHelper.getSqlSessionFactory();
	}

	public Client getClientById(int id) {
		Client client = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			client = session.selectOne("getClientById", id);
		} finally {
			session.close();
		}
		return client;
	}
	
	public Client getClientByDNI(String dni) {
		Client client = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			client = session.selectOne("getClientByDNI", dni);
		} finally {
			session.close();
		}
		return client;
	}

	public List<Client> findAll() {
		List<Client> clients = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			clients = session.selectList("findAllClients");
		} finally {
			session.close();
		}

		return clients;
	}

	public boolean insertClient(Client cliente) {
		boolean resultado = false;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			if (session.insert("insertClient", cliente) > 0) {
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

	public boolean updateClient(Client client) {
		boolean resultado = false;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			if (session.update("updateClient", client) > 0) {
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

	public boolean deleteById(int id) {
		boolean resultado = false;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			if (session.delete("deleteByIdClient", id) > 0) {
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
