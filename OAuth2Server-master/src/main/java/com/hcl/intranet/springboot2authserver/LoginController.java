package com.hcl.intranet.springboot2authserver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }

	@RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@ModelAttribute Object login) {
        return "index";
    }
	
	 @GetMapping("/sites")
	    public String hello(HttpServletRequest request) {
	     
		 System.out.println("hellow world......");
		 String path = request.getContextPath();
		 System.out.println("context path......" + path);
		  return "index";
	    }
}
