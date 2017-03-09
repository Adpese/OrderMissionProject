package com.sopra.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Table(name="Mission")
public class Mission {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_mission")
	private int Id; 
	
	@Column(name="Collab_firstName")
	private String collabFirstName;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="project")
	private String project;
	
	@Column(name="agency")
	private String agency;
	
	@Column(name="division")
	private String division;

	@Column(name="status")
	private String status;
	
	@OneToMany(mappedBy="mission", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Itinerary> itineraries;

	public int getId() {
		return Id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Itinerary> getItineraries() {
		return itineraries;
	}

	public void setItineraries(Set<Itinerary> itineraries) {
		this.itineraries = itineraries;
	}

	@Override
	public String toString() {
		return "Mission [Id=" + Id + ", collabFirstName=" + collabFirstName + ", date=" + date + ", project=" + project
				+ ", agency=" + agency + ", division=" + division + ", status=" + status + ", itineraries="
				+ itineraries + "]";
	}
	
	
	
	

}

