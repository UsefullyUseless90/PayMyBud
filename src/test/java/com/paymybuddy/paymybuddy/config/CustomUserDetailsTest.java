package com.paymybuddy.paymybuddy.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.paymybuddy.paymybuddy.models.dto.UtilisateurDTO;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;

class CustomUserDetailsTest {
    /**
     * Method under test: default or parameterless constructor of {@link CustomUserDetails}
     */
    @Test
    void testConstructor() {
        User user = new User("janedoe", "iloveyou", new ArrayList<>());

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        CustomUserDetails actualCustomUserDetails = new CustomUserDetails(user, utilisateurDTO);

        assertSame(utilisateurDTO, actualCustomUserDetails.getAppUser());
        assertSame(user, actualCustomUserDetails.getUser());
    }

    /**
     * Method under test: {@link CustomUserDetails#getAuthorities()}
     */
    @Test
    void testGetAuthorities() {
        User user = new User("janedoe", "iloveyou", new ArrayList<>());

        assertTrue((new CustomUserDetails(user, new UtilisateurDTO())).getAuthorities().isEmpty());
    }


    /**
     * Method under test: {@link CustomUserDetails#getPassword()}
     */
    @Test
    void testGetPassword() {
        User user = new User("janedoe", "iloveyou", new ArrayList<>());

        assertEquals("iloveyou", (new CustomUserDetails(user, new UtilisateurDTO())).getPassword());
    }

    /**
     * Method under test: {@link CustomUserDetails#getUsername()}
     */
    @Test
    void testGetUsername() {
        User user = new User("janedoe", "iloveyou", new ArrayList<>());

        assertEquals("janedoe", (new CustomUserDetails(user, new UtilisateurDTO())).getUsername());
    }

    /**
     * Method under test: {@link CustomUserDetails#isAccountNonExpired()}
     */
    @Test
    void testIsAccountNonExpired() {
        User user = new User("janedoe", "iloveyou", new ArrayList<>());

        assertTrue((new CustomUserDetails(user, new UtilisateurDTO())).isAccountNonExpired());
    }

    /**
     * Method under test: {@link CustomUserDetails#isAccountNonExpired()}
     */
    @Test
    void testIsAccountNonExpiredDifferentParameter() {
        User user = new User("janedoe", "iloveyou", true, false, true, true, new ArrayList<>());

        assertFalse((new CustomUserDetails(user, new UtilisateurDTO())).isAccountNonExpired());
    }

    /**
     * Method under test: {@link CustomUserDetails#isAccountNonLocked()}
     */
    @Test
    void testIsAccountNonLocked() {
        User user = new User("janedoe", "iloveyou", new ArrayList<>());

        assertTrue((new CustomUserDetails(user, new UtilisateurDTO())).isAccountNonLocked());
    }
    /**
     * Method under test: {@link CustomUserDetails#isAccountNonLocked()}
     */
    @Test
    void testIsAccountNonLockedDifferentCase() {
        User user = new User("janedoe", "iloveyou", true, true, true, false, new ArrayList<>());

        assertFalse((new CustomUserDetails(user, new UtilisateurDTO())).isAccountNonLocked());
    }

    /**
     * Method under test: {@link CustomUserDetails#isCredentialsNonExpired()}
     */
    @Test
    void testIsCredentialsNonExpired() {
        User user = new User("janedoe", "iloveyou", new ArrayList<>());

        assertTrue((new CustomUserDetails(user, new UtilisateurDTO())).isCredentialsNonExpired());
    }

    /**
     * Method under test: {@link CustomUserDetails#isCredentialsNonExpired()}
     */
    @Test
    void testIsCredentialsNonExpiredDifferentScenario() {
        User user = new User("janedoe", "iloveyou", true, true, false, true, new ArrayList<>());

        assertFalse((new CustomUserDetails(user, new UtilisateurDTO())).isCredentialsNonExpired());
    }

    /**
     * Method under test: {@link CustomUserDetails#isEnabled()}
     */
    @Test
    void testIsEnabled() {
        User user = new User("janedoe", "iloveyou", new ArrayList<>());

        assertTrue((new CustomUserDetails(user, new UtilisateurDTO())).isEnabled());
    }

    /**
     * Method under test: {@link CustomUserDetails#isEnabled()}
     */
    @Test
    void testIsEnabledDifferentParameters() {
        User user = new User("janedoe", "iloveyou", false, true, true, true, new ArrayList<>());

        assertFalse((new CustomUserDetails(user, new UtilisateurDTO())).isEnabled());
    }
}

