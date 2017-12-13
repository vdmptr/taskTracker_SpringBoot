package vdm.security;

import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;
import static org.junit.Assert.*;

public class SecurityUserTest {
    private SecurityUser securityUser = new SecurityUser("name",
            "testPassword",
            true,
            Collections.singletonList(new SimpleGrantedAuthority("test")),
            1);
    @Test
    public void getUserId() throws Exception {

        assertEquals(securityUser.getUserId(), 1);
    }

}