package vdm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import vdm.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Component
public class MyUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    MyUserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<vdm.entity.User> users = userRepository.findUsersByEmail(email);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }
        vdm.entity.User user = users.get(0);
        final String userRole = user.getRole().name();
        final List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(userRole));
        return new SecurityUser(user.getEmail(),
                user.getPasswordHash(),
                user.isEnabled(),
                authorities,
                user.getUserId());
    }
}
