package io.vigoz.authz.keycloak.web.controller;

import org.keycloak.KeycloakSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/customer/view", method = {RequestMethod.GET})
    public String customerView(Model model, HttpServletRequest request) {
        KeycloakSecurityContext keycloak = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
        model.addAttribute("authz", keycloak.getAuthorizationContext());
        return "customer/view";
    }

    @RequestMapping(value = "/customer/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public String customerDelete(Model model, HttpServletRequest request) {
        KeycloakSecurityContext keycloak = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
        model.addAttribute("authz", keycloak.getAuthorizationContext());
        return "customer/delete";
    }

    @RequestMapping(value = "/admin", method = {RequestMethod.GET, RequestMethod.POST})
    public String admin(Model model, HttpServletRequest request) {
        KeycloakSecurityContext keycloak = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
        model.addAttribute("authz", keycloak.getAuthorizationContext());
        return "admin/view";
    }

    @RequestMapping(value = "/admin/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public String adminDelete(Model model, HttpServletRequest request) {
        KeycloakSecurityContext keycloak = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
        model.addAttribute("authz", keycloak.getAuthorizationContext());
        return "admin/delete";
    }

    @RequestMapping(value = "/accessDenied", method = {RequestMethod.GET, RequestMethod.POST})
    public String accessDenied() {
        return "access_denied";
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(HttpServletRequest request) {
        try {
            request.logout();
            return "redirect:/";
        } catch (ServletException e) {
            LOGGER.error("keycloak logout error", e);
            return "logout fail";
        }
    }
}
