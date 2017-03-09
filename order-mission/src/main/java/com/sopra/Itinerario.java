package com.sopra;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Itinerario")
public class Itinerario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_itinerary")
	private int  Id;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="origin")
	private String origin;
	
	@Column(name="destination")
	private String destination;
	
	@Column(name="h_departure")
	private String h_departure;
	
	@Column(name="h_arrival")
	private String h_arrival;
	
	@Column(name="transport")
	private String transport;
	
	@Column(name="company")
	private String company;
	
	@Column(name="price")
	private String price;
	
	@ManyToOne
	@JoinColumn(name="id_mission")
	private Mission Id_mission;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getH_departure() {
		return h_departure;
	}
	public void setH_departure(String h_departure) {
		this.h_departure = h_departure;
	}
	public String getH_arrival() {
		return h_arrival;
	}
	public void setH_arrival(String h_arrival) {
		this.h_arrival = h_arrival;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Itinerario [Id=" + Id + ", date=" + date + ", origin=" + origin + ", destination=" + destination
				+ ", h_departure=" + h_departure + ", h_arrival=" + h_arrival + ", transport=" + transport
				+ ", company=" + company + ", price=" + price + "]";
	}

}
