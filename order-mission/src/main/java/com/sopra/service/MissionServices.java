package com.sopra.service;

import java.io.IOException;
import java.util.List;

import com.sopra.entity.Mission;

public interface MissionServices {
	
	
	
	
	 /**
	 * @param missions
	 */
	void saveMissionItinerary(Mission mission);
	void updateMission(Mission m);
	 
	 
	 Mission getNombreById(int id);
	 
	 
	 String login(String credentialsJSON);
}
