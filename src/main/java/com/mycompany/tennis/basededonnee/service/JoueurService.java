package com.mycompany.tennis.basededonnee.service;

import com.mycompany.tennis.basededonnee.HibernateUtil;
import com.mycompany.tennis.basededonnee.entity.Joueur;
import com.mycompany.tennis.basededonnee.repository.JoueurRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class JoueurService {
    private JoueurRepository joueurRepository1;

    public JoueurService(){
        this.joueurRepository1=new JoueurRepository();
    }









  public void createJoueur(Joueur joueur){
      Session session=null;
      Transaction tx = null;
      try {
          session = HibernateUtil.getSessionFactory().getCurrentSession();
          joueurRepository1.create(joueur);
          tx.commit();
          System.out.println("Joueur ajouté");
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
   }

    public Joueur getJoueur(Long id){
        Session session=null;
        Transaction tx = null;
        Joueur joueur = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
           tx = session.beginTransaction();
            joueur =  joueurRepository1.getById(id);
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
        return joueur;
    }

    public void renomme (Long id, String nouveauNom){

        Joueur joueur = getJoueur(id);
        Session session=null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueur.setNom(nouveauNom);

            Joueur joueur1 = (Joueur)session.merge(joueur);
            tx.commit();
            System.out.println("Joueur modifier");

        } catch (Throwable e) {
            if(tx == null){
                tx.rollback();
            }
            e.printStackTrace();

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void changeSexe (Long id, Character sexe){
        Joueur joueur = getJoueur(id);
        Transaction tx = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            joueur.setSexe(sexe);

            Joueur joueursexe = (Joueur) session.merge(joueur);
            tx.commit();

            System.out.println("changer de sexe");


        } catch (Throwable throwable){
            if (tx != null){
                tx.rollback();
            }
            throwable.printStackTrace();
        } finally {
            if (session != null)
            session.close();
        }

    }


    public void deleteJoueur (Long id) {
        Session session = null;
        Transaction tx = null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            joueurRepository1.delete(id);
            tx.commit();

        } catch (Throwable tr) {
            if (tr != null){
                tx.rollback();
            }
            tr.printStackTrace();
        }
        finally {
            if (session!=null){
                session.close();
            }
        }
    }
}
