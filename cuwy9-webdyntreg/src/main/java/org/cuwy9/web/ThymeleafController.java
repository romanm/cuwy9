package org.cuwy9.web;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cuwy9.domain.Folder;
import org.cuwy9.domain.Patient;
import org.cuwy9.domain.Task;
import org.cuwy9.service.Cuwy9Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ThymeleafController {
	protected final Log log = LogFactory.getLog(getClass());
	@Autowired
	Cuwy9Service cuwy7Service;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		log.debug(1);
		model.addAttribute("a1", "a1-value");
		log.debug(2);
		return "thymeleaf/home";
	}
	@RequestMapping(value = "/testform", method = RequestMethod.GET)
	public String testform(Model model) {
		log.debug(1);
		model.addAttribute("testFormModel", new TestFormModel());
		log.debug(2);
		return "thymeleaf/testform";
	}
	@RequestMapping(value = "/testformseek", method = RequestMethod.GET)
    public String processFindForm(TestFormModel testFormModel, BindingResult result, Model model) {
		log.debug(1);
		log.debug(testFormModel.getSeek());
		log.debug(2);
		return "thymeleaf/testform";
	}
	@RequestMapping(value = "/dbreview", method = RequestMethod.GET)
	public String dbreview(Model model) {
		log.debug(1);
		List<Folder> findAllFolder = Folder.findAllFolder();
		model.addAttribute("findAllFolder",findAllFolder);
		log.debug(findAllFolder);
		List<Task> findAllTask = Task.findAllTask();
		log.debug(findAllTask);
		model.addAttribute("findAllTask",findAllTask);
		List<Patient> findAllPatient = Patient.findAllPatient();
		model.addAttribute("findAllPatient",findAllPatient);
		log.debug(2);
		return "thymeleaf/dbreview";
	}
}
