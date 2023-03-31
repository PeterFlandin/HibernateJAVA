package com.mycompany.tennis.basededonnee.service;

import com.mycompany.tennis.basededonnee.entity.Tournoi;
import com.mycompany.tennis.basededonnee.repository.TournoiRepository;

public class TournoiService {

private TournoiRepository tournoiRepository;

public TournoiService(){
    this.tournoiRepository=new TournoiRepository();
}

public void createTournoi(Tournoi tournoi){
    tournoiRepository.create(tournoi);
}

public Tournoi getTournoi (Long id){
   return tournoiRepository.getById(id);
}


}
