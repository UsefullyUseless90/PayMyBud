package com.paymybuddy.paymybuddy.services;

import com.paymybuddy.paymybuddy.models.CompteBancaire;
import com.paymybuddy.paymybuddy.models.dao.TransfertEntrant;
import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import com.paymybuddy.paymybuddy.models.dto.TransfertEntrantDTO;
import com.paymybuddy.paymybuddy.repositories.CompteRepository;
import com.paymybuddy.paymybuddy.repositories.TransactionRepository;
import com.paymybuddy.paymybuddy.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompteBancaireService implements ICompteBancaireService {
    @Autowired
    UtilisateurRepository uRepo;
    @Autowired
    CompteRepository cRepo;
    @Autowired
    TransactionRepository tRepo;
    @Autowired
    UtilisateurService uService;

    public Iterable<CompteBancaire> getComptes(int idUtilisateur) {return cRepo.findAll();}
    @Transactional
    public TransfertEntrantDTO ajoutFondsComptePmb(TransfertEntrantDTO transfertEntrant, int idUtilisateur) throws Exception {

        UtilisateurDAO utilisateur = uRepo.findById(idUtilisateur).orElse(null);

        UtilisateurDAO user = utilisateur;
        CompteBancaire cB = cRepo.findByIdCompte(transfertEntrant.getEnvoi().getIdCompte());
        double montant = transfertEntrant.getMontant();
        TransfertEntrant transfertEntrant1 = new TransfertEntrant(user , cB, transfertEntrant.getMontant());
        double somme = cB.getFonds();

        if (montant > somme) {
            throw new Exception("Vous ne disposez pas de suffisamment de fonds pour effectuer cette transaction");
        } else {
            cB.setFonds(somme - montant);
            utilisateur.setFondsDisponibles(utilisateur.getFondsDisponibles() + montant);
            cRepo.save(cB);
            uRepo.save(utilisateur);
        }
        return new TransfertEntrantDTO(transfertEntrant1);
    }
    @Transactional
    public TransfertEntrantDTO retraitFondsComptePmb(TransfertEntrantDTO transfertEntrant, int idUtilisateur) throws Exception {

        UtilisateurDAO utilisateur = uRepo.findById(idUtilisateur).orElse(null);

        UtilisateurDAO user = utilisateur;
        CompteBancaire cB = cRepo.findByIdCompte(transfertEntrant.getEnvoi().getIdCompte());
        double montant = transfertEntrant.getMontant();
        TransfertEntrant transfertEntrant1 = new TransfertEntrant(user , cB, transfertEntrant.getMontant());
        double somme = utilisateur.getFondsDisponibles();
        if (montant > somme){
            throw new Exception("Vous ne disposez pas de suffisamment de fonds pour effectuer cette transaction");
        }else {
            cB.setFonds(cB.getFonds() + montant);
            utilisateur.setFondsDisponibles(somme - montant);
            cRepo.save(cB);
            uRepo.save(utilisateur);
        }
        return new TransfertEntrantDTO(transfertEntrant1);
    }
    @Transactional
    public CompteBancaire saveCompteBancaire(CompteBancaire compteBancaire){
        compteBancaire.setIdCompte(compteBancaire.getIdCompte());
        compteBancaire.setIban(compteBancaire.getIban());
        compteBancaire.setBic(compteBancaire.getBic());
        compteBancaire.setBanque(compteBancaire.getBanque());
        compteBancaire.setFonds(1000.0);
        cRepo.save(compteBancaire);
        return compteBancaire;
    }
    /*
      public ConnexionBanqueDTO connexionBanque(ConnexionBanqueDTO connexionBanqueDTO) {

        UtilisateurDAO utilisateur = uRepo.findById(connexionBanqueDTO.getAppCompte().getIdUtilisateur()).orElse(null);
        CompteBancaire compteBancaire =  cRepo.findByIdCompte(connexionBanqueDTO.getBanqueCompte().getIdCompte());

        ConnexionBanque connexionBanque = new ConnexionBanque(utilisateur,compteBancaire);

        compteBancaire.setIdUtilisateur(utilisateur.getIdUtilisateur());
        cRepo.save(compteBancaire);

        List<CompteBancaire> banques = new ArrayList<>();
        banques.add(compteBancaire);
        utilisateur.setBanques(banques);
        uRepo.save(utilisateur);

        return new ConnexionBanqueDTO(connexionBanque);
    }
     */
}



