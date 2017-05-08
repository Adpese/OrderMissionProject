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

public class ItineraryValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Itinerary.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Itinerary itinerary = (Itinerary) target;
		
		System.out.println(itinerary.toString());
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "origin", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "destination", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "departureHour", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "arrivalHour", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "transport", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "company", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty");
		
	}

}
