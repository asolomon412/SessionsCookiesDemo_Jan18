package com.gc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 * author: Antonella Solomon
 *
 */

@Controller
public class HomeController {

	@RequestMapping("/welcome")
	public ModelAndView helloWorld(HttpSession session) {

		if (session.getAttribute("counter") == null) {
			session.setAttribute("counter", 0);
		}
		
		// cast the session to the type of thing we want to use
		int count = (int)session.getAttribute("counter");
		count++;
		session.setAttribute("counter", count);

		return new ModelAndView("welcome", "message", count);
	}
}
