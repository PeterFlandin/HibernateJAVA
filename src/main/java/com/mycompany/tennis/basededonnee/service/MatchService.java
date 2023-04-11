package com.mycompany.tennis.basededonnee.service;

import com.mycompany.tennis.basededonnee.HibernateUtil;
import com.mycompany.tennis.basededonnee.dto.EpreuveFullDto;
import com.mycompany.tennis.basededonnee.dto.JoueurDto;
import com.mycompany.tennis.basededonnee.dto.MatchDto;
import com.mycompany.tennis.basededonnee.dto.TournoiDto;
import com.mycompany.tennis.basededonnee.entity.Epreuve;
import com.mycompany.tennis.basededonnee.entity.Match;
import com.mycompany.tennis.basededonnee.repository.MatchRepository;
import com.mycompany.tennis.basededonnee.repository.ScoreRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {

    private ScoreRepository scoreRepository;
    private MatchRepository matchRepository;

    public MatchService(){
        this.matchRepository=new MatchRepository();
        this.scoreRepository=new ScoreRepository();
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
