package ru.sapronov.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.sapronov.springsecurity.models.Role;
import ru.sapronov.springsecurity.models.User;
import ru.sapronov.springsecurity.service.RoleService;
import ru.sapronov.springsecurity.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;

    @Autowired
    private RoleService roleService;

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

	@GetMapping("/admin/edit")
	public String editUser(@RequestParam(name = "id", defaultValue = "0") long id,
							 ModelMap model) {

		model.addAttribute("user", userService.show(id));
		return "edit";
	}

	@PostMapping("/admin/edit")
	public String updatedUser(@ModelAttribute("user") User user,
							  @RequestParam(value = "adminRole", defaultValue = "") String adminRole,
							  @RequestParam(value = "userRole", defaultValue = "") String userRole) {

		user.setRoles(getRoles(adminRole, userRole));
		userService.update(user.getId(), user);
		return "redirect:/admin/index";
	}

	@RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
	@GetMapping("/admin/delete")
	public String deleteUser(@RequestParam(name = "id", defaultValue = "0") long id) {
		userService.delete(id);
		return "redirect:/admin/index";
	}

	public Set<Role> getRoles(String adminRole, String userRole) {
		Set<Role> roles = new HashSet<>();
		if (!adminRole.isEmpty()) {
			roles.add(new Role(adminRole));
		}
		if (!userRole.isEmpty()) {
			roles.add(new Role(userRole));
		}
		return roles;
	}
}