package es.deusto.gamesubscription.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Client {
	private int id;
	private String name;
	private String surname;
	private String dni;
	private String address;
	private String tel_number;
	public Client(){
		this.id=-1;
		this.name="";
		this.surname="";
		this.dni="";
		this.address="";
		this.tel_number="";
	}
	public Client(int id, String name, String surname, String dni,
			String address, String tel_number) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.address = address;
		this.tel_number = tel_number;
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel_number() {
		return tel_number;
	}
	public void setTel_number(String tel_number) {
		this.tel_number = tel_number;
	}
	
	
}
