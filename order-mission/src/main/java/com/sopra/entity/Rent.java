package com.sopra.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Entity
@Table(name="rentacar")
public class Rent {
	
	
	
	 @javax.persistence.Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(name="id_rentacar")
     private int  Id;
	 
	 @Column(name="driver_name")
	 private String driverName;
	 
	 @Column(name="pickup_date")
	 private String pickupDate;
	 
	 @Column(name="pickup_hour")
	 private String pickupHour;
	 
	 @Column(name="pickup_place")
	 private String pickupPlace;
	 
	 @Column(name="delivery_date")
	 private String deliveryDate;
	 
	 @Column(name="delivery_hour")
	 private String deliveryHour;
	 
	 @Column(name="delivery_place")
	 private String deliveryPlace;
	 
	 @Column(name="price")
	 private long price;
	 
	 @ManyToOne
     @JoinColumn(name="id_mission")
     private Mission mission;
	 
	 

	 public Rent(int id, String driverName, String pickupDate, String pickupHour, String pickupPlace,
			String deliveryDate, String deliveryHour, String deliveryPlace, long price) {
		super();
		Id = id;
		this.driverName = driverName;
		this.pickupDate = pickupDate;
		this.pickupHour = pickupHour;
		this.pickupPlace = pickupPlace;
		this.deliveryDate = deliveryDate;
		this.deliveryHour = deliveryHour;
		this.deliveryPlace = deliveryPlace;
		this.price = price;
	}
	 
	 
	public Rent(int id, String driverName, String pickupDate, String pickupHour, String pickupPlace,
			String deliveryDate, String deliveryHour, String deliveryPlace, long price, Mission mission) {
		super();
		Id = id;
		this.driverName = driverName;
		this.pickupDate = pickupDate;
		this.pickupHour = pickupHour;
		this.pickupPlace = pickupPlace;
		this.deliveryDate = deliveryDate;
		this.deliveryHour = deliveryHour;
		this.deliveryPlace = deliveryPlace;
		this.price = price;
		this.mission = mission;
	}


	public Rent() {
		super();
	}


	public int getId() {
			return Id;
		}
		public void setId(int id) {
			Id = id;
		}
		public String getDriverName() {
			return driverName;
		}
		public void setDriverName(String driverName) {
			this.driverName = driverName;
		}
		public String getPickupDate() {
			return pickupDate;
		}
		public void setPickupDate(String pickupDate) {
			this.pickupDate = pickupDate;
		}
		public String getPickupHour() {
			return pickupHour;
		}
		public void setPickupHour(String pickupHour) {
			this.pickupHour = pickupHour;
		}
		public String getPickupPlace() {
			return pickupPlace;
		}
		public void setPickupPlace(String pickupPlace) {
			this.pickupPlace = pickupPlace;
		}
		public String getDeliveryDate() {
			return deliveryDate;
		}
		public void setDeliveryDate(String deliveryDate) {
			this.deliveryDate = deliveryDate;
		}
		public String getDeliveryHour() {
			return deliveryHour;
		}
		public void setDeliveryHour(String deliveryHour) {
			this.deliveryHour = deliveryHour;
		}
		public String getDeliveryPlace() {
			return deliveryPlace;
		}
		public void setDeliveryPlace(String deliveryPlace) {
			this.deliveryPlace = deliveryPlace;
		}
		public long getPrice() {
			return price;
		}
		public void setPrice(long price) {
			this.price = price;
		}
		@JsonIgnore
		public Mission getMission() {
			return mission;
		}
		public void setMission(Mission mission) {
			this.mission = mission;
		}
		@Override
		public String toString() {
			return "Rents [Id=" + Id + ", driverName=" + driverName + ", pickupDate=" + pickupDate
					+ ", pickupHour=" + pickupHour + ", pickupPlace=" + pickupPlace + ", deliveryDate=" + deliveryDate
					+ ", deliveryHour=" + deliveryHour + ", deliveryPlace=" + deliveryPlace + ", price=" + price + "]";
		}
		
		
}