package com.mycompany.tennis.basededonnee.entity;

import org.hibernate.annotations.Table;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Entity

public class Epreuve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
@Type(type = "long")
  private Long id;
    @Type(type = "short")
  private Short annee;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "id_tournoi")
  private  Tournoi tournoi;
@Column(name = "TYPE_EPREUVE")
  private  Character typeEpreuve;

@ManyToMany
@JoinTable(name = "participants",
        joinColumns = {@JoinColumn (name = "id_epreuve")},
        inverseJoinColumns = {@JoinColumn (name = "id_joueur")})
private Set<Joueur> participants;

    public Set<Joueur> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Joueur> participant) {
        this.participants = participant;
    }

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

    public Short getAnnee() {
        return annee;
    }

    public void setAnnee(Short annee) {
        this.annee = annee;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }
}
