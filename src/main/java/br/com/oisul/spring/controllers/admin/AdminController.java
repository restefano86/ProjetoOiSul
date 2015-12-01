package br.com.oisul.spring.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.oisul.spring.controllers.site.DefaultController;

@Controller
public class AdminController extends DefaultController {
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String home(Model model) {
		return "admin/index";
	}

}
