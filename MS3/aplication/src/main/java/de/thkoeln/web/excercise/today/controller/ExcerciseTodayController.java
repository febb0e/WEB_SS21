package de.thkoeln.web.excercise.today.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExcerciseTodayController {
    @GetMapping("/")
    public String home() {
        return "WEB_excercise_today_Home";
    }
    @GetMapping("/upload")
    public String upload(Model model) {
        return "WEB_excercise_today_Upload";
    }
}
