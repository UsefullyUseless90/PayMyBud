package com.paymybuddy.paymybuddy.config;

import com.paymybuddy.paymybuddy.models.dto.UtilisateurDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private User user;

    /** Keep information about app user in local database */
    private UtilisateurDTO appUser;

    public CustomUserDetails(User user, UtilisateurDTO appUser) {
        this.user = user;
        this.appUser = appUser;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {return this.user.getUsername();}

    @Override
    public boolean isAccountNonExpired() {
        return this.user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.user.isEnabled();
    }

    public User getUser() {
        return user;
    }

    public UtilisateurDTO getAppUser() {
        return appUser;
    }

    @Override
    public String toString() {
        return "CustomUserDetails [user=" + user + "]";
    }

}
