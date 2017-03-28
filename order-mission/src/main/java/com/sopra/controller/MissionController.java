package com.sopra.controller;

import java.util.Hashtable;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sopra.doa.Persona;
import com.sopra.entity.Mission;
import com.sopra.service.MissionServices;

@RestController
public class MissionController {
	
	@Resource 
	MissionServices missionServices;
	
	@RequestMapping(value = "missionSave", method = RequestMethod.POST)
	public void save(@RequestBody Mission mission){
		
	   missionServices.saveMissionItinerary(mission);
   }
   
   @RequestMapping(value="getPerson/{id}", method= RequestMethod.GET)
   public Persona getPerson( @PathVariable int id){
	 

	   Mission nombre = missionServices.getNombreById(id);
	   Persona per = new Persona();
	    per.setNombre(nombre.getCollabFirstName());
	  //per.setTelefono(66666);
	   
	   return per;
   }
   
   @RequestMapping(value = "loginLDAP", method = RequestMethod.POST)
	public String login(@RequestBody String credentialsJSON){
	   
	   
	   return missionServices.login(credentialsJSON);
	
  }
   

}
