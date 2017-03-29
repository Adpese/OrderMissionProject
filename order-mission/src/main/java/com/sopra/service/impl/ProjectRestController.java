package com.sopra.service.impl;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sopra.entity.Mission;
import com.sopra.entity.Project;
import com.sopra.repository.ItineraryRepository;
import com.sopra.repository.MissionRepository;
import com.sopra.repository.ProjectRepository;
import com.sopra.repository.RentACarRepository;
import com.sopra.repository.RoleRepository;
import com.sopra.repository.UserRepository;

@RequestMapping("/misiones")
@RestController
public class ProjectRestController {
	
	@Resource
	private MissionRepository  missionRepository;
	@Resource
	private ItineraryRepository itineraryRepository;
	@Resource
	private RentACarRepository rentACarRepository;
	@Resource
	private UserRepository userRepository;
	@Resource
	private ProjectRepository projectRepository;
	@Resource
	private RoleRepository roleRepository;
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	  public Mission getMission(@PathVariable("id") int id) {
		
	Mission p = missionRepository.findOne(id);	
	    return p;
	  }
}
