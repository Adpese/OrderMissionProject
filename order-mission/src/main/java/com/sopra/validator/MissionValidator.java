package com.sopra.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sopra.entity.Mission;

@Component
public class MissionValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Mission.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	Mission mission = (Mission) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "collabFirstName", "NotEmpty");
       if (mission.getCollabFirstName().length() > 2) {
            errors.rejectValue("collabFirstName", "Size.missionForm.colab");
        }
       
    }
}
