package com.manexra.techverse.controller;

import com.manexra.techverse.entity.Contact;
import com.manexra.techverse.repository.ContactRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

    private final ContactRepository repository;

    public PageController(ContactRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/services")
    public String services() {
        return "services";
    }

    @GetMapping("/technologies")
    public String technologies() {
        return "technologies";
    }

    @GetMapping("/contact")
    public String contactForm() {
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContact(Contact contact) {
        repository.save(contact);
        return "redirect:/contact?success";
    }

}
