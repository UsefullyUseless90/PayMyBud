package com.paymybuddy.paymybuddy.services;

import com.paymybuddy.paymybuddy.models.dao.ConnexionDAO;
import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import com.paymybuddy.paymybuddy.models.dto.ConnexionDTO;
import com.paymybuddy.paymybuddy.models.dto.UtilisateurDTO;
import com.paymybuddy.paymybuddy.repositories.ConnexionRepository;
import com.paymybuddy.paymybuddy.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ConnexionService implements IConnexionService {

    @Autowired
    private ConnexionRepository connexionRepository;


    @Autowired
    private UtilisateurRepository utilisateurRepository;


    public ConnexionService(UtilisateurRepository repo, ConnexionRepository cRepo) {
    }

    public Iterable<ConnexionDAO> getConnexions(){
        return connexionRepository.findAll();
    }
    @Transactional
    public List<UtilisateurDTO> getConnexionById(Integer idUtilisateur){
        UtilisateurDAO utilisateurDAO = utilisateurRepository.findById(idUtilisateur).orElse(null);

        List<UtilisateurDTO> listeConnexion = new ArrayList<>();

        Iterator<ConnexionDAO> connexion = utilisateurDAO.getConnexionDAOSet().iterator();

        while(connexion.hasNext()) {
            ConnexionDAO connexionDAO = connexion.next();

            UtilisateurDTO nouvelUtilisateurDTO = new UtilisateurDTO(connexionDAO.getEmbeddedConnexion().getDemandeur());

            listeConnexion.add(nouvelUtilisateurDTO);

        }


        return listeConnexion;
    }

    @Override
    @Transactional
    public ConnexionDTO creationConnexion(ConnexionDTO connexionDTO, Integer idUtilisateur) {

        UtilisateurDAO receveur = utilisateurRepository.findById(idUtilisateur).orElse(null);

        UtilisateurDAO demandeurDAO =  utilisateurRepository.findById(connexionDTO.getDemandeur().getIdUtilisateur()).orElse(null);
        UtilisateurDAO receveurDAO =  receveur;

        ConnexionDAO connexionDAO = new ConnexionDAO(demandeurDAO,receveurDAO);

        connexionDAO = connexionRepository.save(connexionDAO);

        return new ConnexionDTO(connexionDAO);
    }

}
