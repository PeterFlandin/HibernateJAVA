package com.mycompany.tennis.basededonnee.repository;

import com.mycompany.tennis.basededonnee.DataSourceProvider;
import com.mycompany.tennis.basededonnee.entity.Joueur;
import com.mycompany.tennis.basededonnee.entity.Score;

import javax.sql.DataSource;
import java.sql.*;

public class ScoreRepository {
    public void create(Score score) {
        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();


            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","246810");
            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO SCORE_VAINQUEUR (ID_MATCH,SET_1,SET_2 ,SET_3,SET_4,SET_5) VALUE (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, score.getMatch().getId());
            preparedStatement.setByte(2, score.getSet1());
            preparedStatement.setByte(3, score.getSet2());

            if (score.getSet3()==null){
                preparedStatement.setNull(4, Types.TINYINT );

            } else {
                preparedStatement.setByte(4, score.getSet4());
            }

            if (score.getSet4()==null){
                preparedStatement.setNull(5, Types.TINYINT);
            } else {
                preparedStatement.setByte(5, score.getSet4());
            }

            if (score.getSet5()==null){
                preparedStatement.setNull(6, Types.TINYINT);
            } else {
                preparedStatement.setByte(6, score.getSet5());
            }


            preparedStatement.executeUpdate();


            ResultSet res = preparedStatement.getGeneratedKeys();

            if (res.next()){
                score.setId(res.getLong(1));
            }

            System.out.println("Joueur crée");
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
