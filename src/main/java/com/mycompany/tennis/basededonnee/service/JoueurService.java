package com.mycompany.tennis.basededonnee.service;

import com.mycompany.tennis.basededonnee.entity.Joueur;
import com.mycompany.tennis.basededonnee.repository.JoueurRepository;

public class JoueurService {

    private JoueurRepository joueurRepository1;
 //  private  JoueurRepository joueurRepository2;
    public JoueurService(){
        this.joueurRepository1=new JoueurRepository();
    }


  public void createJoueur(Joueur joueur){
      joueurRepository1.create(joueur);
   }




    public Joueur getJoueur(Long id){
     return joueurRepository1.getById(id);
    }

}
