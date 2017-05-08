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
public class AccommodationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return Accommodation.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Accommodation accommodation = (Accommodation) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hotelName", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "entryDate", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "departureDate", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numberOfNights", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty");

		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");			
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
