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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	
	@Column(name="status",nullable=false)
	private String status;
	@Column(name="created_By")
	private String createdBy;

	@ManyToOne
	@JoinColumn(name="id_project")
	private Project project;
	
	
	@OneToMany(mappedBy="mission", cascade = CascadeType.ALL)
	private List<Itinerary> itineraries;

	@OneToMany(mappedBy="mission", cascade = CascadeType.ALL)
	private List<Accommodation> accommodations;

	@OneToMany(mappedBy="mission", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Rent> rents;
	
	

	public List<Accommodation> getAccommodations() {
		return accommodations;
	}

	public void setAccommodations(List<Accommodation> accommodations) {
		this.accommodations = accommodations;
	}

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
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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
	
	public List<Rent> getRents() {
		return rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}
	@Override
	public String toString() {
		return "Mission [Id=" + Id + ", collabFirstName=" + collabFirstName + ", date=" + date + ", project=" + project
				 + ", status=" + status + ", itineraries="
				+ itineraries + "]";
	}
	
	
	
	

}

