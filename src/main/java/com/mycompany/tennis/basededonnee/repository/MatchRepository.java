package com.mycompany.tennis.basededonnee.repository;

import com.mycompany.tennis.basededonnee.DataSourceProvider;
import com.mycompany.tennis.basededonnee.entity.Joueur;
import com.mycompany.tennis.basededonnee.entity.Match;

import javax.sql.DataSource;
import java.sql.*;

public class MatchRepository {

    public void create(Match match) {
        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();


            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","246810");
            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO MATCH_TENNIS (id_epreuve,id_vainqueur,id_finaliste) VALUE (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, match.getEpreuve().getId());
            preparedStatement.setLong(2, match.getVainqueur().getId());
            preparedStatement.setLong(3,match.getFinaliste().getId());

            preparedStatement.executeUpdate();


            ResultSet res = preparedStatement.getGeneratedKeys();

            if (res.next()){
                match.setId(res.getLong(1));
            }

            System.out.println("Match crée");
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
