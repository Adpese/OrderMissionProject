package com.sopra.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sopra.doa.Persona;
import com.sopra.entity.Mission;
import com.sopra.service.MissionServices;

@RestController
public class MissionController {
	
	@Resource 
	MissionServices missionServices;


   @RequestMapping(value = "missionSave", method = RequestMethod.POST)
   public void save(@RequestBody List<Mission> missions){
	   missionServices.saveMissionItinerary(missions);
   }
   
   @RequestMapping(value="getPerson/{id}", method= RequestMethod.GET)
   public Persona getPerson( @PathVariable int id){
	   System.out.println(">>>>>>>>>>>>>>>>>>>>>> "+id);

	   Mission nombre = missionServices.getNombreById(id);
	   Persona per = new Persona();
	    per.setNombre(nombre.getCollabFirstName());
	  //per.setTelefono(66666);
	   
	   return per;
   }


}
