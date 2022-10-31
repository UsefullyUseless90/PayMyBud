package com.paymybuddy.paymybuddy.config;

import com.paymybuddy.paymybuddy.models.RoleUtilisateurs;
import com.paymybuddy.paymybuddy.models.dto.UtilisateurDTO;
import com.paymybuddy.paymybuddy.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UtilisateurDTO user = userRepository.findByAdresseEmail(userName);

        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }else {
            List<GrantedAuthority> authorities = getUserAuthority(user.roles);
            return new CustomUserDetails(new org.springframework.security.core.userdetails.User( user.getNomPrenom(), user.getMotDePasse(),
                    user.actif, true, true, true, authorities), user);
        }
    }

    public List<GrantedAuthority> getUserAuthority(List<RoleUtilisateurs> roles) {
        Set<GrantedAuthority> r = new HashSet<>();
        if(roles != null) {
            for (RoleUtilisateurs p : roles) {
                r.add(new SimpleGrantedAuthority(p.getNom()));
            }
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(r);
        return grantedAuthorities;
    }

}