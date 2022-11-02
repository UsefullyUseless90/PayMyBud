package com.paymybuddy.paymybuddy.controllers;

import com.paymybuddy.paymybuddy.config.CustomUserDetails;
import com.paymybuddy.paymybuddy.config.JwtManager;
import com.paymybuddy.paymybuddy.models.dto.UtilisateurDTO;
import com.paymybuddy.paymybuddy.repositories.UtilisateurRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;



@CrossOrigin
@RestController
@RequestMapping("/login")
public class AuthController{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtManager jwt;

    @Autowired
    UserDetailsService userDetailsService;

/*
    @PostMapping(path = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object[] login(@RequestBody UtilisateurDTO user){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getAdresseEmail(), user.getMotDePasse()));

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return new Object[]{ userDetails.getAppUser(), jwt.generateToken(userDetails)};

    }
*/
    private static final Logger log = LogManager.getLogger(AuthController.class);

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UtilisateurDTO authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getAdresseEmail(), authenticationRequest.getMotDePasse());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getAdresseEmail());

        final String token = jwt.generateToken(userDetails);

        log.info("Voici le token généré:" + token);

        return ResponseEntity.ok(token);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}



