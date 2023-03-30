package com.mycompany.tennis.basededonnee;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {
    private static BasicDataSource singledataSource;


    public static DataSource getSingleDataSourceInstance(){
        if(singledataSource==null){
            singledataSource = new BasicDataSource();

            singledataSource.setInitialSize(5);

            singledataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");

            singledataSource.setUsername("root");
            singledataSource.setPassword("246810");

        }
        return singledataSource;
    }
}
