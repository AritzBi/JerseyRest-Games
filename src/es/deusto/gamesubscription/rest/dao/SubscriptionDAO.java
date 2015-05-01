package es.deusto.gamesubscription.rest.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;

import es.deusto.gamesubscription.rest.model.Subscription;

public class SubscriptionDAO {

	private SqlSessionFactory sqlSessionFactory;
	
	public SubscriptionDAO() {
		sqlSessionFactory = SqlSessionFactoryHelper.getSqlSessionFactory();
	}
	
	public List<Subscription> findSubscriptionsByIdGame(int id) {
		List<Subscription> subscriptions = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			subscriptions = session.selectList("findSubscriptionsByIdGame", id);
		} finally {
			session.close();
		}
		return subscriptions;
	}
	
	public boolean insertSubscription(Subscription subscription) {
		boolean resultado = false;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			if (session.insert("insertSubscription", subscription) > 0) {
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

	
	public boolean updateSubscription(Subscription subscription) {
		boolean resultado = false;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			if (session.update("updateSubscription", subscription) > 0) {
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
	
	public boolean deleteById(long id) {
		boolean resultado = false;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			if (session.delete("deleteByIdSubscription", id) > 0) {
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
