package com.sopra.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sopra.entity.Accommodation;
import com.sopra.entity.Itinerary;
import com.sopra.entity.Mission;
import com.sopra.entity.RentACar;
import com.sopra.repository.ItineraryRepository;
import com.sopra.repository.MissionRepository;
import com.sopra.repository.RentACarRepository;
import com.sopra.service.MissionServices;



@Service(value = "services")
public class ServicesImp implements MissionServices {
	
	@Resource
	private MissionRepository  missionRepository;
	@Resource
	private ItineraryRepository itineraryReposity;
	@Resource
	private RentACarRepository rentACarRepository;

	@Override
	public void saveMissionItinerary(Mission missions) {
//		for(Itinerary it : missions.getItineraries()) {
//			if(it != null){
//			   it.setMission(missions);
//			}
//	   	}
//		for(RentACar rent : missions.getRentACar()) {
//			if(rent != null){
//				rent.setMission(missions);
//			}
//	   	}
//		for(Accommodation acc : missions.getAccommodations()) {
//			if(acc != null){
//				acc.setMission(missions);
//			}
//	   	}
				
		   missionRepository.save(missions);
	
	   }
	
	
	@Override
	public Mission getNombreById(int id){
		Mission m = missionRepository.findOne(id);
		return m;
	}
		
	

}
