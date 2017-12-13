package vdm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import vdm.entity.User;
import vdm.security.SecurityUser;
import vdm.service.UserService;

@Controller
public class InputController {

    private UserService userService;

    @Autowired
    InputController(UserService userService)
    {
        this.userService = userService;
    }

    @RequestMapping(value = {"/","/login/ok"})
    public String getPage(Authentication authentication){
        SecurityUser securityUser = (SecurityUser)authentication.getPrincipal();
        int userId = securityUser.getUserId();
        User user = userService.getUser(userId);
        switch (user.getRole()){
            case ROLE_ADMIN: return "admin";
            case ROLE_MANAGER: return "manager";
            case ROLE_DEVELOPER: return "developer";
            default: return "registration";
        }
    }
}
