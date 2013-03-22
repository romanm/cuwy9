package org.cuwy9.web;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cuwy9.domain.Folder;
import org.cuwy9.service.Cuwy7Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ThymeleafController {
	protected final Log log = LogFactory.getLog(getClass());
	@Autowired
	Cuwy7Service cuwy7Service;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		log.debug(1);
		model.addAttribute("a1", "a1-value");
		log.debug(2);
		return "thymeleaf/home";
	}
	@RequestMapping(value = "/dbreview", method = RequestMethod.GET)
	public String dbreview(Model model) {
		log.debug(1);
		List<Folder> findAllFolder = Folder.findAllFolder();
		model.addAttribute("findAllFolder",findAllFolder);
		log.debug(findAllFolder);
		log.debug(2);
		return "thymeleaf/dbreview";
	}
}
