package com.quiz.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.quiz.models.Roles;

public class RoleValidator implements ConstraintValidator<ValidRole, String> {
	private Roles[] allowedTypes;

	@Override
	public void initialize(ValidRole constraint) {
		allowedTypes = (Roles[]) constraint.value();

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if (value == null) {
			return true;
		}
		if (value.equals(Roles.ADMIN.getRoleCode()) || value.equals(Roles.USER.getRoleCode())) {
			return true;
		}

		return false;
	}

	public boolean isValid(Roles value, ConstraintValidatorContext context) {
		// TODO Auto-generated metvhod stub
		if (value == null) {
			return true;
		}
		switch (value) {
		case ADMIN:

			return true;

		case USER:

			return true;

		}
		return false;
	}

}
