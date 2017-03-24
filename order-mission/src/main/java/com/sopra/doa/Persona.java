package com.sopra.doa;

public class Persona {
	
	private String name;
	private String password;
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return name;
	}
	public void setNombre(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Persona [name=" + name + ", password=" + password + "]";
	}
	

}
