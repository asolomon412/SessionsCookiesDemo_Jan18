package com.gc.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/*
 * author: Antonella Solomon
 *
 */

@Controller
// this session attribute is assigned in the helloWorld() method
@SessionAttributes("sessionId")
// you can add multiple items into the @SessionAttributes(value={"one","two"})

public class HomeController {

	@RequestMapping("/")
	public String index(HttpServletResponse response) {
		Cookie cookie = new Cookie("cookietest", "grand circus");
		response.addCookie(cookie);
		
		//cookie.setMaxAge(0); this is how you would delete the cookie if needed, set it's max age to 0
		return "index";
	}

	@RequestMapping("/welcome")
	public ModelAndView helloWorld(HttpSession session) {

		// this code is for the conter
		if (session.getAttribute("counter") == null) {
			session.setAttribute("counter", 0);
		}

		// cast the session to the type of thing we want to use
		int count = (int) session.getAttribute("counter");
		count++;
		session.setAttribute("counter", count);

		// this code is for the jsessionid demo
		String id = session.getId();
		session.setAttribute("sessionId", id);

		return new ModelAndView("welcome", "message", count);
	}

	@RequestMapping("seeid")
	// the @ModelAttribute annotation allows us to use the session attribute that we
	// assigned with the annotation above the class declaration
	// the @CookieValue is used for the cookie example
	public ModelAndView seeSession(@ModelAttribute("sessionId") String sId,
			@CookieValue("cookietest") String cookie,
			Model model) {
		model.addAttribute("cookietest", cookie);
		
		
		return new ModelAndView("seeid", "userid", sId);
	}
}
