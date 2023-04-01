package com.mycompany.tennis.basededonnee.dao;

import com.mycompany.tennis.basededonnee.DataSourceProvider;
import com.mycompany.tennis.basededonnee.entity.Match;

import javax.sql.DataSource;
import java.sql.*;

public class MatchDAO {

    public void matchWithScore(Match match){

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

            PreparedStatement preparedStatement2 = conn.prepareStatement("INSERT INTO SCORE_VAINQUEUR (ID_MATCH,SET_1,SET_2 ,SET_3,SET_4,SET_5) VALUE (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement2.setLong(1, match.getScore().getMatch().getId());
            preparedStatement2.setByte(2, match.getScore().getSet1());
            preparedStatement2.setByte(3, match.getScore().getSet2());

            if (match.getScore().getSet3()==null){
                preparedStatement2.setNull(4, Types.TINYINT );

            } else {
                preparedStatement2.setByte(4, match.getScore().getSet4());
            }

            if (match.getScore().getSet4()==null){
                preparedStatement2.setNull(5, Types.TINYINT);
            } else {
                preparedStatement2.setByte(5, match.getScore().getSet4());
            }

            if (match.getScore().getSet5()==null){
                preparedStatement2.setNull(6, Types.TINYINT);
            } else {
                preparedStatement2.setByte(6, match.getScore().getSet5());
            }


            preparedStatement2.executeUpdate();


            ResultSet res2 = preparedStatement.getGeneratedKeys();

            if (res2.next()){
                match.getScore().setId(res2.getLong(1));
            }

            System.out.println("score ok");

            conn.commit();

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
