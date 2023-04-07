package com.mycompany.tennis.basededonnee.entity;

import org.hibernate.annotations.Table;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity

public class Epreuve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

  private  Long id;
  private int annee;
@Transient
  private  Tournoi tournoi;
@Column(name = "TYPE_EPREUVE")
  private  Character typeEpreuve;

    public Character getTypeEpreuve() {
        return typeEpreuve;
    }

    public void setTypeEpreuve(Character typeEpreuve) {
        this.typeEpreuve = typeEpreuve;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }
}
