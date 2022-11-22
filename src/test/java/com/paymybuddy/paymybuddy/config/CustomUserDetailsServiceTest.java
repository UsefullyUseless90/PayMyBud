package com.paymybuddy.paymybuddy.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ch.qos.logback.core.util.COWArrayList;
import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.paymybuddy.paymybuddy.models.RoleUtilisateurs;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class CustomUserDetailsServiceTest {

    /**
     * Method under test: {@link CustomUserDetailsService#getUserAuthority(List)}
     */

    @Test
    void testGetUserAuthority() {
        CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService();
        COWArrayList<RoleUtilisateurs> roleUtilisateursList = (COWArrayList<RoleUtilisateurs>) mock(COWArrayList.class);
        when(roleUtilisateursList.iterator())
                .thenReturn(new ArrayIterator<>(new RoleUtilisateurs[]{new RoleUtilisateurs("Nom")}));
        List<GrantedAuthority> actualUserAuthority = customUserDetailsService.getUserAuthority(roleUtilisateursList);
        assertEquals(1, actualUserAuthority.size());
        assertEquals("Nom", actualUserAuthority.get(0).getAuthority());
        verify(roleUtilisateursList).iterator();
    }

}

