package com.mycompany.tennis.basededonnee.dto;

import com.mycompany.tennis.basededonnee.entity.Joueur;

import javax.persistence.*;

public class MatchDto {

    private Long id;
    private JoueurDto vainqueur;

    private JoueurDto finaliste;

    private EpreuveFullDto epreuveFullDto;

    public EpreuveFullDto getEpreuveFullDto() {
        return epreuveFullDto;
    }

    public void setEpreuveFullDto(EpreuveFullDto epreuveFullDto) {
        this.epreuveFullDto = epreuveFullDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JoueurDto getVainqueur() {
        return vainqueur;
    }

    public void setVainqueur(JoueurDto vainqueur) {
        this.vainqueur = vainqueur;
    }

    public JoueurDto getFinaliste() {
        return finaliste;
    }

    public void setFinaliste(JoueurDto finaliste) {
        this.finaliste = finaliste;
    }
}
