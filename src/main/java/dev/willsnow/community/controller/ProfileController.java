package dev.willsnow.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author will
 */

@Controller
public class ProfileController {

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable("action") String action, Model model) {
        if ("questions".equals(action) ) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "My Questions");
        }

        return "profile";
    }


}
