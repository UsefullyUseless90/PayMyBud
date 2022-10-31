package com.paymybuddy.paymybuddy.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class RoleUtilisateurs {

    public static final RoleUtilisateurs ADMIN = new RoleUtilisateurs("ADMIN");
    public static final RoleUtilisateurs COMMON = new RoleUtilisateurs("COMMON");
    public static final RoleUtilisateurs USER = new RoleUtilisateurs("USER");

    String nom;

    public RoleUtilisateurs(){ }

    public RoleUtilisateurs(String nom){
        this.nom = nom;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleUtilisateurs appRole = (RoleUtilisateurs) o;
        return Objects.equals(nom, appRole.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

}
