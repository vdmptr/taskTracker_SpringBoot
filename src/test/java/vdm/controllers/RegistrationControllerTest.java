package vdm.controllers;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import vdm.repository.UserRepository;
import vdm.controllers.helpsOfControllers.FormUser;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {

    @Mock
    private vdm.service.UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private vdm.entity.User user;

    @Mock
    private Model model;

    @Mock
    private FormUser formUser;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private RegistrationController registrationController;

    @Test
    public void account() throws Exception {
        String resultView = registrationController.register(model);

        assertEquals(resultView, "registration");

        verify(model).addAttribute(eq("formUser"),
                                   any(FormUser.class));
    }
    @Ignore
    @Test
    public void email() throws Exception {
        //todo
        List<vdm.entity.User> userList = new ArrayList<>();
        userList.add(user);
        List<vdm.entity.User> users = new ArrayList<>();

        when(userRepository.findUsersByEmail(anyString())).thenReturn(userList)
                                                          .thenReturn(userList)
                                                          .thenReturn(users);
        when(userService.addFormUser(formUser))
                                        .thenThrow(new MockitoException("ok"));
        when(bindingResult.hasErrors()).thenReturn(true,
                                                   false);
        //when(registrationController.email(formUser,
        //                                  bindingResult,
        //                                  model)).thenReturn("registration");
        String resultView = registrationController.email(formUser,
                                                         bindingResult,
                                                         model);
        assertEquals(resultView, "registration");

        resultView = registrationController.email(formUser,
                                                  bindingResult,
                                                  model);
        assertEquals(resultView, "redirect:/login.jsp");

        try {
           registrationController.email(formUser,
                                                      bindingResult,
                                                      model);
        }catch (MockitoException e){
            assertEquals(e.getMessage(), "ok");
        }

    }

    @Test
    public void goodEmail() throws Exception {
        List<vdm.entity.User> userList = new ArrayList<>();
        userList.add(user);
        List<vdm.entity.User> users = new ArrayList<>();
        when(userRepository.findUsersByEmail(anyString())).thenReturn(userList)
                                                          .thenReturn(users);

        String resultView = registrationController.goodEmail("testEmail");
        assertEquals(resultView, "redirect:/login.jsp");

        try {
            registrationController.goodEmail("testEmail");
        }catch (UsernameNotFoundException e){
            assertEquals(e.getMessage(), "testEmail");
        }

    }

}