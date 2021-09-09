package by.du.petrolstation.config;

import by.du.petrolstation.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(encoder().encode("pass")).roles(Role.ADMIN.name(), Role.MANAGER.name())
                .and()
                .withUser("user").password(encoder().encode("1234")).roles(Role.CLIENT.name())
                .and()
                .withUser("manager").password(encoder().encode("1234")).roles(Role.MANAGER.name());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/station/**").hasRole(Role.ADMIN.name())
                .antMatchers("/dispenser/**").hasRole(Role.ADMIN.name())
                .antMatchers("/tank/**").hasRole(Role.ADMIN.name())
                .antMatchers("/petrol/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())
                .antMatchers("/currency/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/login/**").anonymous()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .deleteCookies("JSESSIONID");
    }
}
