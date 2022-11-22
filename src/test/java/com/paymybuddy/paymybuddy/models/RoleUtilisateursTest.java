package com.paymybuddy.paymybuddy.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class RoleUtilisateursTest {

    /**
     * Method under test: {@link RoleUtilisateurs#equals(Object)}
     */
    @Test
    void testEqualsMulti() {
        RoleUtilisateurs roleUtilisateurs = new RoleUtilisateurs("Nom");
        RoleUtilisateurs roleUtilisateurs1 = new RoleUtilisateurs("Nom");
        assertEquals(roleUtilisateurs, roleUtilisateurs1);
        int expectedHashCodeResult = roleUtilisateurs.hashCode();
        assertEquals(expectedHashCodeResult, roleUtilisateurs1.hashCode());
    }

    /**
     * Method under test: {@link RoleUtilisateurs#equals(Object)}
     */
    @Test
    void testEquals() {
        RoleUtilisateurs roleUtilisateurs = new RoleUtilisateurs(null);
        assertNotEquals(roleUtilisateurs, new RoleUtilisateurs("Nom"));
    }
}

