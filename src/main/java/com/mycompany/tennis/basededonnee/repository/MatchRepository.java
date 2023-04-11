package com.mycompany.tennis.basededonnee.repository;

import com.mycompany.tennis.basededonnee.DataSourceProvider;
import com.mycompany.tennis.basededonnee.HibernateUtil;
import com.mycompany.tennis.basededonnee.entity.Joueur;
import com.mycompany.tennis.basededonnee.entity.Match;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.*;

public class MatchRepository {

    public Match getById(Long id) {
            Match match  = null;
            Session session = null;

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            match = session.get(Match.class,id);
            System.out.println("Match lu");

        return match;

        }

    }

