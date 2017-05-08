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
public class RentValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Rent.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Rent rent = (Rent) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "driverName", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pickupDate", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pickupHour", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pickupPlace", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deliveryDate", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deliveryHour", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deliveryPlace", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty");
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

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
