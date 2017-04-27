package com.sopra.service.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sopra.doa.LDAPResponse;
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
//			missions.setCreatedBy(System.getProperty("user.name") 

//);

		   missionRepository.save(missions);
	
	   }
	
	public void updateMission(Mission m){
	
		for(Itinerary it : m.getItineraries()) {
			if(it != null){
			   it.setMission(m);
			}
	   	}
//	ALQUILER COCHE
		for(Rent rent : m.getRents()) {
			if(rent != null){
				rent.setMission(m);
			}
	   	}
//	ALQUILER ALOJAMIENTO
		for(Accommodation acc : m.getAccommodations()) {
			if(acc != null){
				acc.setMission(m);
			}
	   	}
		missionRepository.delete(m.getId());
		missionRepository.save(m);
	}
	
	
	
	
	@Override
	public Mission getNombreById(int id){
		Mission m = missionRepository.findOne(id);
		return m;
	}
		
	
	public LDAPResponse login(String credentialsJSON){
		
	   Persona persona = new Gson().fromJson(credentialsJSON, Persona.class);
	   
	   
	   Hashtable<String, String> env = new Hashtable<String, String>();
	   
	   env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
       env.put(Context.SECURITY_AUTHENTICATION, "Simple");
       env.put(Context.SECURITY_PRINCIPAL, "EMEAAD\\" + persona.getNombre());
       env.put(Context.SECURITY_CREDENTIALS, persona.getPassword());
       env.put(Context.PROVIDER_URL,  "ldap://wptxdc01.ptx.fr.sopra:389");
       env.put(Context.REFERRAL, "follow");
	   
       LdapContext ldapContext;
       try {
    	   System.out.println("ASdfasdgf");
    	   
    	   
           ldapContext = new InitialLdapContext(env, null);
           String searchFilterByName = "(&(sAMAccountName=" + persona.getNombre() + "))";
           SearchControls searchControls = new SearchControls();
           searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
//           NamingEnumeration<SearchResult> results = ldapContext.search("OU=Sopragroup,OU=UsersEmea,DC=emea,DC=msad,DC=sopra", searchFilter, searchControls);
           NamingEnumeration<SearchResult> userResults = ldapContext.search("OU=UsersEmea,DC=emea,DC=msad,DC=sopra", searchFilterByName, searchControls);
           String userAgency="";
           String userDisplayName= "";
           if(userResults.hasMore()) {
        	   SearchResult userResult = (SearchResult) userResults.next();
//               System.out.println(userResult.getAttributes());
               userAgency= (String)userResult.getAttributes().get("department").get();
               userDisplayName=(String)userResult.getAttributes().get("displayname").get();
               System.out.println(userAgency);
               System.out.println(userDisplayName);
//               System.out.println((String)searchResult.getAttributes().get("managedObjects").get());

//               System.out.println((String)searchResult.getAttributes().get("C").get());
               
//               NamingEnumeration<? extends Attribute> resultss = searchResult.getAttributes().getAll();
//               if(resultss.hasMoreElements()){
//            	   System.out.println(resultss.);
//               }
           }
           String searchFilter = "(&(department=341 cs espagne))";
           NamingEnumeration<SearchResult> results = ldapContext.search("OU=UsersEmea,DC=emea,DC=msad,DC=sopra", searchFilter, searchControls);
    	   System.out.println(results);
//           ArrayList<String> agencyList = new ArrayList<String>();
           ArrayList<String> displayNameList = new ArrayList<String>();
           while(results.hasMore()) {
        	   SearchResult searchResult = (SearchResult) results.next();
//               System.out.println(searchResult.getAttributes());
//               agencyList.add((String)searchResult.getAttributes().get("department").get());
               displayNameList.add((String)searchResult.getAttributes().get("displayname").get());
//               System.out.println(userAgency);
//               System.out.println((String)searchResult.getAttributes().get("displayname").get());
//               System.out.println((String)searchResult.getAttributes().get("managedObjects").get());

//               System.out.println((String)searchResult.getAttributes().get("C").get());
               
//               NamingEnumeration<? extends Attribute> resultss = searchResult.getAttributes().getAll();
//               if(resultss.hasMoreElements()){
//            	   System.out.println(resultss.);
//               }
           }
         
           
           User user = userRepository.findUserByName(persona.getNombre());
           if(user == null)
        	   return new LDAPResponse("Colaborador", userAgency, userDisplayName, displayNameList);
//          	 return "Colaborador"+"/"+userAgency+"/"+userDisplayname;
           Role userRole = roleRepository.findOne(user.getRol().getId());
//           String respuesta = role.getRol()+"/"+userAgency+"/"+userDisplayname;
//           System.out.println(respuesta);
//           return respuesta;
           return new LDAPResponse(userRole.getRol(), userAgency, userDisplayName, displayNameList);
  
       } catch (NamingException nex) {
    	   System.out.println("ERROR " + nex);
    		return null;
       }

		
//		 User user = userRepository.findUserByName(persona.getNombre());
//         if(user == null)
//        	 return "Colaborador";
//
//         Role role = roleRepository.findOne(user.getRol().getId());
//         
//
//         
//         if(role != null)
//        	 return role.getRol();
//         else
//        	 return "Colaborador";
		
		
		
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/busquedaMission/{id}")
	public Mission Busqueda (@PathVariable("id") int id)
	{
		
		return missionRepository.findOne(id);
	}

	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/misiones")
	public List<Mission> Misiones()
	{
		
		return (List<Mission>) missionRepository.findAll();
	}
	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/jefe/{userName}")
	public List<Mission> Misiones(@PathVariable("userName") String uName)
	{
		
		return missionRepository.find(uName);
	}
	
//	@ResponseBody
//	@RequestMapping(method=RequestMethod.GET, value="/jefe/{agency}")
//	public List<Mission> MisionesDirector(@PathVariable("agency") String uName)
//	{
//		
//		return missionRepository.find(uName);
//	}
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/colaborador/{userName}")
	public List<Mission> MisionesByCreatedby(@PathVariable("userName") String uName)
	{
		
		return missionRepository.findBycreatedBy(uName);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/director/{agency}/{userName}")
	public List<Mission> findDirector(@PathVariable("agency") String agency, @PathVariable("userName") String userName)
	{
		
		return missionRepository.findDirector(agency, userName);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/assistant/{agency}/{userName}")
	public List<Mission> findAssistant(@PathVariable("agency") String agency, @PathVariable("userName") String userName)
	{
		
		return missionRepository.findAssistant(agency, userName);
	}
	

	public void updateMission(Mission m, int id)
	{
		System.out.println(m.getCollabFirstName());
		//missionRepository.updateMission(id, m);
	}
	
}
