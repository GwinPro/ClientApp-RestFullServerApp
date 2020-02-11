package com.jmSpringBoot.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
public class AppController {

    @RequestMapping("/admin")
    public String mainAdminPage(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        try {
            String roles = (String) session.getAttribute("roles");
            if (roles.contains("ADMIN")) {
                return "admin";
            } else {

                return "error";
            }
        } catch (Exception e) {
            return "error";
        }
    }
    @GetMapping("/user")
    public String userPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String roles= (String) session.getAttribute("roles");
        String name= (String) session.getAttribute("login");
        boolean hasAdmin;
        if (roles.contains("ADMIN")) {
            hasAdmin=true;
        } else {
            hasAdmin=false;
        }
        model.addAttribute("hasAdmin", hasAdmin);
        model.addAttribute("userName", name);
        return "user";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView error(ModelAndView modelAndView) {
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.removeAttribute("token");
        session.removeAttribute("roles");
        return "login";
    }

}
