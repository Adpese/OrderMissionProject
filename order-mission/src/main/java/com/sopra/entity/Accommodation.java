package com.sopra.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="accommodation")
public class Accommodation {
	
	
	@javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_accommodation")
    private int  Id;
	 
	 @Column(name="hotel_name")
	 private String hotelName;
	 @Column(name="entry_date")
	 private String entryDate;
	 @Column(name="departure_date")
	 private String departureDate;
	 @Column(name="number_of_nights")
	 private int numberOfNights;
	 @Column(name="price")
	 private long price;
	 @ManyToOne
     @JoinColumn(name = "id_mission") 
    private Mission mission;
	
	
	 public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public int getNumberOfNights() {
		return numberOfNights;
	}
	public void setNumberOfNights(int numberOfNights) {
		this.numberOfNights = numberOfNights;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
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
		return "Accommodation [Id=" + Id + ", hotelName=" + hotelName + ", entryDate=" + entryDate + ", departureDate=" + departureDate
				+ ", numberOfNights=" + numberOfNights + "]";
	}
	 
}