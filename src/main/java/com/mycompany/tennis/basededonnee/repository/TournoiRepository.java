package com.mycompany.tennis.basededonnee.repository;

import com.mycompany.tennis.basededonnee.DataSourceProvider;
import com.mycompany.tennis.basededonnee.entity.Joueur;
import com.mycompany.tennis.basededonnee.entity.Tournoi;

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
        Connection conn = null;
        Tournoi tournoi = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();


            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","246810");
            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT nom,code FROM tournoi WHERE id=?");

            preparedStatement.setLong(1, id);


            ResultSet res = preparedStatement.executeQuery();

            if (res.next()){
                tournoi=new Tournoi();
                tournoi.setId(id);
                tournoi.setNom(res.getString("nom"));
                tournoi.setCode(res.getString("code"));
            }

            System.out.println("Joueur lu");
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

        return tournoi;
    }

    public void create(Tournoi tournoi) {
        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();


            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","246810");
            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO tournoi (nom,code) VALUE (?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tournoi.getNom());
            preparedStatement.setString(2, tournoi.getCode().toUpperCase());

            preparedStatement.executeUpdate();


            ResultSet res = preparedStatement.getGeneratedKeys();

            if (res.next()){
                tournoi.setId(res.getLong(1));
            }

            System.out.println("tournoi crée");
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






}
