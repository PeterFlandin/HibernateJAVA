package com.mycompany.tennis.basededonnee;

import com.mycompany.tennis.basededonnee.entity.*;
import com.mycompany.tennis.basededonnee.repository.JoueurRepository;
import com.mycompany.tennis.basededonnee.repository.TournoiRepository;
import com.mycompany.tennis.basededonnee.service.JoueurService;
import com.mycompany.tennis.basededonnee.service.MatchService;
import com.mycompany.tennis.basededonnee.service.TournoiService;

import java.util.List;


public class TestDeConnection {
    public static void main(String... args) {

        JoueurService joueurService = new JoueurService();

        Joueur noah = new Joueur();
        noah.setPrenom("yannick");
        noah.setNom("Noah");
        noah.setSexe('H');

        joueurService.createJoueur(noah);

        System.out.println(noah.getId());


        //---------------------------------

        TournoiService tournoiService = new TournoiService();

        Tournoi nouveauTournoi = new Tournoi();

        nouveauTournoi.setNom("zanzibaropen");
        nouveauTournoi.setCode("ZB");

         //tournoiService.createTournoi();

        System.out.println(nouveauTournoi.getNom());


//------------------------------------------
        MatchService matchService = new MatchService();

        Match match = new Match();
        Score score=new Score();

        score.setSet1((byte)3);
        score.setSet2((byte)4);
        score.setSet3((byte)6);

        match.setScore(score);
        score.setMatch(match);

        Joueur federer = new Joueur();
        federer.setId(32L);

        Joueur muray = new Joueur();
        muray.setId(34L);

        match.setVainqueur(federer);
        match.setFinaliste(muray);

        Epreuve epreuve = new Epreuve();
        epreuve.setId(1L);

        match.setEpreuve(epreuve);

        matchService.enregistrerNouveauMatch(match);

    }
  }



