package com.mycompany.tennis.basededonnee.service;

import com.mycompany.tennis.basededonnee.HibernateUtil;
import com.mycompany.tennis.basededonnee.dto.*;
import com.mycompany.tennis.basededonnee.entity.Epreuve;
import com.mycompany.tennis.basededonnee.entity.Joueur;
import com.mycompany.tennis.basededonnee.repository.EpreuveRepository;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;

public class EpreuveService {


    private EpreuveRepository epreuveRepository;

    public EpreuveService() {
        this.epreuveRepository = new EpreuveRepository() ;
    }

    public EpreuveFullDto getEpreuveDetail (Long id){

        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        EpreuveFullDto dto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getById(id);


            dto = new EpreuveFullDto();
            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());
            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(epreuve.getTournoi().getId());
            tournoiDto.setNom(epreuve.getTournoi().getNom());

            dto.setParticipants(new HashSet<>());
           for (Joueur joueur : epreuve.getParticipants() ){
               final JoueurDto joueurDto = new JoueurDto();
               joueurDto.setId(joueur.getId());
               joueurDto.setSexe(joueur.getSexe());
               joueurDto.setPrenom(joueur.getPrenom());
               joueurDto.setNom(joueur.getNom());
               dto.getParticipants().add(joueurDto);


           }
            tx.commit();

        } catch (Throwable tr) {
            if (tx != null){
                tx.rollback();
            }
            tr.printStackTrace();

        } finally {
            if (session != null){
                session.close();
            }
        }

        return dto;



    }


    public EpreuveLiteDto getEpreuveSansTournoi (Long id){

        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        EpreuveLiteDto dto =null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getById(id);


             dto = new EpreuveLiteDto();
            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());

            tx.commit();


        } catch (Throwable tr) {
            if (tx != null){
                tx.rollback();
            }
            tr.printStackTrace();

        } finally {
            if (session != null){
                session.close();
            }
        }

        return dto;



    }

}
