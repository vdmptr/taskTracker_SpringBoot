package vdm.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import vdm.helpers.Role;
import vdm.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyUserDetailServiceTest {
    private static final String EMAIL = "vdm@gmail.com";
    private static final String ERROR_EMAIL = "error@gmail.com";

    @Mock
    private UserRepository userRepository;

    @Mock
    private vdm.entity.User user;

    @InjectMocks
    private MyUserDetailService myUserDetailService =
            new MyUserDetailService(userRepository);

    @Before
    public void setUp() throws Exception {
        when(user.getRole()).thenReturn(Role.ROLE_ADMIN);
        when(user.getUserId()).thenReturn(1);
        when(user.isEnabled()).thenReturn(true);
        when(user.getPasswordHash()).thenReturn("testPassword");
        when(user.getEmail()).thenReturn(EMAIL);

        List<vdm.entity.User> userList = new ArrayList<>();
        when(userRepository.findUsersByEmail(ERROR_EMAIL)).thenReturn(userList);
        userList.add(user);
        when(userRepository.findUsersByEmail(EMAIL)).thenReturn(userList);
    }

    @Test
    public void loadUserByUsername() throws Exception {
        UserDetails userDetails = myUserDetailService.loadUserByUsername(EMAIL);
        assertEquals( userDetails.getPassword(), "testPassword");
        try{
            myUserDetailService.loadUserByUsername(ERROR_EMAIL);
        }catch (UsernameNotFoundException e){
            assertEquals(e.getMessage(), ERROR_EMAIL);
        }
    }

}