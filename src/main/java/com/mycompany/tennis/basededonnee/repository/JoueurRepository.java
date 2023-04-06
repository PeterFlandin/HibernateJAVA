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


  public void create(Joueur joueur) {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.persist(joueur);
            System.out.println("Joueur crée");
    }




    public void delete(Long id) {
       Joueur joueur = getById(id);

     Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    session.delete(joueur);

        System.out.println("Jouueur supprimé");

    }


    public Joueur getById(Long id) {
         Joueur joueur = null;
         Session session = null;

         session = HibernateUtil.getSessionFactory().getCurrentSession();
         joueur = session.get(Joueur.class,id);
         System.out.println("Joueur lu");

        return joueur;
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
