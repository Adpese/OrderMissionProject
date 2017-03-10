package com.sopra.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sopra.entity.Itinerary;
import com.sopra.entity.Mission;
import com.sopra.repository.*;

import com.sopra.service.MissionServices;



@Service(value = "services")
public class ServicesImp implements MissionServices {
	
	@Resource
	private MissionRepository  missionRepository;
	@Resource
	private ItineraryRepository itineraryReposity;

	@Override
	public void saveMissionItinerary(Mission missions) {
		for(Itinerary it : missions.getItineraries()) {
			if(it != null){
			   it.setMission(missions);
			}
	   	}
		   missionRepository.save(missions);
	   }
	@Override
	public Mission getNombreById(int id){
		Mission m = missionRepository.findOne(id);
		return m;
	}
		
	

}
