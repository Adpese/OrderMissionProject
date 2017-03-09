package com.sopra.entity;

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
@Table(name="itinerary")
public class Itinerary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_itinerary")
	private int  Id;
	
	@Column(name="create_date")
	private Date createdate;
	
	@Column(name="origin")
	private String origin;
	
	@Column(name="destination")
	private String destination;
	
	@Column(name="h_departure")
	private String departureHour;
	
	@Column(name="h_arrival")
	private String arrivalHour;
	
	@Column(name="transport")
	private String transport;
	
	@Column(name="company")
	private String company;
	
	@Column(name="price")
	private String price;
	
	@ManyToOne
	@JoinColumn(name="id_mission")
	private Mission mission;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		createdate = createdate;
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

	public String getDepartureHour() {
		return departureHour;
	}

	public void setDepartureHour(String departureHour) {
		this.departureHour = departureHour;
	}

	public String getArrivalHour() {
		return arrivalHour;
	}

	public void setArrivalHour(String arrivalHour) {
		this.arrivalHour = arrivalHour;
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

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	@Override
	public String toString() {
		return "Itinerario [Id=" + Id + ", Createdate=" + createdate + ", origin=" + origin + ", destination="
				+ destination + ", departureHour=" + departureHour + ", arrivalHour=" + arrivalHour + ", transport="
				+ transport + ", company=" + company + ", price=" + price + ", mission=" + mission + "]";
	}
	
}
