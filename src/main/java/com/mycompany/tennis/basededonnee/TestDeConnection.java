package com.mycompany.tennis.basededonnee;

import com.mycompany.tennis.basededonnee.entity.Joueur;
import com.mycompany.tennis.basededonnee.entity.Tournoi;
import com.mycompany.tennis.basededonnee.repository.JoueurRepository;
import com.mycompany.tennis.basededonnee.repository.TournoiRepository;

import java.util.List;


public class TestDeConnection {
    public static void main(String... args){


            TournoiRepository tournoiRepository = new TournoiRepository();

            List list = tournoiRepository.list();

            for (Tournoi tournoi : tournoiRepository.list()){
                System.out.println(tournoi.getNom());
            }
            
        }

    }


