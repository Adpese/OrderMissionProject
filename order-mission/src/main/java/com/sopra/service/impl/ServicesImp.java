package com.sopra.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sopra.entity.Itinerary;
import com.sopra.entity.Mission;
import com.sopra.repository.MissionRepository;
import com.sopra.service.MissionServices;



@Service(value = "services")
public class ServicesImp implements MissionServices {
	
	@Resource
	private MissionRepository  missionRepository;

	@Override
	public void saveMissionItinerary(List<Mission> missions) {
		for(Itinerary it : missions.get(0).getItineraries()) {
			if(it != null){
			   it.setMission(missions.get(0));
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
