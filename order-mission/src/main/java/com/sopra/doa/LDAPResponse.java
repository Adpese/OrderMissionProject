package com.sopra.doa;

import java.util.ArrayList;

public class LDAPResponse {

	
	private String userRole;
	private String userAgency;
	private String userDisplayName;
	private ArrayList<String> displayNameList;
	
	
	public LDAPResponse(String userRole, String userAgency, String userDisplayName, ArrayList<String> displayNameList) {
		super();
		this.userRole = userRole;
		this.userAgency = userAgency;
		this.userDisplayName = userDisplayName;
		this.displayNameList = displayNameList;
	}
	
	public LDAPResponse(String userRole, String userAgency, String userDisplayName) {
		super();
		this.userRole = userRole;
		this.userAgency = userAgency;
		this.userDisplayName = userDisplayName;
	}
	
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserAgency() {
		return userAgency;
	}
	public void setUserAgency(String userAgency) {
		this.userAgency = userAgency;
	}
	public String getUserDisplayName() {
		return userDisplayName;
	}
	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}
	public ArrayList<String> getDisplayNameList() {
		return displayNameList;
	}
	public void setDisplayNameList(ArrayList<String> displayNameList) {
		this.displayNameList = displayNameList;
	} 
	
	
}
