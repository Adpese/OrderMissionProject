package com.sopra.validator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.sopra.entity.Mission;


public class ValidatorTest {
	

	
	@Test
	public void  validateMissionOk(){
		Mission mission =  new Mission(1, "ab", null, null, null, null, null, null, null);
		MissionValidator missionValidator = new MissionValidator();
		Errors errors = new BeanPropertyBindingResult(mission, "mission");
		missionValidator.validate(mission, errors);
		assertEquals(false,errors.hasErrors());
		
	}
	
	
	@Test
	public void  validateMissionKo(){
		Mission mission =  new Mission(1, "ab44", null, null, null, null, null, null, null);
		MissionValidator missionValidator = new MissionValidator();
		Errors errors = new BeanPropertyBindingResult(mission, "mission");
		missionValidator.validate(mission, errors);
		assertEquals(true,errors.hasErrors());
	}

}
