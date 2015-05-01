package es.deusto.gamesubscription.rest.dao;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryHelper {

	private static SqlSessionFactory sqlSessionFactory;
    
	public static SqlSessionFactory getSqlSessionFactory()
	{
		if (sqlSessionFactory == null){
			try {
				String resource = "conf/mybatis-config.xml";
				Reader reader = Resources.getResourceAsReader(resource);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return sqlSessionFactory;
	}
}
