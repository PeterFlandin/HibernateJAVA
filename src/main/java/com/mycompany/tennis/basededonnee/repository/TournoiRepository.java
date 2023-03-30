package com.mycompany.tennis.basededonnee.repository;

import com.mycompany.tennis.basededonnee.DataSourceProvider;
import com.mycompany.tennis.basededonnee.entity.Tournoi;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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



}
