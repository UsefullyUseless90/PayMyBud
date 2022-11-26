package com.paymybuddy.paymybuddy.models.dto;

import com.paymybuddy.paymybuddy.models.RoleUtilisateurs;
import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class UtilisateurDTO implements Serializable {

    private Integer idUtilisateur;

    private String adresseEmail;

    private String nomPrenom;

    private String motDePasse;

    public boolean actif;

    public List<RoleUtilisateurs> roles;

    public UtilisateurDTO(){

    }


    public UtilisateurDTO(UtilisateurDAO utilisateurDAO) {
        this.idUtilisateur = utilisateurDAO.getIdUtilisateur();
        this.nomPrenom = utilisateurDAO.getNom().concat(" ").concat(utilisateurDAO.getPrenom());
        this.motDePasse = utilisateurDAO.getMotDePasse();
        this.actif = actif;
        this.roles = new ArrayList<>();
        this.roles.add(RoleUtilisateurs.USER);
    }

}
