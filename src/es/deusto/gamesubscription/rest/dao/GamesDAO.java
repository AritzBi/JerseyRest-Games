package es.deusto.gamesubscription.rest.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;

import es.deusto.gamesubscription.rest.model.Game;

public class GamesDAO {

	private SqlSessionFactory sqlSessionFactory;

	public GamesDAO() {
		sqlSessionFactory = SqlSessionFactoryHelper.getSqlSessionFactory();
	}

	public Game getGameById(int id) {
		Game game = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			game = session.selectOne("getGameById", id);
		} finally {
			session.close();
		}
		return game;
	}

	public List<Game> findAll() {
		List<Game> games = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			games = session.selectList("findAllGames");
		} finally {
			session.close();
		}

		return games;
	}

	public boolean insertGame(Game game) {
		boolean resultado = false;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			if (session.insert("insertGame", game) > 0) {
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

	public boolean updateGame(Game game) {
		boolean resultado = false;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			if (session.update("updateGame", game) > 0) {
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
			if (session.delete("deleteByIdGame", id) > 0) {
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
