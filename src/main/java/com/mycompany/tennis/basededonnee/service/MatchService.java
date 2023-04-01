package com.mycompany.tennis.basededonnee.service;

import com.mycompany.tennis.basededonnee.dao.MatchDAO;
import com.mycompany.tennis.basededonnee.entity.Match;
import com.mycompany.tennis.basededonnee.repository.MatchRepository;
import com.mycompany.tennis.basededonnee.repository.ScoreRepository;

public class MatchService {

    private ScoreRepository scoreRepository;
    private MatchRepository matchRepository;

private MatchDAO matchDAO;

    public MatchService(){
        this.matchRepository=new MatchRepository();
        this.scoreRepository=new ScoreRepository();
        this.matchDAO=new MatchDAO();
    }

    public void enregistrerNouveauMatch(Match match){
        matchRepository.create(match);
        scoreRepository.create(match.getScore());





}

}
