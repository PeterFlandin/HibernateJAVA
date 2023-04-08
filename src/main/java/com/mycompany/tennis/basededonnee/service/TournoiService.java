package com.mycompany.tennis.basededonnee.service;

import com.mycompany.tennis.basededonnee.HibernateUtil;
import com.mycompany.tennis.basededonnee.dto.TournoiDto;
import com.mycompany.tennis.basededonnee.entity.Tournoi;
import com.mycompany.tennis.basededonnee.repository.TournoiRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TournoiService {

private TournoiRepository tournoiRepository;

public TournoiService(){
    this.tournoiRepository=new TournoiRepository();
}

public void createTournoi(TournoiDto dto){
    Session session = null;
    Transaction tx = null;
    try {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        tx = session.beginTransaction();

        Tournoi tournoi = new Tournoi();
        tournoi.setId(tournoi.getId());
        tournoi.setCode(tournoi.getCode());
        tournoi.setNom(tournoi.getNom());
        tournoiRepository.create(tournoi);

        tx.commit();

    } catch (Throwable tr){
        if (tx != null){
            tx.rollback();
        }
        tr.printStackTrace();
    } finally {
        if (session!=null){
            session.close();
        }
    }
}

public TournoiDto getTournoi (Long id){
    Session session = null;
    Tournoi tournoi = null;
    Transaction tx = null;
    TournoiDto tournoiDto = null;
    try {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
tx = session.beginTransaction();

tournoi = tournoiRepository.getById(id);

 tournoiDto = new TournoiDto();
tournoiDto.setNom(tournoi.getNom());
tournoiDto.setId(tournoiDto.getId());
tournoiDto.setCode(tournoiDto.getCode());

tx.commit();




    } catch (Throwable tr){
        if (tx != null){
            tx.rollback();
        }
        tr.printStackTrace();
    } finally {
        if (session!=null){
            session.close();
        }
    }
   return tournoiDto;
}

public void deleteTournoiSelectionne(Long id){

    Session session = null;
    Transaction tx = null;

    try {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        tx = session.beginTransaction();
     tournoiRepository.delete(id);
        tx.commit();

    } catch (Throwable tr){
        if (tx != null){
            tx.rollback();
        }
        tr.printStackTrace();
    } finally {
        if (session!=null){
            session.close();
        }
    }
}




}
