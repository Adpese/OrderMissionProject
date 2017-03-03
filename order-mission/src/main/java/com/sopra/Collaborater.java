package com.sopra;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name="collaborater")
public class Collaborater {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="collab_name")
	private String collabName;
	
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
	
	@Column(name="collab_id")
	private String collab_id;

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getCollab_id() {
		return collab_id;
	}

	public void setCollab_id(String collab_id) {
		this.collab_id = collab_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCollabName() {
		return collabName;
	}

	public void setCollabName(String collabName) {
		this.collabName = collabName;
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

	@Override
	public String toString() {
		return "TaKK [id=" + id + ", collabName=" + collabName + ", collabFirstName=" + collabFirstName + ", date="
				+ date + ", project=" + project + ", agency=" + agency + "]";
	}
	

}

