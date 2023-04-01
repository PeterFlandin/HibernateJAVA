package com.mycompany.tennis.basededonnee.service;

import com.mycompany.tennis.basededonnee.entity.Match;
import com.mycompany.tennis.basededonnee.repository.MatchRepository;
import com.mycompany.tennis.basededonnee.repository.ScoreRepository;

public class MatchService {

    private ScoreRepository scoreRepository;
    private MatchRepository matchRepository;


    public MatchService(){
        this.matchRepository=new MatchRepository();
        this.scoreRepository=new ScoreRepository();
    }

    public void enregistrerNouveauMatch(Match match){
matchRepository.create(match);





}

}
