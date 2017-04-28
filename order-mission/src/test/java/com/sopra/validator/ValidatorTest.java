package com.sopra.validator;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.sopra.entity.Accommodation;
import com.sopra.entity.Mission;
import com.sopra.entity.Rent;


public class ValidatorTest {
	

	
	@Test
	public void  validateMissionOk(){
		
		Accommodation accommodation = new Accommodation();
		accommodation.setEntryDate("10-04-2017");
		accommodation.setDepartureDate("11-04-2017");
		
		List<Accommodation> accList = new ArrayList<Accommodation>();
		accList.add(accommodation);
		
		Mission mission =  new Mission(1, "ab", null, null, null, null, null, accList, null);
		MissionValidator missionValidator = new MissionValidator();
		Errors errors = new BeanPropertyBindingResult(mission, "mission");
		missionValidator.validate(mission, errors);
		assertEquals(false,errors.hasErrors());
		
	}
	
	
	@Test
	public void  validateMissionNotOk(){
		Mission mission =  new Mission(1, "aa44", null, null, null, null, null, null, null);
		MissionValidator missionValidator = new MissionValidator();
		Errors errors = new BeanPropertyBindingResult(mission, "mission");
		missionValidator.validate(mission, errors);
		assertEquals(true,errors.hasErrors());
	}
	
	@Test
	public void validateDateRentsOK(){
		
		List<Rent> rentList = new ArrayList<Rent>();
		Rent rent = new Rent();
		Errors errors = new BeanPropertyBindingResult(rent, "rent");
		
		rent.setPickupDate("09-04-2017");
		rent.setDeliveryDate("10-04-2017");
		
		rentList.add(rent);
		
		MissionValidator missionValidator = new MissionValidator();
		missionValidator.validateDateRents(rentList, errors);
		assertEquals(false, errors.hasErrors());
		
	}
	
	@Test
	public void validateDateRentsNotOk(){
		
		List<Rent> rentList = new ArrayList<Rent>();
		Rent rent = new Rent();
		Errors errors = new BeanPropertyBindingResult(rent, "rent");
		
		rent.setPickupDate("11-04-2017");
		rent.setDeliveryDate("10-04-2017");
		
		rentList.add(rent);
		
		MissionValidator missionValidator = new MissionValidator();
		missionValidator.validateDateRents(rentList, errors);
		assertEquals(true, errors.hasErrors());
		
	}
	
	@Test 
	void validateDateAccommodationsOk(){
		
		List<Accommodation> accommodationList = new ArrayList<Accommodation>();
		Accommodation accommodation = new Accommodation();
		Errors errors = new BeanPropertyBindingResult(accommodation, "accommodation");
		
		accommodation.setEntryDate("11-04-2017");
		accommodation.setDepartureDate("10-04-2017");
		
		accommodationList.add(accommodation);
		
		MissionValidator missionValidator = new MissionValidator();
		missionValidator.validateDateAccommodations(accommodationList, errors);
		assertEquals(true, errors.hasErrors());
	}

	@Test 
	void validateDateAccommodationsNotOk(){
		
		List<Accommodation> accommodationList = new ArrayList<Accommodation>();
		Accommodation accommodation = new Accommodation();
		Errors errors = new BeanPropertyBindingResult(accommodation, "accommodation");
		
		accommodation.setEntryDate("09-04-2017");
		accommodation.setDepartureDate("10-04-2017");
		
		accommodationList.add(accommodation);
		
		MissionValidator missionValidator = new MissionValidator();
		missionValidator.validateDateAccommodations(accommodationList, errors);
		assertEquals(false, errors.hasErrors());
	}
	
}
