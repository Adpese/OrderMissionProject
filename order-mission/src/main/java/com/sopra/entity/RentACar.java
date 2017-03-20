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
@Table(name="rentacar")
public class RentACar {
	
	
	
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
	  @JoinColumn(name = "id_mission") 
     private Mission mission;

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
		public String getPickup_Date() {
			return pickupDate;
		}
		public void setPickup_Date(String pickup_Date) {
			this.pickupDate = pickup_Date;
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
		 public Mission getMission() {
	            return mission;
	        }

	        public void setMission(Mission mission) {
	            this.mission = mission;
	        }
		@Override
		public String toString() {
			return "RentACar [Id=" + Id + ", driverName=" + driverName + ", pickupDate=" + pickupDate
					+ ", pickupHour=" + pickupHour + ", pickupPlace=" + pickupPlace + ", deliveryDate=" + deliveryDate
					+ ", deliveryHour=" + deliveryHour + ", deliveryPlace=" + deliveryPlace + ", price=" + price + "]";
		}
		
		
}