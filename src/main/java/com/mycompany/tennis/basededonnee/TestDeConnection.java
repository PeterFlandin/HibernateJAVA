package com.mycompany.tennis.basededonnee;

import com.mycompany.tennis.basededonnee.entity.Joueur;
import com.mycompany.tennis.basededonnee.entity.Tournoi;
import com.mycompany.tennis.basededonnee.repository.JoueurRepository;
import com.mycompany.tennis.basededonnee.repository.TournoiRepository;
import com.mycompany.tennis.basededonnee.service.JoueurService;

import java.util.List;


public class TestDeConnection {
    public static void main(String... args){

        JoueurService joueurService = new JoueurService();
         JoueurService joueurService1 = new JoueurService();


         Joueur noah = new Joueur();
         noah.setPrenom("yannick");
         noah.setNom("Noah");
         noah.setSexe('H');

       joueurService.createJoueur(noah);

       System.out.println(noah.getId());


       //---------------------------------

        
        Joueur bartoum = joueurService1.getJoueur(25L);
        System.out.println(bartoum.getId());

            }





  }



