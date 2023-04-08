package com.mycompany.tennis.basededonnee.repository;

import com.mycompany.tennis.basededonnee.DataSourceProvider;
import com.mycompany.tennis.basededonnee.HibernateUtil;
import com.mycompany.tennis.basededonnee.entity.Epreuve;
import com.mycompany.tennis.basededonnee.entity.Tournoi;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EpreuveRepository {

    public Epreuve getById(Long id) {
        Session session = null;
        Epreuve epreuve = null;

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        epreuve = session.get(Epreuve.class, id);
        System.out.println("Epreuve lu");
        return epreuve;
    }


}