package com.mycompany.tennis.basededonnee.service;

import com.mycompany.tennis.basededonnee.HibernateUtil;
import com.mycompany.tennis.basededonnee.entity.Epreuve;
import com.mycompany.tennis.basededonnee.repository.EpreuveRepository;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EpreuveService {


    private EpreuveRepository epreuveRepository;

    public EpreuveService() {
        this.epreuveRepository = new EpreuveRepository() ;
    }

    public Epreuve getEpreuveAvecTournoi (Long id){

        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getById(id);

            Hibernate.initialize(epreuve.getTournoi());

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

        return epreuve;



    }


    public Epreuve getEpreuveSansTournoi (Long id){

        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getById(id);

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

        return epreuve;



    }

}
