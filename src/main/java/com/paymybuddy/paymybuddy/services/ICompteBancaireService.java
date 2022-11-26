package com.paymybuddy.paymybuddy.services;

import com.paymybuddy.paymybuddy.models.CompteBancaire;
import com.paymybuddy.paymybuddy.models.dto.TransfertEntrantDTO;

public interface ICompteBancaireService {

TransfertEntrantDTO ajoutFondsComptePmb(TransfertEntrantDTO transfertEntrant,int idUtilisateur) throws Exception;

Iterable<CompteBancaire> getComptes (int idUtilisateur);

TransfertEntrantDTO retraitFondsComptePmb(TransfertEntrantDTO transfertEntrant, int idUtilisateur) throws Exception;

CompteBancaire saveCompteBancaire(CompteBancaire compteBancaire);

// ConnexionBanqueDTO connexionBanque(ConnexionBanqueDTO connexionBanqueDTO);
}
