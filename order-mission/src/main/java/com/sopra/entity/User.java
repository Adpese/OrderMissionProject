package com.sopra.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name="User", uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_user") 
	private int Id;
	@Column(name="name") 
	private String name;
	
	
	@OneToOne
	@JoinColumn(name="id_role")
	private Role rol;
	
	@ManyToMany
	private List<Project> project;
	
	@Column(name="agency") 
	private String agency;


	public int getId() {
		return Id;
	} 
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		name = name;
	}
	private String password;
	public Role getRol() {
		return rol;
	}
	public void setRol(Role rol) {
		this.rol = rol;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [Id=" + Id + ", Name=" + name + ", rol=" + rol + ", password=" + password + "]";
	}
	

}
