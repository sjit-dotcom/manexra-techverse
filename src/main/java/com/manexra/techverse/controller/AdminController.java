package com.manexra.techverse.controller;

import com.manexra.techverse.entity.Admin;
import com.manexra.techverse.repository.AdminRepository;
import com.manexra.techverse.repository.ContactRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final AdminRepository repository;
	private final ContactRepository contactRepository;

	public AdminController(AdminRepository repository,
	                       ContactRepository contactRepository) {
	    this.repository = repository;
	    this.contactRepository = contactRepository;
	}

    @GetMapping("/login")
    public String loginPage() {
        return "admin-login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {

    	Admin admin = repository.findByUsername(username);

    	if (admin != null) {
    	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	    if (encoder.matches(password, admin.getPassword())) {
    	        session.setAttribute("admin", admin);
    	        return "redirect:/admin/dashboard";
    	    }
    	}
    	return "redirect:/admin/login?error";


    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }

        return "admin-dashboard";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
    @GetMapping("/enquiries")
    public String enquiries(HttpSession session, Model model) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }

        model.addAttribute("contacts", contactRepository.findAll());
        return "admin-enquiries";
    }

}
