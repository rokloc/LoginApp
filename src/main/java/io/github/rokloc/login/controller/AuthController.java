package io.github.rokloc.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import io.github.rokloc.login.service.AuthService;
import jakarta.servlet.http.HttpSession;
import io.github.rokloc.login.repository.UserRepository;
import io.github.rokloc.login.entity.User;

@Controller
public class AuthController {
	private AuthService authservice;
	private UserRepository userrepository;
	
	public AuthController(AuthService authservice, UserRepository userrepository) {
		this.authservice = authservice;
		this.userrepository = userrepository;
	}
	
	@GetMapping("/")
	public String getlogin() {
		return ("login");
	}
	
	@PostMapping("/")
	public String postlogin(@RequestParam String username, @RequestParam String password, HttpSession session,Model model) {
		User user = authservice.authenticate(username, password);
		if (user == null) {
			model.addAttribute("error", "ログイン失敗");
			return ("index");
		}
		session.setAttribute("user", user);
		return (user.isAdmin() ? "redirect:/admin" : "redirect:/home");
	}

	@GetMapping("/home")
    public String userHome(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.isAdmin()) return "redirect:/login";
        model.addAttribute("username", user.getUsername());
        return "home";
    }
	
	@GetMapping("/admin")
    public String addminHome(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || !(user.isAdmin())) return "redirect:/login";
        model.addAttribute("username", user.getUsername());
        return "admin";
    }

	
	
	
	
}
