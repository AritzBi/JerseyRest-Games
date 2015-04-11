package es.deusto.gamesubscription.rest.model;


import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SubscriptionUser {
	private String fechaSuscripcion;
	
	private Client cliente;
	
	public SubscriptionUser(String fecha, Client cliente){
		this.fechaSuscripcion=fecha;
		this.cliente=cliente;
	}
	public SubscriptionUser(){
		this.fechaSuscripcion="";
		this.cliente=new Client();
	}


	public String getFechaSuscripcion() {
		return fechaSuscripcion;
	}



	public void setFechaSuscripcion(String fechaSuscripcion) {
		this.fechaSuscripcion = fechaSuscripcion;
	}



	public Client getCliente() {
		return cliente;
	}

	public void setCliente(Client cliente) {
		this.cliente = cliente;
	}
	
	
}
