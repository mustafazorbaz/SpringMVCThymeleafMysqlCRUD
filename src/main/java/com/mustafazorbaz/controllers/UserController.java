package com.mustafazorbaz.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mustafazorbaz.entities.Users;
import com.mustafazorbaz.services.UserServices;

@Controller
@RequestMapping(value = "users")
public class UserController {

	@Autowired
	UserServices userServices;
 

	 
	@RequestMapping(value = "/getPageUserAdd", method = RequestMethod.GET)
	private ModelAndView getPageUserAdd() {
		ModelAndView view = new ModelAndView("addUser");
		view.addObject("user", new Users());
		return view;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") Users user) {
		userServices.saveUser(user);
		return "redirect:/users/list";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") Users user) {
		
		
		userServices.updateUser(user); 
		return "redirect:/users/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAll(Users users) {
		ModelAndView view = new ModelAndView("homePage");
		List<Users> data = userServices.list();
		view.addObject("datas", data);

		return view;
	}

	@RequestMapping(value = "/userDelete/{id}")
	public String delete(@PathVariable("id") int id) {
		userServices.removeUser(id);
		return "redirect:/users/list";
	}

	@RequestMapping("/edit/{id}")
	public String editUser(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", this.userServices.getUser(id));
		return "editUser";
	}
}
