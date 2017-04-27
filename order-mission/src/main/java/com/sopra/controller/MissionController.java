package com.sopra.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sopra.doa.LDAPResponse;
import com.sopra.doa.Persona;
import com.sopra.entity.Mission;
import com.sopra.service.MissionServices;
import com.sopra.validator.MissionValidator;

@RestController
public class MissionController {
	
	@Resource 
	MissionServices missionServices;
	
	@Autowired
    MissionValidator missionValidator;
    
    @InitBinder("mission")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new MissionValidator());
    }

	
	@RequestMapping(value = "missionSave", method = RequestMethod.POST)
	public void save(@Valid @RequestBody Mission mission){
		
	   missionServices.saveMissionItinerary(mission);
   }
	
	@RequestMapping(value = "missionUpdate", method = RequestMethod.PUT)
	public void missionUpdate(@RequestBody Mission mission){
		
	   missionServices.updateMission(mission);   }
	
   @RequestMapping(value="getPerson/{id}", method= RequestMethod.GET)
   public Persona getPerson( @PathVariable int id){
	 

	   Mission nombre = missionServices.getNombreById(id);
	   Persona per = new Persona();
	    per.setNombre(nombre.getCollabFirstName());
	  //per.setTelefono(66666);
	   
	   return per;
   }
   
   @RequestMapping(value = "loginLDAP", method = RequestMethod.POST)
	public LDAPResponse login(@RequestBody String credentialsJSON){
	   
	   return missionServices.login(credentialsJSON);
	
  }
   

}
