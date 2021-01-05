package com.ufps.springboot.form.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdRegexValidador implements ConstraintValidator<IdRegex, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value.matches("[0-9]{2}[.][0-9]{3}[.][0-9]{3}[-][A-Z]{1}")) {
			return true;
		}
		return false;
	}

}
