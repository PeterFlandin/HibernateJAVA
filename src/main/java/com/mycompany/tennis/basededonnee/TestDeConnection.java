package com.mycompany.tennis.basededonnee;

import java.sql.*;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {
            //Seulement avant Java 7/JDBC 4
            //Class.forName(DRIVER_CLASS_NAME);

            //MySQL driver MySQL Connector
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","246810");


            long identifiant =5L;


            PreparedStatement preparedStatement = conn.prepareStatement("SELECT nom,prenom, id FROM joueur WHERE id=?");
            preparedStatement.setLong(1, identifiant );
            ResultSet res = preparedStatement.executeQuery();


            if (res.next()) {
               final String nom = res.getString("nom");
               final String prenom = res.getString("prenom");
                final long id = res.getLong("id");
                System.out.println("le joueur ou la joueuse representé par le numéro "+id+" ce prénomme "+nom+" "+prenom+" ");
            }
            else {

                System.out.println("Il n'existe personne avec cet identifiant");
            }

            //Oracle Driver officiel OJDBC Thin
            //conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:tennis","COURSDB","COURSDB");
            //Postgres Driver officiel
            //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tennis","COURSDB","COURSDB");
            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
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
