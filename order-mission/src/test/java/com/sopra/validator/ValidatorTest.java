package com.sopra.validator;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.sopra.entity.Accommodation;
import com.sopra.entity.Itinerary;
import com.sopra.entity.Mission;
import com.sopra.entity.Rent;


public class ValidatorTest {
	
	private Accommodation accommodation = new Accommodation(1, "hotel", "10-04-2017", "11-04-2017");
	private Itinerary itinerary = new Itinerary(1, "10-04-2017", "Valencia", "Madrid", "10:50", "20:00", "Avion", "Iberia", 300);
	private Rent rent =  new Rent(1, "Adrian", "10-04-2017", "10:10", "Valencia", "11-04-2017", "10:10", "Valencia", 300);
	
	@Test
	public void  validateMissionOk(){
		
		
		validateAccommodation();
		validateItinerary();
		validateRent();
		
		List<Accommodation> accList = new ArrayList<Accommodation>();
		accList.add(accommodation);
		
		List<Itinerary> itList = new ArrayList<Itinerary>();
		itList.add(itinerary);
		
		Mission mission =  new Mission(1, "asd", null, null, null, null, itList, accList, null);
		MissionValidator missionValidator = new MissionValidator();
		
		Errors missionErrors = new BeanPropertyBindingResult(mission, "mission");
		missionValidator.validate(mission, missionErrors);
		
		assertEquals(false,missionErrors.hasErrors());
		
		
	}
	
	@Test
	public void validateAccommodation(){

		
		AccommodationValidator accommodationValidator =  new AccommodationValidator();
		Errors accommodationErrors = new BeanPropertyBindingResult(accommodation, "accommodation");
		accommodationValidator.validate(accommodation, accommodationErrors);
		assertEquals(false,accommodationErrors.hasErrors());
	}
	
	@Test
	public void validateItinerary(){
		
		ItineraryValidator itineraryValidator = new ItineraryValidator();
		Errors itinararyErrors = new BeanPropertyBindingResult(itinerary, "itinerary");
		itineraryValidator.validate(itinerary, itinararyErrors);
		assertEquals(false,itinararyErrors.hasErrors());
	}
	
	@Test
	public void validateRent(){
		
		RentValidator rentValidator = new RentValidator();
		Errors rentErrors = new BeanPropertyBindingResult(rent, "rent");
		rentValidator.validate(rent, rentErrors);
		assertEquals(false,rentErrors.hasErrors());
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
	public void validateAccommodationsOK(){
		
	}
	
//	@Test
//	public void validateDateRentsOK(){
//		
//		List<Rent> rentList = new ArrayList<Rent>();
//		Rent rent = new Rent();
//		Errors errors = new BeanPropertyBindingResult(rent, "rent");
//		
//		rent.setPickupDate("09-04-2017");
//		rent.setDeliveryDate("10-04-2017");
//		
//		rentList.add(rent);
//		
//		MissionValidator missionValidator = new MissionValidator();
//		missionValidator.validateDateRents(rentList, errors);
//		assertEquals(false, errors.hasErrors());
//		
//	}
//	
//	@Test
//	public void validateDateRentsNotOk(){
//		
//		List<Rent> rentList = new ArrayList<Rent>();
//		Rent rent = new Rent();
//		Errors errors = new BeanPropertyBindingResult(rent, "rent");
//		
//		rent.setPickupDate("11-04-2017");
//		rent.setDeliveryDate("10-04-2017");
//		
//		rentList.add(rent);
//		
//		MissionValidator missionValidator = new MissionValidator();
//		missionValidator.validateDateRents(rentList, errors);
//		assertEquals(true, errors.hasErrors());
//		
//	}
	
//	@Test 
//	public void validateDateAccommodationsOk(){
//		
//		List<Accommodation> accommodationList = new ArrayList<Accommodation>();
//		Accommodation accommodation = new Accommodation();
//		Errors errors = new BeanPropertyBindingResult(accommodation, "accommodation");
//		
//		accommodation.setEntryDate("11-04-2017");
//		accommodation.setDepartureDate("10-04-2017");
//		
//		accommodationList.add(accommodation);
//		
//		MissionValidator missionValidator = new MissionValidator();
//		missionValidator.validateDateAccommodations(accommodationList, errors);
//		assertEquals(true, errors.hasErrors());
//	}
//
//	@Test 
//	public void validateDateAccommodationsNotOk(){
//		
//		List<Accommodation> accommodationList = new ArrayList<Accommodation>();
//		Accommodation accommodation = new Accommodation();
//		Errors errors = new BeanPropertyBindingResult(accommodation, "accommodation");
//		
//		accommodation.setEntryDate("09-04-2017");
//		accommodation.setDepartureDate("10-04-2017");
//		
//		accommodationList.add(accommodation);
//		
//		MissionValidator missionValidator = new MissionValidator();
//		missionValidator.validateDateAccommodations(accommodationList, errors);
//		assertEquals(false, errors.hasErrors());
//	}
	
}
