package com.sopra.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sopra.entity.Accommodation;
import com.sopra.entity.Itinerary;
import com.sopra.entity.Mission;
import com.sopra.entity.Rent;

@Component
public class MissionValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Mission.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {

		Mission mission = (Mission) o;
		
		
		if(mission.getItineraries() != null){
			List<Itinerary> itinerariesList = new ArrayList<Itinerary>();
			itinerariesList = mission.getItineraries();
			ItineraryValidator itineraryValidator = new ItineraryValidator();
			Errors itErrors = null;
			for(Itinerary it : itinerariesList){
				itErrors = new BeanPropertyBindingResult(it, "it");
				itineraryValidator.validate(it, itErrors);
			}
		}
		
		
		if (mission.getAccommodations() != null) {
			List<Accommodation> accList = new ArrayList<Accommodation>();
			accList = mission.getAccommodations();
			AccommodationValidator accValidator = new AccommodationValidator();
			Errors accErrors = null;
			for(Accommodation acc : accList){
				accErrors = new BeanPropertyBindingResult(acc, "acc");
				accValidator.validate(acc, accErrors);
			}
		}
		
		if (mission.getRents() != null) {
			
			List<Rent> rentsList = new ArrayList<Rent>();
			rentsList = mission.getRents();
			RentValidator rentValidator = new RentValidator();
			Errors rentErrors = null;
			for(Rent rent : rentsList){
				rentErrors = new BeanPropertyBindingResult(rent, "rent");
				rentValidator.validate(rent, rentErrors);
			}
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "collabFirstName", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "project", "NotEmpty");
		// if (mission.getCollabFirstName().length() > 2) {
		// errors.rejectValue("collabFirstName", "Size.missionForm.colab");
		// }

	}



}
