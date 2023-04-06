package com.mycompany.tennis.basededonnee.repository;

import com.mycompany.tennis.basededonnee.DataSourceProvider;
import com.mycompany.tennis.basededonnee.HibernateUtil;
import com.mycompany.tennis.basededonnee.entity.Joueur;
import com.mycompany.tennis.basededonnee.entity.Tournoi;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepository {

public List<Tournoi> list () {
    List<Tournoi> listetournoi = new ArrayList<>();



    Connection conn=null;

    try {


        DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

        conn = dataSource.getConnection();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM tournoi");

        ResultSet res = preparedStatement.executeQuery();



     while (res.next()){
         Tournoi tournoi = new Tournoi();
         tournoi.setNom(res.getString("nom"));
         tournoi.setId(res.getLong("id"));
         listetournoi.add(tournoi);

     }

        System.out.println("sucess");
    }
    catch (SQLException e) {
        e.printStackTrace();
        try {
            conn.rollback();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    finally {
        try {
            if (conn!=null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
return listetournoi;
}

    public Tournoi getById(Long id) {
        Session session = null;
        Tournoi tournoi =null;

     session = HibernateUtil.getSessionFactory().getCurrentSession();
    tournoi = session.get(Tournoi.class, id);
        System.out.println("Tournoi lu");
    return  tournoi;
    }

    public void create(Tournoi tournoi) {
    Session session = null;
        Transaction tx = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
          tx = session.beginTransaction();
            session.persist(tournoi);
            tx.commit();
            System.out.println("tournoi crée");
        } catch (Throwable e) {
            if (tx!=null){
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


public void delete(Long id){

    Tournoi tournoi = getById(id);

    Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    session.delete(tournoi);

    System.out.println("Tournoi supprimé");

}
}
