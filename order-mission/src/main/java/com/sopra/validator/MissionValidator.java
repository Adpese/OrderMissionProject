package com.sopra.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
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

		if (mission.getItineraries() != null) {
			List<Itinerary> itinerariesList = new ArrayList<Itinerary>();
			itinerariesList = mission.getItineraries();
		}

		if (mission.getAccommodations() != null) {
			List<Accommodation> accommodationsList = new ArrayList<Accommodation>();
			accommodationsList = mission.getAccommodations();
			validateDateAccommodations(accommodationsList, errors);
		}

		if (mission.getRents() != null) {
			List<Rent> rentsList = new ArrayList<Rent>();
			rentsList = mission.getRents();
			validateDateRents(rentsList, errors);
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "collabFirstName", "NotEmpty");
		// if (mission.getCollabFirstName().length() > 2) {
		// errors.rejectValue("collabFirstName", "Size.missionForm.colab");
		// }

	}

	public void validateDateRents(List<Rent> array, Errors errors) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

		for (Rent rent : array) {

			try {
				Date pickupDate = formatter.parse(rent.getPickupDate());
				Date deliveryDate = formatter.parse(rent.getDeliveryDate());

				if (deliveryDate.before(pickupDate)) {
					errors.rejectValue("deliveryDate", "Delivery date before pickup date");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	public void validateDateAccommodations(List<Accommodation> array, Errors errors) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		
		for (Accommodation accommodation : array) {

			try {
				Date departureDate = formatter.parse(accommodation.getDepartureDate());
				Date entryDate = formatter.parse(accommodation.getEntryDate());

				if (departureDate.before(entryDate)) {
					errors.rejectValue("departureDate", "Departure date before entry date");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
}
