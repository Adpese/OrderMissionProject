package com.sopra.entity;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Project")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_project")
	private int Id;
	
	

	@Column(name="name_proj")
	private String nameProj;
	
	@Column(name="agency")
	private String agency;
	
	@Column(name="division")
	private String division;
	
	@OneToMany(mappedBy="project", cascade = CascadeType.ALL)
    private List<Mission> missions;

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNameProj() {
		return nameProj;
	}
	public void setNameProj(String nameProj) {
		this.nameProj = nameProj;
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
	@JsonIgnore
	public List<Mission> getMission() {
		return missions;
	}

	public void setMission(List<Mission> misiones) {
		this.missions = misiones;
		
	}
	@Override
	public String toString() {
		return "Project [Id=" + Id + ", nameProj=" + nameProj + ", agency=" + agency + ", division=" + division
				+ ", missions=" + missions + "]";
	}
}
