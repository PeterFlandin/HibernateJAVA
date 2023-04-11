package com.mycompany.tennis.basededonnee.service;

import com.mycompany.tennis.basededonnee.HibernateUtil;
import com.mycompany.tennis.basededonnee.dto.EpreuveFullDto;
import com.mycompany.tennis.basededonnee.dto.MatchDto;
import com.mycompany.tennis.basededonnee.dto.ScoreFullDto;
import com.mycompany.tennis.basededonnee.dto.TournoiDto;
import com.mycompany.tennis.basededonnee.entity.Joueur;
import com.mycompany.tennis.basededonnee.entity.Score;
import com.mycompany.tennis.basededonnee.repository.ScoreRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreService {

    private ScoreRepository scoreRepository;


    public ScoreService(){
        this.scoreRepository = new  ScoreRepository();
    }

    public ScoreFullDto getScore(Long id){
        Session session=null;
        Transaction tx = null;
        Score score = null;
        ScoreFullDto scoreFullDto=null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            score =  scoreRepository.getById(id);

            scoreFullDto = new ScoreFullDto();
            scoreFullDto.setId(score.getId());
            scoreFullDto.setSet1(scoreFullDto.getSet1());
            scoreFullDto.setSet2(scoreFullDto.getSet2());
            scoreFullDto.setSet3(scoreFullDto.getSet3());
            scoreFullDto.setSet4(scoreFullDto.getSet4());
            scoreFullDto.setSet5(scoreFullDto.getSet5());

            MatchDto matchDto = new MatchDto();
            matchDto.setId(score.getMatch().getId());

            scoreFullDto.setMatchDto(matchDto);


            EpreuveFullDto epreuveDto = new EpreuveFullDto();
            epreuveDto.setId(score.getMatch().getEpreuve().getId());
            epreuveDto.setAnnee(score.getMatch().getEpreuve().getAnnee());
            epreuveDto.setTypeEpreuve(score.getMatch().getEpreuve().getTypeEpreuve());
            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(score.getMatch().getEpreuve().getTournoi().getId());
            tournoiDto.setNom(score.getMatch().getEpreuve().getTournoi().getNom());
            epreuveDto.setTournoi(tournoiDto);

            matchDto.setEpreuveFullDto(epreuveDto);




            tx.commit();
        } catch (Throwable e) {
            if(tx == null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (session!=null){
                session.close();
            }
        }
        return scoreFullDto;
    }

}
