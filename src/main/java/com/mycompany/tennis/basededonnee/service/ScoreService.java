package com.mycompany.tennis.basededonnee.service;

import com.mycompany.tennis.basededonnee.HibernateUtil;
import com.mycompany.tennis.basededonnee.entity.Joueur;
import com.mycompany.tennis.basededonnee.entity.Score;
import com.mycompany.tennis.basededonnee.repository.ScoreRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreService {

    private ScoreRepository scoreRepository;


    public ScoreService(){
        this.scoreRepository = new  ScoreRepository();
    }

    public Score getScore(Long id){
        Session session=null;
        Transaction tx = null;
        Score score = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            score =  scoreRepository.getById(id);
            tx.commit();
        } catch (Throwable e) {
            if(tx == null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (session!=null){
                session.close();
            }
        }
        return score;
    }

}
