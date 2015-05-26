package es.deusto.gamesubscription.rest.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import es.deusto.gamesubscription.rest.model.User;

public class UserDAO {

	private SqlSessionFactory sqlSessionFactory;
	
	public UserDAO() {
		sqlSessionFactory = SqlSessionFactoryHelper.getSqlSessionFactory();
	}
	
	public boolean findByUserAndPassword( String user, String password ) {
		User userAux = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		HashMap<String, String> mapaDatos = new HashMap<String, String>();
		mapaDatos.put("username", user );
		mapaDatos.put("password", password );
		
		try {
			userAux = session.selectOne("getByUserAndPassword", mapaDatos);
		} finally {
			session.close();
		}
		return ( userAux != null );
	}
}
