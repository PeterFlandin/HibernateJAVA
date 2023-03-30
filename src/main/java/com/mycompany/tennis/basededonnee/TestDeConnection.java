package com.mycompany.tennis.basededonnee;

import com.mycompany.tennis.basededonnee.entity.Joueur;
import com.mycompany.tennis.basededonnee.repository.JoueurRepository;

import java.util.List;


public class TestDeConnection {
    public static void main(String... args){
        JoueurRepository joueurRepository = new JoueurRepository();
        Joueur bartoli = joueurRepository.getById(26L);
        System.out.println(bartoli.getPrenom()+" "+bartoli.getNom());


        JoueurRepository joueurRepository1 = new JoueurRepository();
        List<Joueur> liste = joueurRepository1.list();

        for (Joueur joueur : liste){
            System.out.println(joueur.getNom()+" "+joueur.getPrenom());
        }



    }

}
