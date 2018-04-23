package projekat.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<PasswordAnnotation, String>{

	@Override
	public void initialize(PasswordAnnotation string) {
		
	}

	@Override
	public boolean isValid(String customField, ConstraintValidatorContext ctx) {
		
		if(customField == null) {
			return false;
		}
		return customField.matches("[a-z0-9_-]{8,15}");
	}

}
