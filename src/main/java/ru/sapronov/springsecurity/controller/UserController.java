package ru.sapronov.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.sapronov.springsecurity.models.User;
import ru.sapronov.springsecurity.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;

//	@Autowired
//	private RoleService roleService;

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC-SECURITY application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "hello";
	}

	@GetMapping("login")
    public String loginPage() {
        return "login";
    }

	@GetMapping("user")
	public String userPage(Authentication authentication, ModelMap model) {
		User user = userService.getUserByUsername(authentication.getName());
		model.addAttribute("user", user);
		return "user";
	}

	@GetMapping("/admin/index")
	public String adminPage(ModelMap model) {
		List<User> users = userService.index();
		model.addAttribute("users", users);
		return "index";
	}

}