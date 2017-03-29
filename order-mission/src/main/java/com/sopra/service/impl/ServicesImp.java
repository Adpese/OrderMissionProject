package com.sopra.service.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sopra.doa.Persona;
import com.sopra.entity.Accommodation;
import com.sopra.entity.Itinerary;
import com.sopra.entity.Mission;
import com.sopra.entity.Project;
import com.sopra.entity.Rent;
import com.sopra.entity.Role;
import com.sopra.entity.User;
import com.sopra.repository.ItineraryRepository;
import com.sopra.repository.MissionRepository;
import com.sopra.repository.ProjectRepository;
import com.sopra.repository.RentACarRepository;
import com.sopra.repository.RoleRepository;
import com.sopra.repository.UserRepository;
import com.sopra.service.MissionServices;
//import com.sun.jna.platform.win32.Secur32Util;
//import com.sun.jna.platform.win32.Secur32;
@RestController
@Service(value = "services")
public class ServicesImp implements MissionServices {
	
	@Resource
	private MissionRepository  missionRepository;
	@Resource
	private ItineraryRepository itineraryReposity;
	@Resource
	private RentACarRepository rentACarRepository;
	@Resource
	private UserRepository userRepository;
	@Resource
	private RoleRepository roleRepository;
	@Resource
	private ProjectRepository projectRepository;

	@Override
	public void saveMissionItinerary(Mission missions) {
		
	//	PROYECTO
	Project proj= missions.getProject();
	List<Mission> misiones = new ArrayList<Mission>();
	misiones.add(missions);
	proj.setMission(misiones);
	
//	ITINERARIO
		for(Itinerary it : missions.getItineraries()) {
			if(it != null){
			   it.setMission(missions);
			}
	   	}
//	ALQUILER COCHE
		for(Rent rent : missions.getRents()) {
			if(rent != null){
				rent.setMission(missions);
			}
	   	}
//	ALQUILER ALOJAMIENTO
		for(Accommodation acc : missions.getAccommodations()) {
			if(acc != null){
				acc.setMission(missions);
			}
	   	}
//			String s = Secur32Util.getUserNameEx(Secur32.EXTENDED_NAME_FORMAT.NameDisplay);
//			System.out.println(s);
			missions.setCreatedBy(System.getProperty("user.name") 
);
		   missionRepository.save(missions);
	
	   }
	
	
	
	
	@Override
	public Mission getNombreById(int id){
		Mission m = missionRepository.findOne(id);
		return m;
	}
		
	
	public String login(String credentialsJSON){
		
	   Persona persona = new Gson().fromJson(credentialsJSON, Persona.class);
	   System.out.println(persona.getPassword());
	   
	   
	   Hashtable<String, String> env = new Hashtable<String, String>();//NOSONAR Hashtable is mandatory for InitialLdapContext initialization
	   
	   env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
       env.put(Context.SECURITY_AUTHENTICATION, "Simple");
       env.put(Context.SECURITY_PRINCIPAL, "EMEAAD\\" + persona.getNombre());
       env.put(Context.SECURITY_CREDENTIALS, persona.getPassword());
       env.put(Context.PROVIDER_URL,  "ldap://wptxdc01.ptx.fr.sopra:389/OU=users");
       env.put(Context.REFERRAL, "follow");
	   
       LdapContext ldapContext;
       try {
    	   
           ldapContext = new InitialLdapContext(env, null);
           System.out.println("Login correcto!!!!");
           User user = userRepository.findUserByName(persona.getNombre());
           Role role = roleRepository.findOne(user.getRol().getId());
           return role.getRol();
       } catch (NamingException nex) {
    	   System.out.println("ERROR " + nex);
           return "Error";
       }
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/busquedaMission/{id}")
	public Mission Busqueda (@PathVariable("id") int id)
	{
		
		return missionRepository.findOne(id);
	}
}
