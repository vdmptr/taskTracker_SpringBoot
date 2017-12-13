package vdm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vdm.entity.User;
import vdm.security.SecurityUser;
import vdm.service.UserService;
import vdm.dto.UserDTO;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class RestUser {


    private UserService userService;

    @Autowired
    RestUser(UserService userService){
        this.userService = userService;
    }

    @Secured("ROLE_DEVELOPER")
    @GetMapping(value = "/developer",
            produces = "application/json")
    public int getDeveloperId(Authentication authentication){
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        return securityUser.getUserId();
    }

    @GetMapping(value = "/",
            produces = "application/json")
    public List<UserDTO> getAllDeveloper(){
        return userService.getAllDeveloper();
    }

    @GetMapping(value = "/{name}/{lastName}",
            produces = "application/json")
    public List<UserDTO> getDeveloper(@PathVariable String name, @PathVariable String lastName){
        return userService.getDeveloper(name, lastName);
    }

    @PostMapping(value = "/",
            produces = "application/json")
    public UserDTO addUser(@RequestBody User user){
        return userService.addUser(user);
    }




}
