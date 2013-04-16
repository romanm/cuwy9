package org.cuwy9.web;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cuwy9.domain.Drug;
import org.cuwy9.service.Cuwy9Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestFormController {
	protected final Log log = LogFactory.getLog(getClass());
	@Autowired
	Cuwy9Service cuwy7Service;

	@RequestMapping(value = "/drugautocomplete", method = RequestMethod.GET)
	public String drugautocomplete(Model model) {
		List<Drug> findAllDrugs = Drug.findAllDrugs();
		model.addAttribute("findAllDrugs", findAllDrugs);
		return "thymeleaf/drugautocomplete";
	}

	@RequestMapping(value="/mapping/produces", method=RequestMethod.GET, produces=MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody JavaBean byProducesXml() {
		return new JavaBean();
	}
	@RequestMapping(value="/mapping/produces", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JavaBean byProducesJson() {
		return new JavaBean();
	}

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
