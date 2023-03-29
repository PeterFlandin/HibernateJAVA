package com.mycompany.tennis.basededonnee;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {

            BasicDataSource dataSource = new BasicDataSource();

            dataSource.setInitialSize(5);

            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");

            dataSource.setUsername("root");
            dataSource.setPassword("246810");

            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","246810");
            conn = dataSource.getConnection();

            conn.setAutoCommit(false);

            long identifiant =24L;
            String nom = "Errani";
            String prenom = "sahara";
            String sexe = "F";
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO JOUEUR (NOM,PRENOM,SEXE) VALUE (?,?,?)");
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, sexe );

            preparedStatement.executeUpdate();

            prenom = "josh";
            nom = "Bryan";
            sexe = "H";

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, sexe);

            preparedStatement.executeUpdate();

            conn.commit();

            System.out.println("success");
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
