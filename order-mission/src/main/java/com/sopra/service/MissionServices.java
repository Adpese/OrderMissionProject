package com.sopra.service;

import java.io.IOException;
import java.util.List;

import com.sopra.entity.Mission;

public interface MissionServices {
	
	
	
	
	 /**
	 * @param missions
	 */
	void saveMissionItinerary(Mission mission);
	
	 
	 
	 Mission getNombreById(int id);
	 
	 
	 String login(String credentialsJSON);
}
