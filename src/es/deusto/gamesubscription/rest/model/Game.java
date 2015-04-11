package es.deusto.gamesubscription.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Game {
	private int id;
	private String name;
	private String description;
	private int age;
	private String type;
	
	public Game(){
		super();
		id=0;
		name="";
		description="";
		age=0;
		type="";
	}
	public Game(int id, String name, String description, int age, String type) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.age = age;
		this.type = type;
	}
	public Game(String name, String description, int age, String type) {
		super();
		this.name = name;
		this.description = description;
		this.age = age;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}