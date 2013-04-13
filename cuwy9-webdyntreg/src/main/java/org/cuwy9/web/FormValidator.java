package org.cuwy9.web;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public class FormValidator {

	public void validate(TestFormModel testFormModel, Errors errors) {
		String name = testFormModel.getSeek();
		if (!StringUtils.hasLength(name)) {
			errors.rejectValue("seek", "required", "required");
		}else if(name.length()<2){
			errors.rejectValue("seek", "length", "length > 2");
		}
	}
	
}
