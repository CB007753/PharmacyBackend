package com.cb007753.pharmacybackend.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("/login")
    public String login(Model model)
    {
        return "login";
    }

    @GetMapping("/")
    public String home(Model model)
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("home");
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        model.addAttribute("useremail",userDetails);
        return "home";
    }

}
