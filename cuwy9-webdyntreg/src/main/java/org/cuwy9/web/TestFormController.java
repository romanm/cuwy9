package org.cuwy9.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestFormController {
	protected final Log log = LogFactory.getLog(getClass());
	@RequestMapping(value = "/testform", method = RequestMethod.GET)
	public String testform(Model model) {
		log.debug(1);
		model.addAttribute("testFormModel", new TestFormModel());
		log.debug(2);
		return "thymeleaf/testform";
	}
	@RequestMapping(value = "/testform", method = RequestMethod.POST)
	public String processFindForm(TestFormModel testFormModel, BindingResult result, Model model) {
		log.debug(1);
		log.debug(testFormModel.getSeek());
		log.debug(testFormModel.getBirthDate());
		new FormValidator().validate(testFormModel, result);
		log.debug(2);
		if (result.hasErrors()) {
			log.debug(3);
			return "thymeleaf/testform";
		}
		log.debug(4);
		return "thymeleaf/testform";
	}
}
