package es.deusto.gamesubscription.rest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.deusto.gamesubscription.rest.model.Client;
import es.deusto.gamesubscription.rest.model.Game;



public class GameDao {
private Connection con;
    
    private String dataSource = "//localhost/games";
    private String username = "root";
    private String password = "toor";
    private String driver = "com.mysql.jdbc.Driver";
    private String protocol = "jdbc:mysql";
    private static GameDao instance;
    
    public GameDao(){
    }
    
    public GameDao(String dataSource, String username, String password){
    	this.dataSource = dataSource;
    	this.username = username;
    	this.password = password;
    }
	
	public static GameDao instance(){
		if (instance == null) instance = new GameDao();
		return instance;
	}
	
    
    public void conectar() throws ClassNotFoundException, SQLException{
        Class.forName(driver);        
        String url = protocol + ":" + dataSource;
        con = DriverManager.getConnection(url, username, password);               
    }
    
    public void desconectar() throws SQLException{
        con.close();
    }    
    
    public Game getGame(long id) throws SQLException, ClassNotFoundException{ 
    	this.conectar();
    	Game game = null;
    	String select = "select * from GAMES where id_game='" + id +"'";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(select);
        if (rs.next()){
        	game = new Game();
        	game.setName(rs.getString("name"));
        	game.setDescription(rs.getString("description"));
        	game.setType(rs.getString("type"));
        	game.setId(rs.getInt("id_game"));
        	game.setAge(rs.getInt("age"));
        }
        rs.close();
        stmt.close();
        this.desconectar();
        return game;
    }
    
    public ArrayList<Game> getGames() throws SQLException, ClassNotFoundException{
    	System.out.println("PASDO PORQ");
    	this.conectar();
    	ArrayList<Game>games=new ArrayList<Game>();
    	String select="select * from GAMES";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(select);
        Game game=null;
        while (rs.next()){
        	game = new Game();
        	game.setName(rs.getString("name"));
        	game.setDescription(rs.getString("description"));
        	game.setType(rs.getString("type"));
        	game.setId(rs.getInt("id_game"));
        	game.setAge(rs.getInt("age"));
        	games.add(game);
        }
        rs.close();
        stmt.close();
        this.desconectar();
        return games;
        
    }
    
    public int insertGame(Game game) throws SQLException, ClassNotFoundException{
    	this.conectar();
        String insert = "insert into GAMES " +
                        "(name, description, type, age) " +
                        "VALUES ('" + game.getName() +
                        "','" + game.getDescription() +
                        "','" + game.getType() +
                        "','"  + game.getAge() + "')";                        
        Statement stmt = con.createStatement();
        stmt.executeUpdate(insert, Statement.RETURN_GENERATED_KEYS);
        ResultSet generatedKeys = stmt.getGeneratedKeys();
        if (generatedKeys.next()) {
        	long key=generatedKeys.getLong(1);
            stmt.close();
            this.desconectar();
            return (int)key;
        }
        else {
            stmt.close();        
            this.desconectar();
            return -1;
        }
    }
    
    public int modifyGame(Game game) throws SQLException, ClassNotFoundException{
    	this.conectar();
    	String query="update GAMES set name=?, description=?, type=?, age=? where id_game=?";
    	PreparedStatement prest;
		try {
			prest = con.prepareStatement(query);
	    	prest.setString(1, game.getName());
	    	prest.setString(2, game.getDescription());
	    	prest.setString(3, game.getType());
	    	prest.setLong(4, game.getAge());
	    	prest.setLong(5, game.getId());
	    	prest.executeUpdate();
	    	prest.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.desconectar();
			return -1;
		}
		this.desconectar();
    	return 1;
    }
    
    public int deleteGame(long id) throws ClassNotFoundException, SQLException {
    	this.conectar();
    	String query="delete from GAMES where id_game=?";
    	PreparedStatement prest;
		try {
			prest = con.prepareStatement(query);
	    	prest.setLong(1, id);
	    	prest.executeUpdate();
	    	prest.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.desconectar();
			return -1;
		}
		this.desconectar();
    	return 1;
    	
    }
    
    public int deleteClient(long id) throws ClassNotFoundException, SQLException {
    	this.conectar();
    	String query="delete from CLIENTS where id_client=?";
    	PreparedStatement prest;
		try {
			prest = con.prepareStatement(query);
	    	prest.setLong(1, id);
	    	prest.executeUpdate();
	    	prest.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.desconectar();
			return -1;
		}
		this.desconectar();
    	return 1;
    	
    }
    public Client getClient(long id) throws SQLException, ClassNotFoundException{ 
    	this.conectar();
    	Client client = null;
    	String select = "select * from CLIENTS where id_client='" + id +"'";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(select);
        if (rs.next()){
        	client = new Client();
        	client.setName(rs.getString("name"));
        	client.setSurname(rs.getString("surname"));
        	client.setDni(rs.getString("dni"));
        	client.setId(rs.getInt("id_client"));
        	client.setAddress(rs.getString("address"));
        	client.setTel_number(rs.getString("tel_number"));
        }
        rs.close();
        stmt.close();
        this.desconectar();
        return client;
    }
    public ArrayList<Client> getClients() throws SQLException, ClassNotFoundException{
    	this.conectar();
    	ArrayList<Client>clients=new ArrayList<Client>();
    	String select="select * from CLIENTS";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(select);
        Client client=null;
        while (rs.next()){
        	client = new Client();
        	client.setName(rs.getString("name"));
        	client.setSurname(rs.getString("surname"));
        	client.setDni(rs.getString("dni"));
        	client.setId(rs.getInt("id_client"));
        	client.setAddress(rs.getString("address"));
        	client.setTel_number(rs.getString("tel_number"));
        	clients.add(client);
        }
        rs.close();
        stmt.close();
        this.desconectar();
        return clients;
        
    }
    
    public int insertClient(Client client) throws SQLException, ClassNotFoundException{
    	this.conectar();
        String insert = "insert into CLIENTS " +
                        "(name, surname, dni, address, tel_number) " +
                        "VALUES ('" + client.getName() +
                        "','" + client.getSurname() +
                        "','" + client.getDni() +
                        "','"  + client.getAddress() + 
                        "','"  + client.getTel_number() + "')";                        
        Statement stmt = con.createStatement();
        stmt.executeUpdate(insert, Statement.RETURN_GENERATED_KEYS);
        ResultSet generatedKeys = stmt.getGeneratedKeys();
        if (generatedKeys.next()) {
        	long key=generatedKeys.getLong(1);
            stmt.close();
            this.desconectar();
            return (int)key;
        }
        else {
            stmt.close();        
            this.desconectar();
            return -1;
        }
    }
    
    public int modifyClient(Client client) throws SQLException, ClassNotFoundException{
    	this.conectar();
    	String query="update CLIENTS set name=?, surname=?, dni=?, address=?,tel_number=? where id_client=?";
    	PreparedStatement prest;
		try {
			prest = con.prepareStatement(query);
	    	prest.setString(1, client.getName());
	    	prest.setString(2, client.getSurname());
	    	prest.setString(3, client.getDni());
	    	prest.setString(4, client.getAddress());
	    	prest.setString(5, client.getTel_number());
	    	prest.executeUpdate();
	    	prest.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.desconectar();
			return -1;
		}
		this.desconectar();
    	return 1;
    }
}
