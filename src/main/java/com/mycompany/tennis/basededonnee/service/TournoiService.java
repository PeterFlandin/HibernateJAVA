package com.mycompany.tennis.basededonnee.service;

import com.mycompany.tennis.basededonnee.HibernateUtil;
import com.mycompany.tennis.basededonnee.entity.Tournoi;
import com.mycompany.tennis.basededonnee.repository.TournoiRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TournoiService {

private TournoiRepository tournoiRepository;

public TournoiService(){
    this.tournoiRepository=new TournoiRepository();
}

public void createTournoi(Tournoi tournoi){
    Session session = null;
    Transaction tx = null;
    try {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        tx = session.beginTransaction();
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

public Tournoi getTournoi (Long id){
    Session session = null;
    Tournoi tournoi = null;
    Transaction tx = null;
    try {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
tx = session.beginTransaction();
tournoi = tournoiRepository.getById(id);
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
   return tournoi;
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
