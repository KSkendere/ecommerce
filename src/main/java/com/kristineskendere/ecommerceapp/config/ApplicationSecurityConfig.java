package com.kristineskendere.ecommerceapp.config;

import com.kristineskendere.ecommerceapp.jwt.JwtTokenFilter;
import com.kristineskendere.ecommerceapp.models.authModels.User;
import com.kristineskendere.ecommerceapp.repositories.authRepositories.UserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@EnableWebSecurity
@Configuration
public class ApplicationSecurityConfig  {


    private UserRepository userRepository;

    JwtTokenFilter jwtTokenFilter;

    public ApplicationSecurityConfig(UserRepository userRepository, JwtTokenFilter jwtTokenFilter) {
        this.userRepository = userRepository;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public UserDetailsService userDetailsService() {

        return  new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userRepository.findByEmail(username).get();

                if (user != null) {
                    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
                } else {
                    throw new UsernameNotFoundException("UserName is not valid");
                }
            }
        };
    }

    private Set <SimpleGrantedAuthority> getAuthorities(User user){
        Set <SimpleGrantedAuthority>authorities = new HashSet<>();
        user.getRoles().forEach(role->
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName())));
        return authorities;
    }

        @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

        @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
            http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers(("/api/ecommerce/products/admin/**")).hasRole("admin")
                .anyRequest().permitAll();

        http.exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) ->
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()));

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
