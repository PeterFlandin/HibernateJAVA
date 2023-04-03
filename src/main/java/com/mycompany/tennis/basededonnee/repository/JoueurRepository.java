package com.mycompany.tennis.basededonnee.repository;

import com.mycompany.tennis.basededonnee.DataSourceProvider;
import com.mycompany.tennis.basededonnee.HibernateUtil;
import com.mycompany.tennis.basededonnee.entity.Joueur;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepository {

    public void renomme(Long id, String nouveauNom ){

        Joueur joueur = null;
        Session session = null;
Transaction tx = null;
        try {

            session=HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            joueur = session.get(Joueur.class,id);
            joueur.setNom(nouveauNom);
            tx.commit();
            System.out.println("Joueur modifié");



        } catch (Throwable t) {
            if (tx!=null){
                tx.rollback();
            }
            t.printStackTrace();
        } finally {
            if (session!=null){
                session.close();
            }
        }
    }
  public void create(Joueur joueur) {
      Session session = null;
      Transaction tx = null;
        try {
session = HibernateUtil.getSessionFactory().openSession();
tx = session.beginTransaction();
            session.persist(joueur);
            tx.commit();
            System.out.println("Joueur crée");

        } catch (Throwable e) {
            if(tx == null){
                tx.rollback();
            }
            e.printStackTrace();


        }
        finally {
           if (session != null){
               session.close();
           }

        }

    }

    public void update(Joueur joueur) {
        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();


            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","246810");
            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=? ,SEXE=? WHERE id=?");
            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
                preparedStatement.setString(3, joueur.getSexe().toString());
            preparedStatement.setLong(4, joueur.getId());

            preparedStatement.executeUpdate();
preparedStatement.getGeneratedKeys();
ResultSet res = preparedStatement.getGeneratedKeys();




            System.out.println("Joueur modifié");
        } catch (SQLException e) {
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

    }


    public void delete(Long id) {
        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();


            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","246810");
            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM joueur WHERE id=?");

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();


            System.out.println("Joueur suprimer");
        } catch (SQLException e) {
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
    }


    public Joueur getById(Long id) {
      Joueur joueur = null;
      Session session = null;

      try {

          session=HibernateUtil.getSessionFactory().openSession();
         joueur = session.get(Joueur.class,id);

          System.out.println("Joueur ajouté");



      } catch (Throwable t) {
         t.printStackTrace();
      } finally {
          if (session!=null){
              session.close();
          }
      }
      return  joueur;
    }

    public List<Joueur> list() {
      List<Joueur> joueurs =new ArrayList<>();
        Connection conn = null;
        try {

       DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","246810");
            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT id,nom,prenom,sexe FROM joueur");

            ResultSet res = preparedStatement.executeQuery();

            while (res.next()){

                Joueur joueur=new Joueur();
                joueur.setId(res.getLong("id"));
                joueur.setNom(res.getString("nom"));
                joueur.setPrenom(res.getString("prenom"));
                joueur.setSexe(res.getString("sexe").charAt(0));
                joueurs.add(joueur);
            }

            System.out.println("Joueur");
        } catch (SQLException e) {
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


        return joueurs;
    }


}
