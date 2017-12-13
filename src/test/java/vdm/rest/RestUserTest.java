package vdm.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import vdm.entity.User;
import vdm.security.SecurityUser;
import vdm.service.UserService;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestUserTest {

    @InjectMocks
    private RestUser restUser;

    @Mock
    private UserService userService;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityUser securityUser;

    @Mock
    private User user;

    @Test
    public void getDeveloperId() throws Exception {
        when(authentication.getPrincipal()).thenReturn(securityUser);

        restUser.getDeveloperId(authentication);

        verify(authentication).getPrincipal();
    }

    @Test
    public void getAllDeveloper() throws Exception {
        restUser.getAllDeveloper();

        verify(userService).getAllDeveloper();
    }

    @Test
    public void getDeveloper() throws Exception {
        restUser.getDeveloper("name", "lastName");

        verify(userService).getDeveloper("name", "lastName");
    }

    @Test
    public void addUser() throws Exception {
        restUser.addUser(user);

        verify(userService).addUser(user);
    }

}


