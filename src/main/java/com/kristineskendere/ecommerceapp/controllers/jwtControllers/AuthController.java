package com.kristineskendere.ecommerceapp.controllers.jwtControllers;

import com.kristineskendere.ecommerceapp.dtos.authDtos.AuthRequest;
import com.kristineskendere.ecommerceapp.dtos.authDtos.AuthResponse;
import com.kristineskendere.ecommerceapp.jwt.JwtTokenUtil;
import com.kristineskendere.ecommerceapp.models.authModels.User;
import com.kristineskendere.ecommerceapp.repositories.authRepositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RequestMapping("/api/ecommerce")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest) {
        try {
            String userName = authRequest.getEmail();
            String password = authRequest.getPassword();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userName, password));
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            String accessToken = jwtTokenUtil.generateToken(userDetails);
            User user = userRepository.findByEmail(userName).get();
            AuthResponse authResponse = new AuthResponse(user, accessToken);
            return ResponseEntity.ok(authResponse);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}



