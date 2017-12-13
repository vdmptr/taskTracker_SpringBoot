package vdm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.*;

import vdm.controllers.helpsOfControllers.EmailInstall;
import vdm.repository.UserRepository;
import vdm.controllers.helpsOfControllers.FormUser;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    private vdm.service.UserService userService;
    private UserRepository userRepository;

    public RegistrationController() {
    }

    @Autowired
    RegistrationController(vdm.service.UserService userService,
                           UserRepository userRepository)
    {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/")
    public String register(Model model) {
        model.addAttribute("formUser", new FormUser());
        return "registration";
    }

    @PostMapping(value = "/new")
    public String email(@Valid FormUser formUser,
                        BindingResult bindingResult,
                        Model model) {

        if(bindingResult.hasErrors()) {
            return "registration";
        }

        String email = formUser.getEmail();
        List<vdm.entity.User> users = userRepository.findUsersByEmail(email);
        if (!users.isEmpty()){
            return "redirect:/login.jsp";
        }
        formUser.setEnabled(false);

        userService.addFormUser(formUser);

        Session session = Session.getDefaultInstance(EmailInstall.smtpServerProperties,
                                                     EmailInstall.authenticator);
        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(EmailInstall.FROM));

            message.addRecipient(Message.RecipientType.TO,
                                 new InternetAddress(email));

            message.setSubject("it is confirmation of your email address");

            message.setText("This is actual message "
                            + "<a href='http://localhost:8080/"
                            +"taskTracker/registration/ok/"
                            + email + "/>");

            Transport.send(message);

        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
        model.addAttribute("activate",
                           "вам отправлено письмо "
                                      + "для подтверждения email");
        return "registration";
    }

    @GetMapping(value = "/ok/{email}")
    public String goodEmail(@PathVariable String email)
                                            throws UsernameNotFoundException {
        List<vdm.entity.User> users = userRepository.findUsersByEmail(email);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }
        vdm.entity.User user = users.get(0);
        user.setEnabled(true);
        userRepository.saveAndFlush(user);
        return "redirect:/login.jsp";
    }
}
