package com.sopra.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="Mission")
public class Mission {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_mission")
	private int Id; 
	
	@Column(name="Collab_firstName",nullable=false)
	private String collabFirstName;
	
	@Column(name="date",nullable=false)
	private Date date;
	
	@Column(name="project",nullable=false)
	private String project;
	
	@Column(name="agency",nullable=false)
	private String agency;
	
	@Column(name="division",nullable=false)
	private String division;

	@Column(name="status",nullable=false)
	private String status;
	@Column(name="created_By")
	private String createdBy;
	
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy="mission", cascade = CascadeType.ALL)
	private List<Itinerary> itineraries;
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy="mission", cascade = CascadeType.ALL)
	private List<Accommodation> accommodations;
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy="mission", cascade = CascadeType.ALL)
	private List<RentACar> rents;

	

	public int getId() {
		return Id;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getCollabFirstName() {
		return collabFirstName;
	}

	public void setCollabFirstName(String collabFirstName) {
		this.collabFirstName = collabFirstName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String  getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Itinerary> getItineraries() {
		return itineraries;
	}

	public void setItineraries(List<Itinerary> itineraries) {
		this.itineraries = itineraries;
	}
	public List<RentACar> getRents() {
		return rents;
	}

	public void setRentACar(List<RentACar> rents) {
		this.rents = rents;
	}
	public List<Accommodation> getAccommodations() {
		return accommodations;
	}
	public void setAccommodations(List<Accommodation> accommodations) {
		this.accommodations = accommodations;
	}
	
	
	@Override
	public String toString() {
		return "Mission [Id=" + Id + ", collabFirstName=" + collabFirstName + ", date=" + date + ", project=" + project
				+ ", agency=" + agency + ", division=" + division + ", status=" + status + ", itineraries="
				+ itineraries + "]";
	}
	
	
	
	

}

