package com.mycompany.tennis.basededonnee.service;

import com.mycompany.tennis.basededonnee.HibernateUtil;
import com.mycompany.tennis.basededonnee.dto.*;
import com.mycompany.tennis.basededonnee.entity.Epreuve;
import com.mycompany.tennis.basededonnee.entity.Joueur;
import com.mycompany.tennis.basededonnee.entity.Match;
import com.mycompany.tennis.basededonnee.entity.Score;
import com.mycompany.tennis.basededonnee.repository.EpreuveRepository;
import com.mycompany.tennis.basededonnee.repository.JoueurRepository;
import com.mycompany.tennis.basededonnee.repository.MatchRepository;
import com.mycompany.tennis.basededonnee.repository.ScoreRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {

    private ScoreRepository scoreRepository;
    private MatchRepository matchRepository;

    private EpreuveRepository epreuveRepository;

    private JoueurRepository joueurRepository;

    public MatchService(){
        this.matchRepository=new MatchRepository();
        this.scoreRepository=new ScoreRepository();
        this.epreuveRepository = new EpreuveRepository();
        this.joueurRepository = new JoueurRepository();
    }



public void createMatch(MatchDto dto){
    Session session=null;
    Match match = null;
    Transaction tx = null;


    try {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        tx = session.beginTransaction();

        match = new Match();
        match.setEpreuve(epreuveRepository.getById(dto.getEpreuveFullDto().getId()));
        match.setVainqueur(joueurRepository.getById(dto.getVainqueur().getId()));
        match.setVainqueur(joueurRepository.getById(dto.getFinaliste().getId()));

        Score score = new Score();
        score.setMatch(match);
        match.setScore(score);
        score.setSet1(dto.getScoreFullDto().getSet1());
        score.setSet2(dto.getScoreFullDto().getSet2());
        score.setSet3(dto.getScoreFullDto().getSet3());
        score.setSet4(dto.getScoreFullDto().getSet4());
        score.setSet5(dto.getScoreFullDto().getSet5());

matchRepository.create(match);

        tx.commit();

    } catch (Throwable tr){
        if (tx != null){
            tx.rollback();
        }
        tr.printStackTrace();
    } finally {
        if(session!=null){
            session.close();
        }

    }


}



    public void tapisVert(Long id){
        Session session=null;
        Match match = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            match = matchRepository.getById(id);

            Joueur ancienVainquer = match.getVainqueur();

            match.setVainqueur(match.getFinaliste());

            match.setFinaliste(ancienVainquer);


            match.getScore().setSet1((byte)0);
            match.getScore().setSet2((byte)0);
            match.getScore().setSet3((byte)0);
            match.getScore().setSet4((byte)0);
            match.getScore().setSet5((byte)0);

            tx.commit();

        } catch (Throwable tr){
            if (tx != null){
                tx.rollback();
            }
            tr.printStackTrace();
        } finally {
            if(session!=null){
                session.close();
            }

        }


    }

    public MatchDto getMatch(Long id){
        Session session=null;
        Match match = null;
        MatchDto matchDto=null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            match = matchRepository.getById(id);

            matchDto = new MatchDto();
            matchDto.setId(match.getId());

            JoueurDto joueurDtoV = new JoueurDto();
            joueurDtoV.setNom(match.getVainqueur().getNom());
            joueurDtoV.setPrenom(match.getVainqueur().getPrenom());
matchDto.setVainqueur(joueurDtoV);

            JoueurDto joueurDtoF = new JoueurDto();
            joueurDtoF.setPrenom(match.getFinaliste().getPrenom());
            joueurDtoF.setNom(match.getFinaliste().getNom());
matchDto.setFinaliste(joueurDtoF);

            EpreuveFullDto epreuveDto = new EpreuveFullDto();
            epreuveDto.setId(match.getEpreuve().getId());
            epreuveDto.setAnnee(match.getEpreuve().getAnnee());
            epreuveDto.setTypeEpreuve(match.getEpreuve().getTypeEpreuve());
            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(match.getEpreuve().getTournoi().getId());
            tournoiDto.setNom(match.getEpreuve().getTournoi().getNom());
            epreuveDto.setTournoi(tournoiDto);

            matchDto.setEpreuveFullDto(epreuveDto);


            ScoreFullDto scoreDto = new ScoreFullDto();
             scoreDto.setId(match.getScore().getId());
            scoreDto.setSet1(match.getScore().getSet1());
            scoreDto.setSet2(match.getScore().getSet2());
            scoreDto.setSet3(match.getScore().getSet3());
            scoreDto.setSet4(match.getScore().getSet4());
            scoreDto.setSet5(match.getScore().getSet5());

            matchDto.setScoreFullDto(scoreDto);

            scoreDto.setMatchDto(matchDto);

            tx.commit();

        } catch (Throwable tr){
            if (tx != null){
                tx.rollback();
            }
            tr.printStackTrace();
        } finally {
            if(session!=null){
                session.close();
            }


        }
                return matchDto;



}



}
