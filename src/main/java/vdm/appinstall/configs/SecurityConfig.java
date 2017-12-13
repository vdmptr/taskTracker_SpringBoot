package vdm.appinstall.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import vdm.security.MyUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   private MyUserDetailService myUserDetailService;

   @Autowired
   public SecurityConfig(MyUserDetailService myUserDetailService) {
       this.myUserDetailService = myUserDetailService;
   }

   @Bean
   DaoAuthenticationProvider daoAuthenticationProvider(){
       DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
       provider.setUserDetailsService(myUserDetailService);
       return provider;
   }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
       auth
            .authenticationProvider(daoAuthenticationProvider());
    }

   @Override
    public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers("/registration/**",
                                   "/WEB-INF/views/registration.jsp" );
    }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
           .authorizeRequests()
               .antMatchers("/admin**").hasRole("ADMIN")
               .anyRequest().authenticated()
               .and()
           .formLogin()
               .permitAll()
               .loginPage("/login.jsp")
               .usernameParameter("j_username")
               .passwordParameter("j_password")
               .loginProcessingUrl("/j_spring_security_check")
               .defaultSuccessUrl("/login/ok")
               .failureUrl("/login.jsp?error=true")
               .and()
           .logout()
               .permitAll();
       http.csrf().disable();
   }

}
