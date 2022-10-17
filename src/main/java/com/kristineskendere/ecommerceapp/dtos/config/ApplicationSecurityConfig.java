package com.kristineskendere.ecommerceapp.dtos.config;

import com.kristineskendere.ecommerceapp.jwt.JwtTokenFilter;
import com.kristineskendere.ecommerceapp.models.authModels.User;
import com.kristineskendere.ecommerceapp.repositories.authRepositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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

    @Autowired
    private UserRepository userRepository;
    @Autowired
    JwtTokenFilter jwtTokenFilter;
//    @Autowired
//    JwtTokenService jwtTokenService;

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

    private Set getAuthorities(User user){
        Set <SimpleGrantedAuthority>authorities = new HashSet();
        user.getRoles().forEach(role->{
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        });

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
//                .antMatchers("/auth/login", "/docs/**", "/error").permitAll()
//                .anyRequest().authenticated();
                .anyRequest().permitAll();

        http.exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                );

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(username -> userRepository.findByEmail(username)
//                .orElseThrow(()-> new UsernameNotFoundException( "User" + username+"not found"))
//        );
//    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService((username -> (UserDetails) userRepository.findByEmail(username)
//                .orElseThrow(()-> new UsernameNotFoundException( "User" + username+"not found"))
//        ));
//    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
////        httpSecurity.cors();
//        httpSecurity.csrf().disable();
//        httpSecurity.authorizeRequests()
//                .antMatchers("/auth/login","/error").permitAll()
//                .anyRequest().authenticated();
//
//
////        httpSecurity.authorizeRequests().antMatchers("/authenticate", "/registerNewUser").permitAll()
////                .antMatchers(HttpHeaders.ALLOW).permitAll()
////                .anyRequest().authenticated();
//        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        httpSecurity.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
//        });
//
//        httpSecurity.addFilterBefore(jwtTokenFilter,UsernamePasswordAuthenticationFilter.class);
//
//    }



}
