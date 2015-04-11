package es.deusto.gamesubscription.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Subscription {

	private long id;
	private String name;
	private String description;
	private double price;
	
	private static int POSITION_ID = 0;
	private static int POSITION_NAME = 1;
	private static int POSITION_DESCRIPTION = 2;
	private static int POSITION_PRICE = 3;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Object[] getObjectArray ()
	{
		Object [] objeto = new Object[4];
		objeto[POSITION_ID] = id;
		objeto[POSITION_NAME] = name;
		objeto[POSITION_DESCRIPTION] = description;
		objeto[POSITION_PRICE] = price;
		
		return objeto;
	}
}
