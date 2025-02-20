package com.nirmalyam.collection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nirmalyam.collection.model.Registration;
import com.nirmalyam.collection.repository.RegistrationRepository;
import com.nirmalyam.collection.whatsappNotification.PushWhatsappNotification;

@Controller
public class HomeController {

    @Autowired
    private RegistrationRepository repository;
    @Autowired
    private PushWhatsappNotification pushWhatsappNotification;
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("registration", new Registration());
        List<Registration> allRegistrations = repository.findAll();
        model.addAttribute("registrations", allRegistrations);
        return "index.html";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registration") Registration registration, Model model) {
        repository.save(registration);
        model.addAttribute("message", "Registration successful!");
        String sendWhatsappNotification = pushWhatsappNotification.sendWhatsappNotification(registration.getPhone());
        List<Registration> allRegistrations = repository.findAll();
        model.addAttribute("registrations", allRegistrations);
        return "data.html";
    }
    @PostMapping("/deleterecord")
    public String deleteRecordByPhoneNumber(@RequestParam("phone") String phone, Model model) {
    	String deleteByPhoneNumber = repository.deleteByPhoneNumber(phone);
    	List<Registration> allRegistrations = repository.findAll();
        model.addAttribute("registrations", allRegistrations);
        return "data.html";
    }


}
